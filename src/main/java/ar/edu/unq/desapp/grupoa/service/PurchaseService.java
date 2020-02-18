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
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static ar.edu.unq.desapp.grupoa.model.enums.Status.COMPLETED;
import static ar.edu.unq.desapp.grupoa.model.enums.Status.CREATED;
import static ar.edu.unq.desapp.grupoa.model.enums.Status.IN_PROGRESS;
import static java.math.BigDecimal.valueOf;

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

    @Transactional
    public void createPurchase(PurchaseDTO purchaseDTO) throws InsufficientCurrencyException {
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

        BigDecimal orderValue = valueOf(newOrder.getValue());

        user.discountMoney(orderValue);
        provider.addMoney(orderValue);

        userService.updateUserOrders(user);
        purchaseRepository.save(newOrder);
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
    @Transactional
    public void processOrders() throws InexistentCurrentOrderException {
        processCreatedForToday();
        processInProgressOfYesterday();
    }

    private void processInProgressOfYesterday() {
        List<Order> ordersInProgress = getOrdersByStatus(IN_PROGRESS);
        List<Order> ordersYesterday = getYesterdayOrders(ordersInProgress);

        ordersYesterday.forEach(order -> order.setStatus(COMPLETED));

        purchaseRepository.saveAll(ordersYesterday);
    }

    private void processCreatedForToday() {
        HashMap<Menu, BigDecimal> map = new HashMap<>();
        List<Order> ordersCreated = getOrdersByStatus(CREATED);
        List<Order> ordersFromToday = getTodayOrders(ordersCreated);

        ordersFromToday.forEach(order -> {
            Menu menu = order.getMenu();
            String providerMail = order.getProviderEmail();
            Provider provider = providerService.findProvider(providerMail);
            CurrentOrder actualCurrentOrder = provider.getOrders().stream().filter(currentOrder -> currentOrder.getOrders().contains(order)).findFirst().orElse(null);

            User user = actualCurrentOrder.getClient();
            order.setStatus(IN_PROGRESS);
            if(map.containsKey(menu)){
                BigDecimal finalPrice = map.get(menu);
                processOrder(order, menu, provider, user, finalPrice);
            } else {
                List<Order> currentDayOrdersWithSameMenu = purchaseRepository.findAllByMenuIdAndStatus(menu.getId(), CREATED).stream().filter(actualOrder -> LocalDate.now().equals(actualOrder.getOrderDateAndHour().toLocalDate())).collect(Collectors.toList());
                int totalPurchasesOfSameMenu = currentDayOrdersWithSameMenu.stream().mapToInt(Order::getQuantity).sum();
                BigDecimal finalPrice = BigDecimal.valueOf(menu.valueForQuantity(totalPurchasesOfSameMenu));
                map.put(menu, finalPrice);
                processOrder(order, menu, provider, user, finalPrice);
            }
        });
    }

    private void processOrder(Order order, Menu menu, Provider provider, User user, BigDecimal finalPrice) {
        BigDecimal priceOffset = valueOf(menu.getPrice()).subtract(finalPrice);
        provider.addMoney(priceOffset.multiply(valueOf(order.getQuantity())).negate());
        user.addMoney(priceOffset.multiply(valueOf(order.getQuantity())));
        purchaseRepository.save(order);
        /*userService.updateUserOrders(user);
        providerService.updateProviderOrders(provider);*/
        sendNotifications(menu, provider, /*user.getEmail()*/ "beniteznahueloscar@gmail.com", valueOf(order.getValue()).subtract(priceOffset.multiply(valueOf(order.getQuantity()))), priceOffset.multiply(valueOf(order.getQuantity())));
    }

    private void sendNotifications(final Menu menu, final Provider provider, final String userEmail, final BigDecimal finalPrice, final BigDecimal offset) {
        emailService.sendSimpleMessage(userEmail, "[ViandasYa] Compra procesada", "La orden del menu: " + menu.getName() +
                " del proveedor " + provider.getName() + " ha sido procesada exitosamente. Precio final: " + finalPrice + ". Se reintegraron: $" + offset);
        emailService.sendSimpleMessage("beniteznahueloscar@gmail.com", "[ViandasYa] Venta procesada", "La orden del menu: " + menu.getName() +
                " del usuario " + userEmail + " ha sido procesada exitosamente. Precio final: " + finalPrice + ". Se descontó: $" + offset);
    }

    private List<Order> getOrdersByStatus(Status status) {
        return purchaseRepository.findAllByStatus(status);
    }

    private List<Order> getTodayOrders(final List<Order> ordersCreated) {
        return ordersCreated.stream()
                .filter(this::orderHasTodayDate)
                .collect(Collectors.toList());
    }

    private List<Order> getYesterdayOrders(final List<Order> ordersInProgress) {
        return ordersInProgress.stream()
                .filter(this::orderHasYesterdayDate)
                .collect(Collectors.toList());
    }

    private boolean orderHasTodayDate(Order order) {
        return order.getOrderDateAndHour().toLocalDate().equals(LocalDate.now());
    }

    private boolean orderHasYesterdayDate(Order order) {
        return order.getDeliveryDateAndHour().toLocalDate().equals(LocalDate.now().minusDays(1));
    }

}
