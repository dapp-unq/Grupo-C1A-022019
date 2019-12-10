package ar.edu.unq.desapp.grupoa.service;

import ar.edu.unq.desapp.grupoa.model.CurrentOrder;
import ar.edu.unq.desapp.grupoa.model.Menu;
import ar.edu.unq.desapp.grupoa.model.Order;
import ar.edu.unq.desapp.grupoa.model.Provider;
import ar.edu.unq.desapp.grupoa.model.User;
import ar.edu.unq.desapp.grupoa.model.enums.Status;
import ar.edu.unq.desapp.grupoa.model.exceptions.InsufficientCurrencyException;
import ar.edu.unq.desapp.grupoa.persistence.PurchaseRepository;
import ar.edu.unq.desapp.grupoa.service.dto.MenuDTO;
import ar.edu.unq.desapp.grupoa.service.dto.OrderDTO;
import ar.edu.unq.desapp.grupoa.service.dto.PurchaseDTO;
import ar.edu.unq.desapp.grupoa.service.exceptions.InexistentCurrentOrderException;
import ar.edu.unq.desapp.grupoa.service.exceptions.InssuficientStockException;
import ar.edu.unq.desapp.grupoa.service.exceptions.NotBusinessDayException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableScheduling
@Slf4j
public class PurchaseService {

    private PurchaseRepository purchaseRepository;

    private ProviderService providerService;

    private UserService userService;

    private ConverterHelper converterHelper;

    private BusinessDayCheckerService businessDayCheckerService;

    private EmailService emailService;

    @Autowired
    public PurchaseService(final ProviderService providerService, final UserService userService,
                           final ConverterHelper converterHelper, final BusinessDayCheckerService businessDayCheckerService,
                           final EmailService emailService, final PurchaseRepository purchaseRepository) {
        this.providerService = providerService;
        this.converterHelper = converterHelper;
        this.businessDayCheckerService = businessDayCheckerService;
        this.userService = userService;
        this.emailService = emailService;
        this.purchaseRepository = purchaseRepository;
    }

    public void createPurchase(PurchaseDTO purchaseDTO) {
        final OrderDTO orderDTO = purchaseDTO.getOrder();
        final Provider provider = providerService.findProvider(orderDTO.getMenu().getProviderEmail());
        final Menu menu = provider.searchMenu(orderDTO.getMenu().getName());
        final MenuDTO menuDto = converterHelper.menuToMenuDTO(menu);
        menuDto.setProviderEmail(provider.getEmail());
        orderDTO.setMenu(menuDto);
        final User user = userService.findUser(purchaseDTO.getUserEmail());

        final Order newOrder = convertDtoToOrder(orderDTO);
        newOrder.setMenu(menu);

        validateStockOfMenu(menu, provider, orderDTO.getQuantity());

        provider.addOrder(user, newOrder);

        providerService.updateProviderMenus(provider);
        userService.updateUserOrders(user);
    }

    private void validateStockOfMenu(Menu menu, Provider provider, Integer quantity) {
        int totalQuantity = provider.getOrders().stream()
                .map(CurrentOrder::getOrders)
                .flatMap(Collection::stream)
                .filter(order -> order.getMenu().equals(menu))
                .mapToInt(Order::getQuantity).sum();
        if (menu.getDailyStock() < (totalQuantity + quantity))
            throw new InssuficientStockException("Stock diario superado para este menu. Sólo puede comprar " +
                    (menu.getDailyStock() - totalQuantity));
    }

    private Order convertDtoToOrder(OrderDTO orderDTO) {
        final LocalDateTime orderDateAndHour = orderDTO.getOrderDateAndHour();
        final LocalDateTime deliveryDateAndHour = orderDTO.getDeliveryDateAndHour();
        checkForBusinessDays(orderDateAndHour.toLocalDate(), deliveryDateAndHour.toLocalDate());
        return converterHelper.orderDtoToOrder(orderDTO);
    }

    private void checkForBusinessDays(LocalDate orderDate, LocalDate deliveryDate) {
        if (businessDayCheckerService.isBusinessDay(orderDate)) {
            if (!businessDayCheckerService.isBusinessDay(deliveryDate))
                throw new NotBusinessDayException("La fecha de delivery es un día feriado");
        } else
            throw new NotBusinessDayException("La fecha de orden es un día feriado");
    }

    @Scheduled(cron = "0 0 0 ? * * ")
    public void processOrders() throws InexistentCurrentOrderException {
        List<Order> ordersInProgress = purchaseRepository.findByStatus(Status.IN_PROGRESS);

        List<Order> ordersFromToday = getTodayOrders(ordersInProgress);

        while (!ordersFromToday.isEmpty()) {
            Order order = ordersFromToday.get(0);
            Menu menu = order.getMenu();
            String providerMail = order.getProviderEmail();
            Provider provider = providerService.findProvider(providerMail);

            List<Order> sameOrdersForToday = ordersFromToday.stream()
                    .filter(o -> o.getMenu().equals(menu) && o.getProviderEmail().equals(providerMail))
                    .collect(Collectors.toList());

            Integer totalQuantity = getTotalQuantity(sameOrdersForToday);

            Integer price = menu.valueForQuantity(totalQuantity);

            sameOrdersForToday.forEach(todayOrder -> {
                CurrentOrder currentOrder = provider.getOrders().stream()
                        .filter(co -> co.getOrders().contains(todayOrder))
                        .findFirst().orElseThrow(() -> new InexistentCurrentOrderException("No se puede procesar la orden"));

                User user = currentOrder.getClient();

                String userEmail = user.getEmail();

                List<Order> userOrder = currentOrder.getOrders().stream()
                        .filter(o -> o.getMenu().equals(menu))
                        .collect(Collectors.toList());

                userOrder.forEach(o -> {
                    BigDecimal finalPrice = BigDecimal.valueOf(o.getQuantity() * price);
                    try {
                        user.discountMoney(finalPrice);
                    } catch (InsufficientCurrencyException e) {
                        log.info(e.getMessage() + " para el usuario " + userEmail + "para el menu " + menu.getName());
                    }
                    provider.addMoney(finalPrice);
                    o.setStatus(Status.COMPLETED);
                    emailService.sendSimpleMessage(userEmail, "[ViandasYa] Compra procesada", "La orden del menu: + " + menu.getName() +
                            " del proveedor " + provider.getName() + " ha sido procesada exitosamente. Precio final: " + finalPrice);
                    emailService.sendSimpleMessage(providerMail, "[ViandasYa] Venta procesada", "La orden del menu: + " + menu.getName() +
                            " del usuario " + userEmail + " ha sido procesada exitosamente. Precio final: " + finalPrice);
                    providerService.updateProviderOrders(provider);
                    userService.updateUserOrders(user);
                });

                ordersFromToday.removeAll(sameOrdersForToday);

            });


        }
    }

    private List<Order> getTodayOrders(List<Order> ordersInProgress) {
        return ordersInProgress.stream()
                .filter(this::orderHasTodayDate)
                .collect(Collectors.toList());
    }

    private int getTotalQuantity(List<Order> sameOrdersForToday) {
        return sameOrdersForToday.stream()
                .mapToInt(Order::getQuantity)
                .sum();
    }

    private boolean orderHasTodayDate(Order order) {
        return order.getOrderDateAndHour().toLocalDate().equals(LocalDate.now());
    }

}
