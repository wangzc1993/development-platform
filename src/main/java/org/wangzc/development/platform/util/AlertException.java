package org.wangzc.development.platform.util;

public class AlertException extends Exception {

    public AlertException() {
    }

    public AlertException(String message) {
        super(message);
    }

    public AlertException(Throwable cause) {
        super(cause);
    }

    public AlertException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String toString() {
        return ExceptionUtils.toString(this);
    }
}
