package com.bank;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * AAAPatternTest - Demonstrates the Arrange-Act-Assert (AAA) Pattern
 * 
 * AAA Pattern:
 * 1. ARRANGE - Set up test data and objects
 * 2. ACT - Perform the action being tested
 * 3. ASSERT - Verify the results
 * 
 * This pattern makes tests clear and easy to understand
 */
public class AAAPatternTest {
    
    private Calculator calculator;
    
    /**
     * @Before - Runs BEFORE each test method
     * ARRANGE phase happens here
     */
    @Before
    public void setUp() {
        System.out.println("\n--- SETUP: Creating Calculator instance ---");
        calculator = new Calculator();
    }
    
    /**
     * @After - Runs AFTER each test method
     * Cleanup/Teardown phase
     */
    @After
    public void tearDown() {
        System.out.println("--- TEARDOWN: Cleaning up ---");
        calculator = null;
    }
    
    /**
     * Test 1: Addition using AAA Pattern
     * 
     * ARRANGE: Create calculator and input values
     * ACT: Call add method
     * ASSERT: Verify result
     */
    @Test
    public void testAdditionWithAAAPattern() {
        System.out.println("\n=== Test: Addition (AAA Pattern) ===");
        
        // ARRANGE - Set up test data
        int num1 = 10;
        int num2 = 5;
        int expectedResult = 15;
        System.out.println("ARRANGE: num1=" + num1 + ", num2=" + num2 + ", expected=" + expectedResult);
        
        // ACT - Perform the action
        int actualResult = calculator.add(num1, num2);
        System.out.println("ACT: calculator.add(" + num1 + ", " + num2 + ") = " + actualResult);
        
        // ASSERT - Verify the results
        assertEquals(expectedResult, actualResult);
        System.out.println("ASSERT: Result matches expected value ✓");
    }
    
    /**
     * Test 2: Subtraction using AAA Pattern
     */
    @Test
    public void testSubtractionWithAAAPattern() {
        System.out.println("\n=== Test: Subtraction (AAA Pattern) ===");
        
        // ARRANGE
        int num1 = 20;
        int num2 = 8;
        int expectedResult = 12;
        System.out.println("ARRANGE: num1=" + num1 + ", num2=" + num2 + ", expected=" + expectedResult);
        
        // ACT
        int actualResult = calculator.subtract(num1, num2);
        System.out.println("ACT: calculator.subtract(" + num1 + ", " + num2 + ") = " + actualResult);
        
        // ASSERT
        assertEquals(expectedResult, actualResult);
        System.out.println("ASSERT: Result matches expected value ✓");
    }
    
    /**
     * Test 3: Multiplication using AAA Pattern
     */
    @Test
    public void testMultiplicationWithAAAPattern() {
        System.out.println("\n=== Test: Multiplication (AAA Pattern) ===");
        
        // ARRANGE
        int num1 = 7;
        int num2 = 6;
        int expectedResult = 42;
        System.out.println("ARRANGE: num1=" + num1 + ", num2=" + num2 + ", expected=" + expectedResult);
        
        // ACT
        int actualResult = calculator.multiply(num1, num2);
        System.out.println("ACT: calculator.multiply(" + num1 + ", " + num2 + ") = " + actualResult);
        
        // ASSERT
        assertEquals(expectedResult, actualResult);
        System.out.println("ASSERT: Result matches expected value ✓");
    }
    
    /**
     * Test 4: Division using AAA Pattern
     */
    @Test
    public void testDivisionWithAAAPattern() {
        System.out.println("\n=== Test: Division (AAA Pattern) ===");
        
        // ARRANGE
        int num1 = 20;
        int num2 = 4;
        int expectedResult = 5;
        System.out.println("ARRANGE: num1=" + num1 + ", num2=" + num2 + ", expected=" + expectedResult);
        
        // ACT
        int actualResult = calculator.divide(num1, num2);
        System.out.println("ACT: calculator.divide(" + num1 + ", " + num2 + ") = " + actualResult);
        
        // ASSERT
        assertEquals(expectedResult, actualResult);
        System.out.println("ASSERT: Result matches expected value ✓");
    }
}