package Exceptions;

public class InfoException extends Exception {
    public InfoException(Exception ex) {
        super("Error at parsing", ex);
    }
}
