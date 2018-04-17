package exception;

public class NoBasketItemsException extends Exception {

    public static final String MESSAGE = "Basket is empty";

    public NoBasketItemsException() {
        super(MESSAGE);
    }
}
