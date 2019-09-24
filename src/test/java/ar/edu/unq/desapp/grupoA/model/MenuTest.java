package ar.edu.unq.desapp.grupoA.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ar.edu.unq.desapp.grupoA.model.Exception.DescriptionLengthException;
import ar.edu.unq.desapp.grupoA.model.Exception.EmptyListException;
import ar.edu.unq.desapp.grupoA.model.Exception.NameLengthException;

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
}
