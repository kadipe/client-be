package com.kadipe.demo.user.model;

public class CheckinRequest {

    private String code;

    public CheckinRequest(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
