package ar.edu.unq.desapp.grupoa.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import ar.edu.unq.desapp.grupoa.model.exceptions.CurrentMenuQuantityException;
import ar.edu.unq.desapp.grupoa.model.exceptions.DescriptionLengthException;
import ar.edu.unq.desapp.grupoa.model.exceptions.EmptyServiceHoursDaysException;
import ar.edu.unq.desapp.grupoa.model.exceptions.EmptyStringException;
import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidEmailException;
import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidPhoneNumberException;
import ar.edu.unq.desapp.grupoa.model.exceptions.MenuNotFoundException;
import ar.edu.unq.desapp.grupoa.model.exceptions.MenuWithRepeatedNameException;
import lombok.Getter;
import lombok.NonNull;

@Getter
@NonNull
public class Provider {

	private String name;
	private String logo;
	private City city;
	private String location;
	private String description;
	private String website;
	private String email;
	private String phoneNumber;
	private List<ServiceHours> openingHoursDays;
	private List<City> deliveryCities;
	private List<Menu> currentMenu;
	private List<CurrentOrder> orders;

	private final Pattern VALID_EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);
	private final Pattern VALID_PHONE_REGEX = Pattern.compile("^\\+(?:[0-9]?){6,14}[0-9]$");

	public Provider(String name, String logo, City city, String location, String description, @NonNull String website,
			String email, String phone, List<ServiceHours> serviceHours, double km) {
		this.name = validateNotEmpty(name, "nombre");
		this.logo = validateNotEmpty(logo, "logo");
		this.city = validateNotEmptyCity(city, "localidad");
		this.location = validateNotEmpty(location, "direccion");
		this.description = validateDescriptionSize(description);
		this.website = website;
		this.email = validateEmail(email);
		this.phoneNumber = validatePhoneNumber(phone);
		this.openingHoursDays = validateNotEmptyOpeningHoursDays(serviceHours, "horarios y días de atención");
		this.deliveryCities = calculateDeliveryCities(km, location);
		this.currentMenu = new ArrayList<Menu>();
		this.orders = new ArrayList<CurrentOrder>();
	}

	private List<City> calculateDeliveryCities(Double km, String location) {
		List<City> cities = new ArrayList<City>();
		// TODO: FALTA COMUNICARSE EN GMAP PARA VER TODAS LAS LOCALIDADES DONDE HACE
		// ENTREGAS DESDE LA CIUDAD DEL LOCAL.
		cities.add(City.Quilmes);
		cities.add(City.Bernal);
		cities.add(City.Ezpeleta);
		return cities;
	}

	private List<ServiceHours> validateNotEmptyOpeningHoursDays(List<ServiceHours> serviceHours, String parameterName) {
		if (serviceHours.isEmpty())
			throw new EmptyServiceHoursDaysException("El campo " + parameterName + " no puede ser vacío");
		return serviceHours;
	}

	private String validatePhoneNumber(String phone) {
		validateNotEmpty(phone, "número de teléfono");
		if (!validatePhoneRegEx(phone))
			throw new InvalidPhoneNumberException(
					"El número de teléfono debe componerse con '+' seguido por la característica.");
		return phone;
	}

	private boolean validatePhoneRegEx(String phone) {
		Matcher matcher = VALID_PHONE_REGEX.matcher(phone);
		return matcher.find();
	}

	private String validateEmail(String email) {
		validateNotEmpty(email, "e-mail");
		if (!validateEmailRegEx(email))
			throw new InvalidEmailException("El email no es válido");
		return email;
	}

	public boolean validateEmailRegEx(String _email) {
		Matcher matcher = VALID_EMAIL_REGEX.matcher(_email);
		return matcher.find();
	}

	private String validateDescriptionSize(String description) {
		int length = description.length();
		if (length < 30)
			throw new DescriptionLengthException("La descripción debe tener al menos 30 caracteres");
		if (length > 200)
			throw new DescriptionLengthException("La descripción debe tener como máximo 200 caracteres");
		return validateNotEmpty(description, "descripción");
	}

	private String validateNotEmpty(String parameter, String parameterName) {
		if (parameter.isEmpty())
			throw new EmptyStringException("El campo " + parameterName + " no puede ser vacío");
		return parameter;
	}

	private City validateNotEmptyCity(City parameter, String parameterName) {
		if (parameter.toString().isEmpty())
			throw new NullPointerException("El campo " + parameterName + " no puede ser vacío");
		return parameter;
	}

	public void setLogo(String logo) {
		this.logo = validateNotEmpty(logo, "logo");
	}

	public void setCity(City city) {
		this.city = validateNotEmptyCity(city, "localidad");
	}

	public void setName(String name) {
		this.name = validateNotEmpty(name, "nombre");
	}

	public void setLocation(String location) {
		this.location = validateNotEmpty(location, "direccion");
	}

	public void setDescription(String description) {
		this.description = validateDescriptionSize(description);
	}

	public void setWebsite(@NonNull String website) {
		this.website = website;
	}

	public void setEmail(String email) {
		this.email = validateEmail(email);
	}

	public void setPhoneNumber(String phone) {
		this.phoneNumber = validatePhoneNumber(phone);
	}

	public void setOpeningHoursDays(List<ServiceHours> serviceHours) {
		this.openingHoursDays = validateNotEmptyOpeningHoursDays(serviceHours, "horarios y días de atención");
	}

	public void setkm(double km) {
		this.deliveryCities = calculateDeliveryCities(km, this.location);
	}

	public void addMenu(Menu aMenu) {
		this.validationCurrentMenuQuantity(aMenu.getName());
		this.currentMenu.add(aMenu);
	}

	private void validationCurrentMenuQuantity(String menuName) {
		if (this.currentMenu.size() == 20)
			throw new CurrentMenuQuantityException(
					"No se puede agregar más menús: Solo se puede tener hasta 20 menús vigentes.");
		if (this.anyCurrentMenuHasName(menuName))
			throw new MenuWithRepeatedNameException(
					"Ya existe un menú con el nombre " + menuName + ". Entente con otro diferente.");
	}

	public boolean anyCurrentMenuHasName(String menuName) {
		return this.currentMenu.stream().anyMatch(menu -> menu.hasName(menuName));
	}

	public Menu searchMenu(String menuName) {
		Optional<Menu> searchMenu = this.currentMenu.stream().filter(menu -> menu.hasName(menuName)).findFirst();
		if (!searchMenu.isPresent())
			throw new MenuNotFoundException("No se encontró un menú con el nombre: " + menuName);
		return searchMenu.get();
	}

	public void removeMenuWithName(String menuName) {
		this.removeMenu(this.searchMenu(menuName));
	}

	public void removeMenu(Menu aMenu) {
		this.currentMenu.remove(aMenu);
	}

	public void editMenu(String nameMenuEdit, Menu newMenu) {
		this.removeMenuWithName(nameMenuEdit);
		this.addMenu(newMenu);
	}
	
	public List<Menu> menusWithNameMatchedWith(String text)
	{
		return this.currentMenu.stream().filter(menu -> menu.hasNameMatchedWith(text)).collect(Collectors.toList());
	}

	public List<Menu> menusWithCategory(Category aCategory)
	{
		return this.currentMenu.stream().filter(menu -> menu.hasCategory(aCategory)).collect(Collectors.toList());
	}
	
	public List<Menu> menusWithLocation(City aCity)
	{
		List<Menu> result =  new ArrayList<Menu>();
		if (this.deliveryCities.contains(aCity))
			result = this.currentMenu;
		return result;
	}

	public void addOrder(User user, Order newOrder) 
	{
		Optional<CurrentOrder> result = this.orders.stream().filter(order -> order.hasUser(user)).findFirst();
		if(result.isPresent())
			result.get().addOrder(newOrder);
		else
			this.orders.add(new CurrentOrder(user, newOrder));
	}
	
}
