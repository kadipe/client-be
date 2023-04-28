package com.kadipe.common;

public enum ResponseCode {

    OK(200),
    BAD_REQUEST(401),
    DENIED(403),
    NOT_FOUND(404),
    CONFLICT(409),
    PRECONDITION_FAILED(412);

    private ResponseCode(int statusCode) {
        this.statusCode = statusCode;
    }

    private final int statusCode;

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getMessage() {
        return this.name();
    }
}
