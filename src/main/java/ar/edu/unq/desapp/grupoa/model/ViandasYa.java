package ar.edu.unq.desapp.grupoa.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import ar.edu.unq.desapp.grupoa.model.exceptions.InsufficientCurrencyException;
import ar.edu.unq.desapp.grupoa.model.exceptions.PurchaseException;
import ar.edu.unq.desapp.grupoa.model.exceptions.RepeatedNameException;
import lombok.Getter;

@Getter
public class ViandasYa {
	private List<Provider> providers;
	private List<User> clients;

	public ViandasYa() {
		this.providers = new ArrayList<>();
		this.clients = new ArrayList<>();
	}

	public void addProvider(Provider aProvider) {
		if (this.hasInUseProviderName(aProvider.getName()))
			throw new RepeatedNameException("Ya existe un proveedor con el nombre " + aProvider.getName());
		this.providers.add(aProvider);
	}

	public Boolean hasInUseProviderName(String providerName) {
		return this.providers.stream().anyMatch(provider -> provider.hasName(providerName));
	}

	public void addUser(User aUser) {
		if (this.hasInUseUserName(aUser.getName()))
			throw new RepeatedNameException("Ya existe un usuario con el nombre " + aUser.getName());
		this.clients.add(aUser);
	}

	public Boolean hasInUseUserName(String userName) {
		return this.clients.stream().anyMatch(client -> client.hasName(userName));
	}

	public Order purchase(User aUser, Provider aProvider, Menu aMenu, Integer aQuantity, DeliveryType typeDelivery,
			GregorianCalendar dateHoursDelivery, GregorianCalendar dateHoursOrder) throws InsufficientCurrencyException {
		this.validatedPendingRanking(aUser);
		Order newOrder = this.makeOrder(aMenu, dateHoursDelivery, dateHoursOrder, aQuantity, typeDelivery);
		aProvider.addOrder(aUser, newOrder);
		aUser.addHistoryOrder(newOrder);
		// Sent mail to Provider
		// Sent mail to User
		BigDecimal newOrderPrice = BigDecimal.valueOf(newOrder.getValue());
		aUser.discountMoney(newOrderPrice);
		aProvider.addMoney(newOrderPrice);
		return newOrder;
	}

	public void validatedPendingRanking(User aUser) {
		if (aUser.hasPendingRanking()) {
			throw new PurchaseException("No se puede realizar la compra: Tiene pedidos sin calificar.");
		}
	}

	public Order makeOrder(Menu aMenu, GregorianCalendar dateHoursDelivery, GregorianCalendar dateHoursOrder,
			Integer aQuantity, DeliveryType typeDelivery) {
		aMenu.validationNumberMenuOrdered(aQuantity);
		aMenu.validationDateDeliveryMenuOrdered(dateHoursOrder, dateHoursDelivery); // Falta considerar los días no
																					// hábiles de un servicio público.
		return new Order(aMenu, dateHoursDelivery, dateHoursOrder, aQuantity, typeDelivery, Status.In_Progress);
	}

	public List<Menu> searchMenuNamesMatchedWith(String text) {
		List<Menu> result = new ArrayList<Menu>();
		this.providers.forEach(provider -> result.addAll(provider.menusWithNameMatchedWith(text)));
		return result;
	}

	public List<Menu> searchMenusWithCategory(Category category) {
		List<Menu> result = new ArrayList<Menu>();
		this.providers.forEach(provider -> result.addAll(provider.menusWithCategory(category)));
		return result;
	}

	public List<Menu> searchMenusWithLocation(City city) {
		List<Menu> result = new ArrayList<Menu>();
		this.providers.forEach(provider -> result.addAll(provider.menusWithLocation(city)));
		return result;
	}
}
