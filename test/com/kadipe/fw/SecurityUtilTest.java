package com.kadipe.fw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SecurityUtilTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void crypto() {
    }

    @Test
    void generateJWT() {
        System.out.println(SecurityUtil.generateJWT("123", "d991a47f-30d4-4afa-9fe1-b3d1cdc62f85", "Kadipe :: Identity to Data"));
    }
}