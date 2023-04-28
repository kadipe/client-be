package com.kadipe.oauth.allow.address.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AllowAddressServiceTest {

    @Autowired
    AllowAddressService allowAddressService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void allowAddress() {
        List<String> listAddress = new ArrayList<>();
        listAddress.add("768ceb95-b972-4974-a4da-996f6a682dd4");
        listAddress.add("94975a4a-0bf3-4d2d-ad78-91c22c03f6cd");
        listAddress.add("96e2dc71-81b5-42e8-ba88-88f7d7a384b8");
        allowAddressService.allowAddress("Bearer 8e6ab42a21df9bf2cf134b00cff61bd843089bf9330454b53cbe81f72995688b", listAddress);
    }
}