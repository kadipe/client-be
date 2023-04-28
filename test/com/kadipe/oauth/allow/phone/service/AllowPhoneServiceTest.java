package com.kadipe.oauth.allow.phone.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AllowPhoneServiceTest {

    @Autowired
    AllowPhoneService allowPhoneService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void allowPhone() {
        List<String> listPhone = new ArrayList<>();
        listPhone.add("ba5421d0-83ea-4b95-90c8-531f1c4aa4b7");
        listPhone.add("f01ef5c4-8f67-4e87-9b69-a481a20e342d");
        listPhone.add("fc6e32fb-1abe-4002-814c-b45e758bed53");
        allowPhoneService.allowPhone("Bearer 8e6ab42a21df9bf2cf134b00cff61bd843089bf9330454b53cbe81f72995688b", listPhone);
    }
}