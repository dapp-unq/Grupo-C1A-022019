package ar.edu.unq.desapp.grupoA.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.edu.unq.desapp.grupoA.model.Exception.DescriptionLengthException;
import ar.edu.unq.desapp.grupoA.model.Exception.EmptyStringException;
import ar.edu.unq.desapp.grupoA.model.Exception.InvalidEmailException;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Provider {

    @NonNull private String name;
    @NonNull private String logo;
    @NonNull private City city;
    @NonNull private String location;
    @NonNull private String description;
    @NonNull private String website;
    @NonNull private String email;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public Provider(String name, String logo, City city, String location, String description, 
    		String website, String email)
    {
        this.name = validateNotEmpty(name, "nombre");
        this.logo = validateNotEmpty(logo, "logo");
        this.city = validateNotEmptyCity(city, "localidad");
        this.location = validateNotEmpty(location, "direccion");
        this.description = validateDescriptionSize(description);
        this.website = validateNotNull(website, "sitio web");
        this.email = validateEmail(email);
    }

	private String validateEmail(String email) 
	{
		validateNotEmpty(email, "e-mail");
		if (! validate(email))
			throw new InvalidEmailException("El email no es válido");
		return email;
	}
    
    public static boolean validate(String emailStr) {
    	Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
    	return matcher.find();
    }

    private String validateNotNull(String parameter, String parameterName) {
        if(parameter.equals(null))
            throw new NullPointerException("El campo " + parameterName + " no puede ser vacío");
        return parameter;
    }
    
    private String validateDescriptionSize(String description) {
        int length = description.length();
        if(length < 30)
            throw new DescriptionLengthException("La descripción debe tener al menos 30 caracteres");
        if(length > 200)
            throw new DescriptionLengthException("La descripción debe tener como máximo 200 caracteres");
        return validateNotEmpty(description, "descripción");
    }

    private String validateNotEmpty(String parameter, String parameterName) {
        if(parameter.isEmpty())
            throw new EmptyStringException("El campo " + parameterName + " no puede ser vacío");
        return parameter;
    }
    
    private City validateNotEmptyCity(City parameter, String parameterName) {
        if(parameter.toString().isEmpty())
            throw new NullPointerException("El campo " + parameterName + " no puede ser vacío");
        return parameter;
    }

    public void setLogo(String logo){
        this.logo = validateNotEmpty(logo, "logo");
    }

    public void setCity(City city){
        this.city = validateNotEmptyCity(city, "localidad");
    }

    public void setName(String name){
        this.name = validateNotEmpty(name, "nombre");
    }

    public void setLocation(String location){
        this.location = validateNotEmpty(location, "direccion");
    }

    public void setDescription(String description){
        this.description = validateDescriptionSize(description);
    }
    
    public void setWebsite(String website){
        this.website = validateNotNull(website, "sitio web");
    }
    
    public void setEmail(String email){
        this.email = validateEmail(email);
    }

}
