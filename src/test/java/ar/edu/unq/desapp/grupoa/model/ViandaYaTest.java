package ar.edu.unq.desapp.grupoa.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.GregorianCalendar;
import org.junit.Test;
import org.mockito.Mockito;

import ar.edu.unq.desapp.grupoa.model.exceptions.IrrationalAmountException;
import ar.edu.unq.desapp.grupoa.model.exceptions.OrderDateException;

public class ViandaYaTest {

	private ViandasYa viandasYa;
	
    @Test
    public void testPurchaseMenuCorrectly(){
    	viandasYa = new ViandasYa();  	
        GregorianCalendar orderDay = new GregorianCalendar(2019, 11, 2, 12, 00);
		GregorianCalendar deliveryDay = new GregorianCalendar(2019, 11, 8, 11, 30);
		User mockUser = mock(User.class);
		Menu mockMenu = mock(Menu.class);
		Provider mockProvider = mock(Provider.class);
		Mockito.when(mockMenu.valueForQuantity(50)).thenReturn(150);
		Mockito.when(mockMenu.getDeliveryPrice()).thenReturn(30);
		
		Order newOrder = viandasYa.purchase(mockUser, mockProvider, mockMenu, 50, DeliveryType.Home_delivery, deliveryDay, orderDay);
		Mockito.verify(mockMenu).validationNumberMenuOrdered(50);
		Mockito.verify(mockMenu).validationDateDeliveryMenuOrdered(orderDay , deliveryDay);
		Mockito.verify(mockUser).addHistoryOrder(newOrder);
		Mockito.verify(mockProvider).addOrder(mockUser, newOrder);
		
		assertEquals(mockMenu, newOrder.getMenu());
		assertEquals(deliveryDay, newOrder.getDateHoursDelivery());
		assertEquals(orderDay, newOrder.getDateHoursOrder());
		assertEquals(new Integer(50), newOrder.getQuantity());
		assertEquals(Status.In_Progress, newOrder.getStatus());
		assertEquals(DeliveryType.Home_delivery, newOrder.getTypeDelivery());
		assertEquals(new Integer(7530), newOrder.getValue());
    }
    
    @Test (expected = IrrationalAmountException.class)
    public void testPurchaseMenuWithout100DailyStockThenThrowException(){
    	viandasYa = new ViandasYa();  	
        GregorianCalendar orderDay = new GregorianCalendar(2019, 11, 2, 12, 00);
		GregorianCalendar deliveryDay = new GregorianCalendar(2019, 11, 8, 11, 30);
		
		User mockUser = mock(User.class);
		Menu mockMenu = mock(Menu.class);
		Provider mockProvider = mock(Provider.class);
		Mockito.when(mockMenu.valueForQuantity(50)).thenReturn(150);
		Mockito.when(mockMenu.getDeliveryPrice()).thenReturn(30);
		Mockito.doThrow(IrrationalAmountException.class).when(mockMenu).validationNumberMenuOrdered(100);	
		
		viandasYa.purchase(mockUser, mockProvider, mockMenu, 100, DeliveryType.Home_delivery, deliveryDay, orderDay);
		Mockito.verify(mockMenu).validationNumberMenuOrdered(100);
		Mockito.verify(mockMenu).validationDateDeliveryMenuOrdered(orderDay , deliveryDay);
    }
    
    @Test (expected = OrderDateException.class)
    public void testPurchaseMenuWithoutHas48HoursBetweenDatesThrowException(){
    	viandasYa = new ViandasYa();  	
        GregorianCalendar orderDay = new GregorianCalendar(2019, 11, 2, 12, 00);
		GregorianCalendar deliveryDay = new GregorianCalendar(2019, 11, 8, 11, 30);
		
		User mockUser = mock(User.class);
		Menu mockMenu = mock(Menu.class);
		Provider mockProvider = mock(Provider.class);
		Mockito.when(mockMenu.valueForQuantity(50)).thenReturn(150);
		Mockito.when(mockMenu.getDeliveryPrice()).thenReturn(30);
		Mockito.doThrow(OrderDateException.class).when(mockMenu).validationDateDeliveryMenuOrdered(orderDay , deliveryDay);	

		viandasYa.purchase(mockUser, mockProvider, mockMenu, 50, DeliveryType.Home_delivery, deliveryDay, orderDay);
		Mockito.verify(mockMenu).validationNumberMenuOrdered(50);
		Mockito.verify(mockMenu).validationDateDeliveryMenuOrdered(orderDay , deliveryDay);
    }
    
    
}
