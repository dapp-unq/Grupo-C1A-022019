package ar.edu.unq.desapp.grupoa.model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import lombok.Getter;

@Getter
public class ViandasYa 
{
	private List<Provider> providers;
	private List<User> clients;
	
	public ViandasYa()
	{
		this.providers = new ArrayList<Provider>();
		this.clients = new ArrayList<User>();
	}
	
	public void addProvider(Provider aProvider)
	{
		// Falta verificar que el nombre del proveedor no este en uso.
		this.providers.add(aProvider);
	}
	
	public void addUser(User aUser)
	{
		// Falta verificar que el nombre del usuario no este en uso.
		this.clients.add(aUser);
	}
	
	public Order purchase(User aUser, Provider aProvider, Menu aMenu, Integer aQuantity, DeliveryType typeDelivery, GregorianCalendar dateHoursDelivery, GregorianCalendar dateHoursOrder)
	{
		Status status = Status.In_Progress;
		Order newOrder = this.makeOrder(aMenu, dateHoursDelivery, dateHoursOrder, aQuantity, typeDelivery, status);
		aProvider.addOrder(aUser, newOrder);
		aUser.addHistoryOrder(newOrder);
    	// Sent mail to Provider
    	// Sent mail to User
    	// Discount balances.
		return newOrder;
	}

	// FALTA TESTEAR
	private Order makeOrder(Menu aMenu, GregorianCalendar dateHoursDelivery,
			GregorianCalendar dateHoursOrder, Integer aQuantity, DeliveryType typeDelivery, Status status) {
		aMenu.validationNumberMenuOrdered(aQuantity);
    	aMenu.validationDateDeliveryMenuOrdered(dateHoursOrder , dateHoursDelivery); // Falta considerar los días no hábiles de un servicio público.
    	return new Order(aMenu, dateHoursDelivery, dateHoursOrder, aQuantity, typeDelivery, status);
	}
	
	public List<Menu> searchMenuNamesMatchedWith(String text)
	{
		List<Menu> result = new ArrayList<Menu>();
		this.providers.forEach(provider -> result.addAll(provider.menusWithNameMatchedWith(text)));
		return result;
	}
	
	public List<Menu> searchMenusWithCategory(Category category)
	{
		List<Menu> result = new ArrayList<Menu>();
		this.providers.forEach(provider -> result.addAll(provider.menusWithCategory(category)));
		return result;
	}
	
	public List<Menu> searchMenusWithLocation(City city)
	{
		List<Menu> result = new ArrayList<Menu>();
		this.providers.forEach(provider -> result.addAll(provider.menusWithLocation(city)));
		return result;
	}
}
