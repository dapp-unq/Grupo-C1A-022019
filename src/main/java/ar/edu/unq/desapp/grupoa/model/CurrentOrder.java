package ar.edu.unq.desapp.grupoa.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class CurrentOrder{

	private User client;
	private List<Order> orders;
	
	public CurrentOrder(User client, Order newOrder) {
		this.client = client;
		this.orders = new ArrayList<Order>();
		orders.add(newOrder);
	}

	public Boolean hasUser(User aUser)
	{
		return this.client.equals(aUser);
	}

	public void addOrder(Order newOrder) {
		this.orders.add(newOrder);
	}

}
