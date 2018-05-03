package exception;
@Deprecated
public class CategoryExistsException extends Exception {

    private static final String MESSAGE = "Category not exists!";

    public CategoryExistsException() {
        super(MESSAGE);
    }
}
