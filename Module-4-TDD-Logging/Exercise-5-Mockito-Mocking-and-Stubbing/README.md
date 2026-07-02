# Exercise 5: Mockito - Mocking and Stubbing

## Objective
Learn to use Mockito framework to mock external dependencies and stub method returns.

## What is Mockito?

Mockito is a framework for creating mock objects in unit tests.

### Why Use Mocks?
- Test code that depends on external APIs/services
- Avoid making real API calls during testing
- Isolate code under test
- Control dependencies' behavior
- Test error scenarios easily

## Key Mockito Concepts

### 1. mock() - Create a Mock Object
```java
ExternalApi mockApi = mock(ExternalApi.class);
```
Creates a fake object that can be controlled in tests.

### 2. when().thenReturn() - Stub Method Return Values
```java
when(mockApi.getData()).thenReturn("Mock Data");
```
Configures what the mock should return when method is called.

### 3. verify() - Verify Method Calls
```java
verify(mockApi).getData();
```
Confirms that a method was called on the mock.

### 4. times() - Verify Call Count
```java
verify(mockApi, times(3)).getData();
```
Verifies method was called exactly N times.

### 5. never() - Verify Never Called
```java
verify(mockApi, never()).getData();
```
Verifies method was never called.

## Classes Created

### ExternalApi (Interface)
```java
public interface ExternalApi {
    String getData();
    String getUserData(int userId);
    double getBalance(String accountId);
    boolean sendData(String data);
}
```
Represents an external service/API that will be mocked.

### MyService (Implementation)
Depends on ExternalApi and uses its methods.

**Methods:**
- `fetchData()` - Gets data from API
- `getUserInfo(userId)` - Gets user information
- `hasSufficientBalance(accountId, amount)` - Checks balance
- `processTransaction(data)` - Processes transaction

### MyServiceTest (Test Class)
Tests MyService using Mockito mocks.

## Test Methods (9 tests)

1. `testFetchDataWithMock()` - Simple mock and stub
2. `testGetUserInfoWithMock()` - Mock with parameters
3. `testSufficientBalanceWithMock()` - Double return value
4. `testInsufficientBalanceWithMock()` - False condition
5. `testSuccessfulTransactionWithMock()` - Boolean return true
6. `testFailedTransactionWithMock()` - Boolean return false
7. `testVerifyMockCalled()` - Verify method called
8. `testVerifyMockCalledTimes()` - Verify call count
9. `testVerifyMockNeverCalled()` - Verify never called

## Mockito Annotations

### @Mock
```java
@Mock
private ExternalApi mockApi;
```
Automatically creates and injects mock (requires MockitoAnnotations.initMocks()).

### @InjectMocks
```java
@InjectMocks
private MyService service;
```
Automatically injects mocks into dependencies.

## Common Mockito Methods

| Method | Purpose |
|--------|---------|
| `mock(Class)` | Create mock object |
| `when().thenReturn()` | Stub return value |
| `verify()` | Verify method called |
| `times(n)` | Called exactly N times |
| `never()` | Never called |
| `atLeast(n)` | Called at least N times |
| `atLeastOnce()` | Called at least once |
| `atMost(n)` | Called at most N times |

## Example: Without vs With Mocks

### WITHOUT Mocks (Bad)
```java
@Test
public void testService() {
    // Must use real API - slow, depends on network
    ExternalApi api = new RealExternalApi();
    MyService service = new MyService(api);
    String result = service.fetchData();
    // Hard to test error scenarios
}
```

Problems:
- Calls real external API
- Slow tests
- Network dependent
- Can't test error cases

### WITH Mocks (Good)
```java
@Test
public void testService() {
    // Use mock - fast, controlled
    ExternalApi mockApi = mock(ExternalApi.class);
    when(mockApi.getData()).thenReturn("Mock Data");
    
    MyService service = new MyService(mockApi);
    String result = service.fetchData();
    
    assertEquals("Mock Data", result);
    verify(mockApi).getData();
}
```

Benefits:
- No external API calls
- Fast tests
- Fully controlled
- Easy error testing

## Mockito Dependency

Added to pom.xml:
```xml
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>5.2.0</version>
    <scope>test</scope>
</dependency>
```

## Test Resultsv
## How to Run Tests

### Using IDE
### Using Command Line
```bash
mvn test
```

## Files Created

- `src/main/java/com/bank/ExternalApi.java` - Interface to mock
- `src/main/java/com/bank/MyService.java` - Service under test
- `src/test/java/com/bank/MyServiceTest.java` - Test with mocks
- `README.md` - This file

## Best Practices

### 1. Mock External Dependencies
```java
ExternalApi mockApi = mock(ExternalApi.class);
```

### 2. Stub Expected Behavior
```java
when(mockApi.getData()).thenReturn("Expected Data");
```

### 3. Verify Interactions
```java
verify(mockApi).getData();
```

### 4. Keep Tests Isolated
- Each test should be independent
- Don't share mock state between tests

### 5. Clear Test Names
```java
testFetchDataWithMock()  // Clear what's being tested
```

## Key Learnings

✅ What is Mockito and why use it
✅ Create mock objects with mock()
✅ Stub method returns with when().thenReturn()
✅ Verify method calls with verify()
✅ Check call counts with times()
✅ Test both success and failure scenarios
✅ Isolate code under test from dependencies
✅ Make tests fast and reliable

## Next Steps

- Exercise 2: Mockito - Verifying Interactions
- Exercise 3: Mockito - Argument Matchers
- SLF4J: Logging Framework
- Spring Core: Dependency Injection

---

**Exercise Status:** ✅ COMPLETED

**Date Completed:** [Your Date]

**Time Taken:** [Your Time]

**Difficulty Level:** ⭐⭐⭐⭐⭐ (5/5 - Advanced)

**Your Understanding:** [Rate 1-10]

---

*This is Mockito Exercise 1 of Module 4: TDD & Logging*
*Part of Digital Nurture 5.0 Deep Skilling Program*