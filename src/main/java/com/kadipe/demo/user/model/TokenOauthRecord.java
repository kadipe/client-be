package com.kadipe.demo.user.model;

public record TokenOauthRecord(
        String accessToken,
        Integer expiresIn,
        String refreshToken,
        String jwtToken) {}
