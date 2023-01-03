package com.greenstar.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JTest {
    @DisplayName("Test MessageService.get()")
    @Test
    public void testAdd() {
        String str = "Junit is working fine";
        System.out.println("Hello");
        assertEquals("Junit is working fine",str);
    }
}
