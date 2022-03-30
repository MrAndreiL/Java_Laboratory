package mainPackage;

public class ViewException extends Exception{
    public ViewException(Exception ex) {
        super("Invalid file", ex);
    }
}
