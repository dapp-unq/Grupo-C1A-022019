package ar.edu.unq.desapp.grupoA.model;

import org.junit.Test;

public class MenuTest 
{
	private Menu menu;
	
	@Test(expected = NullPointerException.class)
    public void providerCreationWithNullNameThrowsException() 
	{
		menu = MenuBuilder.aMenu().withName(null).build();
    }
	
	
}
