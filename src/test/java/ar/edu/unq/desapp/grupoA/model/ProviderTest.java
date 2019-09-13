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
    }

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
    public void providerWithNullCityThrowsException() 
    {
    	provider = ProviderBuilder.aProvider().build();
        provider.setCity(null);
    }

    @Test(expected = NullPointerException.class)
    public void providerCreationWithNullLocationThrowsException() 
    {
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
    	provider = ProviderBuilder.aProvider().withDescription("abcdefghijklmnopqrstuvwxyz123456789abcdefghijklmnopqrstuvwxyz123456789abcdefghijklmnopqrstuvwxyz123456789abcdefghijklmnopqrstuvwxyz123456789abcdefghijklmnopqrstuvwxyz123456789abcdefghijklmnopqrstuvwxyz").build(); 
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
}
