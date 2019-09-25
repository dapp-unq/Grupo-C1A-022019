package ar.edu.unq.desapp.grupoa.model.Exception;

public class InvalidEmailException extends RuntimeException {

	public InvalidEmailException(String message){
        super(message);
    }
}
