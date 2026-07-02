package com.bank;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * AssertionComparisonTest - When to use each assertion
 * 
 * Shows best practices and comparison of different assertions
 */
public class AssertionComparisonTest {
    
    /**
     * BEST PRACTICE: assertEquals vs assertTrue
     * Use assertEquals() when comparing values
     */
    @Test
    public void testEqualsVsTrue() {
        System.out.println("\n=== assertEquals vs assertTrue ===");
        
        int result = 5 + 3;
        
        // GOOD: Using assertEquals (more specific error message)
        assertEquals("Addition should work", 8, result);
        System.out.println("✓ assertEquals provides better error messages");
        
        // WORKS BUT LESS CLEAR: Using assertTrue
        assertTrue("5 + 3 equals 8", result == 8);
        System.out.println("✓ assertTrue works but less specific");
    }
    
    /**
     * BEST PRACTICE: Use specific assertions
     * NOT: assertEquals(true, someCondition)
     * INSTEAD: assertTrue(someCondition)
     */
    @Test
    public void testSpecificAssertions() {
        System.out.println("\n=== Specific Assertions ===");
        
        boolean isValid = true;
        
        // GOOD: Specific assertion
        assertTrue("Should be valid", isValid);
        System.out.println("✓ assertTrue is more specific than assertEquals");
        
        // NOT RECOMMENDED: Generic assertion
        assertEquals("Should be true", true, isValid);
        System.out.println("✓ assertEquals works but less clear");
    }
    
    /**
     * BEST PRACTICE: Null checks
     * Use assertNull() for null checks, not assertEquals()
     */
    @Test
    public void testNullChecks() {
        System.out.println("\n=== Null Checks ===");
        
        String value = null;
        
        // GOOD: Specific null assertion
        assertNull("Value should be null", value);
        System.out.println("✓ assertNull is clearer");
        
        // WORKS BUT LESS CLEAR: Using assertEquals
        assertEquals("Value should be null", null, value);
        System.out.println("✓ assertEquals works but less specific");
    }
    
    /**
     * Real-world example: Validating user input
     */
    @Test
    public void testUserValidation() {
        System.out.println("\n=== Real-world Example: User Validation ===");
        
        // Simulate user data
        String username = "john_doe";
        String email = "john@example.com";
        int age = 25;
        
        // Validate username
        assertNotNull("Username should not be null", username);
        assertTrue("Username should contain letters", username.matches("[a-z_]+"));
        System.out.println("✓ Username validated");
        
        // Validate email
        assertNotNull("Email should not be null", email);
        assertTrue("Email should contain @", email.contains("@"));
        System.out.println("✓ Email validated");
        
        // Validate age
        assertTrue("Age should be positive", age > 0);
        assertTrue("Age should be reasonable", age < 150);
        System.out.println("✓ Age validated");
    }
    
    /**
     * Testing array contents with assertArrayEquals
     */
    @Test
    public void testArrayValidation() {
        System.out.println("\n=== Array Validation ===");
        
        // Test sorted array
        int[] sorted = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        
        assertArrayEquals("Arrays should match", expected, sorted);
        System.out.println("✓ Array validation PASSED");
    }
}