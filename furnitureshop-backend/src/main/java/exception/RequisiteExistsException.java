package exception;

public class RequisiteExistsException extends Exception {

    private static final String MESSAGE = "Requisite not found";

    public RequisiteExistsException() {
        super(MESSAGE);
    }
}
