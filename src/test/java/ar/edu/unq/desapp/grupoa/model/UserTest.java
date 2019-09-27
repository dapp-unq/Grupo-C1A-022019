package ar.edu.unq.desapp.grupoa.model;

import static org.mockito.Mockito.mock;

import java.util.GregorianCalendar;

import org.junit.Test;
import org.mockito.Mockito;

import ar.edu.unq.desapp.grupoa.model.exceptions.EmptyStringException;
import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidEmailException;
import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidPhoneNumberException;
import ar.edu.unq.desapp.grupoa.model.exceptions.IrrationalAmountException;
import ar.edu.unq.desapp.grupoa.model.exceptions.OrderDateException;

public class UserTest {

    @Test(expected = NullPointerException.class)
    public void whenSettingAnUserWithNullNameThenItThrowsNullPointerException(){
        User aUser = anyUser();
        aUser.setName(null);
    }

    @Test(expected = EmptyStringException.class)
    public void whenSettingAnUserWithEmptyNameThenItThrowsNullPointerException(){
        User aUser = anyUser();
        aUser.setName("");
    }

    @Test(expected = NullPointerException.class)
    public void whenSettingAnUserWithNullSurnameThenItThrowsNullPointerException(){
        User aUser = anyUser();
        aUser.setSurname(null);
    }

    @Test(expected = EmptyStringException.class)
    public void whenSettingAnUserWithEmptySurnameThenItThrowsNullPointerException(){
        User aUser = anyUser();
        aUser.setSurname("");
    }

    @Test(expected = NullPointerException.class)
    public void whenSettingAnUserWithNullEmailThenItThrowsNullPointerException(){
        User aUser = anyUser();
        aUser.setEmail(null);
    }

    @Test(expected = InvalidEmailException.class)
    public void whenSettingAnUserWithEmptyEmailThenItThrowsNullPointerException(){
        User aUser = anyUser();
        aUser.setEmail("");
    }

    @Test(expected = NullPointerException.class)
    public void whenSettingAnUserWithNullPhoneNumberThenItThrowsNullPointerException(){
        User aUser = anyUser();
        aUser.setPhoneNumber(null);
    }

    @Test(expected = InvalidPhoneNumberException.class)
    public void whenSettingAnUserWithEmptyPhoneNumberThenItThrowsNullPointerException(){
        User aUser = anyUser();
        aUser.setPhoneNumber("");
    }

    @Test(expected = NullPointerException.class)
    public void whenSettingAnUserWithNullLocationThenItThrowsNullPointerException(){
        User aUser = anyUser();
        aUser.setLocation(null);
    }

    @Test(expected = EmptyStringException.class)
    public void whenSettingAnUserWithEmptyLocationThenItThrowsNullPointerException(){
        User aUser = anyUser();
        aUser.setLocation("");
    }

    @Test(expected = InvalidEmailException.class)
    public void whenSettingAnUserWithInvalidEmailThenItThrowsNullPointerException(){
        User aUser = anyUser();
        aUser.setEmail("invalidEmail");
    }

    @Test(expected = InvalidPhoneNumberException.class)
    public void whenSettingAnUserWithInvalidPhoneNumberThenItThrowsNullPointerException(){
        User aUser = anyUser();
        aUser.setPhoneNumber("1234");
    }
    
    @Test
    public void testPurchaseMenuCorrectly(){
        User aUser = anyUser();
        GregorianCalendar orderDay = new GregorianCalendar(2019, 11, 2, 12, 00);
		GregorianCalendar deliveryDay = new GregorianCalendar(2019, 11, 8, 11, 30);
        
		Menu mockMenu = mock(Menu.class);
		Provider mockProvider = mock(Provider.class);
		Mockito.when(mockMenu.getName()).thenReturn("Pizza de mozzarella");
		
        aUser.purchase(mockProvider, mockMenu, 50, DeliveryType.Home_delivery, deliveryDay, orderDay);
    }
    
    @Test (expected = IrrationalAmountException.class)
    public void whenPurchaseMenuWithou100DailyStockThenThrowException(){
        User aUser = anyUser();
        GregorianCalendar orderDay = new GregorianCalendar(2019, 11, 2, 12, 00);
		GregorianCalendar deliveryDay = new GregorianCalendar(2019, 11, 8, 11, 30);
        
		Menu mockMenu = mock(Menu.class);
		Provider mockProvider = mock(Provider.class);
		Mockito.doThrow(IrrationalAmountException.class).when(mockMenu).validationNumberMenuOrdered(100);	

        aUser.purchase(mockProvider, mockMenu, 100, DeliveryType.Home_delivery, deliveryDay, orderDay);
    }
    
    @Test (expected = OrderDateException.class)
    public void whenPurchaseMenuWithoutHas48HoursBetweenDatesThrowException(){
        User aUser = anyUser();
        GregorianCalendar orderDay = new GregorianCalendar(2019, 11, 2, 12, 00);
		GregorianCalendar deliveryDay = new GregorianCalendar(2019, 11, 3, 11, 30);
        
		Menu mockMenu = mock(Menu.class);
		Provider mockProvider = mock(Provider.class);
		Mockito.doThrow(OrderDateException.class).when(mockMenu).validationDateDeliveryMenuOrdered(orderDay , deliveryDay);	

        aUser.purchase(mockProvider, mockMenu, 50, DeliveryType.Home_delivery, deliveryDay, orderDay);
    }

    private User anyUser(){
        return new User("Nahuel", "Benitez", "beniteznahueloscar@gmail.com", "+42112555005", "Condarco 1549");
    }
}