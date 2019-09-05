package ar.edu.unq.desapp.grupoA.model.Exception;

public class InvalidEmailException extends RuntimeException {

	public InvalidEmailException(String message){
        super(message);
    }
}
