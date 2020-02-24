package ar.edu.unq.desapp.grupoa.model;

import ar.edu.unq.desapp.grupoa.model.enums.Category;
import ar.edu.unq.desapp.grupoa.model.enums.City;
import ar.edu.unq.desapp.grupoa.model.exceptions.CurrentMenuQuantityException;
import ar.edu.unq.desapp.grupoa.model.exceptions.DescriptionLengthException;
import ar.edu.unq.desapp.grupoa.model.exceptions.ElementNotFoundException;
import ar.edu.unq.desapp.grupoa.model.exceptions.EmptyServiceHoursDaysException;
import ar.edu.unq.desapp.grupoa.model.exceptions.EmptyStringException;
import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidEmailException;
import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidPhoneNumberException;
import ar.edu.unq.desapp.grupoa.model.exceptions.RepeatedNameException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NonNull
@Entity
@Table(name = "providers")
@NoArgsConstructor
@ToString
@Slf4j
public class Provider {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String logo;
    @Enumerated(STRING)
    private City city;
    private String location;
    private String description;
    private String website;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    @OneToMany(fetch = LAZY, cascade = ALL)
    @JoinColumn
    private List<ServiceHours> openingHoursDays;
    @ElementCollection(targetClass = City.class)
    @Setter
    private List<City> deliveryCities;
    @OneToMany(fetch = LAZY, cascade = ALL)
    @JoinColumn
    @Setter
    private List<Menu> currentMenus;
    @OneToMany(fetch = LAZY, cascade = ALL)
    @JoinColumn
    @Setter
    private List<CurrentOrder> orders;
    @Setter
    private BigDecimal balance;
    @Setter
    private Integer menusRemoved;

    @Transient
    private final Pattern VALID_EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);
    @Transient
    private final Pattern VALID_PHONE_REGEX = Pattern.compile("^\\+(?:[0-9]?){6,14}[0-9]$");

    public Provider(final String name, final String logo, final City city, final String location, final String description, final @NonNull String website,
                    final String email, final String phone, final List<ServiceHours> serviceHours) {
        this.name = validateNotEmpty(name, "nombre");
        this.logo = validateNotEmpty(logo, "logo");
        this.city = validateNotEmptyCity(city, "localidad");
        this.location = validateNotEmpty(location, "direccion");
        this.description = validateDescriptionSize(description);
        this.website = website;
        this.email = validateEmail(email);
        this.phoneNumber = validatePhoneNumber(phone);
        this.openingHoursDays = validateNotEmptyOpeningHoursDays(serviceHours, "horarios y días de atención");
        this.deliveryCities = new ArrayList<>();
        this.currentMenus = new ArrayList<Menu>();
        this.orders = new ArrayList<CurrentOrder>();
        this.balance = BigDecimal.ZERO;
        this.menusRemoved = 0;
    }

    private List<City> calculateDeliveryCities(final Double km, final String location) {
        List<City> cities = new ArrayList<City>();
        log.debug("km:{}, location:{}", km, location);
        // TODO: FALTA COMUNICARSE EN GMAP PARA VER TODAS LAS LOCALIDADES DONDE HACE
        // ENTREGAS DESDE LA CIUDAD DEL LOCAL.
        cities.add(City.valueOf(location));
        return cities;
    }

    private List<ServiceHours> validateNotEmptyOpeningHoursDays(final List<ServiceHours> serviceHours, final String parameterName) {
        if (serviceHours.isEmpty())
            throw new EmptyServiceHoursDaysException("El campo " + parameterName + " no puede ser vacío");
        return serviceHours;
    }

    private String validatePhoneNumber(final String phone) {
        validateNotEmpty(phone, "número de teléfono");
        if (!validatePhoneRegEx(phone))
            throw new InvalidPhoneNumberException(
                    "El número de teléfono debe componerse con '+' seguido por la característica.");
        return phone;
    }

    private boolean validatePhoneRegEx(final String phone) {
        Matcher matcher = VALID_PHONE_REGEX.matcher(phone);
        return matcher.find();
    }

    private String validateEmail(final String email) {
        validateNotEmpty(email, "e-mail");
        if (!validateEmailRegEx(email))
            throw new InvalidEmailException("El email no es válido");
        return email;
    }

    public boolean validateEmailRegEx(final String _email) {
        Matcher matcher = VALID_EMAIL_REGEX.matcher(_email);
        return matcher.find();
    }

    private String validateDescriptionSize(final String description) {
        int length = description.length();
        if (length < 30)
            throw new DescriptionLengthException("La descripción debe tener al menos 30 caracteres");
        if (length > 200)
            throw new DescriptionLengthException("La descripción debe tener como máximo 200 caracteres");
        return validateNotEmpty(description, "descripción");
    }

    private String validateNotEmpty(final String parameter, final String parameterName) {
        if (parameter.isEmpty())
            throw new EmptyStringException("El campo " + parameterName + " no puede ser vacío");
        return parameter;
    }

    private City validateNotEmptyCity(final City parameter, final String parameterName) {
        if (parameter.toString().isEmpty())
            throw new EmptyStringException("El campo " + parameterName + " no puede ser vacío");
        return parameter;
    }

    public void setLogo(final String logo) {
        this.logo = validateNotEmpty(logo, "logo");
    }

    public void setCity(final City city) {
        this.city = validateNotEmptyCity(city, "localidad");
    }

    public void setName(final String name) {
        this.name = validateNotEmpty(name, "nombre");
    }

    public void setLocation(final String location) {
        this.location = validateNotEmpty(location, "direccion");
    }

    public void setDescription(final String description) {
        this.description = validateDescriptionSize(description);
    }

    public void setWebsite(final @NonNull String website) {
        this.website = website;
    }

    public void setEmail(final String email) {
        this.email = validateEmail(email);
    }

    public void setPhoneNumber(final String phone) {
        this.phoneNumber = validatePhoneNumber(phone);
    }

    public void setOpeningHoursDays(final List<ServiceHours> serviceHours) {
        this.openingHoursDays = validateNotEmptyOpeningHoursDays(serviceHours, "horarios y días de atención");
    }

    public void setkm(final double km) {
        this.deliveryCities = calculateDeliveryCities(km, this.location);
    }

    public void addMenu(final Menu aMenu) {
        this.validationCurrentMenuQuantity(aMenu.getName());
        this.currentMenus.add(aMenu);
    }

    private void validationCurrentMenuQuantity(final String menuName) {
        if (this.currentMenus.size() == 20)
            throw new CurrentMenuQuantityException(
                    "No se puede agregar más menús: Solo se puede tener hasta 20 menús vigentes.");
        if (this.anyCurrentMenuHasName(menuName))
            throw new RepeatedNameException(
                    "Ya existe un menú con el nombre " + menuName + ". Entente con otro diferente.");
    }

    public boolean anyCurrentMenuHasName(final String menuName) {
        return this.currentMenus.stream().anyMatch(menu -> menu.hasName(menuName));
    }

    public Menu searchMenu(final String menuName) {
        return currentMenus.stream().filter(menu -> menu.hasName(menuName))
                .findFirst().orElseThrow(() -> new ElementNotFoundException("No se encontró un menú con el nombre: " + menuName));
    }

    public void removeMenuWithName(final String menuName) {
        this.cancelMenu(this.searchMenu(menuName));
    }

    public void removeMenu(final Menu aMenu) {
        this.currentMenus.remove(aMenu);
    }

    public void editMenu(final String nameMenuEdit, final Menu newMenu) {
        this.removeMenuWithName(nameMenuEdit);
        this.addMenu(newMenu);
    }

    public List<Menu> menusWithNameMatchedWith(final String text) {
        return this.currentMenus.stream().filter(menu -> menu.hasNameMatchedWith(text)).collect(Collectors.toList());
    }

    public List<Menu> menusWithCategory(final Category aCategory) {
        return this.currentMenus.stream().filter(menu -> menu.hasCategory(aCategory)).collect(Collectors.toList());
    }

    public List<Menu> menusWithLocation(final City aCity) {
        List<Menu> result = new ArrayList<Menu>();
        if (this.deliveryCities.contains(aCity))
            result = this.currentMenus;
        return result;
    }

    public void addOrder(final User user, final Order newOrder) {
        Optional<CurrentOrder> result = orders.stream().filter(order -> order.hasUser(user)).findFirst();
        if (result.isPresent())
            result.get().addOrder(newOrder);
        else
            orders.add(new CurrentOrder(user, newOrder));
    }

    public Boolean hasName(final String provideName) {
        return this.name.equals(provideName);
    }

    public void addMoney(final BigDecimal aNumber) {
        this.balance = balance.add(aNumber);
    }

    public void cancelMenu(final Menu menu) {
        this.removeMenu(menu);
        this.menusRemoved++;
    }
}
