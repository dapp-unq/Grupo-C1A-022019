package ar.edu.unq.desapp.grupoA.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ar.edu.unq.desapp.grupoA.model.Exception.DescriptionLengthException;

public class MenuTest 
{
	private Menu menu;
	
	@Test(expected = NullPointerException.class)
    public void menuCreationWithNullNameThrowsException() 
	{
		menu = MenuBuilder.aMenu().withName(null).build();
    }

	@Test(expected = DescriptionLengthException.class)
    public void menuCreationWith3LenghtNameThrowsException() 
	{
		menu = MenuBuilder.aMenu().withName("Pzl").build();
    }
	
	@Test(expected = DescriptionLengthException.class)
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
	
	@Test (expected = DescriptionLengthException.class)
    public void menuWith3LenghtNameThrowsException() 
	{
		menu = MenuBuilder.aMenu().build();
		menu.setName("Pzz");
    }
	
	@Test (expected = DescriptionLengthException.class)
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
    }
}
