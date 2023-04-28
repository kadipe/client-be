package com.kadipe.iam.login.service;

import com.kadipe.iam.login.exception.InvalidTokenException;
import com.kadipe.iam.login.exception.LoginException;
import com.kadipe.iam.login.model.AuthDTO;
import com.kadipe.profile.email.exception.EmailNotExistException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    LoginService loginService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void makeLogin() {

        AuthDTO authDTO = new AuthDTO();
        authDTO.setMail("jorge@mail.com");
        authDTO.setPassword("123");
        try {
            authDTO = loginService.makeLogin(authDTO);
            System.out.println(authDTO.getToken());
        } catch (EmailNotExistException | LoginException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void checkToken() {

        String idLogin = "f5cf78b9-4073-490a-a032-1d26da7c555a";
        String jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJLYWRpcGUgOjogSWRlbnRpdHkgdG8gRGF0YSIsInN1YiI6ImpvcmdlQG1haWwuY29tIiwiYXVkIjoiSm9yZ2UgTmFzY2ltZW50byIsImV4cCI6MTY4MDk2NDcyNH0.NkYpwjoPvjHBhLvyBy5UmF1AkFqohS-vvOkOHKNK2ks";
        try {
            loginService.checkToken(idLogin, jwtToken);
        } catch (InvalidTokenException e) {
            throw new RuntimeException(e);
        }
    }
}