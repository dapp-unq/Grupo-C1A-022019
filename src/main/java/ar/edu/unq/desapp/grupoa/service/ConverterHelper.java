package ar.edu.unq.desapp.grupoa.service;

import ar.edu.unq.desapp.grupoa.model.CurrentOrder;
import ar.edu.unq.desapp.grupoa.model.EffectivePeriod;
import ar.edu.unq.desapp.grupoa.model.Menu;
import ar.edu.unq.desapp.grupoa.model.Offer;
import ar.edu.unq.desapp.grupoa.model.Order;
import ar.edu.unq.desapp.grupoa.model.Provider;
import ar.edu.unq.desapp.grupoa.model.ServiceHours;
import ar.edu.unq.desapp.grupoa.model.User;
import ar.edu.unq.desapp.grupoa.service.dto.CurrentOrderDTO;
import ar.edu.unq.desapp.grupoa.service.dto.EffectivePeriodDTO;
import ar.edu.unq.desapp.grupoa.service.dto.MenuDTO;
import ar.edu.unq.desapp.grupoa.service.dto.OfferDTO;
import ar.edu.unq.desapp.grupoa.service.dto.OrderDTO;
import ar.edu.unq.desapp.grupoa.service.dto.ProviderDTO;
import ar.edu.unq.desapp.grupoa.service.dto.ServiceHoursDTO;
import ar.edu.unq.desapp.grupoa.service.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConverterHelper {

    public User userDtoToUser(final UserDTO userDTO) {
        return new User(userDTO.getName(), userDTO.getSurname(), userDTO.getEmail(), userDTO.getPhoneNumber(), userDTO.getLocation());
    }

    public UserDTO userToUserDTO(final User user) {
        return new UserDTO(user.getName(), user.getSurname(), user.getEmail(), user.getPhoneNumber(), user.getLocation(), orderListToOrderDTOList(user.getOrderHistory()), user.getBalance());
    }

    public ProviderDTO providerToProviderDTO(final Provider provider) {
        ProviderDTO providerDTO = new ProviderDTO();
        providerDTO.setName(provider.getName());
        providerDTO.setLogo(provider.getLogo());
        providerDTO.setCity(provider.getCity());
        providerDTO.setLocation(provider.getLocation());
        providerDTO.setDescription(provider.getDescription());
        providerDTO.setWebsite(provider.getWebsite());
        providerDTO.setEmail(provider.getEmail());
        providerDTO.setPhoneNumber(provider.getPhoneNumber());
        providerDTO.setOpeningHoursDays(serviceHoursListToServiceHoursDTOList(provider.getOpeningHoursDays()));
        providerDTO.setDeliveryCities(provider.getDeliveryCities());
        providerDTO.setCurrentMenus(menuListToMenuDTOList(provider.getCurrentMenus()));
        providerDTO.setOrders(currentOrderListToCurrentOrderDTOList(provider.getOrders()));
        providerDTO.setBalance(provider.getBalance());
        providerDTO.setMenusRemoved(provider.getMenusRemoved());
        return providerDTO;
    }

    public Provider providerDtoToProvider(final ProviderDTO providerDTO) {
        Provider provider = new Provider(providerDTO.getName(), providerDTO.getLogo(), providerDTO.getCity(), providerDTO.getLocation(),
                providerDTO.getDescription(), providerDTO.getWebsite(), providerDTO.getEmail(), providerDTO.getPhoneNumber(),
                serviceHoursDtoListToServiceHoursList(providerDTO.getOpeningHoursDays()), 0L);
        return provider;
    }

    public List<CurrentOrder> currentOrderDtoListToCurrentOrderList(final List<CurrentOrderDTO> currentOrderDTOList) {
        return currentOrderDTOList.stream().map(this::currentOrderDtoToCurrentOrder).collect(Collectors.toList());
    }

    private CurrentOrder currentOrderDtoToCurrentOrder(final CurrentOrderDTO currentOrderDTO) {
        return new CurrentOrder(userDtoToUser(currentOrderDTO.getUser()), orderDtoListToOrderList(currentOrderDTO.getOrders()));
    }

    public List<Menu> menuDtoListToMenuList(final List<MenuDTO> menuDTOList) {
        return menuDTOList.stream().map(this::menuDtoToMenu).collect(Collectors.toList());
    }

    public Menu menuDtoToMenu(final MenuDTO menuDTO) {
        return new Menu(menuDTO.getName(), menuDTO.getDescription(), menuDTO.getCategory(), menuDTO.getDeliveryPrice(),
                effectivePeriodDtoToEffectivePeriod(menuDTO.getEffectivePeriod()), menuDTO.getDeliverySchedules(),
                menuDTO.getAverageDeliveryTime(), menuDTO.getPrice(), menuDTO.getDailyStock(), offerDtoToOffer(menuDTO.getOffer1()),
                offerDtoToOffer(menuDTO.getOffer2()));
    }

    public Offer offerDtoToOffer(final OfferDTO offer) {
        return new Offer(offer.getQuantity(), offer.getPrice());
    }

    public EffectivePeriod effectivePeriodDtoToEffectivePeriod(final EffectivePeriodDTO effectivePeriod) {
        return new EffectivePeriod(effectivePeriod.getInitialDate(), effectivePeriod.getEndDate());
    }

    public List<ServiceHours> serviceHoursDtoListToServiceHoursList(final List<ServiceHoursDTO> serviceHoursDTOList) {
        return serviceHoursDTOList.stream().map(this::serviceHoursDtoToServiceHours).collect(Collectors.toList());
    }

    private ServiceHours serviceHoursDtoToServiceHours(final ServiceHoursDTO serviceHoursDTO) {
        return new ServiceHours(serviceHoursDTO.getDay(), serviceHoursDTO.getOpeningHours(), serviceHoursDTO.getClosingHours());
    }

    private List<CurrentOrderDTO> currentOrderListToCurrentOrderDTOList(final List<CurrentOrder> orders) {
        return orders.stream().map(this::currentOrderToCurrentOrderDTO).collect(Collectors.toList());
    }

    private CurrentOrderDTO currentOrderToCurrentOrderDTO(final CurrentOrder currentOrder) {
        CurrentOrderDTO currentOrderDTO = new CurrentOrderDTO();
        currentOrderDTO.setOrders(orderListToOrderDTOList(currentOrder.getOrders()));
        currentOrderDTO.setUser(userToUserDTO(currentOrder.getClient()));
        return currentOrderDTO;
    }

    private List<OrderDTO> orderListToOrderDTOList(final List<Order> orders) {
        return orders.stream().map(this::orderToOrderDTO).collect(Collectors.toList());
    }

    private OrderDTO orderToOrderDTO(final Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setProviderName(order.getProviderName());
        orderDTO.setDeliveryDateAndHour(order.getDeliveryDateAndHour());
        orderDTO.setOrderDateAndHour(order.getOrderDateAndHour());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setTypeDelivery(order.getTypeDelivery());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setRanking(order.getRanking());
        orderDTO.setValue(order.getValue());
        return orderDTO;
    }

    public List<Order> orderDtoListToOrderList(final List<OrderDTO> orderDTOList) {
        return orderDTOList.stream().map(this::orderDtoToOrder).collect(Collectors.toList());
    }

    private Order orderDtoToOrder(OrderDTO orderDTO) {
        return new Order(menuDtoToMenu(orderDTO.getMenu()), orderDTO.getProviderName(), orderDTO.getDeliveryDateAndHour(),
                orderDTO.getOrderDateAndHour(), orderDTO.getQuantity(), orderDTO.getTypeDelivery(), orderDTO.getStatus());
    }

    private List<ServiceHoursDTO> serviceHoursListToServiceHoursDTOList(final List<ServiceHours> serviceHours) {
        return serviceHours.stream().map(this::serviceHoursToServiceHoursDTO).collect(Collectors.toList());
    }

    private ServiceHoursDTO serviceHoursToServiceHoursDTO(final ServiceHours serviceHours) {
        ServiceHoursDTO serviceHoursDTO = new ServiceHoursDTO();
        serviceHoursDTO.setOpeningHours(serviceHours.getOpeningHours());
        serviceHoursDTO.setClosingHours(serviceHours.getClosingHours());
        serviceHoursDTO.setDay(serviceHours.getDay());
        return serviceHoursDTO;
    }

    private List<MenuDTO> menuListToMenuDTOList(final List<Menu> menuList) {
        return menuList.stream().map(this::menuToMenuDTO).collect(Collectors.toList());
    }

    private MenuDTO menuToMenuDTO(final Menu menu) {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setName(menu.getName());
        menuDTO.setDescription(menu.getDescription());
        menuDTO.setCategory(menu.getCategory());
        menuDTO.setDeliveryPrice(menu.getDeliveryPrice());
        menuDTO.setEffectivePeriod(effectivePeriodToEffectivePeriodDTO(menu.getEffectivePeriod()));
        menuDTO.setDeliverySchedules(menu.getDeliverySchedules());
        menuDTO.setAverageDeliveryTime(menu.getAverageDeliveryTime());
        menuDTO.setPrice(menu.getPrice());
        menuDTO.setDailyStock(menu.getDailyStock());
        menuDTO.setOffer1(offerToOfferDTO(menu.getOffer1()));
        menuDTO.setOffer2(offerToOfferDTO(menu.getOffer2()));
        menuDTO.setRanking(menu.getRanking());
        return menuDTO;
    }

    private OfferDTO offerToOfferDTO(final Offer offer) {
        OfferDTO offerDTO = new OfferDTO();
        offerDTO.setPrice(offer.getPrice());
        offerDTO.setQuantity(offer.getQuantity());
        return offerDTO;
    }

    private EffectivePeriodDTO effectivePeriodToEffectivePeriodDTO(final EffectivePeriod effectivePeriod) {
        EffectivePeriodDTO effectivePeriodDTO = new EffectivePeriodDTO();
        effectivePeriodDTO.setEndDate(effectivePeriod.getEndDate());
        effectivePeriodDTO.setInitialDate(effectivePeriod.getInitialDate());
        return effectivePeriodDTO;
    }
}
