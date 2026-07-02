package com.bank;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * CalculatorTest - JUnit test class for Calculator
 */
public class CalculatorTest {
    
    private Calculator calculator;
    
    @Before
    public void setUp() {
        calculator = new Calculator();
        System.out.println("✓ Calculator setup for test");
    }
    
    @Test
    public void testAddition() {
        System.out.println("Testing: 5 + 3 = 8");
        int result = calculator.add(5, 3);
        assertEquals("5 + 3 should equal 8", 8, result);
    }
    
    @Test
    public void testSubtraction() {
        System.out.println("Testing: 10 - 4 = 6");
        int result = calculator.subtract(10, 4);
        assertEquals("10 - 4 should equal 6", 6, result);
    }
    
    @Test
    public void testMultiplication() {
        System.out.println("Testing: 6 * 7 = 42");
        int result = calculator.multiply(6, 7);
        assertEquals("6 * 7 should equal 42", 42, result);
    }
    
    @Test
    public void testDivision() {
        System.out.println("Testing: 20 / 5 = 4");
        int result = calculator.divide(20, 5);
        assertEquals("20 / 5 should equal 4", 4, result);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDivisionByZero() {
        System.out.println("Testing: Division by zero should throw exception");
        calculator.divide(10, 0);
    }
}