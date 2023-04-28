package com.kadipe.oauth.flow.service;

import com.kadipe.oauth.flow.exception.*;
import com.kadipe.oauth.flow.model.AllowDTO;
import com.kadipe.oauth.flow.model.TokenOAuthDTO;
import com.kadipe.oauth.flow.model.UserInfoDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OAuthServiceTest {

    @Autowired
    OAuthService oAuthService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAuthorizationCode() {
        try {
            System.out.println(oAuthService.getAuthorizationCode("9ac7f411-7c40-439a-b794-66ad16476db8", "http://localhost:8080/login", "123ABC", "ABC123"));
        } catch (InvalidClientIDException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void generateAccessToken() {
        try {
            AllowDTO allowDTO = oAuthService.generateAccessToken("29c17887-f412-452b-8396-a046feebf215", "jorge@mail.com", "123");
            System.out.println(allowDTO.isAllowed());
        } catch (OAuthException | UnavailableTokenException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getTokenOauth() {
        try {
            TokenOAuthDTO authDTO = oAuthService.getTokenOauth("Basic OWFjN2Y0MTEtN2M0MC00MzlhLWI3OTQtNjZhZDE2NDc2ZGI4OjBhNGRjZjE3M2MwM2E5ZTNmNmE2OGM4Njk1MDU5ZjZlNjNhNzJkOGZkZWI2ZWVkYTU0OTMxNzE3OTA2OTJjMDQ=",
                    "79759d28-420d-4a1e-ad19-950584d33e9a",
                    "http://localhost:8080/login");
            System.out.println(authDTO.getAccessToken());
        } catch (SecretKeyInvalidException | NotAuthorizedException | TokenExpiredException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getUserInfo() {
        try {
            UserInfoDTO userInfoDTO = oAuthService.getUserInfo("Bearer 8e6ab42a21df9bf2cf134b00cff61bd843089bf9330454b53cbe81f72995688b");
            System.out.println(userInfoDTO.getPersonalDTO().getName());
        } catch (NotAuthorizedException | TokenExpiredException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void refreshToken() {
        try {
            TokenOAuthDTO authDTO = oAuthService.refreshToken("Basic OWFjN2Y0MTEtN2M0MC00MzlhLWI3OTQtNjZhZDE2NDc2ZGI4OjBhNGRjZjE3M2MwM2E5ZTNmNmE2OGM4Njk1MDU5ZjZlNjNhNzJkOGZkZWI2ZWVkYTU0OTMxNzE3OTA2OTJjMDQ=",
                    "59d557d392ac9e770c028f2beca676b6909da9088763aa6c5b31c84e56364b3c");
            System.out.println(authDTO.getRefreshToken());
        } catch (SecretKeyInvalidException | NotAuthorizedException | OAuthException | TokenExpiredException e) {
            throw new RuntimeException(e);
        }
    }
}