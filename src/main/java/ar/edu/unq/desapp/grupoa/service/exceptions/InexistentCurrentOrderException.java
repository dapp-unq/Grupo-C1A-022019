package ar.edu.unq.desapp.grupoa.service.exceptions;

public class InexistentCurrentOrderException extends RuntimeException {
    public InexistentCurrentOrderException(String message) {
        super(message);
    }
}
