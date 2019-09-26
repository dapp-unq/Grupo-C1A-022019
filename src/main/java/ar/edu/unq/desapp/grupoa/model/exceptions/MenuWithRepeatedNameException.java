package ar.edu.unq.desapp.grupoa.model.exceptions;

public class MenuWithRepeatedNameException extends RuntimeException {
    public MenuWithRepeatedNameException(String message) {
        super(message);
    }
}
