# Exercise 6: Mockito Exercise 2 - Verifying Interactions

## Objective
Learn to verify that mock objects are called with specific arguments and in the correct order.

## What is Verifying Interactions?

Verifying interactions means:
1. Checking that methods were called with exact arguments
2. Verifying the order of method calls
3. Capturing arguments passed to mocks
4. Ensuring no unexpected calls were made

## Why Verify Interactions?

- Ensure correct data is passed to dependencies
- Verify workflow/sequence of operations
- Test integration between components
- Validate API usage correctness

## Key Concepts

### 1. verify() with Arguments
```java
verify(mockApi).method(expectedArgument);
```
Verifies method called with specific argument.

### 2. ArgumentCaptor - Capture Arguments
```java
ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
verify(mockApi).method(captor.capture());
String capturedValue = captor.getValue();
```
Capture the actual argument passed to verify its value.

### 3. InOrder - Verify Call Order
```java
InOrder inOrder = inOrder(mockApi);
inOrder.verify(mockApi).methodA();
inOrder.verify(mockApi).methodB();
```
Verify methods called in specific sequence.

### 4. verifyNoMoreInteractions()
```java
verify(mockApi).method();
verifyNoMoreInteractions(mockApi);
```
Ensure only expected calls were made.

### 5. Exact vs Any Arguments
```java
// Exact argument
verify(mock).method("exact");

// Any string
verify(mock).method(anyString());

// Any int
verify(mock).method(anyInt());

// Any object
verify(mock).method(any());
```

## Classes Created

### PaymentService (Interface)
Represents payment processing service to be mocked.

**Methods:**
- `processPayment(accountId, amount)` - Process payment
- `sendConfirmation(accountId, transactionId, email)` - Send confirmation
- `logPaymentAttempt(accountId, amount)` - Log attempt
- `updateBalance(accountId, amount)` - Update balance

### PaymentProcessor (Implementation)
Processes payments using PaymentService.

**Methods:**
- `completePayment(accountId, amount, email)` - Complete payment workflow
- `processMultiplePayments(accountId, amounts)` - Process multiple payments
- `refundPayment(accountId, transactionId, amount)` - Refund payment

### PaymentProcessorTest (Test Class)
Tests PaymentProcessor by verifying mock interactions.

## Test Methods (9 tests)

1. `testVerifyMethodWithStringArgument()` - Verify with string argument
2. `testVerifyMethodWithDoubleArgument()` - Verify with double argument
3. `testVerifyMethodWithMultipleArguments()` - Verify with multiple args
4. `testCaptureArgumentsWithArgumentCaptor()` - Capture and inspect args
5. `testVerifyInteractionOrder()` - Verify call order with InOrder
6. `testVerifyMethodNeverCalledWithArgument()` - Verify never called
7. `testVerifyMultipleCallsWithDifferentArguments()` - Verify multiple calls
8. `testVerifyRefundWithNegativeAmount()` - Verify with negative amount
9. `testVerifyNoMoreInteractions()` - Verify no other interactions

## Mockito Verification Methods

| Method | Purpose |
|--------|---------|
| `verify(mock)` | Verify method called |
| `verify(mock, times(n))` | Verify called N times |
| `verify(mock, never())` | Verify never called |
| `verify(mock, atLeast(n))` | Called at least N times |
| `verify(mock, atMost(n))` | Called at most N times |
| `InOrder` | Verify call order |
| `ArgumentCaptor` | Capture arguments |
| `verifyNoMoreInteractions()` | No other calls made |

## Argument Matchers

When you need flexibility in verification:

```java
// Match exact value
verify(mock).method("exact");

// Match any string
verify(mock).method(anyString());

// Match any int
verify(mock).method(anyInt());

// Match any double
verify(mock).method(anyDouble());

// Match any object
verify(mock).method(any());

// Match with condition
verify(mock).method(argThat(arg -> arg.contains("test")));
```

## Example: Payment Workflow

Without verification:
```java
@Test
public void test() {
    processor.completePayment(accountId, amount, email);
    // Did it call the methods in correct order?
    // Was email correct? Was amount correct?
    // We don't know!
}
```

With verification:
```java
@Test
public void test() {
    processor.completePayment(accountId, amount, email);
    
    // Verify exact order
    InOrder inOrder = inOrder(mockService);
    inOrder.verify(mockService).logPaymentAttempt(accountId, amount);
    inOrder.verify(mockService).processPayment(accountId, amount);
    inOrder.verify(mockService).updateBalance(accountId, amount);
    inOrder.verify(mockService).sendConfirmation(accountId, txnId, email);
    
    // Verify arguments are correct
    verify(mockService).sendConfirmation("ACC123", "TXN001", "user@example.com");
}
```

## ArgumentCaptor Use Case

When you need to inspect the actual argument:

```java
@Test
public void test() {
    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    processor.someMethod();
    
    verify(mockService).method(captor.capture());
    String actual = captor.getValue();
    
    // Now you can check the actual value
    assertTrue(actual.contains("expected"));
    assertEquals(10, actual.length());
}
```

## Test Results
## How to Run Tests

### Using IDE
### Using Command Line
```bash
mvn test
```

## Files Created

- `src/main/java/com/bank/PaymentService.java` - Interface to mock
- `src/main/java/com/bank/PaymentProcessor.java` - Class under test
- `src/test/java/com/bank/PaymentProcessorTest.java` - Tests with verification
- `README.md` - This file

## Real-World Applications

### Banking
- Verify payment processing order
- Ensure confirmation sent with correct details
- Log all payment attempts

### E-commerce
- Verify order steps execute in order
- Ensure confirmation email sent
- Log user actions

### Healthcare
- Verify medical records updated correctly
- Ensure notifications sent to doctors
- Log patient interactions

## Best Practices

### 1. Verify Exact Arguments
```java
// GOOD - Specific argument
verify(mock).method("exact");

// AVOID - Too loose
verify(mock, times(anyInt())).method(any());
```

### 2. Use ArgumentCaptor for Complex Logic
```java
ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
verify(mock).saveUser(captor.capture());
User captured = captor.getValue();
assertTrue(captured.isValid());
```

### 3. Verify Order for Workflows
```java
InOrder inOrder = inOrder(mock1, mock2);
inOrder.verify(mock1).first();
inOrder.verify(mock2).second();
```

### 4. Use verifyNoMoreInteractions to Prevent Surprises
```java
verify(mock).expectedMethod();
verifyNoMoreInteractions(mock);  // Fail if other methods called
```

## Key Learnings

✅ Verify method calls with specific arguments
✅ Use ArgumentCaptor to inspect arguments
✅ Verify method call order with InOrder
✅ Ensure no unexpected interactions
✅ Test workflows and sequences
✅ Validate data passed between components
✅ Prevent side effects and surprises

## Next Steps

- Exercise 7: Mockito - Argument Matchers
- SLF4J: Logging Framework
- Spring Core: Dependency Injection
- Spring Data JPA

---

**Exercise Status:** ✅ COMPLETED

**Date Completed:** [Your Date]

**Time Taken:** [Your Time]

**Difficulty Level:** ⭐⭐⭐⭐⭐ (5/5 - Advanced)

**Your Understanding:** [Rate 1-10]

---

*This is Mockito Exercise 2 of Module 4: TDD & Logging*
*Part of Digital Nurture 5.0 Deep Skilling Program*