package ar.edu.unq.desapp.grupoa.model;

import java.time.LocalDateTime;
import java.util.GregorianCalendar;

import ar.edu.unq.desapp.grupoa.model.enums.DeliveryType;
import ar.edu.unq.desapp.grupoa.model.enums.Status;
import lombok.Getter;
import lombok.NonNull;

@Getter
@NonNull
public class Order {
	private Menu menu;
	private String providerName;
	private LocalDateTime deliveryDateAndHour;
	private LocalDateTime orderDateAndHour;
	private Integer quantity;
	private DeliveryType typeDelivery;
	private Status status;
	private Integer ranking;
	private Integer value;

	public Order(Menu menu, String providerName, LocalDateTime deliveryDateAndHour, LocalDateTime orderDateAndHour, Integer quantity,
				 DeliveryType typeDelivery, Status status) {
		this.menu = menu;
		this.providerName = providerName;
		this.deliveryDateAndHour = deliveryDateAndHour;
		this.orderDateAndHour = orderDateAndHour;
		this.quantity = quantity;
		this.typeDelivery = typeDelivery;
		this.status = status;
		this.ranking = 0;
		this.value = this.calculateValue(quantity);
	}

	public void setTypeDelivery(@NonNull DeliveryType type) {
		this.typeDelivery = type;
	}

	public void setRanking(@NonNull Integer ranking) {
		this.ranking = ranking;
	}

	public Integer calculateValue(Integer quantity) {
		return (this.menu.valueForQuantity(quantity) * quantity) + deliveryPrice();
	}

	public Integer deliveryPrice() {
		Integer price = 0;
		if (typeDelivery.equals(DeliveryType.HOME_DELIVERY))
			price = this.menu.getDeliveryPrice();
		return price;
	}

}
