package ar.edu.unq.desapp.grupoA.model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import ar.edu.unq.desapp.grupoA.model.Exception.DescriptionLengthException;
import ar.edu.unq.desapp.grupoA.model.Exception.EmptyServiceHoursDaysException;
import ar.edu.unq.desapp.grupoA.model.Exception.EmptyStringException;
import ar.edu.unq.desapp.grupoA.model.Exception.InvalidEmailException;
import ar.edu.unq.desapp.grupoA.model.Exception.InvalidPhoneNumberException;

public class ProviderTest {

    private Provider provider;
    private List<ServiceHours> openingHours;

    @Before
    public void setUp() {
    	openingHours = new ArrayList<ServiceHours>();
    	openingHours.add(new ServiceHours(DayOfWeek.MONDAY, LocalTime.of(10, 00), LocalTime.of(21, 00)));
        provider = new Provider("PerezH", "url", City.Quilmes, "Triunvirato 1523", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com", "emailde.perezh@gmail.com", "+540012345678", openingHours);
    }

    @Test(expected = NullPointerException.class)
    public void providerCreationWithNullNameThrowsException() {
        new Provider(null, "url", City.Quilmes, "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com", "emailde.perezh@gmail.com", "+540012345678", openingHours);
    }

    @Test(expected = EmptyStringException.class)
    public void providerCreationWithEmptyNameThrowsException() {
        new Provider("", "url", City.Quilmes, "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com", "emailde.perezh@gmail.com", "+540012345678", openingHours);
    }

    @Test(expected = NullPointerException.class)
    public void providerWithNullNameThrowsException() {
        provider.setName(null);
    }

    @Test(expected = EmptyStringException.class)
    public void providerWithEmptyNameThrowsException() {
        provider.setName("");
    }

    @Test(expected = NullPointerException.class)
    public void providerCreationWithNullLogoThrowsException() {
        new Provider("name", null, City.Quilmes, "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com", "emailde.perezh@gmail.com", "+540012345678", openingHours);
    }

    @Test(expected = EmptyStringException.class)
    public void providerCreationWithEmptyLogoThrowsException() {
        new Provider("name", "", City.Quilmes, "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com", "emailde.perezh@gmail.com", "+540012345678", openingHours);
    }

    @Test(expected = NullPointerException.class)
    public void providerWithNullLogoThrowsException() {
        provider.setLogo(null);
    }

    @Test(expected = EmptyStringException.class)
    public void providerWithEmptyLogoThrowsException() {
        provider.setLogo("");
    }

    @Test(expected = NullPointerException.class)
    public void providerCreationWithNullCityThrowsException() {
        new Provider("name", "url", null, "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com", "emailde.perezh@gmail.com", "+540012345678", openingHours);
    }

    @Test(expected = NullPointerException.class)
    public void providerWithNullCityThrowsException() {
        provider.setCity(null);
    }

    @Test(expected = NullPointerException.class)
    public void providerCreationWithNullLocationThrowsException() {
        new Provider("name", "url", City.Quilmes, null, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com", "emailde.perezh@gmail.com", "+540012345678", openingHours);
    }

    @Test(expected = EmptyStringException.class)
    public void providerCreationWithEmptyLocationThrowsException() {
        new Provider("name", "url", City.Quilmes, "", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com", "emailde.perezh@gmail.com", "+540012345678", openingHours);
    }

    @Test(expected = NullPointerException.class)
    public void providerWithNullLocationThrowsException() {
        provider.setLocation(null);
    }

    @Test(expected = EmptyStringException.class)
    public void providerWithEmptyLocationThrowsException() {
        provider.setLocation("");
    }

    @Test(expected = DescriptionLengthException.class)
    public void providerCreationWithEmptyDescriptionThrowsException() {
        new Provider("name", "url", City.Quilmes, "Quilmes", "", "sitio_web.com", "emailde.perezh@gmail.com", "+540012345678", openingHours);
    }

    @Test(expected = NullPointerException.class)
    public void providerCreationWithNullDescriptionThrowsException() {
        new Provider("name", "url", City.Quilmes, "Quilmes", null, "sitio_web.com", "emailde.perezh@gmail.com", "+540012345678", openingHours);
    }

    @Test(expected = DescriptionLengthException.class)
    public void providerCreationWith29LengthDescriptionThrowsException() {
        new Provider("name", "url", City.Quilmes, "Quilmes", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com", "emailde.perezh@gmail.com", "+540012345678", openingHours);
    }

    @Test(expected = DescriptionLengthException.class)
    public void providerCreationWith201LengthDescriptionThrowsException() {
        String twoHundredAndOneString = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        new Provider("name", "url", City.Quilmes, "Quilmes", twoHundredAndOneString, "sitio_web.com", "emailde.perezh@gmail.com", "+540012345678", openingHours);
    }
    
    @Test(expected = NullPointerException.class)
    public void providerCreationWithNullWebsiteThrowsException() {
        new Provider("name", "url", City.Quilmes, "Quilmes", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", null, "emailde.perezh@gmail.com", "+540012345678", openingHours);
    }
    
    @Test(expected = NullPointerException.class)
    public void providerWithNullWebsiteThrowsException() {
    	 provider.setWebsite(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void providerCreationWithNullEmailThrowsException() {
        new Provider("name", "url", City.Quilmes, "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com", null, "+540012345678", openingHours);
    }

    @Test(expected = EmptyStringException.class)
    public void providerCreationWithEmptyEmailThrowsException() {
        new Provider("name", "url", City.Quilmes, "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com", "", "+540012345678", openingHours);
    }
    
    @Test(expected = InvalidEmailException.class)
    public void providerCreationWithInvalidEmailThrowsException() {
        new Provider("name", "url", City.Quilmes, "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com", "mailinvalido", "+540012345678", openingHours);
    }
    
    @Test(expected = NullPointerException.class)
    public void providerWithNullEmailThrowsException() {
        provider.setEmail(null);
    }

    @Test(expected = EmptyStringException.class)
    public void providerWithEmptyEmailThrowsException() {
        provider.setEmail("");
    }
    
    @Test(expected = InvalidEmailException.class)
    public void providerWithInvalidEmailThrowsException() {
        provider.setEmail("123mail");
    }
    
    @Test(expected = NullPointerException.class)
    public void providerCreationWithNullPhoneNumberThrowsException() {
        new Provider("name", "url", City.Quilmes, "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com", "emailde.perezh@gmail.com", null, openingHours);
    }

    @Test(expected = EmptyStringException.class)
    public void providerCreationWithEmptyPhoneNumberThrowsException() {
        new Provider("name", "url", City.Quilmes, "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com", "emailde.perezh@gmail.com", "", openingHours);
    }
    
    @Test(expected = InvalidPhoneNumberException.class)
    public void providerCreationWithInvalidPhoneNumberThrowsException() {
        new Provider("name", "url", City.Quilmes, "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com", "emailde.perezh@gmail.com", "12345678", openingHours);
    }
    
    @Test(expected = NullPointerException.class)
    public void providerWithNullPhoneNumberThrowsException() {
        provider.setPhoneNumber(null);
    }

    @Test(expected = EmptyStringException.class)
    public void providerWithEmptyPhoneNumberThrowsException() {
        provider.setPhoneNumber("");
    }
    
    @Test(expected = InvalidPhoneNumberException.class)
    public void providerWithInvalidPhoneNumberThrowsException() {
        provider.setPhoneNumber("teléfono123");
    }
    
    @Test(expected = NullPointerException.class)
    public void providerCreationWithNullServiceHoursDaysThrowsException() {
        new Provider("name", "url", City.Quilmes, "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com", "emailde.perezh@gmail.com", "+540012345678", null);
    }

    @Test(expected = EmptyServiceHoursDaysException.class)
    public void providerCreationWithEmptyServiceHoursDaysThrowsException() {
        new Provider("name", "url", City.Quilmes, "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com", "emailde.perezh@gmail.com", "+540012345678", new ArrayList<ServiceHours>());
    }
    
    @Test(expected = NullPointerException.class)
    public void providerWithNullServiceHoursDaysThrowsException() {
        provider.setOpeningHoursDays(null);
    }

    @Test(expected = EmptyServiceHoursDaysException.class)
    public void providerWithEmptyServiceHoursDaysThrowsException() {
        provider.setOpeningHoursDays(new ArrayList<ServiceHours>());
    }
}
