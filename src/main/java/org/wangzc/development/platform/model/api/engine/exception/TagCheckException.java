package org.wangzc.development.platform.model.api.engine.exception;

public class TagCheckException extends TagException {

    private String id;

    public TagCheckException(String id, String msg) {
        super(msg);
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
