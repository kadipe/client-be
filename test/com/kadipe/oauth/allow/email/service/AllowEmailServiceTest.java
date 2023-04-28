package com.kadipe.oauth.allow.email.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AllowEmailServiceTest {

    @Autowired
    AllowEmailService allowEmailService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void allowEmail() {
        List<String> listEmail = new ArrayList<>();
        listEmail.add("77f11bce-f389-4b28-ab80-ac5a5536d91e");
        listEmail.add("e98c446e-e887-4d6f-8498-d0f8e0197659");
        allowEmailService.allowEmail("Bearer 8e6ab42a21df9bf2cf134b00cff61bd843089bf9330454b53cbe81f72995688b", listEmail);
    }
}