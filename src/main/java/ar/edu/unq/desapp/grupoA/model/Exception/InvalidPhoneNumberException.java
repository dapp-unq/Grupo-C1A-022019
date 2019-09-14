package ar.edu.unq.desapp.grupoA.model.Exception;

public class InvalidPhoneNumberException extends RuntimeException {

	public InvalidPhoneNumberException(String message){
        super(message);
	}
}
