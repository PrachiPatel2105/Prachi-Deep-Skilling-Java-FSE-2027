package com.bank;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

/**
 * PaymentProcessorTest - Demonstrates Mockito verifying interactions
 */
public class PaymentProcessorTest {
    
    private PaymentService mockPaymentService;
    private PaymentProcessor processor;
    
    @Before
    public void setUp() {
        System.out.println("\n--- SETUP: Creating mock and processor ---");
        mockPaymentService = mock(PaymentService.class);
        processor = new PaymentProcessor(mockPaymentService);
        System.out.println("Created PaymentProcessor with mocked PaymentService");
    }
    
    /**
     * Test 1: Verify method called with specific string argument
     */
    @Test
    public void testVerifyMethodWithStringArgument() {
        System.out.println("\n=== Test 1: Verify with String Argument ===");
        
        String accountId = "ACC123";
        double amount = 1000.0;
        String email = "user@example.com";
        
        when(mockPaymentService.processPayment(accountId, amount))
            .thenReturn("TXN001");
        System.out.println("ARRANGE: Stubbed processPayment()");
        
        processor.completePayment(accountId, amount, email);
        System.out.println("ACT: Called completePayment()");
        
        verify(mockPaymentService).logPaymentAttempt(accountId, amount);
        System.out.println("ASSERT: Verified logPaymentAttempt() called with correct arguments ✓");
    }
    
    /**
     * Test 2: Verify method called with specific double argument
     */
    @Test
    public void testVerifyMethodWithDoubleArgument() {
        System.out.println("\n=== Test 2: Verify with Double Argument ===");
        
        String accountId = "ACC123";
        double amount = 5000.50;
        String email = "user@example.com";
        
        when(mockPaymentService.processPayment(accountId, amount))
            .thenReturn("TXN002");
        System.out.println("ARRANGE: Stubbed processPayment() with amount=" + amount);
        
        processor.completePayment(accountId, amount, email);
        System.out.println("ACT: Called completePayment()");
        
        verify(mockPaymentService).updateBalance(accountId, amount);
        System.out.println("ASSERT: Verified updateBalance() called with amount=" + amount + " ✓");
    }
    
    /**
     * Test 3: Verify method called with multiple arguments
     */
    @Test
    public void testVerifyMethodWithMultipleArguments() {
        System.out.println("\n=== Test 3: Verify with Multiple Arguments ===");
        
        String accountId = "ACC123";
        String transactionId = "TXN003";
        String email = "user@example.com";
        
        when(mockPaymentService.processPayment(accountId, 2000.0))
            .thenReturn(transactionId);
        System.out.println("ARRANGE: Stubbed processPayment()");
        
        processor.completePayment(accountId, 2000.0, email);
        System.out.println("ACT: Called completePayment()");
        
        verify(mockPaymentService).sendConfirmation(accountId, transactionId, email);
        System.out.println("ASSERT: Verified sendConfirmation() called with correct arguments ✓");
    }
    
    /**
     * Test 4: Use ArgumentCaptor to capture arguments
     */
    @Test
    public void testCaptureArgumentsWithArgumentCaptor() {
        System.out.println("\n=== Test 4: Capture Arguments with ArgumentCaptor ===");
        
        String accountId = "ACC456";
        double amount = 3000.0;
        String email = "customer@example.com";
        
        when(mockPaymentService.processPayment(accountId, amount))
            .thenReturn("TXN004");
        System.out.println("ARRANGE: Stubbed processPayment()");
        
        ArgumentCaptor<String> accountCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> amountCaptor = ArgumentCaptor.forClass(Double.class);
        System.out.println("ARRANGE: Created ArgumentCaptors");
        
        processor.completePayment(accountId, amount, email);
        System.out.println("ACT: Called completePayment()");
        
        verify(mockPaymentService).logPaymentAttempt(accountCaptor.capture(), amountCaptor.capture());
        
        String capturedAccountId = accountCaptor.getValue();
        Double capturedAmount = amountCaptor.getValue();
        
        assertEquals(accountId, capturedAccountId);
        assertEquals(amount, capturedAmount, 0.01);
        System.out.println("ASSERT: Verified captured arguments ✓");
    }
    
    /**
     * Test 5: Verify method call order (InOrder)
     */
    @Test
    public void testVerifyInteractionOrder() {
        System.out.println("\n=== Test 5: Verify Interaction Order ===");
        
        String accountId = "ACC789";
        double amount = 1500.0;
        String email = "test@example.com";
        
        when(mockPaymentService.processPayment(accountId, amount))
            .thenReturn("TXN005");
        System.out.println("ARRANGE: Stubbed processPayment()");
        
        processor.completePayment(accountId, amount, email);
        System.out.println("ACT: Called completePayment()");
        
        InOrder inOrder = inOrder(mockPaymentService);
        inOrder.verify(mockPaymentService).logPaymentAttempt(accountId, amount);
        inOrder.verify(mockPaymentService).processPayment(accountId, amount);
        inOrder.verify(mockPaymentService).updateBalance(accountId, amount);
        inOrder.verify(mockPaymentService).sendConfirmation(accountId, "TXN005", email);
        System.out.println("ASSERT: Verified all calls in correct order ✓");
    }
    
    /**
     * Test 6: Verify method never called
     */
    @Test
    public void testVerifyMethodNeverCalledWithArgument() {
        System.out.println("\n=== Test 6: Verify Method Never Called with Argument ===");
        
        System.out.println("ARRANGE/ACT: Created processor but didn't call any methods");
        
        verify(mockPaymentService, never()).sendConfirmation("ACC123", "TXN", "wrong@example.com");
        System.out.println("ASSERT: Verified sendConfirmation() was never called ✓");
    }
    
    /**
     * Test 7: Verify multiple method calls with different arguments
     */
    @Test
    public void testVerifyMultipleCallsWithDifferentArguments() {
        System.out.println("\n=== Test 7: Verify Multiple Calls with Different Arguments ===");
        
        String accountId = "ACC999";
        double[] amounts = {100.0, 200.0, 300.0};
        
        when(mockPaymentService.processPayment(accountId, 100.0)).thenReturn("TXN006");
        when(mockPaymentService.processPayment(accountId, 200.0)).thenReturn("TXN007");
        when(mockPaymentService.processPayment(accountId, 300.0)).thenReturn("TXN008");
        System.out.println("ARRANGE: Stubbed processPayment() for 3 amounts");
        
        processor.processMultiplePayments(accountId, amounts);
        System.out.println("ACT: Called processMultiplePayments() with 3 amounts");
        
        verify(mockPaymentService).processPayment(accountId, 100.0);
        verify(mockPaymentService).processPayment(accountId, 200.0);
        verify(mockPaymentService).processPayment(accountId, 300.0);
        System.out.println("ASSERT: Verified processPayment() called with all amounts ✓");
    }
    
    /**
     * Test 8: Verify with negative amount for refund (FIXED)
     */
    @Test
    public void testVerifyRefundWithNegativeAmount() {
        System.out.println("\n=== Test 8: Verify Refund with Negative Amount ===");
        
        String accountId = "ACC111";
        String transactionId = "TXN009";
        double refundAmount = 500.0;
        
        System.out.println("ARRANGE: Original refund amount=" + refundAmount);
        System.out.println("ARRANGE: Will be negated to -" + refundAmount);
        
        processor.refundPayment(accountId, transactionId, refundAmount);
        System.out.println("ACT: Called refundPayment()");
        
        // Verify with the negated amount (internal implementation)
        verify(mockPaymentService).updateBalance(accountId, -refundAmount);
        verify(mockPaymentService).logPaymentAttempt(accountId, -refundAmount);
        System.out.println("ASSERT: Verified both methods called with negative amount ✓");
    }
    
    /**
     * Test 9: Verify no more interactions occurred
     */
    @Test
    public void testVerifyNoMoreInteractions() {
        System.out.println("\n=== Test 9: Verify No More Interactions ===");
        
        String accountId = "ACC222";
        double amount = 1000.0;
        
        when(mockPaymentService.processPayment(accountId, amount))
            .thenReturn("TXN010");
        System.out.println("ARRANGE: Stubbed processPayment()");
        
        String transactionId = mockPaymentService.processPayment(accountId, amount);
        System.out.println("ACT: Called processPayment() only");
        
        verify(mockPaymentService).processPayment(accountId, amount);
        System.out.println("ASSERT: Verified processPayment() was called");
        
        verifyNoMoreInteractions(mockPaymentService);
        System.out.println("ASSERT: Verified no other methods were called ✓");
    }
}