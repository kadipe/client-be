package com.kadipe.iam.validation.service;

import com.kadipe.iam.signup.exception.SignupExpiredException;
import com.kadipe.iam.validation.exception.CodeNotExistException;
import com.kadipe.iam.validation.model.ValidationCodeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;

@SpringBootTest
class ValidationServiceTest {

    @Autowired
    ValidationService validationService;

    @Test
    void checkCode() {
        ValidationCodeDTO validationCodeDTO = new ValidationCodeDTO();
        validationCodeDTO.setCode("712596");
        validationCodeDTO.setTypeValidation("SIGNUP_TYPE");
        validationCodeDTO.setId("eaef1718-2428-4df0-9b83-616c69096e35");
        try {
            validationService.checkCode(validationCodeDTO);
        } catch (CodeNotExistException | SignupExpiredException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}