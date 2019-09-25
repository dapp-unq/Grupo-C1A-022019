package ar.edu.unq.desapp.grupoa.model.exceptions;

public class InvalidPhoneNumberException extends RuntimeException {

	public InvalidPhoneNumberException(String message){
        super(message);
	}
}
