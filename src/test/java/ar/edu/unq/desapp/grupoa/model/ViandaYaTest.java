package ar.edu.unq.desapp.grupoa.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import ar.edu.unq.desapp.grupoa.model.exceptions.IrrationalAmountException;
import ar.edu.unq.desapp.grupoa.model.exceptions.OrderDateException;

public class ViandaYaTest {

	private ViandasYa viandasYa;
	
	@Test
	public void testSearchMenuNamesMatchedWithXWithoutMenusThenReturnEmptyList()
	{
		viandasYa = new ViandasYa();
		assertTrue(viandasYa.searchMenuNamesMatchedWith("x").isEmpty());
	}
	
	@Test
	public void testSearchMenuNamesMatchedWithXThenReturn2Menus()
	{
		viandasYa = new ViandasYa();
		Provider mockProvider1 = mock(Provider.class);
		Provider mockProvider2 = mock(Provider.class);
		Menu mockMenu1 = mock(Menu.class);
		Menu mockMenu2 = mock(Menu.class);
		List<Menu> result = new ArrayList<Menu>();
		result.add(mockMenu1);
		result.add(mockMenu2);
		
		Mockito.when(mockProvider1.menusWithNameMatchedWith("x")).thenReturn(new ArrayList<Menu>());
		Mockito.when(mockProvider2.menusWithNameMatchedWith("x")).thenReturn(result);
		viandasYa.addProvider(mockProvider1);
		viandasYa.addProvider(mockProvider2);
		List<Menu> menus = viandasYa.searchMenuNamesMatchedWith("x");
		
		assertTrue(menus.contains(mockMenu1));
		assertTrue(menus.contains(mockMenu1));
		assertEquals(2, menus.size());
	}
	
	@Test
	public void testSearchMenuWithCategoryCerbezaWithoutMenusThenReturnEmptyList()
	{
		viandasYa = new ViandasYa();
		assertTrue(viandasYa.searchMenusWithCategory(Category.Cerbeza).isEmpty());
	}
	
	@Test
	public void testSearchMenuWithCategoryCerbezaThenReturn2Menus()
	{
		viandasYa = new ViandasYa();
		Provider mockProvider1 = mock(Provider.class);
		Provider mockProvider2 = mock(Provider.class);
		Menu mockMenu1 = mock(Menu.class);
		Menu mockMenu2 = mock(Menu.class);
		List<Menu> result = new ArrayList<Menu>();
		result.add(mockMenu1);
		result.add(mockMenu2);
		
		Mockito.when(mockProvider1.menusWithCategory(Category.Cerbeza)).thenReturn(new ArrayList<Menu>());
		Mockito.when(mockProvider2.menusWithCategory(Category.Cerbeza)).thenReturn(result);
		viandasYa.addProvider(mockProvider1);
		viandasYa.addProvider(mockProvider2);
		List<Menu> menus = viandasYa.searchMenusWithCategory(Category.Cerbeza);
		
		assertTrue(menus.contains(mockMenu1));
		assertTrue(menus.contains(mockMenu1));
		assertEquals(2, menus.size());
	}
	
	@Test
	public void testSearchMenuWithLocationQuilmesThenReturn2Menus()
	{
		viandasYa = new ViandasYa();
		Provider mockProvider1 = mock(Provider.class);
		Provider mockProvider2 = mock(Provider.class);
		Menu mockMenu1 = mock(Menu.class);
		Menu mockMenu2 = mock(Menu.class);
		List<Menu> result = new ArrayList<Menu>();
		result.add(mockMenu1);
		result.add(mockMenu2);
		
		Mockito.when(mockProvider1.menusWithLocation(City.Quilmes)).thenReturn(new ArrayList<Menu>());
		Mockito.when(mockProvider2.menusWithLocation(City.Quilmes)).thenReturn(result);
		viandasYa.addProvider(mockProvider1);
		viandasYa.addProvider(mockProvider2);
		List<Menu> menus = viandasYa.searchMenusWithLocation(City.Quilmes);
		
		assertTrue(menus.contains(mockMenu1));
		assertTrue(menus.contains(mockMenu1));
		assertEquals(2, menus.size());
	}
	
	@Test
	public void testSearchMenuWithLocationBernalWithoutMenusThenReturnEmptyList()
	{
		viandasYa = new ViandasYa();
		assertTrue(viandasYa.searchMenusWithLocation(City.Bernal).isEmpty());
	}
	
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
