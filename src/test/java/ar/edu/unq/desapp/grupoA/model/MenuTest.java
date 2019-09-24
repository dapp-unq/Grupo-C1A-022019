package ar.edu.unq.desapp.grupoA.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import ar.edu.unq.desapp.grupoA.model.Exception.DescriptionLengthException;
import ar.edu.unq.desapp.grupoA.model.Exception.DataIncompleteException;
import ar.edu.unq.desapp.grupoA.model.Exception.EmptyListException;
import ar.edu.unq.desapp.grupoA.model.Exception.NameLengthException;
import ar.edu.unq.desapp.grupoA.model.Exception.PriceAmountException;

public class MenuTest 
{
	private Menu menu;
	
	@Test(expected = NullPointerException.class)
    public void menuCreationWithNullNameThrowsException() 
	{
		menu = MenuBuilder.aMenu().withName(null).build();
    }

	@Test(expected = NameLengthException.class)
    public void menuCreationWith3LenghtNameThrowsException() 
	{
		menu = MenuBuilder.aMenu().withName("Pzl").build();
    }
	
	@Test(expected = NameLengthException.class)
    public void menuCreationWith49LenghtNameThrowsException() 
	{
		menu = MenuBuilder.aMenu().withName("Pizza especial de mozzarella con aceitunas verdes").build();
    }
	
	@Test
    public void menuCreationWithValidName() 
	{
		menu = MenuBuilder.aMenu().withName("Pizza especial Liz").build();
		assertEquals("Pizza especial Liz", menu.getName());
    }
	
	@Test (expected = NullPointerException.class)
    public void menuWithNullNameThrowsException() 
	{
		menu = MenuBuilder.aMenu().build();
		menu.setName(null);
    }
	
	@Test (expected = NameLengthException.class)
    public void menuWith3LenghtNameThrowsException() 
	{
		menu = MenuBuilder.aMenu().build();
		menu.setName("Pzz");
    }
	
	@Test (expected = NameLengthException.class)
    public void menuWith49LenghtNameThrowsException() 
	{
		menu = MenuBuilder.aMenu().build();
		menu.setName("Pizza especial de mozzarella con aceitunas verdes");
    }
	
	@Test
    public void menuWithValidateName() 
	{
		menu = MenuBuilder.aMenu().build();
		menu.setName("Pizza de mozza especial de Liz");
		assertEquals("Pizza de mozza especial de Liz", menu.getName());
    }
	
	@Test(expected = NullPointerException.class)
    public void menuCreationWithNullDescriptionThrowsException() 
	{
		menu = MenuBuilder.aMenu().withDescription(null).build();
    }
	
	@Test(expected = DescriptionLengthException.class)
    public void menuCreationWith19LenghtDescriptionThrowsException() 
	{
		menu = MenuBuilder.aMenu().withDescription("Pizza de mozzarella").build();
    }
	
	@Test(expected = DescriptionLengthException.class)
    public void menuCreationWith41LenghtDescriptionThrowsException() 
	{
		menu = MenuBuilder.aMenu().withDescription("Pizza de mozzarella con extra de morrones").build();
    }
	
	@Test
    public void menuCreationWithValidDescription() 
	{
		menu = MenuBuilder.aMenu().withDescription("Pizza de mozza con lluvia de aceitunas").build();
		assertEquals("Pizza de mozza con lluvia de aceitunas", menu.getDescription());
    }
	
	@Test (expected = NullPointerException.class)
    public void menuWithNullDescriptionThrowsException() 
	{
		menu = MenuBuilder.aMenu().build();
		menu.setDescription(null);
    }
	
	@Test (expected = DescriptionLengthException.class)
    public void menuWith19LenghtDescriptionThrowsException() 
	{
		menu = MenuBuilder.aMenu().build();
		menu.setDescription("Pizza de mozzarella");
    }
	
	@Test (expected = DescriptionLengthException.class)
    public void menuWith41LenghtDescriptionThrowsException() 
	{
		menu = MenuBuilder.aMenu().build();
		menu.setDescription("Pizza de mozzarella con extra de morrones");
    }
	
	@Test
    public void menuWithValidateDescription() 
	{
		menu = MenuBuilder.aMenu().build();
		menu.setDescription("Pizza de mozza con lluvia de aceitunas");
		assertEquals("Pizza de mozza con lluvia de aceitunas", menu.getDescription());
    }
	
	@Test(expected = NullPointerException.class)
    public void menuCreationWithNullCategoriesThrowsException() 
	{
		menu = MenuBuilder.aMenu().withCategory(null).build();
    }
	
	@Test(expected = EmptyListException.class)
    public void menuCreationWithEmptyCategoriesThrowsException() 
	{
		menu = MenuBuilder.aMenu().withCategory(new ArrayList<Category>()).build();
    }
	
	@Test
    public void menuCreationWithValidCategory() 
	{
		List<Category> categories = new ArrayList<Category> ();
		categories.add(Category.Pizza);
		menu = MenuBuilder.aMenu().withCategory(categories).build();
		assertEquals(categories, menu.getCategory());
    }
	
	@Test (expected = NullPointerException.class)
    public void menuWithNullCategoryThrowsException() 
	{
		menu = MenuBuilder.aMenu().build();
		menu.setCategory(null);
    }
	
	@Test (expected = EmptyListException.class)
    public void menuWithEmptyCategoryNameThrowsException() 
	{
		menu = MenuBuilder.aMenu().build();
		menu.setCategory(new ArrayList<Category> ());
    }
	
	@Test
    public void menuWithValidCategory() 
	{
		List<Category> categories = new ArrayList<Category> ();
		categories.add(Category.Pizza);
		menu = MenuBuilder.aMenu().build();
		menu.setCategory(categories);
		assertEquals(categories, menu.getCategory());
    }
	
	@Test
    public void menuCreationWith0DeliveryPrice() 
	{
		menu = MenuBuilder.aMenu().withDeliveryPrice(0).build();
		assertEquals(new Integer(0), menu.getDeliveryPrice());
    }
	
	@Test(expected = PriceAmountException.class)
    public void menuCreationWith50DeliveryPriceThrowsException() 
	{
		menu = MenuBuilder.aMenu().withDeliveryPrice(50).build();
    }
	
	@Test
    public void menuCreationWithValidNullDeliveryPrice() 
	{
		menu = MenuBuilder.aMenu().withDeliveryPrice(40).build();
		assertEquals(new Integer(40), menu.getDeliveryPrice());
    }
	
	@Test
    public void menuCreationWithValidDeliveryPrice() 
	{
		menu = MenuBuilder.aMenu().withDeliveryPrice(40).build();
		assertEquals(new Integer(40), menu.getDeliveryPrice());
    }
	
	@Test (expected = PriceAmountException.class)
    public void menuWith5DeliveryPriceThrowsException() 
	{
		menu = MenuBuilder.aMenu().build();
		menu.setDeliveryPrice(5);
    }
	
	@Test (expected = PriceAmountException.class)
    public void menuWith45DeliveryPriceThrowsException() 
	{
		menu = MenuBuilder.aMenu().build();
		menu.setDeliveryPrice(45);
    }
	
	@Test (expected = NullPointerException.class)
    public void menuWithValidateNullDeliveryPriceThrowsException() 
	{
		menu = MenuBuilder.aMenu().build();
		menu.setDeliveryPrice(null);
    }
	
	@Test
    public void menuWithValidateDeliveryPrice() 
	{
		menu = MenuBuilder.aMenu().build();
		menu.setDeliveryPrice(10);
		assertEquals(new Integer(10), menu.getDeliveryPrice());
    }
	
	@Test
    public void menuWithValidateEmptyDeliveryPrice() 
	{
		menu = MenuBuilder.aMenu().build();
		menu.setDeliveryPrice(0);
		assertEquals(new Integer(0), menu.getDeliveryPrice());
    }
	
	@Test(expected = NullPointerException.class)
    public void menuCreationWithNullEfectiveDatesThrowsException() 
	{
		menu = MenuBuilder.aMenu().withEfectiveDate(null).build();
    }
	
	@Test(expected = EmptyListException.class)
    public void menuCreationWithEmptyEfectiveDatesThrowsException() 
	{
		menu = MenuBuilder.aMenu().withEfectiveDate(new ArrayList<GregorianCalendar>()).build();
    }
	
	@Test(expected = DataIncompleteException.class)
    public void menuCreationWithIncompleteEfectiveDatesThrowsException() 
	{
		List<GregorianCalendar> efectiveDates = new ArrayList<GregorianCalendar>();
		efectiveDates.add(new GregorianCalendar(2019,5,10));
		menu = MenuBuilder.aMenu().withEfectiveDate(efectiveDates).build();
    }
	
	@Test
    public void menuCreationWithValidEfectiveDates() 
	{
		List<GregorianCalendar> efectiveDates = new ArrayList<GregorianCalendar>();
		efectiveDates.add(new GregorianCalendar(2019,5,10));
		efectiveDates.add(new GregorianCalendar(2019,10,10));
		menu = MenuBuilder.aMenu().withEfectiveDate(efectiveDates).build();
		assertEquals(efectiveDates, menu.getEfectiveDate());
    }
	
	@Test (expected = NullPointerException.class)
    public void menuWithNullEfectiveDatesThrowsException() 
	{
		menu = MenuBuilder.aMenu().build();
		menu.setEfectiveDate(null);
    }
	
	@Test (expected = EmptyListException.class)
    public void menuWithEmptyEfectiveDatesThrowsException() 
	{
		menu = MenuBuilder.aMenu().build();
		menu.setEfectiveDate(new ArrayList<GregorianCalendar>());
    }
	
	@Test (expected = DataIncompleteException.class)
    public void menuWithIncompleteEfectiveDatesThrowsException() 
	{
		List<GregorianCalendar> efectiveDates = new ArrayList<GregorianCalendar>();
		efectiveDates.add(new GregorianCalendar(0,0,0));
		efectiveDates.add(new GregorianCalendar(0,0,0));
		efectiveDates.add(new GregorianCalendar(0,0,0));
		menu = MenuBuilder.aMenu().build();
		menu.setEfectiveDate(efectiveDates);
    }
	
	@Test
    public void menuWithValidateEfectiveDates() 
	{
		List<GregorianCalendar> efectiveDates = new ArrayList<GregorianCalendar>();
		efectiveDates.add(new GregorianCalendar(2019,5,10));
		efectiveDates.add(new GregorianCalendar(2019,10,10));
		menu = MenuBuilder.aMenu().build();
		menu.setEfectiveDate(efectiveDates);
		assertEquals(efectiveDates, menu.getEfectiveDate());
    }
	
	@Test(expected = NullPointerException.class)
    public void menuCreationWithNullDeliverySchedulesThrowsException() 
	{
		menu = MenuBuilder.aMenu().withDeliverySchedules(null).build();
    }
	
	@Test(expected = EmptyListException.class)
    public void menuCreationWithEmptyDeliverySchedulesThrowsException() 
	{
		menu = MenuBuilder.aMenu().withDeliverySchedules(new ArrayList<LocalTime>()).build();
    }
	
	@Test(expected = DataIncompleteException.class)
    public void menuCreationWithIncompleteDeliverySchedulesThrowsException() 
	{
		List<LocalTime> deliverySchedules = new ArrayList<LocalTime>();
		deliverySchedules.add(LocalTime.of(12,00));
		menu = MenuBuilder.aMenu().withDeliverySchedules(deliverySchedules).build();
    }
	
	@Test
    public void menuCreationWithValidDeliverySchedules() 
	{
		List<LocalTime> deliverySchedules = new ArrayList<LocalTime>();
		deliverySchedules.add(LocalTime.of(12,00));
		deliverySchedules.add(LocalTime.of(22,30));
		menu = MenuBuilder.aMenu().withDeliverySchedules(deliverySchedules).build();
		assertEquals(deliverySchedules, menu.getDeliverySchedules());
    }
	
	@Test (expected = NullPointerException.class)
    public void menuWithNullDeliverySchedulesThrowsException()
	{
		menu = MenuBuilder.aMenu().build();
		menu.setDeliverySchedules(null);
    }
	
	@Test (expected = EmptyListException.class)
    public void menuWithEmptyDeliverySchedulesThrowsException() 
	{
		menu = MenuBuilder.aMenu().build();
		menu.setDeliverySchedules(new ArrayList<LocalTime>());
    }
	
	@Test (expected = DataIncompleteException.class)
    public void menuWithIncompleteDeliverySchedulesThrowsException() 
	{
		List<GregorianCalendar> efectiveDates = new ArrayList<GregorianCalendar>();
		efectiveDates.add(new GregorianCalendar(0,0,0));
		efectiveDates.add(new GregorianCalendar(0,0,0));
		efectiveDates.add(new GregorianCalendar(0,0,0));
		menu = MenuBuilder.aMenu().build();
		menu.setEfectiveDate(efectiveDates);
    }
	
	@Test
    public void menuWithValidateDeliverySchedules() 
	{
		List<LocalTime> deliverySchedules = new ArrayList<LocalTime>();
		deliverySchedules.add(LocalTime.of(12,00));
		deliverySchedules.add(LocalTime.of(22,30));
		menu = MenuBuilder.aMenu().build();
		menu.setDeliverySchedules(deliverySchedules);
		assertEquals(deliverySchedules, menu.getDeliverySchedules());
    }
}
