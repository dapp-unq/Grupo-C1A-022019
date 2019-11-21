package ar.edu.unq.desapp.grupoa.model;

import ar.edu.unq.desapp.grupoa.model.exceptions.EmptyStringException;
import ar.edu.unq.desapp.grupoa.model.exceptions.InsufficientCurrencyException;
import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidEmailException;
import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidPhoneNumberException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@NonNull
@Entity
@Table(name = "users")
@SequenceGenerator(name="UserSeq", sequenceName="USERseq", allocationSize=1)
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserSeq")
    private Long id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    private String location;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    @Setter
    private List<Order> orderHistory;
    @Setter
    private BigDecimal balance;

    @Transient
    private final Pattern VALID_EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);
    @Transient
    private final Pattern VALID_PHONE_REGEX = Pattern.compile("^\\+(?:[0-9]?){6,14}[0-9]$");

    public User(final String name, final String surname, final String email, final String phoneNumber, final String location) {
        this.name = validateNotEmpty(name, "nombre");
        this.surname = validateNotEmpty(surname, "apellido");
        this.email = isValidEmail(email);
        this.phoneNumber = isValidPhone(phoneNumber);
        this.location = validateNotEmpty(location, "dirección");
        this.orderHistory = new ArrayList<Order>();
        this.balance = BigDecimal.ZERO;
    }

    private String isValidEmail(final String email) {
        Matcher matcher = VALID_EMAIL_REGEX.matcher(email);
        if (!matcher.find())
            throw new InvalidEmailException("El email ingresado es inválido");
        return email;
    }

    private String isValidPhone(final String phone) {
        Matcher matcher = VALID_PHONE_REGEX.matcher(phone);
        if (!matcher.find())
            throw new InvalidPhoneNumberException("El telefono ingresado es invalido");
        return phone;
    }

    private String validateNotEmpty(final String parameter, String parameterName) {
        if (parameter.isEmpty())
            throw new EmptyStringException("El campo " + parameterName + " no puede ser vacío");
        return parameter;
    }

    public void setName(final String name) {
        this.name = validateNotEmpty(name, "nombre");
    }

    public void setSurname(final String surname) {
        this.surname = validateNotEmpty(surname, "apellido");
    }

    public void setEmail(final String email) {
        this.email = isValidEmail(email);
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = isValidPhone(phoneNumber);
    }

    public void setLocation(final String location) {
        this.location = validateNotEmpty(location, "dirección");
    }

    public void addHistoryOrder(final Order newOrder) {
        this.orderHistory.add(newOrder);
    }

    public Boolean hasPendingRanking() {
        return this.orderHistory.stream().anyMatch(order -> order.getRanking() == 0);
    }

    public Boolean hasName(final String userName) {
        return this.name.equals(userName);
    }

    public void rankIt(final Order order, final Integer rank) {
        order.getMenu().rankIt(rank);
        order.setRanking(rank);
    }

    public void addMoney(BigDecimal currency) {
        this.balance = balance.add(currency);
    }

    public void discountMoney(BigDecimal currency) throws InsufficientCurrencyException {
        BigDecimal result = this.balance.subtract(currency);
        if (isBelowZero(result))
            throw new InsufficientCurrencyException("Saldo insuficiente para efectuar la compra");
        else
            this.balance = result;
    }

    private boolean isBelowZero(BigDecimal aNumber) {
        return aNumber.compareTo(BigDecimal.ZERO) < 0;
    }
}