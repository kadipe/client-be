package com.kadipe.common;

public abstract class ResponseRoot {

    private Integer statusCode;
    private String message;

    public ResponseRoot() {
        setStatusCode(ResponseCode.OK.getStatusCode());
        setMessage(ResponseCode.OK.getMessage());
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
