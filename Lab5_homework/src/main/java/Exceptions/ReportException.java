package Exceptions;

public class ReportException extends Exception {
    public ReportException(Exception ex) {
        super("Error at creating report", ex);
    }
}
