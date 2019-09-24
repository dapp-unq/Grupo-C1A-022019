package ar.edu.unq.desapp.grupoA.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class MenuBuilder 
{
	private String name = "no name";
	private String description = "no menu description 1";
	private List<Category> category = inicializeCategory();
	private Integer deliveryPrice = 30;
	private List<GregorianCalendar> efectiveDate = inicializeEfectiveDate();
	private List<LocalTime> deliverySchedules = inicializeDeliverySchedules();
	private LocalTime averigeDeliveryTime = LocalTime.of(0, 30);
	private Integer price = 10;
	private Integer minQuantity1 = 70;
	private Integer minPrice1 = 100;
	private Integer minQuantity2 = 150;
	private Integer minPrice2 = 100;
	private Integer dailyStock = 1000;
	
	private List<Category> inicializeCategory() 
	{
		List<Category> categorias = new ArrayList<Category>();
		categorias.add(Category.Green);
		return categorias;
	}

	private List<LocalTime> inicializeDeliverySchedules() 
	{
		List<LocalTime> horariosDeEntrega = new ArrayList<LocalTime>();
		horariosDeEntrega.add(LocalTime.of(10, 0));
		horariosDeEntrega.add(LocalTime.of(20, 0));
		return horariosDeEntrega;
	}

	private List<GregorianCalendar> inicializeEfectiveDate() 
	{
		List<GregorianCalendar> fechasDisponible = new ArrayList<GregorianCalendar>();
		fechasDisponible.add(new GregorianCalendar(2019,1,1));
		fechasDisponible.add(new GregorianCalendar(2019,12,30));
		return fechasDisponible;
	}
	
	public static MenuBuilder aMenu()
	{
		return new MenuBuilder();
	}
	
	public Menu build()
	{
		return new Menu(name, description, category, deliveryPrice, efectiveDate, deliverySchedules, averigeDeliveryTime, price, minQuantity1, minPrice1, minQuantity2, minPrice2, dailyStock);
	}
	
	public MenuBuilder withName(String aName)
	{
		this.name = aName;
		return this;
	}
	
	public MenuBuilder withDescription(String aDescription)
	{
		this.description = aDescription;
		return this;
	}
	
	public MenuBuilder withCategory(List<Category> categories)
	{
		this.category = categories;
		return this;
	}
	
	public MenuBuilder withDeliveryPrice(Integer aPrice)
	{
		this.deliveryPrice = aPrice;
		return this;
	}
	
	public MenuBuilder withEfectiveDate(List<GregorianCalendar> dates)
	{
		this.efectiveDate = dates;
		return this;
	}
	
	public MenuBuilder withDeliverySchedules(List<LocalTime> times)
	{
		this.deliverySchedules = times;
		return this;
	}
	
	public MenuBuilder withAverigeDeliveryTime(LocalTime aTime)
	{
		this.averigeDeliveryTime = aTime;
		return this;
	}
	
	public MenuBuilder withPrice(Integer aPrice)
	{
		this.price = aPrice;
		return this;
	}
	
	public MenuBuilder withMinQuantity1(Integer aQuantity)
	{
		this.minQuantity1 = aQuantity;
		return this;
	}
	
	public MenuBuilder withMinQuantity2(Integer aQuantity)
	{
		this.minQuantity2 = aQuantity;
		return this;
	}
	
	public MenuBuilder withMinPrice1(Integer aPrice)
	{
		this.minPrice1 = aPrice;
		return this;
	}
	
	public MenuBuilder withMinPrice2(Integer aPrice)
	{
		this.minPrice2 = aPrice;
		return this;
	}
	
	public MenuBuilder withDailyStock(Integer aStock)
	{
		this.dailyStock = aStock;
		return this;
	}
	
}
