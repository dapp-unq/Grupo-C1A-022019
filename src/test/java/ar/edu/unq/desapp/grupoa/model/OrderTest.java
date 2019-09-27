package ar.edu.unq.desapp.grupoa.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.GregorianCalendar;

import org.junit.Test;
import org.mockito.Mockito;

public class OrderTest {

	private Order order;

	private Order anyOrder() {
		GregorianCalendar orderDay = new GregorianCalendar(2019, 11, 2, 12, 00);
		GregorianCalendar deliveryDay = new GregorianCalendar(2019, 11, 8, 11, 30);
		Menu mockMenu = mock(Menu.class);
		return new Order(mockMenu, deliveryDay, orderDay, 50, DeliveryType.Home_delivery, Status.In_Progress);
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
		this.order.setTypeDelivery(DeliveryType.Branch_office);
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
		this.order.setTypeDelivery(DeliveryType.Branch_office);
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
		order.setTypeDelivery(DeliveryType.Branch_office);
		assertEquals(DeliveryType.Branch_office, order.getTypeDelivery());
	}

	@Test
	public void testSetTypeDeliveryHomeDeliverySuccessfully() {
		this.order = this.anyOrder();
		order.setTypeDelivery(DeliveryType.Home_delivery);
		assertEquals(DeliveryType.Home_delivery, order.getTypeDelivery());
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
}
