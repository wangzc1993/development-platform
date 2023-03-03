package org.wangzc.development.platform.model.api.engine.exception;

public class ReturnException extends TagException {

    private Object data;

    public ReturnException(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
