package ar.edu.unq.desapp.grupoA.model;

import java.time.LocalTime;
import java.util.GregorianCalendar;
import java.util.List;

import ar.edu.unq.desapp.grupoA.model.Exception.DataIncompleteException;
import ar.edu.unq.desapp.grupoA.model.Exception.DescriptionLengthException;
import ar.edu.unq.desapp.grupoA.model.Exception.EmptyListException;
import ar.edu.unq.desapp.grupoA.model.Exception.EmptyStringException;
import ar.edu.unq.desapp.grupoA.model.Exception.IrrationalPriceException;
import ar.edu.unq.desapp.grupoA.model.Exception.NameLengthException;
import ar.edu.unq.desapp.grupoA.model.Exception.PriceAmountException;
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
	@NonNull private Integer minQuantity1;
	@NonNull private Integer minPrice1;
	@NonNull private Integer minQuantity2;
	@NonNull private Integer minPrice2;
	@NonNull private Integer dailyStock;
	
	public Menu(String name, String description, List<Category> category, Integer deliveryPrice, 
			List<GregorianCalendar> efectiveDate, List<LocalTime> deliverySchedules, 
			@NonNull LocalTime averigeDeliveryTime, Integer price, Integer minQuantity1, Integer minPrice1,
			Integer minQuantity2, Integer minPrice2, Integer dailyStock) 
	{
		this.name = validateName(name);
		this.description = validateDescription(description);
		this.category = validateNotEmptyList(category, "categorías");
		this.deliveryPrice = validateDeliveryPrice(deliveryPrice);
		this.efectiveDate = validateEfectiveDate(efectiveDate);
		this.deliverySchedules = validateDeliverySchedules(deliverySchedules);
		this.averigeDeliveryTime = averigeDeliveryTime;
		this.price = validatePrice(price);
		this.minQuantity1 = minQuantity1;
		this.minPrice1 = minPrice1;
		this.minQuantity2 = minQuantity2;
		this.minPrice2 = minPrice2;
		this.dailyStock = dailyStock;
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
			throw new PriceAmountException("El monto máximo es de 40");
		if(price < 10 && price != 0)
			throw new PriceAmountException("El monto mínimo es de 10");
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

	public void setMinQuantity1(Integer minQuantity1) {
		this.minQuantity1 = minQuantity1;
	}

	public void setMinPrice1(Integer minPrice1) {
		this.minPrice1 = minPrice1;
	}

	public void setMinQuantity2(Integer minQuantity2) {
		this.minQuantity2 = minQuantity2;
	}

	public void setMinPrice2(Integer minPrice2) {
		this.minPrice2 = minPrice2;
	}

	public void setDailyStock(Integer dailyStock) {
		this.dailyStock = dailyStock;
	}
	
}
