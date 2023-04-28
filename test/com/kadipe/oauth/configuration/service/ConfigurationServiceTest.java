package com.kadipe.oauth.configuration.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ConfigurationServiceTest {

    @Autowired
    ConfigurationService configurationService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void checkClientID() {

        assertTrue(configurationService.checkClientID("9ac7f411-7c40-439a-b794-66ad16476db8", "http://localhost:8080/login"));
    }
}