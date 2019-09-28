package ar.edu.unq.desapp.grupoa.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.edu.unq.desapp.grupoa.model.exceptions.EmptyStringException;
import ar.edu.unq.desapp.grupoa.model.exceptions.InsufficientCurrencyException;
import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidEmailException;
import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidPhoneNumberException;
import lombok.Getter;
import lombok.NonNull;

@Getter
@NonNull
public class User {

	private String name;
	private String surname;
	private String email;
	private String phoneNumber;
	private String location;
	private List<Order> orderHistory;
	private BigDecimal balance;

	private final Pattern VALID_EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);
	private final Pattern VALID_PHONE_REGEX = Pattern.compile("^\\+(?:[0-9]?){6,14}[0-9]$");

	public User(String name, String surname, String email, String phoneNumber, String location) {
		this.name = validateNotEmpty(name, "nombre");
		this.surname = validateNotEmpty(surname, "apellido");
		this.email = isValidEmail(email);
		this.phoneNumber = isValidPhone(phoneNumber);
		this.location = validateNotEmpty(location, "dirección");
		this.orderHistory = new ArrayList<Order>();
		this.balance = BigDecimal.ZERO;
	}

	private String isValidEmail(String email) {
		Matcher matcher = VALID_EMAIL_REGEX.matcher(email);
		if (!matcher.find())
			throw new InvalidEmailException("El email ingresado es inválido");
		return email;
	}

	private String isValidPhone(String phone) {
		Matcher matcher = VALID_PHONE_REGEX.matcher(phone);
		if (!matcher.find())
			throw new InvalidPhoneNumberException("El telefono ingresado es invalido");
		return phone;
	}

	private String validateNotEmpty(String parameter, String parameterName) {
		if (parameter.isEmpty())
			throw new EmptyStringException("El campo " + parameterName + " no puede ser vacío");
		return parameter;
	}

	public void setName(String name) {
		this.name = validateNotEmpty(name, "nombre");
	}

	public void setSurname(String surname) {
		this.surname = validateNotEmpty(surname, "apellido");
	}

	public void setEmail(String email) {
		this.email = isValidEmail(email);
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = isValidPhone(phoneNumber);
	}

	public void setLocation(String location) {
		this.location = validateNotEmpty(location, "dirección");
	}

	public void addHistoryOrder(Order newOrder) {
		this.orderHistory.add(newOrder);
	}

	public Boolean hasPendingRanking() {
		return this.orderHistory.stream().anyMatch(order -> order.getRanking() == 0);
	}

	public Boolean hasName(String userName) {
		return this.name.equals(userName);
	}

	public void rankIt(Order order, Integer rank) {
		order.getMenu().rankIt(rank);
		order.setRanking(rank);
	}

	public void addMoney(BigDecimal currency){
		this.balance = balance.add(currency);
	}

	public void discountMoney(BigDecimal currency) throws InsufficientCurrencyException {
		BigDecimal result = this.balance.subtract(currency);
		if(isBelowZero(result))
			throw new InsufficientCurrencyException("Saldo insuficiente para efectuar la compra");
		else
			this.balance = result;
	}

	private boolean isBelowZero(BigDecimal aNumber){
		return aNumber.compareTo(BigDecimal.ZERO) < 0;
	}
}