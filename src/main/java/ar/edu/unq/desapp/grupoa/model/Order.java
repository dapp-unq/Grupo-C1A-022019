package ar.edu.unq.desapp.grupoa.model;

import java.util.GregorianCalendar;
import lombok.Getter;

@Getter
public class Order {
	private Menu menu;
	private GregorianCalendar dateHoursDelivery;
	private GregorianCalendar dateHoursOrder;
	private Integer quantity;
	private DeliveryType typeDelivery;
	private Status status;
	private Integer value;
	
	public Order(Menu menu, GregorianCalendar dateHoursDelivery, GregorianCalendar dateHoursOrder, Integer quantity,
			DeliveryType typeDelivery, Status status) {
		super();
		this.menu = menu;
		this.dateHoursDelivery = dateHoursDelivery;
		this.dateHoursOrder = dateHoursOrder;
		this.quantity = quantity;
		this.typeDelivery = typeDelivery;
		this.status = status;
		this.value = this.calculateValue(quantity);
	}

	// falta testear
	private Integer calculateValue(Integer quantity) 
	{
		return (this.menu.valueForQuantity(quantity) * quantity) + deliveryPrice();
	}
	
	// falta testear
	private Integer deliveryPrice()
	{
		Integer price = 0;
		if (typeDelivery.equals(DeliveryType.Home_delivery))
			price = this.menu.getDeliveryPrice();
		return price;
	}

}
