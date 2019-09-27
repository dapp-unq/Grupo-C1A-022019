package ar.edu.unq.desapp.grupoa.model;

import java.util.GregorianCalendar;
import lombok.Getter;

@Getter
public class CurrentOrder extends Order {

	private Integer quantity;
	private DeliveryType typeDelivery;
	private User client;
	
	public CurrentOrder(String nameMenu, Integer aQuantity, DeliveryType typeDelivery,
			GregorianCalendar dateHoursDelivery, GregorianCalendar dateHoursOrder, User client) 
	{
		super(nameMenu, dateHoursDelivery, dateHoursDelivery);
		this.quantity = aQuantity;
		this.typeDelivery = typeDelivery;
		this.client = client;
	}

}
