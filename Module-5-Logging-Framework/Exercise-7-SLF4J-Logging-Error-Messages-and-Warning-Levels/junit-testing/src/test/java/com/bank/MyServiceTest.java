package com.bank;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * MyServiceTest - Demonstrates Mockito mocking and stubbing
 * 
 * Mockito Concepts:
 * 1. mock() - Create a mock object
 * 2. when().thenReturn() - Stub a method with return value
 * 3. verify() - Verify method was called
 * 4. verify().times() - Verify method called N times
 */
public class MyServiceTest {
    
    private ExternalApi mockApi;
    private MyService service;
    
    @Before
    public void setUp() {
        System.out.println("\n--- SETUP: Creating mock and service ---");
        
        // STEP 1: Create mock object
        mockApi = mock(ExternalApi.class);
        System.out.println("Created mock for ExternalApi");
        
        // Inject mock into service
        service = new MyService(mockApi);
        System.out.println("Injected mock into MyService");
    }
    
    /**
     * Test 1: Mock simple method with string return
     * 
     * Demonstrates:
     * - Creating a mock
     * - Stubbing with thenReturn()
     * - Using mock in test
     */
    @Test
    public void testFetchDataWithMock() {
        System.out.println("\n=== Test 1: Fetch Data with Mock ===");
        
        // ARRANGE - Stub the mock method
        String expectedData = "Mock Data from API";
        when(mockApi.getData()).thenReturn(expectedData);
        System.out.println("ARRANGE: Stubbed getData() to return: " + expectedData);
        
        // ACT - Call service method which uses mock
        String result = service.fetchData();
        System.out.println("ACT: Called service.fetchData()");
        
        // ASSERT - Verify result
        assertEquals(expectedData, result);
        System.out.println("ASSERT: Result matches stubbed value ✓");
    }
    
    /**
     * Test 2: Mock method with parameters
     * 
     * Demonstrates:
     * - Stubbing methods with parameters
     * - Using when().thenReturn() with arguments
     */
    @Test
    public void testGetUserInfoWithMock() {
        System.out.println("\n=== Test 2: Get User Info with Mock ===");
        
        // ARRANGE - Stub method with parameters
        int userId = 123;
        String expectedUserData = "John Doe";
        when(mockApi.getUserData(userId)).thenReturn(expectedUserData);
        System.out.println("ARRANGE: Stubbed getUserData(123) to return: " + expectedUserData);
        
        // ACT
        String result = service.getUserInfo(userId);
        System.out.println("ACT: Called service.getUserInfo(123)");
        
        // ASSERT
        String expected = "User: " + expectedUserData;
        assertEquals(expected, result);
        System.out.println("ASSERT: Result is correct ✓");
    }
    
    /**
     * Test 3: Mock method with double return value
     * 
     * Demonstrates:
     * - Stubbing methods that return double
     * - Testing business logic with mocked data
     */
    @Test
    public void testSufficientBalanceWithMock() {
        System.out.println("\n=== Test 3: Sufficient Balance with Mock ===");
        
        // ARRANGE
        String accountId = "ACC001";
        double balance = 5000.0;
        double requiredAmount = 3000.0;
        
        when(mockApi.getBalance(accountId)).thenReturn(balance);
        System.out.println("ARRANGE: Stubbed getBalance() to return: $" + balance);
        System.out.println("ARRANGE: Required amount: $" + requiredAmount);
        
        // ACT
        boolean hasBalance = service.hasSufficientBalance(accountId, requiredAmount);
        System.out.println("ACT: Called hasSufficientBalance()");
        
        // ASSERT
        assertTrue("Should have sufficient balance", hasBalance);
        System.out.println("ASSERT: Has sufficient balance ✓");
    }
    
    /**
     * Test 4: Mock method with insufficient balance
     */
    @Test
    public void testInsufficientBalanceWithMock() {
        System.out.println("\n=== Test 4: Insufficient Balance with Mock ===");
        
        // ARRANGE
        String accountId = "ACC002";
        double balance = 1000.0;
        double requiredAmount = 3000.0;
        
        when(mockApi.getBalance(accountId)).thenReturn(balance);
        System.out.println("ARRANGE: Stubbed getBalance() to return: $" + balance);
        System.out.println("ARRANGE: Required amount: $" + requiredAmount);
        
        // ACT
        boolean hasBalance = service.hasSufficientBalance(accountId, requiredAmount);
        System.out.println("ACT: Called hasSufficientBalance()");
        
        // ASSERT
        assertFalse("Should NOT have sufficient balance", hasBalance);
        System.out.println("ASSERT: Does not have sufficient balance ✓");
    }
    
    /**
     * Test 5: Mock method with boolean return
     * 
     * Demonstrates:
     * - Stubbing boolean returning methods
     * - Multiple test cases for same method
     */
    @Test
    public void testSuccessfulTransactionWithMock() {
        System.out.println("\n=== Test 5: Successful Transaction with Mock ===");
        
        // ARRANGE
        String transactionData = "Transfer $500";
        when(mockApi.sendData(transactionData)).thenReturn(true);
        System.out.println("ARRANGE: Stubbed sendData() to return: true");
        
        // ACT
        boolean result = service.processTransaction(transactionData);
        System.out.println("ACT: Called processTransaction()");
        
        // ASSERT
        assertTrue("Transaction should be successful", result);
        System.out.println("ASSERT: Transaction successful ✓");
    }
    
    /**
     * Test 6: Mock method returning failure
     */
    @Test
    public void testFailedTransactionWithMock() {
        System.out.println("\n=== Test 6: Failed Transaction with Mock ===");
        
        // ARRANGE
        String transactionData = "Invalid Transaction";
        when(mockApi.sendData(transactionData)).thenReturn(false);
        System.out.println("ARRANGE: Stubbed sendData() to return: false");
        
        // ACT
        boolean result = service.processTransaction(transactionData);
        System.out.println("ACT: Called processTransaction()");
        
        // ASSERT
        assertFalse("Transaction should fail", result);
        System.out.println("ASSERT: Transaction failed as expected ✓");
    }
    
    /**
     * Test 7: Verify mock method was called
     * 
     * Demonstrates:
     * - Using verify() to confirm method was called
     * - Checking method invocations
     */
    @Test
    public void testVerifyMockCalled() {
        System.out.println("\n=== Test 7: Verify Mock Called ===");
        
        // ARRANGE
        when(mockApi.getData()).thenReturn("Test Data");
        System.out.println("ARRANGE: Stubbed getData()");
        
        // ACT
        service.fetchData();
        System.out.println("ACT: Called service.fetchData()");
        
        // ASSERT - Verify the mock method was actually called
        verify(mockApi).getData();
        System.out.println("ASSERT: Verified getData() was called ✓");
    }
    
    /**
     * Test 8: Verify mock called with specific times
     */
    @Test
    public void testVerifyMockCalledTimes() {
        System.out.println("\n=== Test 8: Verify Mock Called N Times ===");
        
        // ARRANGE
        when(mockApi.getData()).thenReturn("Test Data");
        System.out.println("ARRANGE: Stubbed getData()");
        
        // ACT - Call multiple times
        service.fetchData();
        service.fetchData();
        service.fetchData();
        System.out.println("ACT: Called service.fetchData() 3 times");
        
        // ASSERT - Verify called exactly 3 times
        verify(mockApi, times(3)).getData();
        System.out.println("ASSERT: Verified getData() was called exactly 3 times ✓");
    }
    
    /**
     * Test 9: Verify mock was never called
     */
    @Test
    public void testVerifyMockNeverCalled() {
        System.out.println("\n=== Test 9: Verify Mock Never Called ===");
        
        // ARRANGE
        when(mockApi.getData()).thenReturn("Test Data");
        System.out.println("ARRANGE: Stubbed getData()");
        
        // ACT - Don't call the method
        // (not calling service.fetchData())
        System.out.println("ACT: Did not call service.fetchData()");
        
        // ASSERT - Verify method was never called
        verify(mockApi, never()).getData();
        System.out.println("ASSERT: Verified getData() was never called ✓");
    }
}