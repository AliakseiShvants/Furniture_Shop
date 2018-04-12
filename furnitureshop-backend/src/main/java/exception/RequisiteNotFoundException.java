package exception;

public class RequisiteNotFoundException extends Exception{

    private static final String MESSAGE = "Requisite not found";

    public RequisiteNotFoundException() {
        super(MESSAGE);
    }
}
