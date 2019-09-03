package ar.edu.unq.desapp.grupoA.model;

import ar.edu.unq.desapp.grupoA.model.Exception.DescriptionLengthException;
import ar.edu.unq.desapp.grupoA.model.Exception.EmptyStringException;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Provider {

    @NonNull private String name;
    @NonNull private String logo;
    @NonNull private String city;
    @NonNull private String location;
    @NonNull private String description;

    public Provider(String name, String logo, String city, String location, String description){
        this.name = validateNotEmpty(name, "nombre");
        this.logo = validateNotEmpty(logo, "logo");
        this.city = validateNotEmpty(city, "localidad");
        this.location = validateNotEmpty(location, "direccion");
        this.description = validateDescriptionSize(description);
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

    public void setLogo(String logo){
        this.logo = validateNotEmpty(logo, "logo");
    }

    public void setCity(String city){
        this.city = validateNotEmpty(city, "localidad");
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

}
