package exception;

public class ProductExistsException extends Exception {

    private static final String MESSAGE = "Product not exists!";

    public ProductExistsException() {
        super(MESSAGE);
    }
}
