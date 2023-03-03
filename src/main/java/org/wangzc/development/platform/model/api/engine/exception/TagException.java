package org.wangzc.development.platform.model.api.engine.exception;

public class TagException extends ApiException {

    public TagException() {
    }

    public TagException(String message) {
        super(message);
    }

    public TagException(Throwable cause) {
        super(cause);
    }

    public TagException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
