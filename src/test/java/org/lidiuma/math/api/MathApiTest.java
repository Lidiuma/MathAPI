package org.lidiuma.math.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MathApiTest {
    @Test
    void verifyHello() {
        assertEquals("Hello World!", new MathApi().getMessage());
    }
}
