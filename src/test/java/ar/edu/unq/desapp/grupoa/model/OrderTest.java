package ar.edu.unq.desapp.grupoa.model;

import ar.edu.unq.desapp.grupoa.model.enums.DeliveryType;
import ar.edu.unq.desapp.grupoa.model.enums.Status;
import ar.edu.unq.desapp.grupoa.model.exceptions.OrderDateException;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class OrderTest {

    private Order order;

    private Order anyOrder() {
        LocalDateTime orderDay = LocalDateTime.of(2019, 11, 7, 12, 00);
        LocalDateTime deliveryDay = LocalDateTime.of(2019, 11, 8, 11, 30);
        Menu mockMenu = mock(Menu.class);
        return new Order(mockMenu, "ViandaLiz", deliveryDay, orderDay, 50, DeliveryType.HOME_DELIVERY, Status.IN_PROGRESS);
    }

    @Test
    public void testCalculateValueFor50MenuWith120PriceWithDeliveryThenReturn6030() {
        this.order = this.anyOrder();
        Mockito.when(order.getMenu().valueForQuantity(50)).thenReturn(120);
        Mockito.when(order.getMenu().getDeliveryPrice()).thenReturn(30);
        assertEquals(new Integer(6030), order.calculateValue(50));
    }

    @Test
    public void testCalculateValueFor50MenuWith120PriceWithoutDeliveryThenReturn6000() {
        this.order = this.anyOrder();
        this.order.setTypeDelivery(DeliveryType.BRANCH_OFFICE);
        Mockito.when(order.getMenu().valueForQuantity(50)).thenReturn(120);
        assertEquals(new Integer(6000), order.calculateValue(50));
    }

    @Test
    public void testCalculateValueFor50MenuWith100PriceOfferWithDeliveryThenReturn5030() {
        this.order = this.anyOrder();
        Mockito.when(order.getMenu().valueForQuantity(50)).thenReturn(100);
        Mockito.when(order.getMenu().getDeliveryPrice()).thenReturn(30);
        assertEquals(new Integer(5030), order.calculateValue(50));
    }

    @Test
    public void testDeliveryPriceWithoutHomeDeliveryThenReturn0() {
        this.order = this.anyOrder();
        this.order.setTypeDelivery(DeliveryType.BRANCH_OFFICE);
        assertEquals(new Integer(0), order.deliveryPrice());
    }

    @Test
    public void testDeliveryPriceWithHomeDeliveryThenReturn35() {
        this.order = this.anyOrder();
        Mockito.when(order.getMenu().getDeliveryPrice()).thenReturn(35);
        assertEquals(new Integer(35), order.deliveryPrice());
    }

    @Test
    public void testSetTypeDeliveryBranchOfficeSuccessfully() {
        this.order = this.anyOrder();
        order.setTypeDelivery(DeliveryType.BRANCH_OFFICE);
        assertEquals(DeliveryType.BRANCH_OFFICE, order.getTypeDelivery());
    }

    @Test
    public void testSetTypeDeliveryHomeDeliverySuccessfully() {
        this.order = this.anyOrder();
        order.setTypeDelivery(DeliveryType.HOME_DELIVERY);
        assertEquals(DeliveryType.HOME_DELIVERY, order.getTypeDelivery());
    }

    @Test(expected = NullPointerException.class)
    public void testSetTypeDeliveryWithNullThenReturnThrowException() {
        this.order = this.anyOrder();
        order.setTypeDelivery(null);
    }

    @Test
    public void testSetRanking4Successfully() {
        this.order = this.anyOrder();
        order.setRanking(4);
        assertEquals(new Integer(4), order.getRanking());
    }

    @Test(expected = NullPointerException.class)
    public void testSetRankingWithNullThenReturnThrowException() {
        this.order = this.anyOrder();
        order.setRanking(null);
    }

    @Test(expected = OrderDateException.class)
    public void orderWithOrderDayAfter48hsDeliveryDayThrowException() {
        LocalDateTime orderDay = LocalDateTime.of(2019, 8, 30, 12, 00);
        LocalDateTime deliveryDay = LocalDateTime.of(2019, 9, 2, 11, 30);
        new Order(mock(Menu.class), "ViandaLiz", deliveryDay, orderDay, 50, DeliveryType.HOME_DELIVERY, Status.IN_PROGRESS);
    }

    @Test(expected = OrderDateException.class)
    public void menuWithOrderDayAfter48hsDeliveryDay2ThrowException() {
        LocalDateTime orderDay = LocalDateTime.of(2019, 10, 14, 12, 00);
        LocalDateTime deliveryDay = LocalDateTime.of(2019, 10, 17, 11, 30);
        new Order(mock(Menu.class), "ViandaLiz", deliveryDay, orderDay, 50, DeliveryType.HOME_DELIVERY, Status.IN_PROGRESS);
    }
}
