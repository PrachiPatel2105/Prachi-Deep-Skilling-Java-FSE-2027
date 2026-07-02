# Exercise 3: Assertions in JUnit

## Objective
Use different assertions in JUnit to validate test results.

## What We Did

### 1. Created AssertionsTest Class
Demonstrates all major JUnit assertions:

- `assertEquals()` - Check if two values are equal
- `assertTrue()` - Check if condition is true
- `assertFalse()` - Check if condition is false
- `assertNull()` - Check if object is null
- `assertNotNull()` - Check if object is not null
- `assertSame()` - Check if two objects are same instance
- `assertNotSame()` - Check if two objects are different
- `assertArrayEquals()` - Check if arrays are equal
- `fail()` - Force test to fail

### 2. Created AssertionComparisonTest Class
Shows best practices for assertion selection:

- When to use assertEquals vs assertTrue
- Using specific assertions
- Null checking patterns
- Real-world validation examples
- Array validation

## Key Assertions Learned

### assertEquals()
```java
assertEquals("Expected vs actual", expected, actual);
assertEquals("Double with precision", 3.14, pi, 0.01);
```

### assertTrue() / assertFalse()
```java
assertTrue("Condition should be true", condition);
assertFalse("Condition should be false", condition);
```

### assertNull() / assertNotNull()
```java
assertNull("Object should be null", nullObject);
assertNotNull("Object should not be null", object);
```

### assertSame() / assertNotSame()
```java
assertSame("Should be same instance", obj1, obj2);
assertNotSame("Should be different instances", obj1, obj2);
```

### assertArrayEquals()
```java
assertArrayEquals("Arrays should be equal", expected, actual);
assertArrayEquals("Doubles with precision", expected, actual, 0.001);
```

### fail()
```java
fail("Force test to fail with message");
```

## Test Methods

### AssertionsTest (10 tests)
1. `testAssertEquals()` - Equality testing
2. `testAssertTrue()` - Boolean true testing
3. `testAssertFalse()` - Boolean false testing
4. `testAssertNull()` - Null reference testing
5. `testAssertNotNull()` - Non-null reference testing
6. `testAssertSame()` - Same instance testing
7. `testAssertNotSame()` - Different instance testing
8. `testAssertArrayEquals()` - Array equality testing
9. `testMultipleAssertions()` - Combining assertions
10. `testFailMethod()` - Using fail() method

### AssertionComparisonTest (5 tests)
1. `testEqualsVsTrue()` - When to use assertEquals
2. `testSpecificAssertions()` - Being specific
3. `testNullChecks()` - Null validation
4. `testUserValidation()` - Real-world example
5. `testArrayValidation()` - Array testing

## Best Practices

### 1. Use Specific Assertions
```java
// GOOD
assertTrue("Value is positive", value > 0);

// AVOID
assertEquals(true, value > 0);
```

### 2. Include Descriptive Messages
```java
// GOOD
assertEquals("User age should be 25", 25, user.getAge());

// AVOID
assertEquals(25, user.getAge());
```

### 3. Test Both Positive and Negative Cases
```java
// Positive case
assertTrue("Valid password", isValidPassword("Secure123"));

// Negative case
assertFalse("Invalid password", isValidPassword("123"));
```

### 4. Use Delta for Floating Point
```java
// Good for floating point comparisons
assertEquals("PI approximation", 3.14159, pi, 0.0001);
```

### 5. Keep Assertions Clear
```java
// GOOD
assertNotNull("User should exist", user);

// CLEAR
assertTrue("User should exist", user != null);
```

## How to Run Tests

### Using IDE
### Using Command Line
```bash
mvn test
```

## Test Results
## Assertion Comparison Table

| Assertion | Use Case | Example |
|-----------|----------|---------|
| `assertEquals()` | Compare values | `assertEquals(8, 5+3)` |
| `assertTrue()` | Condition is true | `assertTrue(5 > 3)` |
| `assertFalse()` | Condition is false | `assertFalse(5 < 3)` |
| `assertNull()` | Object is null | `assertNull(obj)` |
| `assertNotNull()` | Object is not null | `assertNotNull(obj)` |
| `assertSame()` | Same instance | `assertSame(obj1, obj2)` |
| `assertNotSame()` | Different instances | `assertNotSame(obj1, obj2)` |
| `assertArrayEquals()` | Arrays are equal | `assertArrayEquals(arr1, arr2)` |
| `fail()` | Force failure | `fail("Not implemented")` |

## Files Created
- `src/test/java/com/bank/AssertionsTest.java`
- `src/test/java/com/bank/AssertionComparisonTest.java`
- `README.md` (this file)

## Next Steps
- Exercise 4: AAA Pattern (Arrange, Act, Assert)
- Exercise 1: Test Fixtures (@Before, @After)
- Mockito: Mocking and Stubbing

## Learning Outcomes

✅ Understand all major JUnit assertions
✅ Know when to use each assertion
✅ Write clear assertion messages
✅ Test both positive and negative cases
✅ Handle floating point comparisons
✅ Test arrays and complex objects
✅ Follow assertion best practices

---

**Exercise Status:** ✅ COMPLETED

**Date Completed:** [Your Date]

**Time Taken:** [Your Time]

**Difficulty Level:** ⭐⭐⭐☆☆ (3/5 - Intermediate)

**Your Understanding:** [Rate 1-10]

---

*This is Exercise 3 of Module 4: TDD & Logging*
*Part of Digital Nurture 5.0 Deep Skilling Program*