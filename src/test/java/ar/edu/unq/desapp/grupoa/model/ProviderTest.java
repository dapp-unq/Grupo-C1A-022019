package ar.edu.unq.desapp.grupoa.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.desapp.grupoa.model.enums.Category;
import ar.edu.unq.desapp.grupoa.model.enums.City;
import org.junit.Test;
import org.mockito.Mockito;

import ar.edu.unq.desapp.grupoa.model.exceptions.CurrentMenuQuantityException;
import ar.edu.unq.desapp.grupoa.model.exceptions.DescriptionLengthException;
import ar.edu.unq.desapp.grupoa.model.exceptions.ElementNotFoundException;
import ar.edu.unq.desapp.grupoa.model.exceptions.EmptyServiceHoursDaysException;
import ar.edu.unq.desapp.grupoa.model.exceptions.EmptyStringException;
import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidEmailException;
import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidPhoneNumberException;
import ar.edu.unq.desapp.grupoa.model.exceptions.RepeatedNameException;

public class ProviderTest {

	private Provider provider;

	@Test(expected = NullPointerException.class)
	public void providerCreationWithNullNameThrowsException() {
		provider = ProviderBuilder.aProvider().withName(null).build();
	}

	@Test(expected = EmptyStringException.class)
	public void providerCreationWithEmptyNameThrowsException() {
		provider = ProviderBuilder.aProvider().withName("").build();
	}

	@Test(expected = NullPointerException.class)
	public void providerWithNullNameThrowsException() {
		provider = ProviderBuilder.aProvider().build();
		provider.setName(null);
	}

	@Test(expected = EmptyStringException.class)
	public void providerWithEmptyNameThrowsException() {
		provider = ProviderBuilder.aProvider().build();
		provider.setName("");
	}

	@Test(expected = NullPointerException.class)
	public void providerCreationWithNullLogoThrowsException() {
		provider = ProviderBuilder.aProvider().withLogo(null).build();
	}

	@Test(expected = EmptyStringException.class)
	public void providerCreationWithEmptyLogoThrowsException() {
		provider = ProviderBuilder.aProvider().withLogo("").build();
	}

	@Test(expected = NullPointerException.class)
	public void providerWithNullLogoThrowsException() {
		provider = ProviderBuilder.aProvider().build();
		provider.setLogo(null);
	}

	@Test(expected = EmptyStringException.class)
	public void providerWithEmptyLogoThrowsException() {
		provider = ProviderBuilder.aProvider().build();
		provider.setLogo("");
	}

	@Test(expected = NullPointerException.class)
	public void providerCreationWithNullCityThrowsException() {
		provider = ProviderBuilder.aProvider().withCity(null).build();
	}

	@Test(expected = NullPointerException.class)
	public void providerWithNullCityThrowsException() {
		provider = ProviderBuilder.aProvider().build();
		provider.setCity(null);
	}

	@Test(expected = NullPointerException.class)
	public void providerCreationWithNullLocationThrowsException() {
		provider = ProviderBuilder.aProvider().withLocation(null).build();
	}

	@Test(expected = EmptyStringException.class)
	public void providerCreationWithEmptyLocationThrowsException() {
		provider = ProviderBuilder.aProvider().withLocation("").build();
	}

	@Test(expected = NullPointerException.class)
	public void providerWithNullLocationThrowsException() {
		provider = ProviderBuilder.aProvider().build();
		provider.setLocation(null);
	}

	@Test(expected = EmptyStringException.class)
	public void providerWithEmptyLocationThrowsException() {
		provider = ProviderBuilder.aProvider().build();
		provider.setLocation("");
	}

	@Test(expected = DescriptionLengthException.class)
	public void providerCreationWithEmptyDescriptionThrowsException() {
		provider = ProviderBuilder.aProvider().withDescription("").build();
	}

	@Test(expected = NullPointerException.class)
	public void providerCreationWithNullDescriptionThrowsException() {
		provider = ProviderBuilder.aProvider().withDescription(null).build();
	}

	@Test(expected = DescriptionLengthException.class)
	public void providerCreationWith29LengthDescriptionThrowsException() {
		provider = ProviderBuilder.aProvider().withDescription("123abcdefghijklmnopqrstuvwxyz").build();
	}

	@Test(expected = DescriptionLengthException.class)
	public void providerCreationWith201LengthDescriptionThrowsException() {
		provider = ProviderBuilder.aProvider().withDescription(
				"abcdefghijklmnopqrstuvwxyz123456789abcdefghijklmnopqrstuvwxyz123456789abcdefghijklmnopqrstuvwxyz123456789abcdefghijklmnopqrstuvwxyz123456789abcdefghijklmnopqrstuvwxyz123456789abcdefghijklmnopqrstuvwxyz")
				.build();
	}

	@Test(expected = NullPointerException.class)
	public void providerCreationWithNullWebsiteThrowsException() {
		provider = ProviderBuilder.aProvider().withWebsite(null).build();
	}

	@Test(expected = NullPointerException.class)
	public void providerWithNullWebsiteThrowsException() {
		provider = ProviderBuilder.aProvider().build();
		provider.setWebsite(null);
	}

	@Test(expected = NullPointerException.class)
	public void providerCreationWithNullEmailThrowsException() {
		provider = ProviderBuilder.aProvider().withEmail(null).build();
	}

	@Test(expected = EmptyStringException.class)
	public void providerCreationWithEmptyEmailThrowsException() {
		provider = ProviderBuilder.aProvider().withEmail("").build();
	}

	@Test(expected = InvalidEmailException.class)
	public void providerCreationWithInvalidEmailThrowsException() {
		provider = ProviderBuilder.aProvider().withEmail("invalid mail").build();
	}

	@Test(expected = NullPointerException.class)
	public void providerWithNullEmailThrowsException() {
		provider = ProviderBuilder.aProvider().build();
		provider.setEmail(null);
	}

	@Test(expected = EmptyStringException.class)
	public void providerWithEmptyEmailThrowsException() {
		provider = ProviderBuilder.aProvider().build();
		provider.setEmail("");
	}

	@Test(expected = InvalidEmailException.class)
	public void providerWithInvalidEmailThrowsException() {
		provider = ProviderBuilder.aProvider().build();
		provider.setEmail("123mail");
	}

	@Test(expected = NullPointerException.class)
	public void providerCreationWithNullPhoneNumberThrowsException() {
		provider = ProviderBuilder.aProvider().withPhoneNumber(null).build();
	}

	@Test(expected = EmptyStringException.class)
	public void providerCreationWithEmptyPhoneNumberThrowsException() {
		provider = ProviderBuilder.aProvider().withPhoneNumber("").build();
	}

	@Test(expected = InvalidPhoneNumberException.class)
	public void providerCreationWithInvalidPhoneNumberThrowsException() {
		provider = ProviderBuilder.aProvider().withPhoneNumber("teléfono123").build();
	}

	@Test(expected = NullPointerException.class)
	public void providerWithNullPhoneNumberThrowsException() {
		provider = ProviderBuilder.aProvider().build();
		provider.setPhoneNumber(null);
	}

	@Test(expected = EmptyStringException.class)
	public void providerWithEmptyPhoneNumberThrowsException() {
		provider = ProviderBuilder.aProvider().build();
		provider.setPhoneNumber("");
	}

	@Test(expected = InvalidPhoneNumberException.class)
	public void providerWithInvalidPhoneNumberThrowsException() {
		provider = ProviderBuilder.aProvider().build();
		provider.setPhoneNumber("teléfono123");
	}

	@Test(expected = NullPointerException.class)
	public void providerCreationWithNullServiceHoursDaysThrowsException() {
		provider = ProviderBuilder.aProvider().withOpeningHoursDays(null).build();
	}

	@Test(expected = EmptyServiceHoursDaysException.class)
	public void providerCreationWithEmptyServiceHoursDaysThrowsException() {
		provider = ProviderBuilder.aProvider().withOpeningHoursDays(new ArrayList<ServiceHours>()).build();
	}

	@Test(expected = NullPointerException.class)
	public void providerWithNullServiceHoursDaysThrowsException() {
		provider = ProviderBuilder.aProvider().build();
		provider.setOpeningHoursDays(null);
	}

	@Test(expected = EmptyServiceHoursDaysException.class)
	public void providerWithEmptyServiceHoursDaysThrowsException() {
		provider = ProviderBuilder.aProvider().build();
		provider.setOpeningHoursDays(new ArrayList<ServiceHours>());
	}

	@Test
	public void createValidProvider() {
		provider = ProviderBuilder.aProvider().build();
		assertNotNull(provider);
	}

	@Test
	public void providerCreationWithViandaLizName() {
		provider = ProviderBuilder.aProvider().withName("ViandaLiz").build();
		assertEquals("ViandaLiz", provider.getName());
	}

	@Test
	public void providerCreationWithLogoLizPNGLogo() {
		provider = ProviderBuilder.aProvider().withLogo("logoLiz.png").build();
		assertEquals("logoLiz.png", provider.getLogo());
	}

	@Test
	public void providerCreationWithQuilmesCity() {
		provider = ProviderBuilder.aProvider().withCity(City.QUILMES).build();
		assertEquals(City.QUILMES, provider.getCity());
	}

	@Test
	public void providerCreationWithSaenzPenaLocation() {
		provider = ProviderBuilder.aProvider().withLocation("Roque Sáenz Peña 352").build();
		assertEquals("Roque Sáenz Peña 352", provider.getLocation());
	}

	@Test
	public void providerCreationWithValidDescrition() {
		provider = ProviderBuilder.aProvider()
				.withDescription(
						"La comidas más rica y nutritiva hecha con productos orgánicos, y sin uso de conservantes")
				.build();
		assertEquals("La comidas más rica y nutritiva hecha con productos orgánicos, y sin uso de conservantes",
				provider.getDescription());
	}

	@Test
	public void providerCreationWithValidWebsite() {
		provider = ProviderBuilder.aProvider().withWebsite("viandaLiz.com.ar").build();
		assertEquals("viandaLiz.com.ar", provider.getWebsite());
	}

	@Test
	public void providerCreationWithValidEmail() {
		provider = ProviderBuilder.aProvider().withEmail("vianda-liz@gmail.com").build();
		assertEquals("vianda-liz@gmail.com", provider.getEmail());
	}

	@Test
	public void providerCreationWithValidPhoneNumber() {
		provider = ProviderBuilder.aProvider().withPhoneNumber("+42112555005").build();
		assertEquals("+42112555005", provider.getPhoneNumber());
	}

	@Test
	public void providerCreationWithValidOpeningHoursDays() {
		List<ServiceHours> openingHours = new ArrayList<>();
		openingHours.add(new ServiceHours(DayOfWeek.MONDAY, LocalTime.of(9, 00), LocalTime.of(21, 00)));
		openingHours.add(new ServiceHours(DayOfWeek.WEDNESDAY, LocalTime.of(10, 00), LocalTime.of(22, 00)));
		openingHours.add(new ServiceHours(DayOfWeek.FRIDAY, LocalTime.of(9, 00), LocalTime.of(21, 00)));
		provider = ProviderBuilder.aProvider().withOpeningHoursDays(openingHours).build();
		assertEquals(openingHours, provider.getOpeningHoursDays());
	}

	@Test
	public void providerWithoutCurrentMenusAddACurrentMenu() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu = mock(Menu.class);
		provider.addMenu(mockMenu);
		assertTrue(provider.getCurrentMenus().contains(mockMenu));
	}

	@Test(expected = RepeatedNameException.class)
	public void providerWithMenuWithNameXAndAddMenuWithTheSameNameThrowsException() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu1 = mock(Menu.class);
		Menu mockMenu2 = mock(Menu.class);
		Mockito.when(mockMenu1.hasName("Pizza especial Liz")).thenReturn(true);
		Mockito.when(mockMenu2.getName()).thenReturn("Pizza especial Liz");
		provider.addMenu(mockMenu1);
		provider.addMenu(mockMenu2);
	}

	@Test(expected = CurrentMenuQuantityException.class)
	public void providerWith20CurrentMenusAddACurrentMenuThrowsException() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu = mock(Menu.class);
		Menu mockMenu21 = mock(Menu.class);

		provider.addMenu(mockMenu); provider.addMenu(mockMenu);
		provider.addMenu(mockMenu); provider.addMenu(mockMenu);
		provider.addMenu(mockMenu); provider.addMenu(mockMenu);
		provider.addMenu(mockMenu); provider.addMenu(mockMenu);
		provider.addMenu(mockMenu); provider.addMenu(mockMenu);
		provider.addMenu(mockMenu); provider.addMenu(mockMenu);
		provider.addMenu(mockMenu); provider.addMenu(mockMenu);
		provider.addMenu(mockMenu); provider.addMenu(mockMenu);
		provider.addMenu(mockMenu); provider.addMenu(mockMenu);
		provider.addMenu(mockMenu); provider.addMenu(mockMenu);
		provider.addMenu(mockMenu21);
	}

	@Test
	public void providerWithMenuWithNameXLooksForMenuWithNameXThenReturnMenu() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu = mock(Menu.class);
		Mockito.when(mockMenu.hasName("Pizza especial Liz")).thenReturn(true);

		provider.addMenu(mockMenu);
		assertEquals(mockMenu, provider.searchMenu("Pizza especial Liz"));
	}

	@Test(expected = ElementNotFoundException.class)
	public void providerWithMenuWithNameXLooksForMenuWithNameYThrowsException() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu = mock(Menu.class);
		Mockito.when(mockMenu.hasName("Pizza especial Liz")).thenReturn(false);

		provider.addMenu(mockMenu);
		provider.searchMenu("Pizza especial Liz");
	}

	@Test
	public void providerWithMenuWithNameXThenRemoveMenuWithNameX() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu = mock(Menu.class);
		Mockito.when(mockMenu.hasName("Pizza especial Liz")).thenReturn(true);

		provider.addMenu(mockMenu);
		provider.removeMenuWithName("Pizza especial Liz");
		assertFalse(provider.getCurrentMenus().contains(mockMenu));
	}

	@Test(expected = ElementNotFoundException.class)
	public void providerWithMenuWithNameXThenRemoveMenuWithNameYThrowsException() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu = mock(Menu.class);
		Mockito.when(mockMenu.hasName("Pizza especial Liz")).thenReturn(false);

		provider.addMenu(mockMenu);
		provider.removeMenuWithName("Pizza especial Liz");
	}

	@Test
	public void providerWithMenuXThenRemoveMenuX() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu = mock(Menu.class);

		provider.addMenu(mockMenu);
		provider.removeMenu(mockMenu);
		assertFalse(provider.getCurrentMenus().contains(mockMenu));
	}

	@Test
	public void providerWithMenuXThenRemoveMenuYThenDoesNothing() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu1 = mock(Menu.class);
		Menu mockMenu2 = mock(Menu.class);
		provider.addMenu(mockMenu1);

		assertTrue(provider.getCurrentMenus().contains(mockMenu1));
		assertEquals(1, provider.getCurrentMenus().size());

		provider.removeMenu(mockMenu2);

		assertTrue(provider.getCurrentMenus().contains(mockMenu1));
		assertEquals(1, provider.getCurrentMenus().size());
	}

	@Test
	public void providerWithoutCurrentMenuThenRemoveMenuXThenDoesNothing() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu = mock(Menu.class);

		assertEquals(0, provider.getCurrentMenus().size());
		provider.removeMenu(mockMenu);
		assertEquals(0, provider.getCurrentMenus().size());
	}

	@Test
	public void providerWithoutCurrentMenuThenAskIfAnyHasNameXThenReturnFalse() {
		provider = ProviderBuilder.aProvider().build();
		assertFalse(provider.anyCurrentMenuHasName("Pizza especial Liz"));
	}

	@Test
	public void providerWithMenuWithNameZThenAskIfAnyHasNameXThenReturnFalse() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu = mock(Menu.class);
		Mockito.when(mockMenu.hasName("Pizza especial Liz")).thenReturn(false);
		provider.addMenu(mockMenu);
		assertFalse(provider.anyCurrentMenuHasName("Pizza especial Liz"));
	}

	@Test
	public void providerWithMenuWithNameXThenAskIfAnyHasNameXThenReturnTrue() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu = mock(Menu.class);
		Mockito.when(mockMenu.hasName("Pizza especial Liz")).thenReturn(true);
		provider.addMenu(mockMenu);
		assertTrue(provider.anyCurrentMenuHasName("Pizza especial Liz"));
	}

	@Test
	public void providerWithMenuWithNameXThenEditMenuWithNameX() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu1 = mock(Menu.class);
		Menu mockMenu2 = mock(Menu.class);
		Mockito.when(mockMenu1.hasName("Pizza especial Liz")).thenReturn(true);
		Mockito.when(mockMenu2.getName()).thenReturn("Pizza de mozza Liz");
		Mockito.when(mockMenu2.hasName("Pizza de mozza Liz")).thenReturn(true);
		provider.addMenu(mockMenu1);

		provider.editMenu("Pizza especial Liz", mockMenu2);
		assertFalse(provider.getCurrentMenus().contains(mockMenu1));
		assertTrue(provider.getCurrentMenus().contains(mockMenu2));
		assertTrue(provider.anyCurrentMenuHasName("Pizza de mozza Liz"));
	}

	@Test(expected = ElementNotFoundException.class)
	public void providerWithoutCurrentMenusEditMenuWithNameXThrowsException() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu = mock(Menu.class);
		Mockito.when(mockMenu.getName()).thenReturn("Pizza especial Liz");

		provider.editMenu("Pizza especial Liz", mockMenu);
	}
	
	@Test
	public void providerWithCurrentMenusThenAskForMenusWithNameMatchedWithXThenReturnEmptyList() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu1 = mock(Menu.class);
		Menu mockMenu2 = mock(Menu.class);
		Mockito.when(mockMenu1.hasNameMatchedWith("Pizza")).thenReturn(false);
		Mockito.when(mockMenu2.hasNameMatchedWith("Pizza")).thenReturn(false);
		
		provider.addMenu(mockMenu1);
		provider.addMenu(mockMenu2);
		
		assertTrue(provider.menusWithNameMatchedWith("Pizza").isEmpty());
	}
	
	@Test
	public void providerWithoutCurrentMenusThenAskForMenusWithNameMatchedWithXThenReturnEmptyList() {
		provider = ProviderBuilder.aProvider().build();
		
		assertTrue(provider.menusWithNameMatchedWith("Pizza").isEmpty());
	}
	
	@Test
	public void providerWithCurrentMenusThenAskForMenusWithNameMatchedWithXThenReturnAList() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu1 = mock(Menu.class);
		Menu mockMenu2 = mock(Menu.class);
		Mockito.when(mockMenu1.hasNameMatchedWith("Pizza")).thenReturn(true);
		Mockito.when(mockMenu2.hasNameMatchedWith("Pizza")).thenReturn(false);
		
		provider.addMenu(mockMenu1);
		provider.addMenu(mockMenu2);
		
		assertTrue(provider.menusWithNameMatchedWith("Pizza").contains(mockMenu1));
		assertFalse(provider.menusWithNameMatchedWith("Pizza").contains(mockMenu2));
	}
	
	@Test
	public void providerWithCurrentMenusThenAskForMenusWithCategoryVeganoThenReturnEmptyList() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu1 = mock(Menu.class);
		Menu mockMenu2 = mock(Menu.class);
		Mockito.when(mockMenu1.hasCategory(Category.VEGANO)).thenReturn(false);
		Mockito.when(mockMenu2.hasCategory(Category.VEGANO)).thenReturn(false);
		
		provider.addMenu(mockMenu1);
		provider.addMenu(mockMenu2);
		
		assertTrue(provider.menusWithCategory(Category.VEGANO).isEmpty());
	}
	
	@Test
	public void providerWithCurrentMenusThenAskForMenusWithCategoryPizzaThenReturnAList() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu1 = mock(Menu.class);
		Menu mockMenu2 = mock(Menu.class);
		Mockito.when(mockMenu1.hasCategory(Category.PIZZA)).thenReturn(true);
		Mockito.when(mockMenu2.hasCategory(Category.PIZZA)).thenReturn(false);
		
		provider.addMenu(mockMenu1);
		provider.addMenu(mockMenu2);
		
		assertTrue(provider.menusWithCategory(Category.PIZZA).contains(mockMenu1));
		assertFalse(provider.menusWithCategory(Category.PIZZA).contains(mockMenu2));
	}
	
	@Test
	public void providerWithCurrentMenusThenAskForMenusWithLocationQuilmesThenReturnMenus() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu1 = mock(Menu.class);
		Menu mockMenu2 = mock(Menu.class);
		
		provider.addMenu(mockMenu1);
		provider.addMenu(mockMenu2);
		
		assertTrue(provider.menusWithLocation(City.QUILMES).contains(mockMenu1));
		assertTrue(provider.menusWithLocation(City.QUILMES).contains(mockMenu2));
		assertEquals(2, provider.menusWithLocation(City.QUILMES).size());
	}
	
	@Test
	public void providerWithCurrentMenusThenAskForMenusWithLocationLuisGuillonThenReturnEmptyList() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu1 = mock(Menu.class);
		Menu mockMenu2 = mock(Menu.class);
		
		provider.addMenu(mockMenu1);
		provider.addMenu(mockMenu2);
		
		assertTrue(provider.menusWithLocation(City.LUIS_GUILLON).isEmpty());
	}
	
	@Test
	public void providerWithEmptyOrdersThenAddOrderCorrectly() {
		provider = ProviderBuilder.aProvider().build();
		Order mockOrder = mock(Order.class);
		User mockUser = mock(User.class);
		
		provider.addOrder(mockUser, mockOrder);
		assertEquals(1, provider.getOrders().size());
		assertTrue(provider.getOrders().get(0).hasUser(mockUser));
	}
	
	@Test
	public void providerWithXUserOrdersThenAddXUserOrderCorrectly() {
		provider = ProviderBuilder.aProvider().build();
		Order mockOrder1 = mock(Order.class);
		Order mockOrder2 = mock(Order.class);
		User mockUser = mock(User.class);
		
		provider.addOrder(mockUser, mockOrder1);
		provider.addOrder(mockUser, mockOrder2);
		
		assertEquals(1, provider.getOrders().size());
		assertTrue(provider.getOrders().get(0).getOrders().contains(mockOrder2));
	}
	
	@Test
	public void providerWithNameViandaLizThenAskIfHasNameViandaLizThenReturnTrue()
	{
		provider = ProviderBuilder.aProvider().withName("ViandaLiz").build();
		assertTrue(provider.hasName("ViandaLiz"));
	}
	
	@Test
	public void providerWithNameViandaLizThenAskIfHasNameViandaMelThenReturnFalse()
	{
		provider = ProviderBuilder.aProvider().withName("ViandaLiz").build();
		assertFalse(provider.hasName("ViandaMel"));
	}

	@Test
	public void providerAddsMoneyCorrectly(){
		provider = ProviderBuilder.aProvider().build();
		provider.addMoney(BigDecimal.valueOf(100));
		assertEquals(BigDecimal.valueOf(100), provider.getBalance());
	}

	@Test
	public void providerWith1MenuCancelMenuSuccessfully() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu = mock(Menu.class);
		provider.addMenu(mockMenu);
		provider.cancelMenu(mockMenu);

		assertFalse(provider.getCurrentMenus().contains(mockMenu));
		assertTrue(provider.getCurrentMenus().isEmpty());
		assertEquals(new Integer(1), provider.getMenusRemoved());
	}
}
