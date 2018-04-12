package exception;

public class OrderExistsException extends Exception {

    private static final String MESSAGE = "Order not exists!";

    public OrderExistsException() {
        super(MESSAGE);
    }
}
