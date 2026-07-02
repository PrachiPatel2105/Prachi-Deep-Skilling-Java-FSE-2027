package com.bank;

import org.junit.Test;
import static org.junit.Assert.*;

public class AssertionsTest {
    
    @Test
    public void testAssertEquals() {
        int result = 5 + 3;
        assertEquals(8, result);
        System.out.println("✓ testAssertEquals PASSED");
    }
    
    @Test
    public void testAssertTrue() {
        assertTrue(5 > 3);
        System.out.println("✓ testAssertTrue PASSED");
    }
    
    @Test
    public void testAssertFalse() {
        assertFalse(5 < 3);
        System.out.println("✓ testAssertFalse PASSED");
    }
    
    @Test
    public void testAssertNull() {
        String nullString = null;
        assertNull(nullString);
        System.out.println("✓ testAssertNull PASSED");
    }
    
    @Test
    public void testAssertNotNull() {
        Object obj = new Object();
        assertNotNull(obj);
        System.out.println("✓ testAssertNotNull PASSED");
    }
    
    @Test
    public void testAssertSame() {
        String str1 = "Hello";
        String str2 = str1;
        assertSame(str1, str2);
        System.out.println("✓ testAssertSame PASSED");
    }
    
    @Test
    public void testAssertNotSame() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        assertNotSame(obj1, obj2);
        System.out.println("✓ testAssertNotSame PASSED");
    }
    
    @Test
    public void testAssertArrayEquals() {
        int[] expected = {1, 2, 3, 4, 5};
        int[] actual = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, actual);
        System.out.println("✓ testAssertArrayEquals PASSED");
    }
    
    @Test
    public void testMultipleAssertions() {
        int value = 25;
        String name = "TestValue";
        Object obj = new Object();
        
        assertEquals(25, value);
        assertTrue(value > 0);
        assertNotNull(name);
        assertNotNull(obj);
        assertEquals("TestValue", name);
        
        System.out.println("✓ testMultipleAssertions PASSED");
    }
}