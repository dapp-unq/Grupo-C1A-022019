package ar.edu.unq.desapp.grupoa.model;

import org.junit.Test;

import ar.edu.unq.desapp.grupoa.model.User;
import ar.edu.unq.desapp.grupoa.model.exceptions.EmptyStringException;
import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidEmailException;
import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidPhoneNumberException;

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

    private User anyUser(){
        return new User("Nahuel", "Benitez", "beniteznahueloscar@gmail.com", "+42112555005", "Condarco 1549");
    }
}