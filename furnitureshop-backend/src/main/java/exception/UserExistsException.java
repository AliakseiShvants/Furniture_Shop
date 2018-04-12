package exception;

public class UserExistsException extends Exception {

    private static final String MESSAGE = "User already exists";

    public UserExistsException() {
        super(MESSAGE);
    }
}
