package com.kadipe.common;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MasterEntityTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testToString() {
        TestEntity testEntity = new TestEntity();
        System.out.println(testEntity.toString());
    }

    @AfterEach
    void tearDown() {
    }
}