package com.bank;

/**
 * ExternalApi - Represents an external API/service
 * This will be mocked in tests
 */
public interface ExternalApi {
    
    /**
     * Get data from external API
     * @return data from external source
     */
    String getData();
    
    /**
     * Get user data from external API
     * @param userId user ID
     * @return user data
     */
    String getUserData(int userId);
    
    /**
     * Get balance from external API
     * @param accountId account ID
     * @return account balance
     */
    double getBalance(String accountId);
    
    /**
     * Send data to external API
     * @param data data to send
     * @return success status
     */
    boolean sendData(String data);
}