package ar.edu.unq.desapp.grupoa.model.Exception;

public class InvalidPhoneNumberException extends RuntimeException {

	public InvalidPhoneNumberException(String message){
        super(message);
	}
}
