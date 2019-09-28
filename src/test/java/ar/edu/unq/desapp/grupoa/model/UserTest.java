package ar.edu.unq.desapp.grupoa.model;

import ar.edu.unq.desapp.grupoa.model.exceptions.*;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;

public class UserTest {

	@Test(expected = NullPointerException.class)
	public void whenSettingAnUserWithNullNameThenItThrowsNullPointerException() {
		User aUser = anyUser();
		aUser.setName(null);
	}

	@Test(expected = EmptyStringException.class)
	public void whenSettingAnUserWithEmptyNameThenItThrowsNullPointerException() {
		User aUser = anyUser();
		aUser.setName("");
	}

	@Test(expected = NullPointerException.class)
	public void whenSettingAnUserWithNullSurnameThenItThrowsNullPointerException() {
		User aUser = anyUser();
		aUser.setSurname(null);
	}

	@Test(expected = EmptyStringException.class)
	public void whenSettingAnUserWithEmptySurnameThenItThrowsNullPointerException() {
		User aUser = anyUser();
		aUser.setSurname("");
	}

	@Test(expected = NullPointerException.class)
	public void whenSettingAnUserWithNullEmailThenItThrowsNullPointerException() {
		User aUser = anyUser();
		aUser.setEmail(null);
	}

	@Test(expected = InvalidEmailException.class)
	public void whenSettingAnUserWithEmptyEmailThenItThrowsNullPointerException() {
		User aUser = anyUser();
		aUser.setEmail("");
	}

	@Test(expected = NullPointerException.class)
	public void whenSettingAnUserWithNullPhoneNumberThenItThrowsNullPointerException() {
		User aUser = anyUser();
		aUser.setPhoneNumber(null);
	}

	@Test(expected = InvalidPhoneNumberException.class)
	public void whenSettingAnUserWithEmptyPhoneNumberThenItThrowsNullPointerException() {
		User aUser = anyUser();
		aUser.setPhoneNumber("");
	}

	@Test(expected = NullPointerException.class)
	public void whenSettingAnUserWithNullLocationThenItThrowsNullPointerException() {
		User aUser = anyUser();
		aUser.setLocation(null);
	}

	@Test(expected = EmptyStringException.class)
	public void whenSettingAnUserWithEmptyLocationThenItThrowsNullPointerException() {
		User aUser = anyUser();
		aUser.setLocation("");
	}

	@Test(expected = InvalidEmailException.class)
	public void whenSettingAnUserWithInvalidEmailThenItThrowsNullPointerException() {
		User aUser = anyUser();
		aUser.setEmail("invalidEmail");
	}

	@Test(expected = InvalidPhoneNumberException.class)
	public void whenSettingAnUserWithInvalidPhoneNumberThenItThrowsNullPointerException() {
		User aUser = anyUser();
		aUser.setPhoneNumber("1234");
	}

	@Test
	public void userWithEmptyOrderHistoryAddANewOrderCorrectly() {
		User aUser = anyUser();
		Order mockOrder = mock(Order.class);

		aUser.addHistoryOrder(mockOrder);
		assertTrue(aUser.getOrderHistory().contains(mockOrder));
		assertEquals(1, aUser.getOrderHistory().size());
	}

	@Test
	public void userWith1OrderHistoryAddANewOrderCorrectly() {
		User aUser = anyUser();
		Order mockOrder1 = mock(Order.class);
		Order mockOrder2 = mock(Order.class);

		aUser.addHistoryOrder(mockOrder1);
		aUser.addHistoryOrder(mockOrder2);

		assertTrue(aUser.getOrderHistory().contains(mockOrder1));
		assertTrue(aUser.getOrderHistory().contains(mockOrder2));
		assertEquals(2, aUser.getOrderHistory().size());
	}

	@Test
	public void userWithNameLizaChambiThenAskIfHasNameLizaChambiThenReturnTrue() {
		User aUser = anyUser();
		aUser.setName("LizaChambi");
		assertTrue(aUser.hasName("LizaChambi"));
	}

	@Test
	public void userWithNameLizaChambiThenAskIfHasNameMelOrellanaThenReturnFalse() {
		User aUser = anyUser();
		aUser.setName("LizaChambi");
		assertFalse(aUser.hasName("MelOrellana"));
	}

	@Test
	public void userWithPendingRankingThenAskIfHasPendingRankingThenReturnTrue() {
		User aUser = anyUser();
		Order mockOrder = mock(Order.class);
		Mockito.when(mockOrder.getRanking()).thenReturn(0);

		aUser.addHistoryOrder(mockOrder);
		assertTrue(aUser.hasPendingRanking());
	}

	@Test
	public void userWithoutPendingRankingThenAskIfHasPendingRankingThenReturnFalse() {
		User aUser = anyUser();
		Order mockOrder = mock(Order.class);
		Mockito.when(mockOrder.getRanking()).thenReturn(5);

		aUser.addHistoryOrder(mockOrder);
		assertFalse(aUser.hasPendingRanking());
	}

	@Test
	public void userWithoutOrderRankingThenRankIt5Successfully() {
		User aUser = anyUser();
		Menu mockMenu = mock(Menu.class);
		Order mockOrder = mock(Order.class);
		Mockito.when(mockOrder.getMenu()).thenReturn(mockMenu);
		Mockito.when(mockOrder.getRanking()).thenReturn(5);

		aUser.rankIt(mockOrder, 5);
		assertEquals(new Integer(5), mockOrder.getRanking());
		Mockito.verify(mockOrder).setRanking(5);
	}

	@Test(expected = InvalidRankingException.class)
	public void userWithoutOrderRankingThenRankIt0ThenReturnThrowException() {
		User aUser = anyUser();
		Menu mockMenu = mock(Menu.class);
		Order mockOrder = mock(Order.class);
		Mockito.when(mockOrder.getMenu()).thenReturn(mockMenu);
		Mockito.doThrow(InvalidRankingException.class).when(mockMenu).rankIt(0);
		aUser.rankIt(mockOrder, 0);
	}

	@Test(expected = InvalidRankingException.class)
	public void userWithoutOrderRankingThenRankIt8ThenReturnThrowException() {
		User aUser = anyUser();
		Menu mockMenu = mock(Menu.class);
		Order mockOrder = mock(Order.class);
		Mockito.when(mockOrder.getMenu()).thenReturn(mockMenu);
		Mockito.doThrow(InvalidRankingException.class).when(mockMenu).rankIt(8);
		aUser.rankIt(mockOrder, 8);
	}

	@Test
	public void userAddsMoneyCorrectly(){
		User aUser = anyUser();
		aUser.addMoney(new BigDecimal(100));
		assertEquals(new BigDecimal(100), aUser.getBalance());
	}

	@Test
	public void userDiscountsMoneyCorrectly() throws InsufficientCurrencyException {
		User aUser = anyUser();
		aUser.addMoney(new BigDecimal(100));
		aUser.discountMoney(new BigDecimal(30));
		assertEquals(new BigDecimal(70), aUser.getBalance());
	}

	@Test(expected = InsufficientCurrencyException.class)
	public void userDiscountsMoreMoneyThanBalanceAndThrowsException() throws InsufficientCurrencyException {
		User aUser = anyUser();
		aUser.addMoney(new BigDecimal(100));
		aUser.discountMoney(new BigDecimal(150));
	}

	private User anyUser() {
		return new User("Nahuel", "Benitez", "beniteznahueloscar@gmail.com", "+42112555005", "Condarco 1549");
	}
}