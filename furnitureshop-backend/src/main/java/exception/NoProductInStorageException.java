package exception;

public class NoProductInStorageException extends Exception {

    public static final String MESSAGE = "is not available in the storage.";

    public NoProductInStorageException(String product) {
        super(product + MESSAGE);
    }
}
