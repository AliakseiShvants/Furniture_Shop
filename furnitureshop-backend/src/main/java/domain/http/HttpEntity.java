package domain.http;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A proxy object for request/response to UI.
 * @param <T>
 */
@Component
@Scope("prototype")
public class HttpEntity<T> {

    private String[] authorization;
    private T body;
    private boolean success = true;

    public HttpEntity() {
    }

    public String[] getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String[] authorization) {
        this.authorization = authorization;
    }

    public HttpEntity(String[] authorization) {
        this.authorization = authorization;
    }

    public HttpEntity(T body) {
        this.body = body;
    }

    public HttpEntity(boolean success) {
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
