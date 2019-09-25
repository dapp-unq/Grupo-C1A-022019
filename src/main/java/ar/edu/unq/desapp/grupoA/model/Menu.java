package ar.edu.unq.desapp.grupoA.model;

import java.time.LocalTime;
import java.util.GregorianCalendar;
import java.util.List;

import ar.edu.unq.desapp.grupoA.model.Exception.DataIncompleteException;
import ar.edu.unq.desapp.grupoA.model.Exception.DescriptionLengthException;
import ar.edu.unq.desapp.grupoA.model.Exception.EmptyListException;
import ar.edu.unq.desapp.grupoA.model.Exception.EmptyStringException;
import ar.edu.unq.desapp.grupoA.model.Exception.IrrationalAmountException;
import ar.edu.unq.desapp.grupoA.model.Exception.IrrationalPriceException;
import ar.edu.unq.desapp.grupoA.model.Exception.NameLengthException;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Menu 
{
	@NonNull private String name;
	@NonNull private String description;
	@NonNull private List<Category> category;
	@NonNull private Integer deliveryPrice;
	@NonNull private List<GregorianCalendar> efectiveDate;
	@NonNull private List<LocalTime> deliverySchedules;
	@NonNull private LocalTime averigeDeliveryTime;
	@NonNull private Integer price;
	@NonNull private Integer dailyStock;
	@NonNull private Offer offer1;
	@NonNull private Offer offer2;
	
	public Menu(String name, String description, List<Category> category, Integer deliveryPrice, 
			List<GregorianCalendar> efectiveDate, List<LocalTime> deliverySchedules, 
			@NonNull LocalTime averigeDeliveryTime, Integer price, Integer dailyStock, Offer minQuantity1,
			Offer minQuantity2) 
	{
		this.name = validateName(name);
		this.description = validateDescription(description);
		this.category = validateNotEmptyList(category, "categorías");
		this.deliveryPrice = validateDeliveryPrice(deliveryPrice);
		this.efectiveDate = validateEfectiveDate(efectiveDate);
		this.deliverySchedules = validateDeliverySchedules(deliverySchedules);
		this.averigeDeliveryTime = averigeDeliveryTime;
		this.price = validatePrice(price);
		this.dailyStock = validateDailyStock(dailyStock);
		this.offer1 = validationOffert1(minQuantity1);
		this.offer2 = minQuantity2;
	}

	private @NonNull Offer validationOffert1(Offer minQuantity1) 
	{
		this.validationQuantityOffer(minQuantity1.getQuantity(), 10, 70);
		this.validationPriceOffer(minQuantity1.getPrice(), 0, 1000);
		return minQuantity1;
	}

	private void validationPriceOffer(Integer aPrice, Integer minPrice, Integer maxPrice) 
	{
		if(aPrice >= this.price)
			throw new IrrationalPriceException("El precio de la oferta debe ser menor al precio normal del menú"); 
		if(aPrice < minPrice)
			throw new IrrationalPriceException("El precio de la oferta debe ser mayor a " + minPrice + ".");
		if(aPrice > maxPrice)
			throw new IrrationalPriceException("El precio de la oferta debe ser menor a " + maxPrice + ".");
	}

	private void validationQuantityOffer(Integer aQuantity, Integer minQuantity, Integer maxQuantity) 
	{
		if(aQuantity > this.dailyStock)
			
			throw new IrrationalAmountException("La cantidad mínima de la oferta no puede ser mayor al del stock diario");
		if(aQuantity < minQuantity)
			throw new IrrationalAmountException("La cantidad mínima de la oferta debe ser mayor a " + minQuantity + " unidades");
		if(aQuantity > maxQuantity)	
			throw new IrrationalAmountException("La cantidad mínima de la oferta no puede superar las " + maxQuantity + " unidades");
	}

	private @NonNull Integer validateDailyStock(Integer dailyStock) 
	{
		if(dailyStock <= 0)
			throw new IrrationalAmountException("El menú debe tener al menos una unidad diaria disponible");
		return dailyStock;
	}

	private @NonNull Integer validatePrice(Integer price) 
	{
		if(price <= 0)
			throw new IrrationalPriceException("El menú debe tener un valor mayor a 0");
		return price;
	}

	private @NonNull List<LocalTime> validateDeliverySchedules(List<LocalTime> deliverySchedules) 
	{
		validateNotEmptyList(deliverySchedules, "horarios de entrega");
		if(deliverySchedules.size() !=2)
			throw new DataIncompleteException("El menú debe tener un horario de entrega inicial y final");
		return deliverySchedules;
	}

	private @NonNull List<GregorianCalendar> validateEfectiveDate(List<GregorianCalendar> efectiveDate) 
	{
		validateNotEmptyList(efectiveDate, "fecha de vigencia");
		if(efectiveDate.size() !=2)
			throw new DataIncompleteException("El menú debe tener una fecha de vigencia inicial y otra de su finalización.");
		return efectiveDate;
	}

	private @NonNull Integer validateDeliveryPrice(Integer price) 
	{
		if(price > 40)
			throw new IrrationalPriceException("El precio máximo de envío es de 40");
		if(price < 10 && price != 0)
			throw new IrrationalPriceException("El precio mínimo de envío es de 10");
		return price;
	}

	private <T> List<T> validateNotEmptyList(List<T> parameter, String parameterName) {
		if(parameter.isEmpty())
	           throw new EmptyListException("El campo " + parameterName + " no puede ser vacío");
		return parameter;
	}

	private @NonNull String validateDescription(String description) 
	{
		Integer size = description.length();
		if(size < 20)
			throw new DescriptionLengthException("La descripción del menú debe tener al menos 20 caracteres.");
		if(size > 40)
			throw new DescriptionLengthException("La descripción del menú debe tener hasta 40 caracteres.");
		return validateNotEmpty(description, "descripción");
	}

	private @NonNull String validateName(String name) 
	{
		Integer size = name.length();
		if( size < 4)
			throw new NameLengthException("El nombre del menú debe tener al menos 4 caracteres.");
		if( size > 30)
			throw new NameLengthException("El nombre del menú debe tener hasta 30 caracteres.");
		return validateNotEmpty(name, "nombre");
	}

	private String validateNotEmpty(String parameter, String parameterName) 
	{
		 if(parameter.isEmpty())
	            throw new EmptyStringException("El campo " + parameterName + " no puede ser vacío");
		 return parameter;
	}

	public void setName(String name) {
		this.name = validateName(name);
	}

	public void setDescription(String description) {
		this.description = validateDescription(description);
	}

	public void setCategory(List<Category> category) {
		this.category = validateNotEmptyList(category, "categorías");
	}

	public void setDeliveryPrice(Integer deliveryPrice) {
		this.deliveryPrice = validateDeliveryPrice(deliveryPrice);
	}

	public void setEfectiveDate(List<GregorianCalendar> efectiveDate) {
		this.efectiveDate = validateEfectiveDate(efectiveDate);
	}

	public void setDeliverySchedules(List<LocalTime> deliverySchedules) {
		this.deliverySchedules = validateDeliverySchedules(deliverySchedules);
	}

	public void setAverigeDeliveryTime(@NonNull LocalTime averigeDeliveryTime) {
		this.averigeDeliveryTime = averigeDeliveryTime;
	}

	public void setPrice(Integer price) {
		this.price = validatePrice(price);
	}
	
	public void setOffer1(Offer aOffer){
		this.offer1 = validationOffert1(aOffer);
	}

	public void setMinQuantity1(Integer minQuantity1) {
		//this.minQuantity1 = minQuantity1;
	}

	public void setMinPrice1(Integer minPrice1) {
		//this.minPrice1 = minPrice1;
	}

	public void setMinQuantity2(Integer minQuantity2) {
		//this.minQuantity2 = minQuantity2;
	}

	public void setMinPrice2(Integer minPrice2) {
		//this.minPrice2 = minPrice2;
	}

	public void setDailyStock(Integer dailyStock) {
		this.dailyStock = validateDailyStock(dailyStock);
	}
	
}
