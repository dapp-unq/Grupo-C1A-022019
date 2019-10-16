package ar.edu.unq.desapp.grupoa.model;

import ar.edu.unq.desapp.grupoa.model.enums.Category;
import ar.edu.unq.desapp.grupoa.model.enums.City;
import ar.edu.unq.desapp.grupoa.model.enums.DeliveryType;
import ar.edu.unq.desapp.grupoa.model.enums.Status;
import ar.edu.unq.desapp.grupoa.model.exceptions.ElementNotFoundException;
import ar.edu.unq.desapp.grupoa.model.exceptions.InsufficientCurrencyException;
import ar.edu.unq.desapp.grupoa.model.exceptions.IrrationalAmountException;
import ar.edu.unq.desapp.grupoa.model.exceptions.OrderDateException;
import ar.edu.unq.desapp.grupoa.model.exceptions.PurchaseException;
import ar.edu.unq.desapp.grupoa.model.exceptions.RepeatedNameException;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        assertTrue(viandasYa.searchMenusWithCategory(Category.CERVEZA).isEmpty());
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

        when(mockProvider1.menusWithCategory(Category.CERVEZA)).thenReturn(new ArrayList<Menu>());
        when(mockProvider2.menusWithCategory(Category.CERVEZA)).thenReturn(result);
        viandasYa.addProvider(mockProvider1);
        viandasYa.addProvider(mockProvider2);
        List<Menu> menus = viandasYa.searchMenusWithCategory(Category.CERVEZA);

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

        when(mockProvider1.menusWithLocation(City.QUILMES)).thenReturn(new ArrayList<Menu>());
        when(mockProvider2.menusWithLocation(City.QUILMES)).thenReturn(result);
        viandasYa.addProvider(mockProvider1);
        viandasYa.addProvider(mockProvider2);
        List<Menu> menus = viandasYa.searchMenusWithLocation(City.QUILMES);

        assertTrue(menus.contains(mockMenu1));
        assertTrue(menus.contains(mockMenu1));
        assertEquals(2, menus.size());
    }

    @Test
    public void testSearchMenuWithLocationBernalWithoutMenusThenReturnEmptyList() {
        viandasYa = new ViandasYa();
        assertTrue(viandasYa.searchMenusWithLocation(City.BERNAL).isEmpty());
    }

    @Test
    public void testPurchaseMenuCorrectly() throws InsufficientCurrencyException {
        viandasYa = new ViandasYa();
        LocalDateTime orderDay = LocalDateTime.of(2019, 11, 2, 12, 00);
        LocalDateTime deliveryDay = LocalDateTime.of(2019, 11, 8, 11, 30);
        User mockUser = mock(User.class);
        Menu mockMenu = mock(Menu.class);
        Provider mockProvider = mock(Provider.class);
        when(mockMenu.valueForQuantity(50)).thenReturn(150);
        when(mockMenu.getDeliveryPrice()).thenReturn(30);
        doNothing().when(mockUser).discountMoney(any(BigDecimal.class));
        doNothing().when(mockProvider).addMoney(any(BigDecimal.class));

        Order newOrder = viandasYa.purchase(mockUser, mockProvider, mockMenu, 50, DeliveryType.HOME_DELIVERY,
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
        LocalDateTime orderDay = LocalDateTime.of(2019, 11, 2, 12, 00);
        LocalDateTime deliveryDay = LocalDateTime.of(2019, 11, 8, 11, 30);
        User mockUser = mock(User.class);
        Menu mockMenu = mock(Menu.class);
        Provider mockProvider = mock(Provider.class);
        when(mockMenu.valueForQuantity(50)).thenReturn(150);
        when(mockMenu.getDeliveryPrice()).thenReturn(30);
        doThrow(InsufficientCurrencyException.class).when(mockUser).discountMoney(any(BigDecimal.class));

        viandasYa.purchase(mockUser, mockProvider, mockMenu, 50, DeliveryType.HOME_DELIVERY,
                deliveryDay, orderDay);
    }

    @Test(expected = IrrationalAmountException.class)
    public void testPurchaseMenuWithout100DailyStockThenThrowException() throws InsufficientCurrencyException {
        viandasYa = new ViandasYa();
        LocalDateTime orderDay = LocalDateTime.of(2019, 11, 2, 12, 00);
        LocalDateTime deliveryDay = LocalDateTime.of(2019, 11, 8, 11, 30);

        User mockUser = mock(User.class);
        Menu mockMenu = mock(Menu.class);
        Provider mockProvider = mock(Provider.class);
        when(mockMenu.valueForQuantity(50)).thenReturn(150);
        when(mockMenu.getDeliveryPrice()).thenReturn(30);
        doThrow(IrrationalAmountException.class).when(mockMenu).validationNumberMenuOrdered(100);

        viandasYa.purchase(mockUser, mockProvider, mockMenu, 100, DeliveryType.HOME_DELIVERY, deliveryDay, orderDay);
        verify(mockMenu).validationNumberMenuOrdered(100);
        verify(mockMenu).validationDateDeliveryMenuOrdered(orderDay, deliveryDay);
    }

    @Test(expected = OrderDateException.class)
    public void testPurchaseMenuWithoutHas48HoursBetweenDatesThrowException() throws InsufficientCurrencyException {
        viandasYa = new ViandasYa();
        LocalDateTime orderDay = LocalDateTime.of(2019, 11, 2, 12, 00);
        LocalDateTime deliveryDay = LocalDateTime.of(2019, 11, 8, 11, 30);

        User mockUser = mock(User.class);
        Menu mockMenu = mock(Menu.class);
        Provider mockProvider = mock(Provider.class);
        when(mockMenu.valueForQuantity(50)).thenReturn(150);
        when(mockMenu.getDeliveryPrice()).thenReturn(30);
        doThrow(OrderDateException.class).when(mockMenu).validationDateDeliveryMenuOrdered(orderDay,
                deliveryDay);

        viandasYa.purchase(mockUser, mockProvider, mockMenu, 50, DeliveryType.HOME_DELIVERY, deliveryDay, orderDay);
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
        LocalDateTime orderDay = LocalDateTime.of(2019, 11, 2, 12, 00);
        LocalDateTime deliveryDay = LocalDateTime.of(2019, 11, 8, 11, 30);
        Menu mockMenu = mock(Menu.class);
        when(mockMenu.valueForQuantity(50)).thenReturn(150);
        when(mockMenu.getDeliveryPrice()).thenReturn(30);

		Order newOrder = viandasYa.makeOrder(mockMenu, "ViandaLiz", deliveryDay, orderDay, 50, DeliveryType.HOME_DELIVERY);

        verify(mockMenu).validationNumberMenuOrdered(50);
        verify(mockMenu).validationDateDeliveryMenuOrdered(orderDay, deliveryDay);
        assertEquals(mockMenu, newOrder.getMenu());
        assertEquals(deliveryDay, newOrder.getDeliveryDateAndHour());
        assertEquals(orderDay, newOrder.getOrderDateAndHour());
        assertEquals(new Integer(50), newOrder.getQuantity());
        assertEquals(Status.IN_PROGRESS, newOrder.getStatus());
        assertEquals(DeliveryType.HOME_DELIVERY, newOrder.getTypeDelivery());
        assertEquals(new Integer(7530), newOrder.getValue());
        assertEquals(new Integer(0), newOrder.getRanking());
    }

    @Test(expected = IrrationalAmountException.class)
    public void testMakeAOrderWithout100DailyStockThenThrowException() {
        viandasYa = new ViandasYa();
        LocalDateTime orderDay = LocalDateTime.of(2019, 11, 2, 12, 00);
        LocalDateTime deliveryDay = LocalDateTime.of(2019, 11, 8, 11, 30);
        Menu mockMenu = mock(Menu.class);
        doThrow(IrrationalAmountException.class).when(mockMenu).validationNumberMenuOrdered(100);

		viandasYa.makeOrder(mockMenu, "ViandaLiz", deliveryDay, orderDay, 100, DeliveryType.HOME_DELIVERY);
		verify(mockMenu).validationNumberMenuOrdered(100);
	}

    @Test(expected = OrderDateException.class)
    public void testMakeAOrderWithoutHas48HoursBetweenDatesThrowException() {
        viandasYa = new ViandasYa();
        LocalDateTime orderDay = LocalDateTime.of(2019, 11, 2, 12, 00);
        LocalDateTime deliveryDay = LocalDateTime.of(2019, 11, 8, 11, 30);
        Menu mockMenu = mock(Menu.class);
        doThrow(OrderDateException.class).when(mockMenu).validationDateDeliveryMenuOrdered(orderDay,
                deliveryDay);

        viandasYa.makeOrder(mockMenu, "ViandaLiz",deliveryDay, orderDay, 50, DeliveryType.HOME_DELIVERY);
        verify(mockMenu).validationNumberMenuOrdered(50);
        verify(mockMenu).validationDateDeliveryMenuOrdered(orderDay, deliveryDay);
	}

    @Test
	public void testRemoveProviderSuccessfully() {
		viandasYa = new ViandasYa();
		Provider mockProvider = mock(Provider.class);
		when(mockProvider.getName()).thenReturn("ViandaLiz");
		when(mockProvider.hasName("ViandaLiz")).thenReturn(true);
		viandasYa.addProvider(mockProvider);

		viandasYa.removeProvider(mockProvider);
		assertTrue(viandasYa.getProviders().isEmpty());
		assertFalse(viandasYa.getProviders().contains(mockProvider));
	}

	@Test (expected = ElementNotFoundException.class)
	public void testRemoveProviderWhitoutProviderThenReturnThrowException() {
		viandasYa = new ViandasYa();
		Provider mockProvider = mock(Provider.class);
		when(mockProvider.getName()).thenReturn("ViandaLiz");

		viandasYa.removeProvider(mockProvider);
	}

	@Test
	public void testCancelProviderWithLowQualitySuccessfully() {
		viandasYa = new ViandasYa();
		Provider mockProvider = mock(Provider.class);
		when(mockProvider.getName()).thenReturn("ViandaLiz");
		when(mockProvider.hasName("ViandaLiz")).thenReturn(true);
		when(mockProvider.getMenusRemoved()).thenReturn(10);
		viandasYa.addProvider(mockProvider);

		viandasYa.cancelProvider(mockProvider);
		assertTrue(viandasYa.getProviders().isEmpty());
		assertFalse(viandasYa.getProviders().contains(mockProvider));
	}

	@Test
	public void testCancelProviderWithHighQualityThenNotRemoved() {
		viandasYa = new ViandasYa();
		Provider mockProvider = mock(Provider.class);
		when(mockProvider.getName()).thenReturn("ViandaLiz");
		when(mockProvider.hasName("ViandaLiz")).thenReturn(true);
		when(mockProvider.getMenusRemoved()).thenReturn(9);
		viandasYa.addProvider(mockProvider);

		viandasYa.cancelProvider(mockProvider);
		assertTrue(viandasYa.getProviders().contains(mockProvider));
	}

	@Test
	public void testCancelMenuWihtoutCancelProviderSuccessfully() {
		viandasYa = new ViandasYa();
		Provider mockProvider = mock(Provider.class);
		Menu mockMenu = mock(Menu.class);
		when(mockProvider.getName()).thenReturn("ViandaLiz");
		when(mockProvider.hasName("ViandaLiz")).thenReturn(true);
		when(mockProvider.getMenusRemoved()).thenReturn(9);
		when(mockProvider.getCurrentMenu()).thenReturn(new ArrayList<Menu>());
		when(mockMenu.hasLowQualityMenu()).thenReturn(true);
		viandasYa.addProvider(mockProvider);

		viandasYa.cancelMenu(mockProvider.getName(), mockMenu);
		assertFalse(mockProvider.getCurrentMenu().contains(mockMenu));
		assertEquals(new Integer(9), mockProvider.getMenusRemoved());
		verify(mockProvider).cancelMenu(mockMenu);
	}

	@Test
	public void testCancelMenuAndCancelProviderSuccessfully() {
		viandasYa = new ViandasYa();
		Provider mockProvider = mock(Provider.class);
		Menu mockMenu = mock(Menu.class);
		when(mockProvider.getName()).thenReturn("ViandaLiz");
		when(mockProvider.hasName("ViandaLiz")).thenReturn(true);
		when(mockProvider.getMenusRemoved()).thenReturn(10);
		when(mockProvider.getCurrentMenu()).thenReturn(new ArrayList<Menu>());
		when(mockMenu.hasLowQualityMenu()).thenReturn(true);
		viandasYa.addProvider(mockProvider);

		viandasYa.cancelMenu(mockProvider.getName(), mockMenu);
		assertFalse(mockProvider.getCurrentMenu().contains(mockMenu));
		assertFalse(viandasYa.getProviders().contains(mockProvider));
		verify(mockProvider).cancelMenu(mockMenu);
	}

	@Test
	public void testRankItWith4Successfully() {
		viandasYa = new ViandasYa();
		User mockUser = mock(User.class);
		Menu mockMenu = mock(Menu.class);
		Order mockOrder = mock(Order.class);

		when(mockOrder.getProviderName()).thenReturn("ViandaLiz");
		when(mockOrder.getMenu()).thenReturn(mockMenu);
		when(mockOrder.getRanking()).thenReturn(4);
		when(mockMenu.hasLowQualityMenu()).thenReturn(false);

		viandasYa.rankIt(mockUser, mockOrder, 4);
		assertEquals(new Integer(4), mockOrder.getRanking());
		verify(mockUser).rankIt(mockOrder, 4);
	}
}
