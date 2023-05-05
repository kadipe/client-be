package com.kadipe.demo.user.model;

public class TokenRequest {

    public TokenRequest() {
    }

    public TokenRequest(String code, String redirectURI) {
        this.code = code;
        this.redirectURI = redirectURI;
    }

    private String code;
    private String redirectURI;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirectURI() {
        return redirectURI;
    }

    public void setRedirectURI(String redirectURI) {
        this.redirectURI = redirectURI;
    }
}