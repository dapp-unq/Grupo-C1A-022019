package ar.edu.unq.desapp.grupoA.model;

import ar.edu.unq.desapp.grupoA.model.Exception.DescriptionLengthException;
import ar.edu.unq.desapp.grupoA.model.Exception.EmptyStringException;
import org.junit.Before;
import org.junit.Test;

public class ProviderTest {

    private Provider provider;

    @Before
    public void setUp() {
        provider = new Provider("PerezH", "url", "Quilmes", "Triunvirato 1523", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = NullPointerException.class)
    public void providerCreationWithNullNameThrowsException() {
        new Provider(null, "url", "Quilmes", "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = EmptyStringException.class)
    public void providerCreationWithEmptyNameThrowsException() {
        new Provider("", "url", "Quilmes", "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
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
        new Provider("name", null, "Quilmes", "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = EmptyStringException.class)
    public void providerCreationWithEmptyLogoThrowsException() {
        new Provider("name", "", "Quilmes", "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
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
        new Provider("name", "url", null, "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = EmptyStringException.class)
    public void providerCreationWithEmptyCityThrowsException() {
        new Provider("name", "url", "", "location", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = NullPointerException.class)
    public void providerWithNullCityThrowsException() {
        provider.setCity(null);
    }

    @Test(expected = EmptyStringException.class)
    public void providerWithEmptyCityThrowsException() {
        provider.setCity("");
    }

    @Test(expected = NullPointerException.class)
    public void providerCreationWithNullLocationThrowsException() {
        new Provider("name", "url", "Quilmes", null, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = EmptyStringException.class)
    public void providerCreationWithEmptyLocationThrowsException() {
        new Provider("name", "url", "Quilmes", "", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
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
        new Provider("name", "url", "Quilmes", "Quilmes", "");
    }

    @Test(expected = NullPointerException.class)
    public void providerCreationWithNullDescriptionThrowsException() {
        new Provider("name", "url", "Quilmes", "Quilmes", null);
    }

    @Test(expected = DescriptionLengthException.class)
    public void providerCreationWith29LengthDescriptionThrowsException() {
        new Provider("name", "url", "Quilmes", "Quilmes", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = DescriptionLengthException.class)
    public void providerCreationWith201LengthDescriptionThrowsException() {
        String twoHundredAndOneString = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        new Provider("name", "url", "Quilmes", "Quilmes", twoHundredAndOneString);
    }

}
