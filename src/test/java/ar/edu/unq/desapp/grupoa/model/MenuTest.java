package ar.edu.unq.desapp.grupoa.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import ar.edu.unq.desapp.grupoa.model.exceptions.DataIncompleteException;
import ar.edu.unq.desapp.grupoa.model.exceptions.DescriptionLengthException;
import ar.edu.unq.desapp.grupoa.model.exceptions.EmptyListException;
import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidRankingException;
import ar.edu.unq.desapp.grupoa.model.exceptions.IrrationalAmountException;
import ar.edu.unq.desapp.grupoa.model.exceptions.IrrationalPriceException;
import ar.edu.unq.desapp.grupoa.model.exceptions.NameLengthException;
import ar.edu.unq.desapp.grupoa.model.exceptions.OrderDateException;

public class MenuTest {
	private Menu menu;

	@Test(expected = NullPointerException.class)
	public void menuCreationWithNullNameThrowsException() {
		menu = MenuBuilder.aMenu().withName(null).build();
	}

	@Test(expected = NameLengthException.class)
	public void menuCreationWith3LenghtNameThrowsException() {
		menu = MenuBuilder.aMenu().withName("Pzl").build();
	}

	@Test(expected = NameLengthException.class)
	public void menuCreationWith49LenghtNameThrowsException() {
		menu = MenuBuilder.aMenu().withName("Pizza especial de mozzarella con aceitunas verdes").build();
	}

	@Test
	public void menuCreationWithValidName() {
		menu = MenuBuilder.aMenu().withName("Pizza especial Liz").build();
		assertEquals("Pizza especial Liz", menu.getName());
	}

	@Test(expected = NullPointerException.class)
	public void menuWithNullNameThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setName(null);
	}

	@Test(expected = NameLengthException.class)
	public void menuWith3LenghtNameThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setName("Pzz");
	}

	@Test(expected = NameLengthException.class)
	public void menuWith49LenghtNameThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setName("Pizza especial de mozzarella con aceitunas verdes");
	}

	@Test
	public void menuWithValidateName() {
		menu = MenuBuilder.aMenu().build();
		menu.setName("Pizza de mozza especial de Liz");
		assertEquals("Pizza de mozza especial de Liz", menu.getName());
	}

	@Test(expected = NullPointerException.class)
	public void menuCreationWithNullDescriptionThrowsException() {
		menu = MenuBuilder.aMenu().withDescription(null).build();
	}

	@Test(expected = DescriptionLengthException.class)
	public void menuCreationWith19LenghtDescriptionThrowsException() {
		menu = MenuBuilder.aMenu().withDescription("Pizza de mozzarella").build();
	}

	@Test(expected = DescriptionLengthException.class)
	public void menuCreationWith41LenghtDescriptionThrowsException() {
		menu = MenuBuilder.aMenu().withDescription("Pizza de mozzarella con extra de morrones").build();
	}

	@Test
	public void menuCreationWithValidDescription() {
		menu = MenuBuilder.aMenu().withDescription("Pizza de mozza con lluvia de aceitunas").build();
		assertEquals("Pizza de mozza con lluvia de aceitunas", menu.getDescription());
	}

	@Test(expected = NullPointerException.class)
	public void menuWithNullDescriptionThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setDescription(null);
	}

	@Test(expected = DescriptionLengthException.class)
	public void menuWith19LenghtDescriptionThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setDescription("Pizza de mozzarella");
	}

	@Test(expected = DescriptionLengthException.class)
	public void menuWith41LenghtDescriptionThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setDescription("Pizza de mozzarella con extra de morrones");
	}

	@Test
	public void menuWithValidateDescription() {
		menu = MenuBuilder.aMenu().build();
		menu.setDescription("Pizza de mozza con lluvia de aceitunas");
		assertEquals("Pizza de mozza con lluvia de aceitunas", menu.getDescription());
	}

	@Test(expected = NullPointerException.class)
	public void menuCreationWithNullCategoriesThrowsException() {
		menu = MenuBuilder.aMenu().withCategory(null).build();
	}

	@Test(expected = EmptyListException.class)
	public void menuCreationWithEmptyCategoriesThrowsException() {
		menu = MenuBuilder.aMenu().withCategory(new ArrayList<Category>()).build();
	}

	@Test
	public void menuCreationWithValidCategory() {
		List<Category> categories = new ArrayList<Category>();
		categories.add(Category.Pizza);
		menu = MenuBuilder.aMenu().withCategory(categories).build();
		assertEquals(categories, menu.getCategory());
	}

	@Test(expected = NullPointerException.class)
	public void menuWithNullCategoryThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setCategory(null);
	}

	@Test(expected = EmptyListException.class)
	public void menuWithEmptyCategoryNameThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setCategory(new ArrayList<Category>());
	}

	@Test
	public void menuWithValidCategory() {
		List<Category> categories = new ArrayList<Category>();
		categories.add(Category.Pizza);
		menu = MenuBuilder.aMenu().build();
		menu.setCategory(categories);
		assertEquals(categories, menu.getCategory());
	}

	@Test
	public void menuCreationWith0DeliveryPrice() {
		menu = MenuBuilder.aMenu().withDeliveryPrice(0).build();
		assertEquals(new Integer(0), menu.getDeliveryPrice());
	}

	@Test(expected = IrrationalPriceException.class)
	public void menuCreationWith50DeliveryPriceThrowsException() {
		menu = MenuBuilder.aMenu().withDeliveryPrice(50).build();
	}

	@Test
	public void menuCreationWithValidNullDeliveryPrice() {
		menu = MenuBuilder.aMenu().withDeliveryPrice(40).build();
		assertEquals(new Integer(40), menu.getDeliveryPrice());
	}

	@Test
	public void menuCreationWithValidDeliveryPrice() {
		menu = MenuBuilder.aMenu().withDeliveryPrice(40).build();
		assertEquals(new Integer(40), menu.getDeliveryPrice());
	}

	@Test(expected = IrrationalPriceException.class)
	public void menuWith5DeliveryPriceThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setDeliveryPrice(5);
	}

	@Test(expected = IrrationalPriceException.class)
	public void menuWith45DeliveryPriceThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setDeliveryPrice(45);
	}

	@Test(expected = NullPointerException.class)
	public void menuWithValidateNullDeliveryPriceThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setDeliveryPrice(null);
	}

	@Test
	public void menuWithValidateDeliveryPrice() {
		menu = MenuBuilder.aMenu().build();
		menu.setDeliveryPrice(10);
		assertEquals(new Integer(10), menu.getDeliveryPrice());
	}

	@Test
	public void menuWithValidateEmptyDeliveryPrice() {
		menu = MenuBuilder.aMenu().build();
		menu.setDeliveryPrice(0);
		assertEquals(new Integer(0), menu.getDeliveryPrice());
	}

	@Test(expected = NullPointerException.class)
	public void menuCreationWithNullEfectiveDatesThrowsException() {
		menu = MenuBuilder.aMenu().withEfectiveDate(null).build();
	}

	@Test(expected = EmptyListException.class)
	public void menuCreationWithEmptyEfectiveDatesThrowsException() {
		menu = MenuBuilder.aMenu().withEfectiveDate(new ArrayList<GregorianCalendar>()).build();
	}

	@Test(expected = DataIncompleteException.class)
	public void menuCreationWithIncompleteEfectiveDatesThrowsException() {
		List<GregorianCalendar> efectiveDates = new ArrayList<GregorianCalendar>();
		efectiveDates.add(new GregorianCalendar(2019, 5, 10));
		menu = MenuBuilder.aMenu().withEfectiveDate(efectiveDates).build();
	}

	@Test
	public void menuCreationWithValidEfectiveDates() {
		List<GregorianCalendar> efectiveDates = new ArrayList<GregorianCalendar>();
		efectiveDates.add(new GregorianCalendar(2019, 5, 10));
		efectiveDates.add(new GregorianCalendar(2019, 10, 10));
		menu = MenuBuilder.aMenu().withEfectiveDate(efectiveDates).build();
		assertEquals(efectiveDates, menu.getEfectiveDate());
	}

	@Test(expected = NullPointerException.class)
	public void menuWithNullEfectiveDatesThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setEfectiveDate(null);
	}

	@Test(expected = EmptyListException.class)
	public void menuWithEmptyEfectiveDatesThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setEfectiveDate(new ArrayList<GregorianCalendar>());
	}

	@Test(expected = DataIncompleteException.class)
	public void menuWithIncompleteEfectiveDatesThrowsException() {
		List<GregorianCalendar> efectiveDates = new ArrayList<GregorianCalendar>();
		efectiveDates.add(new GregorianCalendar(0, 0, 0));
		efectiveDates.add(new GregorianCalendar(0, 0, 0));
		efectiveDates.add(new GregorianCalendar(0, 0, 0));
		menu = MenuBuilder.aMenu().build();
		menu.setEfectiveDate(efectiveDates);
	}

	@Test
	public void menuWithValidateEfectiveDates() {
		List<GregorianCalendar> efectiveDates = new ArrayList<GregorianCalendar>();
		efectiveDates.add(new GregorianCalendar(2019, 5, 10));
		efectiveDates.add(new GregorianCalendar(2019, 10, 10));
		menu = MenuBuilder.aMenu().build();
		menu.setEfectiveDate(efectiveDates);
		assertEquals(efectiveDates, menu.getEfectiveDate());
	}

	@Test(expected = NullPointerException.class)
	public void menuCreationWithNullDeliverySchedulesThrowsException() {
		menu = MenuBuilder.aMenu().withDeliverySchedules(null).build();
	}

	@Test(expected = EmptyListException.class)
	public void menuCreationWithEmptyDeliverySchedulesThrowsException() {
		menu = MenuBuilder.aMenu().withDeliverySchedules(new ArrayList<LocalTime>()).build();
	}

	@Test(expected = DataIncompleteException.class)
	public void menuCreationWithIncompleteDeliverySchedulesThrowsException() {
		List<LocalTime> deliverySchedules = new ArrayList<LocalTime>();
		deliverySchedules.add(LocalTime.of(12, 00));
		menu = MenuBuilder.aMenu().withDeliverySchedules(deliverySchedules).build();
	}

	@Test
	public void menuCreationWithValidDeliverySchedules() {
		List<LocalTime> deliverySchedules = new ArrayList<LocalTime>();
		deliverySchedules.add(LocalTime.of(12, 00));
		deliverySchedules.add(LocalTime.of(22, 30));
		menu = MenuBuilder.aMenu().withDeliverySchedules(deliverySchedules).build();
		assertEquals(deliverySchedules, menu.getDeliverySchedules());
	}

	@Test(expected = NullPointerException.class)
	public void menuWithNullDeliverySchedulesThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setDeliverySchedules(null);
	}

	@Test(expected = EmptyListException.class)
	public void menuWithEmptyDeliverySchedulesThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setDeliverySchedules(new ArrayList<LocalTime>());
	}

	@Test(expected = DataIncompleteException.class)
	public void menuWithIncompleteDeliverySchedulesThrowsException() {
		List<GregorianCalendar> efectiveDates = new ArrayList<GregorianCalendar>();
		efectiveDates.add(new GregorianCalendar(0, 0, 0));
		efectiveDates.add(new GregorianCalendar(0, 0, 0));
		efectiveDates.add(new GregorianCalendar(0, 0, 0));
		menu = MenuBuilder.aMenu().build();
		menu.setEfectiveDate(efectiveDates);
	}

	@Test
	public void menuWithValidateDeliverySchedules() {
		List<LocalTime> deliverySchedules = new ArrayList<LocalTime>();
		deliverySchedules.add(LocalTime.of(12, 00));
		deliverySchedules.add(LocalTime.of(22, 30));
		menu = MenuBuilder.aMenu().build();
		menu.setDeliverySchedules(deliverySchedules);
		assertEquals(deliverySchedules, menu.getDeliverySchedules());
	}

	@Test(expected = NullPointerException.class)
	public void menuCreationWithNullAverigeDeliveryTimeThrowsException() {
		menu = MenuBuilder.aMenu().withAverigeDeliveryTime(null).build();
	}

	@Test
	public void menuCreationWithValidAverigeDeliveryTime() {
		menu = MenuBuilder.aMenu().withAverigeDeliveryTime(LocalTime.of(0, 30)).build();
		assertEquals(LocalTime.of(0, 30), menu.getAverigeDeliveryTime());
	}

	@Test(expected = NullPointerException.class)
	public void menuWithNullAverigeDeliveryTimeThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setAverigeDeliveryTime(null);
	}

	@Test
	public void menuWithValidateAverigeDeliveryTime() {
		menu = MenuBuilder.aMenu().build();
		menu.setAverigeDeliveryTime(LocalTime.of(1, 10));
		assertEquals(LocalTime.of(1, 10), menu.getAverigeDeliveryTime());
	}

	@Test(expected = NullPointerException.class)
	public void menuCreationWithNullPriceThrowsException() {
		menu = MenuBuilder.aMenu().withPrice(null).build();
	}

	@Test(expected = IrrationalPriceException.class)
	public void menuCreationWithIrrationalPriceThrowsException() {
		menu = MenuBuilder.aMenu().withPrice(-5).build();
	}

	@Test
	public void menuCreationWithValidPrice() {
		menu = MenuBuilder.aMenu().withPrice(100).build();
		assertEquals(new Integer(100), menu.getPrice());
	}

	@Test(expected = NullPointerException.class)
	public void menuWithNullPriceThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setPrice(null);
	}

	@Test(expected = IrrationalPriceException.class)
	public void menuWith0PriceThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setPrice(0);
	}

	@Test
	public void menuWithValidatePrice() {
		menu = MenuBuilder.aMenu().build();
		menu.setPrice(150);
		assertEquals(new Integer(150), menu.getPrice());
	}

	@Test(expected = NullPointerException.class)
	public void menuCreationWithNullDailyStockThrowsException() {
		menu = MenuBuilder.aMenu().withDailyStock(null).build();
	}

	@Test(expected = IrrationalAmountException.class)
	public void menuCreationWithIrrationalDailyStockThrowsException() {
		menu = MenuBuilder.aMenu().withDailyStock(-1).build();
	}

	@Test
	public void menuCreationWithValidDailyStock() {
		menu = MenuBuilder.aMenu().withDailyStock(50).build();
		assertEquals(new Integer(50), menu.getDailyStock());
	}

	@Test(expected = NullPointerException.class)
	public void menuWithNullDailyStockThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setDailyStock(null);
	}

	@Test(expected = IrrationalAmountException.class)
	public void menuWith0DailyStockThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setDailyStock(0);
	}

	@Test
	public void menuWithValidateDailyStock() {
		menu = MenuBuilder.aMenu().build();
		menu.setDailyStock(65);
		assertEquals(new Integer(65), menu.getDailyStock());
	}

	@Test(expected = NullPointerException.class)
	public void menuCreationWithNullOffer1ThrowsException() {
		menu = MenuBuilder.aMenu().withOffer1(null).build();
	}

	@Test(expected = IrrationalAmountException.class)
	public void menuCreationWith50DailyStockAnd51MinQuantityOffer1ThrowsException() {
		menu = MenuBuilder.aMenu().withDailyStock(50).withOffer1MinQuantity(51).build();
	}

	@Test
	public void menuCreationWith50DailyStockAndValid10MinQuantityOffer1() {
		menu = MenuBuilder.aMenu().withDailyStock(50).withOffer1MinQuantity(10).build();
		assertEquals(new Integer(10), menu.getOffer1().getQuantity());
	}

	@Test(expected = IrrationalAmountException.class)
	public void menuCreationWith50DailyStockAnd5MinQuantityOffer1ThrowsException() {
		menu = MenuBuilder.aMenu().withDailyStock(50).withOffer1MinQuantity(5).build();
	}

	@Test(expected = IrrationalAmountException.class)
	public void menuCreationWith100DailyStockAnd71MinQuantityOffer1ThrowsException() {
		menu = MenuBuilder.aMenu().withDailyStock(100).withOffer1MinQuantity(71).build();
	}

	@Test(expected = IrrationalPriceException.class)
	public void menuCreationWith10PriceMenuAnd20PriceOffer1ThrowsException() {
		menu = MenuBuilder.aMenu().withPrice(10).withOffer1Price(20).build();
	}

	@Test
	public void menuCreationWith10PriceMenuAndValid8PriceOffer1() {
		menu = MenuBuilder.aMenu().withPrice(10).withOffer1Price(8).build();
		assertEquals(new Integer(8), menu.getOffer1().getPrice());
	}

	@Test(expected = IrrationalPriceException.class)
	public void menuCreationWith10PriceMenuAndIrrationalPriceOffer1ThrowsException() {
		menu = MenuBuilder.aMenu().withPrice(10).withOffer1Price(-1).build();
	}

	@Test(expected = IrrationalPriceException.class)
	public void menuCreationWith10PriceMenuAnd1001PriceOffer1ThrowsException() {
		menu = MenuBuilder.aMenu().withPrice(10).withOffer1Price(1001).build();
	}

	@Test(expected = NullPointerException.class)
	public void menuWithNullOffer1ThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setOffer1(null);
	}

	@Test(expected = IrrationalAmountException.class)
	public void menuWith50DailyStockAnd51MinQuantityOffer1ThrowsException() {
		menu = MenuBuilder.aMenu().withDailyStock(50).withPrice(10).build();
		menu.setOffer1(new Offer(51, 8));
	}

	@Test
	public void menuWith50DailyStock10PriceMenuAndValid13MinQuantityOffer1() {
		menu = MenuBuilder.aMenu().withDailyStock(50).withPrice(10).build();
		menu.setOffer1(new Offer(13, 8));
		assertEquals(new Integer(13), menu.getOffer1().getQuantity());
	}

	@Test(expected = IrrationalAmountException.class)
	public void menuWith50DailyStock10PriceMenuAnd9MinQuantityOffer1ThrowsException() {
		menu = MenuBuilder.aMenu().withDailyStock(50).withPrice(10).build();
		menu.setOffer1(new Offer(9, 8));
	}

	@Test(expected = IrrationalAmountException.class)
	public void menuWith100DailyStock10PriceMenuAnd71MinQuantityOffer1ThrowsException() {
		menu = MenuBuilder.aMenu().withDailyStock(100).withPrice(10).build();
		menu.setOffer1(new Offer(71, 8));
	}

	@Test(expected = IrrationalPriceException.class)
	public void menuWith50DailyStock10PriceAnd20PriceOffer1ThrowsException() {
		menu = MenuBuilder.aMenu().withDailyStock(50).withPrice(10).build();
		menu.setOffer1(new Offer(10, 20));
	}

	@Test
	public void menuWith50DailyStock10PriceAndValid20PriceOffer1() {
		menu = MenuBuilder.aMenu().withDailyStock(50).withPrice(10).build();
		menu.setOffer1(new Offer(15, 8));
		assertEquals(new Integer(8), menu.getOffer1().getPrice());
	}

	@Test(expected = IrrationalPriceException.class)
	public void menuWith50DailyStock10PriceAndIrrationalPriceOffer1ThrowsException() {
		menu = MenuBuilder.aMenu().withDailyStock(50).withPrice(10).build();
		menu.setOffer1(new Offer(15, -2));
	}

	@Test(expected = IrrationalPriceException.class)
	public void menuWith50DailyStock10PriceAnd1001PriceOffer1ThrowsException() {
		menu = MenuBuilder.aMenu().withDailyStock(50).withPrice(10).build();
		menu.setOffer1(new Offer(15, 1001));
	}

	@Test(expected = NullPointerException.class)
	public void menuCreationWithNullOffer2ThrowsException() {
		menu = MenuBuilder.aMenu().withOffer2(null).build();
	}

	@Test(expected = IrrationalAmountException.class)
	public void menuCreationWith30DailyStockAnd50MinQuantityOffer2ThrowsException() {
		menu = MenuBuilder.aMenu().withDailyStock(30).withOffer2MinQuantity(50).build();
	}

	@Test
	public void menuCreationWith50DailyStockAndValid40MinQuantityOffer2() {
		menu = MenuBuilder.aMenu().withDailyStock(50).withOffer2MinQuantity(40).build();
		assertEquals(new Integer(40), menu.getOffer2().getQuantity());
	}

	@Test(expected = IrrationalAmountException.class)
	public void menuCreationWith50DailyStockAnd50MinQuantityOffer2ThrowsException() {
		menu = MenuBuilder.aMenu().withDailyStock(50).withOffer2MinQuantity(39).build();
	}

	@Test(expected = IrrationalAmountException.class)
	public void menuCreationWith50DailyStockAnd151MinQuantityOffer2ThrowsException() {
		menu = MenuBuilder.aMenu().withDailyStock(50).withOffer2MinQuantity(151).build();
	}

	@Test(expected = IrrationalPriceException.class)
	public void menuCreationWith15PriceMenuAnd2PriceOffer2ThrowsException() {
		menu = MenuBuilder.aMenu().withPrice(15).withOffer2Price(20).build();
	}

	@Test
	public void menuCreationWith15PriceMenuAndValid5PriceOffer2() {
		menu = MenuBuilder.aMenu().withPrice(15).withOffer2Price(5).build();
		assertEquals(new Integer(5), menu.getOffer2().getPrice());
	}

	@Test(expected = IrrationalPriceException.class)
	public void menuCreationWith15PriceMenuAndIrrationalPriceOffer2ThrowsException() {
		menu = MenuBuilder.aMenu().withPrice(15).withOffer2Price(-1).build();
	}

	@Test(expected = IrrationalPriceException.class)
	public void menuCreationWith15PriceMenuAnd1001PriceOffer2ThrowsException() {
		menu = MenuBuilder.aMenu().withPrice(15).withOffer2Price(1001).build();
	}

	@Test(expected = NullPointerException.class)
	public void menuWithNullOffer2ThrowsException() {
		menu = MenuBuilder.aMenu().build();
		menu.setOffer2(null);
	}

	@Test(expected = IrrationalAmountException.class)
	public void menuWith50DailyStockAnd51MinQuantityOffer2ThrowsException() {
		menu = MenuBuilder.aMenu().withDailyStock(50).withPrice(15).build();
		menu.setOffer2(new Offer(51, 10));
	}

	@Test
	public void menuWith50DailyStock15PriceMenuAndValid40MinQuantityOffer2() {
		menu = MenuBuilder.aMenu().withDailyStock(50).withPrice(15).build();
		menu.setOffer2(new Offer(40, 5));
		assertEquals(new Integer(40), menu.getOffer2().getQuantity());
	}

	@Test(expected = IrrationalAmountException.class)
	public void menuWith50DailyStock15PriceMenuAnd39MinQuantityOffer2ThrowsException() {
		menu = MenuBuilder.aMenu().withDailyStock(50).withPrice(15).build();
		menu.setOffer2(new Offer(39, 5));
	}

	@Test(expected = IrrationalAmountException.class)
	public void menuWith200DailyStock15PriceMenuAnd151MinQuantityOffer2ThrowsException() {
		menu = MenuBuilder.aMenu().withDailyStock(200).withPrice(15).build();
		menu.setOffer2(new Offer(151, 5));
	}

	@Test(expected = IrrationalPriceException.class)
	public void menuWith50DailyStock10PriceAnd20PriceOffer2ThrowsException() {
		menu = MenuBuilder.aMenu().withDailyStock(50).withPrice(10).build();
		menu.setOffer2(new Offer(40, 20));
	}

	@Test
	public void menuWith50DailyStock10PriceAndValid20PriceOffer2() {
		menu = MenuBuilder.aMenu().withDailyStock(50).withPrice(10).build();
		menu.setOffer2(new Offer(40, 5));
		assertEquals(new Integer(5), menu.getOffer2().getPrice());
	}

	@Test(expected = IrrationalPriceException.class)
	public void menuWith50DailyStock10PriceAndIrrationalPriceOffer2ThrowsException() {
		menu = MenuBuilder.aMenu().withDailyStock(50).withPrice(10).build();
		menu.setOffer2(new Offer(40, -2));
	}

	@Test(expected = IrrationalPriceException.class)
	public void menuWith50DailyStock10PriceAnd1001PriceOffer2ThrowsException() {
		menu = MenuBuilder.aMenu().withDailyStock(50).withPrice(10).build();
		menu.setOffer2(new Offer(40, 1001));
	}

	@Test(expected = IrrationalAmountException.class)
	public void menuWith50kMinQuantityOffer1SetTo45QuantityOffer2ThrowsException() {
		Offer offer1 = new Offer(50, 8);
		Offer offer2 = new Offer(60, 6);

		menu = MenuBuilder.aMenu().withDailyStock(100).withPrice(10).withOffer1(offer1).withOffer2(offer2).build();

		menu.setOffer2(new Offer(45, 6));
	}

	@Test(expected = IrrationalPriceException.class)
	public void menuWith10PriceOffer1Set15PriceOffer2ThrowsException() {
		Offer offer1 = new Offer(10, 10);
		Offer offer2 = new Offer(40, 5);

		menu = MenuBuilder.aMenu().withDailyStock(100).withPrice(20).withOffer1(offer1).withOffer2(offer2).build();

		menu.setOffer2(new Offer(40, 15));
	}

	@Test
	public void menuWith50MinQuantityAnd15PriceOffer1Set55MinQuantityAnd10PriceOffer2() {
		Offer offer1 = new Offer(50, 15);
		Offer offer2 = new Offer(60, 5);

		menu = MenuBuilder.aMenu().withDailyStock(100).withPrice(20).withOffer1(offer1).withOffer2(offer2).build();

		menu.setOffer2(new Offer(55, 10));

		assertEquals(new Integer(55), menu.getOffer2().getQuantity());
		assertEquals(new Integer(10), menu.getOffer2().getPrice());
	}

	@Test(expected = IrrationalAmountException.class)
	public void menuWith60MinQuantityOffer2SetTo65MinQuantityOffer1ThrowsException() {
		Offer offer1 = new Offer(50, 8);
		Offer offer2 = new Offer(60, 6);

		menu = MenuBuilder.aMenu().withDailyStock(100).withPrice(10).withOffer1(offer1).withOffer2(offer2).build();

		menu.setOffer1(new Offer(65, 6));
	}

	@Test(expected = IrrationalPriceException.class)
	public void menuWith10PriceOffer2Set5PriceOffer1ThrowsException() {
		Offer offer1 = new Offer(10, 15);
		Offer offer2 = new Offer(40, 10);

		menu = MenuBuilder.aMenu().withDailyStock(100).withPrice(20).withOffer1(offer1).withOffer2(offer2).build();

		menu.setOffer1(new Offer(10, 5));
	}

	@Test
	public void menuWith60MinQuantityAnd5PriceOffer2Set55MinQuantityAnd10PriceOffer1() {
		Offer offer1 = new Offer(50, 15);
		Offer offer2 = new Offer(60, 5);

		menu = MenuBuilder.aMenu().withDailyStock(100).withPrice(20).withOffer1(offer1).withOffer2(offer2).build();

		menu.setOffer1(new Offer(55, 10));
		assertEquals(new Integer(55), menu.getOffer1().getQuantity());
		assertEquals(new Integer(10), menu.getOffer1().getPrice());
	}

	@Test
	public void menuWithNameXAskIfHasNameXThenReturnTrue() {
		menu = MenuBuilder.aMenu().withName("Pizza especial Liz").build();
		assertTrue(menu.hasName("Pizza especial Liz"));
	}

	@Test
	public void menuWithNameXAskIfHasNameYThenReturnFalse() {
		menu = MenuBuilder.aMenu().withName("Pizza especial Liz").build();
		assertFalse(menu.hasName("Pizza de mozzarella"));
	}

	@Test
	public void menuWithNameXYZAskIfHasNameMatchedWithXYThenReturnTrue() {
		menu = MenuBuilder.aMenu().withName("Pizza especial Liz").build();
		assertTrue(menu.hasNameMatchedWith("Especial"));
	}

	@Test
	public void menuWithNameXYZAskIfHasNameMatchedWithABCThenReturnFalse() {
		menu = MenuBuilder.aMenu().withName("Pizza especial Liz").build();
		assertFalse(menu.hasNameMatchedWith("Mozzarella"));
	}

	@Test
	public void menuWithCategoryPizzaAskIfHasCategoryPizzaThenReturnTrue() {
		List<Category> categories = new ArrayList<Category>();
		categories.add(Category.Pizza);
		menu = MenuBuilder.aMenu().withCategory(categories).build();
		assertTrue(menu.hasCategory(Category.Pizza));
	}

	@Test
	public void menuWithCategoryPizzaAskIfHasCategoryCerbezaThenReturnFalse() {
		List<Category> categories = new ArrayList<Category>();
		categories.add(Category.Pizza);
		menu = MenuBuilder.aMenu().withCategory(categories).build();
		assertFalse(menu.hasCategory(Category.Cerbeza));
	}

	@Test
	public void menuWithoutRankingThenAddRanking5() {
		menu = MenuBuilder.aMenu().build();
		menu.rankIt(5);
		assertTrue(menu.getRanking().contains(5));
	}

	@Test(expected = InvalidRankingException.class)
	public void menuWithoutRankingThenAddRanking0ThrowException() {
		menu = MenuBuilder.aMenu().build();
		menu.rankIt(0);
	}

	@Test(expected = InvalidRankingException.class)
	public void menuWithoutRankingThenAddRanking10ThrowException() {
		menu = MenuBuilder.aMenu().build();
		menu.rankIt(10);
	}

	@Test(expected = IrrationalAmountException.class)
	public void menuWith100DailyStockThenAsFor101MenusOrderedThrowException() {
		menu = MenuBuilder.aMenu().withDailyStock(100).build();
		menu.validationNumberMenuOrdered(101);
	}

	@Test(expected = OrderDateException.class)
	public void menuWithOrderDayAfter48hsDeliveryDayThrowException() {
		menu = MenuBuilder.aMenu().build();
		GregorianCalendar orderDay = new GregorianCalendar(2019, 8, 30, 12, 00);
		GregorianCalendar deliveryDay = new GregorianCalendar(2019, 9, 2, 11, 30);
		menu.validationDateDeliveryMenuOrdered(orderDay, deliveryDay);
	}

	@Test(expected = OrderDateException.class)
	public void menuWithOrderDayAfter48hsDeliveryDay2ThrowException() {
		menu = MenuBuilder.aMenu().build();
		GregorianCalendar orderDay = new GregorianCalendar(2019, 10, 15, 12, 00);
		GregorianCalendar deliveryDay = new GregorianCalendar(2019, 10, 17, 11, 30);
		menu.validationDateDeliveryMenuOrdered(orderDay, deliveryDay);
	}

	@Test
	public void menuWith150PriceThenValueForQuantity50ThenReturn150() {
		menu = MenuBuilder.aMenu().withDailyStock(200).withPrice(150).withOffer1(new Offer(60, 130)).build();
		assertEquals(new Integer(150), menu.valueForQuantity(50));
	}

	@Test
	public void menuWith150PriceThenValueForQuantity65ThenReturn130() {
		menu = MenuBuilder.aMenu().withDailyStock(200).withPrice(150).withOffer1(new Offer(60, 130)).build();
		assertEquals(new Integer(130), menu.valueForQuantity(65));
	}

	@Test
	public void menuWith150PriceThenValueForQuantity100ThenReturn110() {
		Offer offer1 = new Offer(60, 130);
		Offer offer2 = new Offer(90, 110);
		menu = MenuBuilder.aMenu().withDailyStock(200).withPrice(150).withOffer1(offer1).withOffer2(offer2).build();
		assertEquals(new Integer(110), menu.valueForQuantity(100));
	}

	@Test
	public void menuWith20LowQualityAskIfHasLowQualityMenuThenReturnTrue() {

		menu = MenuBuilder.aMenu().build();
		menu.rankIt(2); menu.rankIt(2); menu.rankIt(2); menu.rankIt(2);
		menu.rankIt(2); menu.rankIt(2); menu.rankIt(2); menu.rankIt(2); 
		menu.rankIt(2); menu.rankIt(2); menu.rankIt(2); menu.rankIt(2);
		menu.rankIt(2); menu.rankIt(2); menu.rankIt(2); menu.rankIt(2);
		menu.rankIt(2); menu.rankIt(2); menu.rankIt(2); menu.rankIt(2);

		assertTrue(menu.hasLowQualityMenu());
	}

	@Test
	public void menuWithoutRankingAskIfHasLowQualityMenuThenReturnFalse() {

		menu = MenuBuilder.aMenu().build();
		assertFalse(menu.hasLowQualityMenu());
	}

	@Test
	public void menuWith4LowQualityAskIfHasLowQualityMenuThenReturnFalse() {

		menu = MenuBuilder.aMenu().build();
		menu.rankIt(1);
		menu.rankIt(1);
		menu.rankIt(1);
		menu.rankIt(2);

		assertFalse(menu.hasLowQualityMenu());
	}

	@Test
	public void menuWith20HightQualityAskIfHasLowQualityMenuThenReturnFalse() {

		menu = MenuBuilder.aMenu().build();
		menu.rankIt(5); menu.rankIt(4); menu.rankIt(3); menu.rankIt(5);
		menu.rankIt(5); menu.rankIt(4); menu.rankIt(3); menu.rankIt(5);
		menu.rankIt(5); menu.rankIt(4); menu.rankIt(3); menu.rankIt(5);
		menu.rankIt(5); menu.rankIt(4); menu.rankIt(3); menu.rankIt(5);
		menu.rankIt(5); menu.rankIt(4); menu.rankIt(3); menu.rankIt(5);
		assertFalse(menu.hasLowQualityMenu());
	}

	@Test
	public void menuWith4AverageRatingThenAverageRatingReturn4() {

		menu = MenuBuilder.aMenu().build();
		menu.rankIt(4);
		menu.rankIt(3);
		menu.rankIt(4);
		menu.rankIt(5);
		assertEquals(new Integer(4), menu.averageRating());
	}

	@Test
	public void menuWithoutAverageRatingThenAverageRatingReturn0() {

		menu = MenuBuilder.aMenu().build();
		assertEquals(new Integer(0), menu.averageRating());
	}

}
