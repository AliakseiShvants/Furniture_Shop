package exception;

public class StorageNotFoundException extends Exception {

    private static final String MESSAGE = "This product is not available";

    public StorageNotFoundException() {
        super(MESSAGE);
    }
}
