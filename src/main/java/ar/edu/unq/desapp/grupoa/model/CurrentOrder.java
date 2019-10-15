package ar.edu.unq.desapp.grupoa.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

@Getter
public class CurrentOrder {

	private User client;
	private List<Order> orders = new ArrayList<>();

	public CurrentOrder(User client, Order newOrder) {
		this.client = client;
		addOrder(newOrder);
	}

	public Boolean hasUser(User aUser) {
		return this.client.equals(aUser);
	}

	public void addOrder(Order newOrder) {
		this.orders.add(newOrder);
	}

}
