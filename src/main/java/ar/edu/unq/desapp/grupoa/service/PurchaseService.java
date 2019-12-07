package ar.edu.unq.desapp.grupoa.service;

import ar.edu.unq.desapp.grupoa.model.CurrentOrder;
import ar.edu.unq.desapp.grupoa.model.Menu;
import ar.edu.unq.desapp.grupoa.model.Order;
import ar.edu.unq.desapp.grupoa.model.Provider;
import ar.edu.unq.desapp.grupoa.model.User;
import ar.edu.unq.desapp.grupoa.persistence.PurchaseRepository;
import ar.edu.unq.desapp.grupoa.service.dto.MenuDTO;
import ar.edu.unq.desapp.grupoa.service.dto.OrderDTO;
import ar.edu.unq.desapp.grupoa.service.dto.PurchaseDTO;
import ar.edu.unq.desapp.grupoa.service.exceptions.InssuficientStockException;
import ar.edu.unq.desapp.grupoa.service.exceptions.NotBusinessDayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class PurchaseService {

    private PurchaseRepository purchaseRepository;

    private ProviderService providerService;

    private UserService userService;

    private ConverterHelper converterHelper;

    private BusinessDayCheckerService businessDayCheckerService;

    @Autowired
    public PurchaseService(final ProviderService providerService, final UserService userService,
                           final ConverterHelper converterHelper, final BusinessDayCheckerService businessDayCheckerService) {
        this.providerService = providerService;
        this.converterHelper = converterHelper;
        this.businessDayCheckerService = businessDayCheckerService;
        this.userService = userService;
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
        if(menu.getDailyStock() < (totalQuantity + quantity))
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
}
