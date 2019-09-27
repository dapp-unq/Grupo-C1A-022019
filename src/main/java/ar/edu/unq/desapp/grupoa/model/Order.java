package ar.edu.unq.desapp.grupoa.model;

import java.util.GregorianCalendar;
import lombok.Getter;

@Getter
public class Order {
	private String nameMenu;
	private GregorianCalendar dateHoursDelivery;
	private GregorianCalendar dateHoursOrder;
	
	public Order(String nameMenu, GregorianCalendar dateHoursDelivery, GregorianCalendar dateHoursOrder) {
		this.nameMenu = nameMenu;
		this.dateHoursDelivery = dateHoursDelivery;
		this.dateHoursOrder = dateHoursOrder;
	}

}
