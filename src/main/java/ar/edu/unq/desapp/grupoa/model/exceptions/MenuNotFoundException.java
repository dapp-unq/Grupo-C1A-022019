package ar.edu.unq.desapp.grupoa.model.exceptions;

public class MenuNotFoundException extends RuntimeException {
    public MenuNotFoundException(String message){
        super(message);
    }
}
