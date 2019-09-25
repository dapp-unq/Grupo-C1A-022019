package ar.edu.unq.desapp.grupoA.model;

import ar.edu.unq.desapp.grupoA.model.Exception.EmptyStringException;
import ar.edu.unq.desapp.grupoA.model.Exception.InvalidEmailException;
import ar.edu.unq.desapp.grupoA.model.Exception.InvalidPhoneNumberException;
import lombok.Getter;
import lombok.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@NonNull
public class User {

    public String name;
    public String surname;
    public String email;
    public String phoneNumber;
    public String location;

    private final Pattern VALID_EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private final Pattern VALID_PHONE_REGEX = Pattern.compile("^\\+(?:[0-9]?){6,14}[0-9]$");

    public User(String name, String surname, String email, String phoneNumber, String location) {
        this.name = validateNotEmpty(name, "nombre");
        this.surname = validateNotEmpty(surname, "apellido");
        this.email = isValidEmail(email);
        this.phoneNumber = isValidPhone(phoneNumber);
        this.location = validateNotEmpty(location, "dirección");
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
}