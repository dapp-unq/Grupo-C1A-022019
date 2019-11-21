package ar.edu.unq.desapp.grupoa.service.exceptions;

public class NotBusinessDayException extends RuntimeException {
    public NotBusinessDayException(String s) {
        super(s);
    }
}
