package domain.http;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A proxy object for request/response to UI.
 * @param <T>
 */
@Component
@Scope("prototype")
public class ResponseEntity<T> {

    private T body;
    private boolean success = true;

    public ResponseEntity() {
    }

    public ResponseEntity(T body) {
        this.body = body;
    }

    public ResponseEntity(boolean success) {
        this.success = success;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
