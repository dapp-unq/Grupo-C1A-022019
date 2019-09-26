package ar.edu.unq.desapp.grupoa.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import ar.edu.unq.desapp.grupoa.model.exceptions.CurrentMenuQuantityException;
import ar.edu.unq.desapp.grupoa.model.exceptions.DescriptionLengthException;
import ar.edu.unq.desapp.grupoa.model.exceptions.EmptyServiceHoursDaysException;
import ar.edu.unq.desapp.grupoa.model.exceptions.EmptyStringException;
import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidEmailException;
import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidPhoneNumberException;
import ar.edu.unq.desapp.grupoa.model.exceptions.MenuNotFoundException;
import ar.edu.unq.desapp.grupoa.model.exceptions.MenuWithRepeatedNameException;

public class ProviderTest {

	private Provider provider;
	private List<ServiceHours> openingHours;

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
		provider = ProviderBuilder.aProvider().withCity(City.Quilmes).build();
		assertEquals(City.Quilmes, provider.getCity());
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
		openingHours = new ArrayList<ServiceHours>();
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
		assertTrue(provider.getCurrentMenu().contains(mockMenu));
	}

	@Test(expected = MenuWithRepeatedNameException.class)
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

		provider.addMenu(mockMenu);
		provider.addMenu(mockMenu);
		provider.addMenu(mockMenu);
		provider.addMenu(mockMenu);
		provider.addMenu(mockMenu);
		provider.addMenu(mockMenu);
		provider.addMenu(mockMenu);
		provider.addMenu(mockMenu);
		provider.addMenu(mockMenu);
		provider.addMenu(mockMenu);
		provider.addMenu(mockMenu);
		provider.addMenu(mockMenu);
		provider.addMenu(mockMenu);
		provider.addMenu(mockMenu);
		provider.addMenu(mockMenu);
		provider.addMenu(mockMenu);
		provider.addMenu(mockMenu);
		provider.addMenu(mockMenu);
		provider.addMenu(mockMenu);
		provider.addMenu(mockMenu);
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

	@Test(expected = MenuNotFoundException.class)
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
		assertFalse(provider.getCurrentMenu().contains(mockMenu));
	}

	@Test(expected = MenuNotFoundException.class)
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
		assertFalse(provider.getCurrentMenu().contains(mockMenu));
	}

	@Test
	public void providerWithMenuXThenRemoveMenuYThenDoesNothing() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu1 = mock(Menu.class);
		Menu mockMenu2 = mock(Menu.class);
		provider.addMenu(mockMenu1);

		assertTrue(provider.getCurrentMenu().contains(mockMenu1));
		assertEquals(1, provider.getCurrentMenu().size());

		provider.removeMenu(mockMenu2);

		assertTrue(provider.getCurrentMenu().contains(mockMenu1));
		assertEquals(1, provider.getCurrentMenu().size());
	}

	@Test
	public void providerWithoutCurrentMenuThenRemoveMenuXThenDoesNothing() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu = mock(Menu.class);

		assertEquals(0, provider.getCurrentMenu().size());
		provider.removeMenu(mockMenu);
		assertEquals(0, provider.getCurrentMenu().size());
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
		assertFalse(provider.getCurrentMenu().contains(mockMenu1));
		assertTrue(provider.getCurrentMenu().contains(mockMenu2));
		assertTrue(provider.anyCurrentMenuHasName("Pizza de mozza Liz"));
	}

	@Test(expected = MenuNotFoundException.class)
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
		Mockito.when(mockMenu1.hasCategory(Category.Vegano)).thenReturn(false);
		Mockito.when(mockMenu2.hasCategory(Category.Vegano)).thenReturn(false);
		
		provider.addMenu(mockMenu1);
		provider.addMenu(mockMenu2);
		
		assertTrue(provider.menusWithCategory(Category.Vegano).isEmpty());
	}
	
	@Test
	public void providerWithCurrentMenusThenAskForMenusWithCategoryPizzaThenReturnAList() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu1 = mock(Menu.class);
		Menu mockMenu2 = mock(Menu.class);
		Mockito.when(mockMenu1.hasCategory(Category.Pizza)).thenReturn(true);
		Mockito.when(mockMenu2.hasCategory(Category.Pizza)).thenReturn(false);
		
		provider.addMenu(mockMenu1);
		provider.addMenu(mockMenu2);
		
		assertTrue(provider.menusWithCategory(Category.Pizza).contains(mockMenu1));
		assertFalse(provider.menusWithCategory(Category.Pizza).contains(mockMenu2));
	}
	
	@Test
	public void providerWithCurrentMenusThenAskForMenusWithLocationQuilmesThenReturnMenus() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu1 = mock(Menu.class);
		Menu mockMenu2 = mock(Menu.class);
		
		provider.addMenu(mockMenu1);
		provider.addMenu(mockMenu2);
		
		assertTrue(provider.menusWithLocation(City.Quilmes).contains(mockMenu1));
		assertTrue(provider.menusWithLocation(City.Quilmes).contains(mockMenu2));
		assertEquals(2, provider.menusWithLocation(City.Quilmes).size());
	}
	
	@Test
	public void providerWithCurrentMenusThenAskForMenusWithLocationLuisGuillonThenReturnEmptyList() {
		provider = ProviderBuilder.aProvider().build();
		Menu mockMenu1 = mock(Menu.class);
		Menu mockMenu2 = mock(Menu.class);
		
		provider.addMenu(mockMenu1);
		provider.addMenu(mockMenu2);
		
		assertTrue(provider.menusWithLocation(City.Luis_Guillon).isEmpty());
	}
	
}
