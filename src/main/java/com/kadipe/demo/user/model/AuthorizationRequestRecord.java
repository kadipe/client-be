package com.kadipe.demo.user.model;

public record AuthorizationRequestRecord(
        String code,
        String state) {}
