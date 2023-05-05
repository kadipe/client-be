package com.kadipe.demo.user.model;

public record TokenOAuthRecord(
        String accessToken,
        Integer expiresIn,
        String refreshToken,
        String jwtToken) {}
