package com.kadipe.iam.recover.service;

import com.kadipe.iam.recover.model.RecoverDTO;
import com.kadipe.profile.email.exception.EmailNotExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RecoverServiceTest {

    @Autowired
    RecoverService recoverService;

    @Test
    void askRecover() {
        RecoverDTO recoverDTO = new RecoverDTO();
        recoverDTO.setEmail("jorge@mail.com");
        try {
            recoverService.askRecover(recoverDTO);
        } catch (EmailNotExistException e) {
            throw new RuntimeException(e);
        }
    }
}