package ar.edu.unq.desapp.grupoA.model;

import ar.edu.unq.desapp.grupoA.model.Exception.DescriptionLengthException;
import ar.edu.unq.desapp.grupoA.model.Exception.EmptyStringException;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ProviderTest {

    private Provider provider;

    @Before
    public void setUp() {
        provider = new Provider("PerezH", "url", City.Quilmes, "Triunvirato 1523", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com");
    }

    @Test(expected = NullPointerException.class)
    public void providerCreationWithNullNameThrowsException() {
        new Provider(null, "url", City.Quilmes, "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com");
    }

    @Test(expected = EmptyStringException.class)
    public void providerCreationWithEmptyNameThrowsException() {
        new Provider("", "url", City.Quilmes, "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com");
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
        new Provider("name", null, City.Quilmes, "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com");
    }

    @Test(expected = EmptyStringException.class)
    public void providerCreationWithEmptyLogoThrowsException() {
        new Provider("name", "", City.Quilmes, "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com");
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
        new Provider("name", "url", null, "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com");
    }

    @Test(expected = NullPointerException.class)
    public void providerWithNullCityThrowsException() {
        provider.setCity(null);
    }

    @Test(expected = NullPointerException.class)
    public void providerCreationWithNullLocationThrowsException() {
        new Provider("name", "url", City.Quilmes, null, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com");
    }

    @Test(expected = EmptyStringException.class)
    public void providerCreationWithEmptyLocationThrowsException() {
        new Provider("name", "url", City.Quilmes, "", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com");
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
        new Provider("name", "url", City.Quilmes, "Quilmes", "", "sitio_web.com");
    }

    @Test(expected = NullPointerException.class)
    public void providerCreationWithNullDescriptionThrowsException() {
        new Provider("name", "url", City.Quilmes, "Quilmes", null, "sitio_web.com");
    }

    @Test(expected = DescriptionLengthException.class)
    public void providerCreationWith29LengthDescriptionThrowsException() {
        new Provider("name", "url", City.Quilmes, "Quilmes", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "sitio_web.com");
    }

    @Test(expected = DescriptionLengthException.class)
    public void providerCreationWith201LengthDescriptionThrowsException() {
        String twoHundredAndOneString = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        new Provider("name", "url", City.Quilmes, "Quilmes", twoHundredAndOneString, "sitio_web.com");
    }
    
    @Test(expected = NullPointerException.class)
    public void providerCreationWithNullWebsiteThrowsException() {
        new Provider("name", "url", City.Quilmes, "Quilmes", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", null);
    }
    
    @Test(expected = NullPointerException.class)
    public void providerWithNullWebsiteThrowsException() 
    {
    	 provider.setWebsite(null);
    }
    
}
