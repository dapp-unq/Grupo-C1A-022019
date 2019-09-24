package ar.edu.unq.desapp.grupoA.model;

import java.time.LocalTime;
import java.util.GregorianCalendar;
import java.util.List;

import ar.edu.unq.desapp.grupoA.model.Exception.DescriptionLengthException;
import ar.edu.unq.desapp.grupoA.model.Exception.EmptyStringException;
import ar.edu.unq.desapp.grupoA.model.Exception.NameLengthException;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Menu 
{
	@NonNull private String name;
	@NonNull private String description;
	@NonNull private List<Category> category;
	private Double deliveryPrice;
	@NonNull private List<GregorianCalendar> efectiveDate;
	@NonNull private List<LocalTime> deliverySchedules;
	@NonNull private LocalTime averigeDeliveryTime;
	@NonNull private Double price;
	@NonNull private Double minQuantity1;
	@NonNull private Double minPrice1;
	private double minQuantity2;
	private double minPrice2;
	@NonNull private Double dailyStock;
	
	public Menu(String name, String description, List<Category> category, Double deliveryPrice, 
			List<GregorianCalendar> efectiveDate, List<LocalTime> deliverySchedules, 
			LocalTime averigeDeliveryTime, Double price, Double minQuantity1, Double minPrice1,
			Double minQuantity2, Double minPrice2, Double dailyStock) 
	{
		this.name = validateName(name);
		this.description = validateDescription(description);
		this.category = category;
		this.deliveryPrice = deliveryPrice;
		this.efectiveDate = efectiveDate;
		this.deliverySchedules = deliverySchedules;
		this.averigeDeliveryTime = averigeDeliveryTime;
		this.price = price;
		this.minQuantity1 = minQuantity1;
		this.minPrice1 = minPrice1;
		this.minQuantity2 = minQuantity2;
		this.minPrice2 = minPrice2;
		this.dailyStock = dailyStock;
	}

	private String validateDescription(String description) 
	{
		Integer size = description.length();
		if(size < 20)
			throw new DescriptionLengthException("La descripción del menú debe tener al menos 20 caracteres.");
		if(size > 40)
			throw new DescriptionLengthException("La descripción del menú debe tener menos de 40 caracteres.");
		return validateNotEmpty(description, "descripción");
	}

	private String validateName(String name) 
	{
		Integer size = name.length();
		if( size < 4)
			throw new NameLengthException("El nombre del menú debe tener al menos 4 caracteres.");
		if( size > 30)
			throw new NameLengthException("El nombre del menú debe tener menos de 30 caracteres.");
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
		this.category = category;
	}

	public void setDeliveryPrice(Double deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}

	public void setEfectiveDate(List<GregorianCalendar> efectiveDate) {
		this.efectiveDate = efectiveDate;
	}

	public void setDeliverySchedules(List<LocalTime> deliverySchedules) {
		this.deliverySchedules = deliverySchedules;
	}

	public void setAverigeDeliveryTime(LocalTime averigeDeliveryTime) {
		this.averigeDeliveryTime = averigeDeliveryTime;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setMinQuantity1(Double minQuantity1) {
		this.minQuantity1 = minQuantity1;
	}

	public void setMinPrice1(Double minPrice1) {
		this.minPrice1 = minPrice1;
	}

	public void setMinQuantity2(double minQuantity2) {
		this.minQuantity2 = minQuantity2;
	}

	public void setMinPrice2(double minPrice2) {
		this.minPrice2 = minPrice2;
	}

	public void setDailyStock(Double dailyStock) {
		this.dailyStock = dailyStock;
	}
	
}
