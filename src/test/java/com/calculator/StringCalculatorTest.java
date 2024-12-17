package com.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    private StringCalculator stringCalculator;

    @BeforeEach
    void setUp() {
        stringCalculator = new StringCalculator();
    }

    @Test
    void getNumbers() {
        stringCalculator.setNumbers("abc");
        assertEquals("abc", stringCalculator.getNumbers());
    }

    @Test
    void add() {
        stringCalculator.setNumbers("");
        assertEquals(0, stringCalculator.add());
        stringCalculator.setNumbers("0");
        assertEquals(0, stringCalculator.add());
        stringCalculator.setNumbers("1");
        assertEquals(1, stringCalculator.add());
    }

}