package ar.edu.unq.desapp.grupoa.model.exceptions;

public class UserServiceException extends RuntimeException {
    public UserServiceException(String message, Exception ex) {
        super(message,ex);
    }
}
