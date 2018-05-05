package entity;

public class UIResponse<T> {

    private boolean isSuccess;
    private T body;
    private Exception exception;

    public UIResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public UIResponse(Boolean isSuccess, T body) {
        this.isSuccess = isSuccess;
        this.body = body;
    }

    public UIResponse(Exception exception) {
        this.exception = exception;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
