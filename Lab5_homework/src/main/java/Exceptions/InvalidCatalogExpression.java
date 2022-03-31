package Exceptions;

public class InvalidCatalogExpression extends Exception {
    public InvalidCatalogExpression(Exception ex) {
        super("Invalid catalog file", ex);
    }
}
