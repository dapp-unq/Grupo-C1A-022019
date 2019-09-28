package ar.edu.unq.desapp.grupoa.model;

import ar.edu.unq.desapp.grupoa.model.exceptions.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ViandaYaTest {

    private ViandasYa viandasYa;

    @Test
    public void testAddProviderSuccessfully() {
        viandasYa = new ViandasYa();
        Provider mockProvider = mock(Provider.class);
        when(mockProvider.getName()).thenReturn("ViandaLiz");
        viandasYa.addProvider(mockProvider);

        assertTrue(viandasYa.getProviders().contains(mockProvider));
    }

    @Test
    public void testAddUserSuccessfully() {
        viandasYa = new ViandasYa();
        User mockUser = mock(User.class);
        when(mockUser.getName()).thenReturn("ViandaLiz");
        viandasYa.addUser(mockUser);

        assertTrue(viandasYa.getClients().contains(mockUser));
    }

    @Test(expected = RepeatedNameException.class)
    public void testAddProviderWithRepeatedNameThenReturnThrowException() {
        viandasYa = new ViandasYa();
        Provider mockProvider1 = mock(Provider.class);
        Provider mockProvider2 = mock(Provider.class);
        when(mockProvider1.getName()).thenReturn("ViandaLiz");
        when(mockProvider1.hasName("ViandaLiz")).thenReturn(true);
        when(mockProvider2.getName()).thenReturn("ViandaLiz");
        viandasYa.addProvider(mockProvider1);
        viandasYa.addProvider(mockProvider2);

        assertEquals(1, viandasYa.getProviders().size());
        assertTrue(viandasYa.getProviders().contains(mockProvider1));
        assertFalse(viandasYa.getProviders().contains(mockProvider2));
    }

    @Test(expected = RepeatedNameException.class)
    public void testAddUserWithRepeatedNameThenReturnThrowException() {
        viandasYa = new ViandasYa();
        User mockUser1 = mock(User.class);
        User mockUser2 = mock(User.class);
        when(mockUser1.getName()).thenReturn("LizaChambi");
        when(mockUser1.hasName("LizaChambi")).thenReturn(true);
        when(mockUser2.getName()).thenReturn("LizaChambi");
        viandasYa.addUser(mockUser1);
        viandasYa.addUser(mockUser2);

        assertEquals(1, viandasYa.getClients().size());
        assertTrue(viandasYa.getClients().contains(mockUser1));
        assertFalse(viandasYa.getClients().contains(mockUser2));
    }

    @Test
    public void testHasInUseProviderNameViandaLizThenReturnTrue() {
        viandasYa = new ViandasYa();
        Provider mockProvider1 = mock(Provider.class);
        Provider mockProvider2 = mock(Provider.class);
        when(mockProvider1.getName()).thenReturn("ViandaLiz");
        when(mockProvider1.hasName("ViandaLiz")).thenReturn(true);
        when(mockProvider2.getName()).thenReturn("ViandaLiz");
        viandasYa.addProvider(mockProvider1);

        assertTrue(viandasYa.hasInUseProviderName(mockProvider2.getName()));
    }

    @Test
    public void testHasInUseUserNameLizaChambiThenReturnTrue() {
        viandasYa = new ViandasYa();
        User mockUser1 = mock(User.class);
        User mockUser2 = mock(User.class);
        when(mockUser1.getName()).thenReturn("LizaChambi");
        when(mockUser1.hasName("LizaChambi")).thenReturn(true);
        when(mockUser2.getName()).thenReturn("LizaChambi");
        viandasYa.addUser(mockUser1);

        assertTrue(viandasYa.hasInUseUserName(mockUser2.getName()));
    }

    @Test
    public void testHasInUseProviderNameViandaMelThenReturnFalse() {
        viandasYa = new ViandasYa();
        Provider mockProvider1 = mock(Provider.class);
        Provider mockProvider2 = mock(Provider.class);
        when(mockProvider1.getName()).thenReturn("ViandaLiz");
        when(mockProvider1.hasName("ViandaLiz")).thenReturn(false);
        when(mockProvider2.getName()).thenReturn("ViandaMel");
        viandasYa.addProvider(mockProvider1);

        assertFalse(viandasYa.hasInUseProviderName(mockProvider1.getName()));
    }

    @Test
    public void testHasInUseUserNameMelOrellanaThenReturnFalse() {
        viandasYa = new ViandasYa();
        User mockUser1 = mock(User.class);
        User mockUser2 = mock(User.class);
        when(mockUser1.getName()).thenReturn("LizaChambi");
        when(mockUser1.hasName("LizaChambi")).thenReturn(false);
        when(mockUser2.getName()).thenReturn("MelOrellana");
        viandasYa.addUser(mockUser1);

        assertFalse(viandasYa.hasInUseUserName(mockUser1.getName()));
    }

    @Test
    public void testHasInUseProviderNameViandaLizWithViandaYaEmptyProvidersThenReturnFalse() {
        viandasYa = new ViandasYa();
        Provider mockProvider1 = mock(Provider.class);
        when(mockProvider1.getName()).thenReturn("ViandaLiz");
        when(mockProvider1.hasName("ViandaLiz")).thenReturn(true);

        assertFalse(viandasYa.hasInUseProviderName(mockProvider1.getName()));
    }

    @Test
    public void testHasInUseUserNameLizaChambiWithViandaYaEmptyClientsThenReturnFalse() {
        viandasYa = new ViandasYa();
        User mockUser1 = mock(User.class);
        when(mockUser1.getName()).thenReturn("LizaChambi");
        when(mockUser1.hasName("LizaChambi")).thenReturn(true);

        assertFalse(viandasYa.hasInUseUserName(mockUser1.getName()));
    }

    @Test
    public void testSearchMenuNamesMatchedWithXWithoutMenusThenReturnEmptyList() {
        viandasYa = new ViandasYa();
        assertTrue(viandasYa.searchMenuNamesMatchedWith("x").isEmpty());
    }

    @Test
    public void testSearchMenuNamesMatchedWithXThenReturn2Menus() {
        viandasYa = new ViandasYa();
        Provider mockProvider1 = mock(Provider.class);
        Provider mockProvider2 = mock(Provider.class);
        Menu mockMenu1 = mock(Menu.class);
        Menu mockMenu2 = mock(Menu.class);
        List<Menu> result = new ArrayList<Menu>();
        result.add(mockMenu1);
        result.add(mockMenu2);

        when(mockProvider1.menusWithNameMatchedWith("x")).thenReturn(new ArrayList<Menu>());
        when(mockProvider2.menusWithNameMatchedWith("x")).thenReturn(result);
        viandasYa.addProvider(mockProvider1);
        viandasYa.addProvider(mockProvider2);
        List<Menu> menus = viandasYa.searchMenuNamesMatchedWith("x");

        assertTrue(menus.contains(mockMenu1));
        assertTrue(menus.contains(mockMenu1));
        assertEquals(2, menus.size());
    }

    @Test
    public void testSearchMenuWithCategoryCerbezaWithoutMenusThenReturnEmptyList() {
        viandasYa = new ViandasYa();
        assertTrue(viandasYa.searchMenusWithCategory(Category.Cerbeza).isEmpty());
    }

    @Test
    public void testSearchMenuWithCategoryCerbezaThenReturn2Menus() {
        viandasYa = new ViandasYa();
        Provider mockProvider1 = mock(Provider.class);
        Provider mockProvider2 = mock(Provider.class);
        Menu mockMenu1 = mock(Menu.class);
        Menu mockMenu2 = mock(Menu.class);
        List<Menu> result = new ArrayList<Menu>();
        result.add(mockMenu1);
        result.add(mockMenu2);

        when(mockProvider1.menusWithCategory(Category.Cerbeza)).thenReturn(new ArrayList<Menu>());
        when(mockProvider2.menusWithCategory(Category.Cerbeza)).thenReturn(result);
        viandasYa.addProvider(mockProvider1);
        viandasYa.addProvider(mockProvider2);
        List<Menu> menus = viandasYa.searchMenusWithCategory(Category.Cerbeza);

        assertTrue(menus.contains(mockMenu1));
        assertTrue(menus.contains(mockMenu1));
        assertEquals(2, menus.size());
    }

    @Test
    public void testSearchMenuWithLocationQuilmesThenReturn2Menus() {
        viandasYa = new ViandasYa();
        Provider mockProvider1 = mock(Provider.class);
        Provider mockProvider2 = mock(Provider.class);
        Menu mockMenu1 = mock(Menu.class);
        Menu mockMenu2 = mock(Menu.class);
        List<Menu> result = new ArrayList<Menu>();
        result.add(mockMenu1);
        result.add(mockMenu2);

        when(mockProvider1.menusWithLocation(City.Quilmes)).thenReturn(new ArrayList<Menu>());
        when(mockProvider2.menusWithLocation(City.Quilmes)).thenReturn(result);
        viandasYa.addProvider(mockProvider1);
        viandasYa.addProvider(mockProvider2);
        List<Menu> menus = viandasYa.searchMenusWithLocation(City.Quilmes);

        assertTrue(menus.contains(mockMenu1));
        assertTrue(menus.contains(mockMenu1));
        assertEquals(2, menus.size());
    }

    @Test
    public void testSearchMenuWithLocationBernalWithoutMenusThenReturnEmptyList() {
        viandasYa = new ViandasYa();
        assertTrue(viandasYa.searchMenusWithLocation(City.Bernal).isEmpty());
    }

    @Test
    public void testPurchaseMenuCorrectly() throws InsufficientCurrencyException {
        viandasYa = new ViandasYa();
        GregorianCalendar orderDay = new GregorianCalendar(2019, 11, 2, 12, 00);
        GregorianCalendar deliveryDay = new GregorianCalendar(2019, 11, 8, 11, 30);
        User mockUser = mock(User.class);
        Menu mockMenu = mock(Menu.class);
        Provider mockProvider = mock(Provider.class);
        when(mockMenu.valueForQuantity(50)).thenReturn(150);
        when(mockMenu.getDeliveryPrice()).thenReturn(30);
        doNothing().when(mockUser).discountMoney(any(BigDecimal.class));
        doNothing().when(mockProvider).addMoney(any(BigDecimal.class));

        Order newOrder = viandasYa.purchase(mockUser, mockProvider, mockMenu, 50, DeliveryType.Home_delivery,
                deliveryDay, orderDay);
        verify(mockMenu).validationNumberMenuOrdered(50);
        verify(mockMenu).validationDateDeliveryMenuOrdered(orderDay, deliveryDay);
        verify(mockUser).addHistoryOrder(newOrder);
        verify(mockProvider).addOrder(mockUser, newOrder);

        assertNotNull(newOrder);
    }

    @Test(expected = InsufficientCurrencyException.class)
    public void whenUserWithInsufficientMoneyMakeAPurchaseItThrowsException() throws InsufficientCurrencyException {
        viandasYa = new ViandasYa();
        GregorianCalendar orderDay = new GregorianCalendar(2019, 11, 2, 12, 00);
        GregorianCalendar deliveryDay = new GregorianCalendar(2019, 11, 8, 11, 30);
        User mockUser = mock(User.class);
        Menu mockMenu = mock(Menu.class);
        Provider mockProvider = mock(Provider.class);
        when(mockMenu.valueForQuantity(50)).thenReturn(150);
        when(mockMenu.getDeliveryPrice()).thenReturn(30);
        doThrow(InsufficientCurrencyException.class).when(mockUser).discountMoney(any(BigDecimal.class));

        viandasYa.purchase(mockUser, mockProvider, mockMenu, 50, DeliveryType.Home_delivery,
                deliveryDay, orderDay);
    }

    @Test(expected = IrrationalAmountException.class)
    public void testPurchaseMenuWithout100DailyStockThenThrowException() throws InsufficientCurrencyException {
        viandasYa = new ViandasYa();
        GregorianCalendar orderDay = new GregorianCalendar(2019, 11, 2, 12, 00);
        GregorianCalendar deliveryDay = new GregorianCalendar(2019, 11, 8, 11, 30);

        User mockUser = mock(User.class);
        Menu mockMenu = mock(Menu.class);
        Provider mockProvider = mock(Provider.class);
        when(mockMenu.valueForQuantity(50)).thenReturn(150);
        when(mockMenu.getDeliveryPrice()).thenReturn(30);
        Mockito.doThrow(IrrationalAmountException.class).when(mockMenu).validationNumberMenuOrdered(100);

        viandasYa.purchase(mockUser, mockProvider, mockMenu, 100, DeliveryType.Home_delivery, deliveryDay, orderDay);
        verify(mockMenu).validationNumberMenuOrdered(100);
        verify(mockMenu).validationDateDeliveryMenuOrdered(orderDay, deliveryDay);
    }

    @Test(expected = OrderDateException.class)
    public void testPurchaseMenuWithoutHas48HoursBetweenDatesThrowException() throws InsufficientCurrencyException {
        viandasYa = new ViandasYa();
        GregorianCalendar orderDay = new GregorianCalendar(2019, 11, 2, 12, 00);
        GregorianCalendar deliveryDay = new GregorianCalendar(2019, 11, 8, 11, 30);

        User mockUser = mock(User.class);
        Menu mockMenu = mock(Menu.class);
        Provider mockProvider = mock(Provider.class);
        when(mockMenu.valueForQuantity(50)).thenReturn(150);
        when(mockMenu.getDeliveryPrice()).thenReturn(30);
        Mockito.doThrow(OrderDateException.class).when(mockMenu).validationDateDeliveryMenuOrdered(orderDay,
                deliveryDay);

        viandasYa.purchase(mockUser, mockProvider, mockMenu, 50, DeliveryType.Home_delivery, deliveryDay, orderDay);
        verify(mockMenu).validationNumberMenuOrdered(50);
        verify(mockMenu).validationDateDeliveryMenuOrdered(orderDay, deliveryDay);
    }

    @Test(expected = PurchaseException.class)
    public void testValidatedPendingRankingThrowException() {
        viandasYa = new ViandasYa();
        User mockUser = mock(User.class);
        when(mockUser.hasPendingRanking()).thenReturn(true);
        viandasYa.validatedPendingRanking(mockUser);
    }

    @Test
    public void testMakeAOrderThenReturnAOrder() {
        viandasYa = new ViandasYa();
        GregorianCalendar orderDay = new GregorianCalendar(2019, 11, 2, 12, 00);
        GregorianCalendar deliveryDay = new GregorianCalendar(2019, 11, 8, 11, 30);
        Menu mockMenu = mock(Menu.class);
        when(mockMenu.valueForQuantity(50)).thenReturn(150);
        when(mockMenu.getDeliveryPrice()).thenReturn(30);

        Order newOrder = viandasYa.makeOrder(mockMenu, deliveryDay, orderDay, 50, DeliveryType.Home_delivery);

        verify(mockMenu).validationNumberMenuOrdered(50);
        verify(mockMenu).validationDateDeliveryMenuOrdered(orderDay, deliveryDay);
        assertEquals(mockMenu, newOrder.getMenu());
        assertEquals(deliveryDay, newOrder.getDateHoursDelivery());
        assertEquals(orderDay, newOrder.getDateHoursOrder());
        assertEquals(new Integer(50), newOrder.getQuantity());
        assertEquals(Status.In_Progress, newOrder.getStatus());
        assertEquals(DeliveryType.Home_delivery, newOrder.getTypeDelivery());
        assertEquals(new Integer(7530), newOrder.getValue());
        assertEquals(new Integer(0), newOrder.getRanking());
    }

    @Test(expected = IrrationalAmountException.class)
    public void testMakeAOrderWithout100DailyStockThenThrowException() {
        viandasYa = new ViandasYa();
        GregorianCalendar orderDay = new GregorianCalendar(2019, 11, 2, 12, 00);
        GregorianCalendar deliveryDay = new GregorianCalendar(2019, 11, 8, 11, 30);
        Menu mockMenu = mock(Menu.class);
        Mockito.doThrow(IrrationalAmountException.class).when(mockMenu).validationNumberMenuOrdered(100);

        viandasYa.makeOrder(mockMenu, deliveryDay, orderDay, 100, DeliveryType.Home_delivery);
        verify(mockMenu).validationNumberMenuOrdered(100);
    }

    @Test(expected = OrderDateException.class)
    public void testMakeAOrderWithoutHas48HoursBetweenDatesThrowException() {
        viandasYa = new ViandasYa();
        GregorianCalendar orderDay = new GregorianCalendar(2019, 11, 2, 12, 00);
        GregorianCalendar deliveryDay = new GregorianCalendar(2019, 11, 8, 11, 30);
        Menu mockMenu = mock(Menu.class);
        Mockito.doThrow(OrderDateException.class).when(mockMenu).validationDateDeliveryMenuOrdered(orderDay,
                deliveryDay);

        viandasYa.makeOrder(mockMenu, deliveryDay, orderDay, 50, DeliveryType.Home_delivery);
        verify(mockMenu).validationNumberMenuOrdered(50);
        verify(mockMenu).validationDateDeliveryMenuOrdered(orderDay, deliveryDay);

    }
}
