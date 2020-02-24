package ar.edu.unq.desapp.grupoa.model;

import ar.edu.unq.desapp.grupoa.model.enums.City;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ProviderBuilder {
	private String name = "no name i";
	private String logo = "no logo";
	private City city = City.LUIS_GUILLON;
	private String location = "QUILMES_CENTRO";
	private String description = "description with at least 30 characters";
	private String website = "no webside";
	private String email = "noe-mail@gmail.com";
	private String phoneNumber = "+541125983441";
	private List<ServiceHours> openingHoursDays = inicializedOpeningHoursDays();
	private double km = 15;

	private ArrayList<ServiceHours> inicializedOpeningHoursDays() {
		ArrayList<ServiceHours> opening = new ArrayList<ServiceHours>();
		opening.add(new ServiceHours(DayOfWeek.MONDAY, LocalTime.of(10, 00), LocalTime.of(21, 00)));
		return opening;
	}

	public static ProviderBuilder aProvider() {
		return new ProviderBuilder();
	}

	public Provider build() {
		return new Provider(name, logo, city, location, description, website, email, phoneNumber, openingHoursDays, km);
	}

	public ProviderBuilder withName(final String aName) {
		this.name = aName;
		return this;
	}

	public ProviderBuilder withLogo(final String aDirlogo) {
		this.logo = aDirlogo;
		return this;
	}

	public ProviderBuilder withCity(final City aCity) {
		this.city = aCity;
		return this;
	}

	public ProviderBuilder withLocation(final String aLocation) {
		this.location = aLocation;
		return this;
	}

	public ProviderBuilder withDescription(final String aDescription) {
		this.description = aDescription;
		return this;
	}

	public ProviderBuilder withWebsite(final String aWebsite) {
		this.website = aWebsite;
		return this;
	}

	public ProviderBuilder withEmail(final String aEmail) {
		this.email = aEmail;
		return this;
	}

	public ProviderBuilder withPhoneNumber(final String aPhoneNumber) {
		this.phoneNumber = aPhoneNumber;
		return this;
	}

	public ProviderBuilder withOpeningHoursDays(final List<ServiceHours> aOpeningHoursDays) {
		this.openingHoursDays = aOpeningHoursDays;
		return this;
	}

	public ProviderBuilder withMaxDeliveryDistance(final double km) {
		this.km = km;
		return this;
	}
}
