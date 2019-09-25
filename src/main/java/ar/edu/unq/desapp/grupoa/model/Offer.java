package ar.edu.unq.desapp.grupoa.model;

import lombok.Getter;

@Getter
public class Offer {
	private Integer quantity;
	private Integer price;

	public Offer(Integer quantity, Integer price) {
		this.quantity = quantity;
		this.price = price;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Boolean isEffectiveOffer() {
		return (!this.quantity.equals(0) && !this.price.equals(0));
	}

}
