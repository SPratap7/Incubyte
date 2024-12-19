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
    void ShouldReturnZeroOnEmptyString() {
        stringCalculator.setNumbers("");
        assertEquals(0, stringCalculator.add());
    }

    @Test
    void ShouldReturnZeroOnWhiteSpace() {
        stringCalculator.setNumbers(" ");
        assertEquals(0, stringCalculator.add());
        stringCalculator.setNumbers("\n");
        assertEquals(0, stringCalculator.add());
        stringCalculator.setNumbers("  \n");
    }

    @Test
    void ShouldReturnNumbersOnNumbers() {
        assertEquals(0, stringCalculator.add());
        stringCalculator.setNumbers("0");
        assertEquals(0, stringCalculator.add());
        stringCalculator.setNumbers("1");
        assertEquals(1, stringCalculator.add());
    }

    @Test
    void ShouldReturnSumOnNumbersDelimitedByComma() {
        stringCalculator.setNumbers("1,2");
        assertEquals(3, stringCalculator.add());
        stringCalculator.setNumbers("5,4");
        assertEquals(9, stringCalculator.add());
    }

    @Test
    void ShouldAcceptNewLineAsDelimiter() {
        stringCalculator.setNumbers("5\n4,1");
        assertEquals(10, stringCalculator.add());
    }

    @Test
    void ShouldAcceptCustomDelimiter() {
        stringCalculator.setNumbers("//;\n4;1");
        assertEquals(5, stringCalculator.add());
    }

    @Test
    void ShouldThrowErrorWithNegativeNumbers() {
        try {
            stringCalculator.setNumbers("-4,1");
            stringCalculator.add();
        } catch (IllegalArgumentException e) {
            assertEquals("Negatives not allowed: -4", e.getMessage());
        }
    }

    @Test
    void ShouldNotAcceptNumberGreaterThanThousand() {
        stringCalculator.setNumbers("4,1001");
        assertEquals(4, stringCalculator.add());
    }

    @Test
    void ShouldAcceptCustomDelimiterOfAnyLength(){
        stringCalculator.setNumbers("//[***]\n1***2***3");
        assertEquals(6, stringCalculator.add());
    }

    @Test
    void ShouldAcceptMultipleCustomDelimiterOfAnyLength(){
        stringCalculator.setNumbers("//[*][%]\n1*2%3");
        assertEquals(6, stringCalculator.add());
        stringCalculator.setNumbers("//[**][%]\n1**2%3");
        assertEquals(6, stringCalculator.add());
        stringCalculator.setNumbers("//[*][%%]\n1*2%%3");
        assertEquals(6, stringCalculator.add());
    }

}