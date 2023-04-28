package com.kadipe.iam.signup.service;

import com.kadipe.iam.signup.exception.SignupException;
import com.kadipe.iam.signup.model.SignupDTO;
import com.kadipe.iam.validation.model.ValidationCodeDTO;
import com.kadipe.profile.email.exception.EmailExistException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SignupServiceTest {

    @Autowired
    SignupService signupService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void makeSignup() {
        SignupDTO signupDTO = new SignupDTO();
        signupDTO.setEmail("jorge@mail.com");
        signupDTO.setName("Jorge Nascimento");
        signupDTO.setPassword("123");
        try {
            ValidationCodeDTO validationCodeDTO = signupService.makeSignup(signupDTO);
            assertNotNull(validationCodeDTO);
        } catch (EmailExistException | SignupException e) {
            throw new RuntimeException(e);
        }
    }
}