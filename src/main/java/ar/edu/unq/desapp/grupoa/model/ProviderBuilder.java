package ar.edu.unq.desapp.grupoa.model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ProviderBuilder 
{
	private String name = "no name i";
    private String logo = "no logo";
    private City city = City.Luis_Guillon;
    private String location = "no location";
    private String description = "description with at least 30 characters";
    private String website = "no webside";
    private String email = "noe-mail@gmail.com";
    private String phoneNumber = "+541125983441";
    private List<ServiceHours> openingHoursDays = inicializedOpeningHoursDays();
    private double km = 15;
    private List<Menu> currentMenu = new ArrayList<Menu>();
    
	private ArrayList<ServiceHours> inicializedOpeningHoursDays() 
	{
		ArrayList<ServiceHours> opening = new ArrayList<ServiceHours>();
		opening.add(new ServiceHours(DayOfWeek.MONDAY, LocalTime.of(10, 00), LocalTime.of(21, 00)));
		return opening;
	}
	
	public static ProviderBuilder aProvider()
	{
		return new ProviderBuilder();
	}
	
	public Provider build()
	{
		return new Provider(name, logo, city,location, description, website, email, phoneNumber, openingHoursDays, km);
	}
	
	public ProviderBuilder withName(String aName)
	{
		this.name = aName;
		return this;
	}
	
	public ProviderBuilder withLogo(String aDirlogo)
	{
		this.logo = aDirlogo;
		return this;
	}
	
	public ProviderBuilder withCity(City aCity)
	{
		this.city = aCity;
		return this;
	}
	
	public ProviderBuilder withLocation(String aLocation)
	{
		this.location = aLocation;
		return this;
	}
	
	public ProviderBuilder withDescription(String aDescription)
	{
		this.description = aDescription;
		return this;
	}
	
	public ProviderBuilder withWebsite(String aWebsite)
	{
		this.website = aWebsite;
		return this;
	}
	
	public ProviderBuilder withEmail(String aEmail)
	{
		this.email = aEmail;
		return this;
	}
	
	public ProviderBuilder withPhoneNumber(String aPhoneNumber)
	{
		this.phoneNumber = aPhoneNumber;
		return this;
	}
	
	public ProviderBuilder withOpeningHoursDays(List<ServiceHours> aOpeningHoursDays)
	{
		this.openingHoursDays = aOpeningHoursDays;
		return this;
	}
	
	public ProviderBuilder withMaxDeliveryDistance(double km)
	{
		this.km = km;
		return this;
	}
}
