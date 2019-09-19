package ar.edu.unq.desapp.grupoA.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ar.edu.unq.desapp.grupoA.model.Exception.DescriptionLengthException;

public class MenuTest 
{
	private Menu menu;
	
	@Test(expected = NullPointerException.class)
    public void providerCreationWithNullNameThrowsException() 
	{
		menu = MenuBuilder.aMenu().withName(null).build();
    }

	@Test(expected = DescriptionLengthException.class)
    public void providerCreationWith3LenghtNameThrowsException() 
	{
		menu = MenuBuilder.aMenu().withName("Pzl").build();
    }
	
	@Test(expected = DescriptionLengthException.class)
    public void providerCreationWith49LenghtNameThrowsException() 
	{
		menu = MenuBuilder.aMenu().withName("Pizza especial de mozzarella con aceitunas verdes").build();
    }
	
	@Test
    public void providerCreationWithValidName() 
	{
		menu = MenuBuilder.aMenu().withName("Pizza especial Liz").build();
		assertEquals("Pizza especial Liz", menu.getName());
    }
}
