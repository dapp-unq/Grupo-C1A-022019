package ar.edu.unq.desapp.grupoa.model.exceptions;

public class InvalidEmailException extends RuntimeException {

	public InvalidEmailException(String message){
        super(message);
    }
}
