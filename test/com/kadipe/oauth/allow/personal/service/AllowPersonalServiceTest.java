package com.kadipe.oauth.allow.personal.service;

import com.kadipe.oauth.allow.personal.model.AllowPersonalDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AllowPersonalServiceTest {

    @Autowired
    AllowPersonalService allowPersonalService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void allowPersonal() {

        AllowPersonalDTO allowPersonalDTO = new AllowPersonalDTO();
        allowPersonalDTO.setName("Y");
        allowPersonalDTO.setBirthday("Y");
        allowPersonalDTO.setMotherName("Y");
        allowPersonalDTO.setFatherName("Y");
        allowPersonalDTO.setGender("N");
        allowPersonalDTO.setMaritalState("N");
        allowPersonalDTO.setBlood("Y");
        allowPersonalDTO.setRh("Y");

        allowPersonalService.allowPersonal("Bearer 8e6ab42a21df9bf2cf134b00cff61bd843089bf9330454b53cbe81f72995688b", allowPersonalDTO);
    }
}