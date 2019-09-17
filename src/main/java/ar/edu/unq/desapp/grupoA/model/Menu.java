package ar.edu.unq.desapp.grupoA.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import lombok.NonNull;

public class Menu 
{
	@NonNull private String name;
	@NonNull private String description;
	@NonNull private List<Category> category;
	private double deliveryPrice;
	@NonNull private List<GregorianCalendar> efectiveDate;
	@NonNull private List<LocalTime> deliverySchedules;
	@NonNull private LocalTime averigeDeliveryTime;
	@NonNull private double price;
	@NonNull private double minQuantity1;
	@NonNull private double minPrice1;
	private double minQuantity2;
	private double minPrice2;
	@NonNull private double dailyStock;
	
	public Menu(String name, String description, List<Category> category, double deliveryPrice, 
			List<GregorianCalendar> efectiveDate, List<LocalTime> deliverySchedules, 
			LocalTime averigeDeliveryTime, double price, double minQuantity1, double minPrice1,
			double minQuantity2, double minPrice2, double dailyStock) 
	{
		this.name = name;
		this.description = description;
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
	
}
