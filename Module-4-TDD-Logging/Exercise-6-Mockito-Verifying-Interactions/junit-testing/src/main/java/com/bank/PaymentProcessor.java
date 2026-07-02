package com.bank;

/**
 * PaymentProcessor - Processes payments using PaymentService
 * This class will be tested by verifying interactions
 */
public class PaymentProcessor {
    
    private PaymentService paymentService;
    
    /**
     * Constructor - inject PaymentService
     */
    public PaymentProcessor(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    
    /**
     * Complete payment process with all steps
     * @param accountId account ID
     * @param amount amount to pay
     * @param email email for confirmation
     * @return transaction ID
     */
    public String completePayment(String accountId, double amount, String email) {
        // Log the attempt
        paymentService.logPaymentAttempt(accountId, amount);
        
        // Process the payment
        String transactionId = paymentService.processPayment(accountId, amount);
        
        // Update balance
        paymentService.updateBalance(accountId, amount);
        
        // Send confirmation
        paymentService.sendConfirmation(accountId, transactionId, email);
        
        return transactionId;
    }
    
    /**
     * Process multiple payments
     * @param accountId account ID
     * @param amounts array of amounts
     */
    public void processMultiplePayments(String accountId, double[] amounts) {
        for (double amount : amounts) {
            paymentService.processPayment(accountId, amount);
            paymentService.updateBalance(accountId, amount);
        }
    }
    
    /**
     * Refund a payment
     * @param accountId account ID
     * @param transactionId transaction to refund
     * @param amount refund amount
     */
    public void refundPayment(String accountId, String transactionId, double amount) {
        paymentService.updateBalance(accountId, -amount);  // Negative to add back
        paymentService.logPaymentAttempt(accountId, -amount);
    }
}