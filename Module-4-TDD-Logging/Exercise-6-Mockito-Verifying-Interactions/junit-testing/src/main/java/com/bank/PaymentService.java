package com.bank;

/**
 * PaymentService - Service that processes payments
 * This will be tested by verifying interactions
 */
public interface PaymentService {
    
    /**
     * Process a payment transaction
     * @param accountId account ID
     * @param amount amount to pay
     * @return transaction ID
     */
    String processPayment(String accountId, double amount);
    
    /**
     * Send payment confirmation
     * @param accountId account ID
     * @param transactionId transaction ID
     * @param email email address
     */
    void sendConfirmation(String accountId, String transactionId, String email);
    
    /**
     * Log payment attempt
     * @param accountId account ID
     * @param amount amount
     */
    void logPaymentAttempt(String accountId, double amount);
    
    /**
     * Update account balance
     * @param accountId account ID
     * @param amount amount to deduct
     */
    void updateBalance(String accountId, double amount);
}