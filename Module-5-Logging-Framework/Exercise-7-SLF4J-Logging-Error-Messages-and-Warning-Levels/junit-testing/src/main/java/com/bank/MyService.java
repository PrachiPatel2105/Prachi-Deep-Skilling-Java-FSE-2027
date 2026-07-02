package com.bank;

/**
 * MyService - Service that depends on external API
 * This class will be tested using mocks
 */
public class MyService {
    
    private ExternalApi externalApi;
    
    /**
     * Constructor - inject ExternalApi dependency
     */
    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }
    
    /**
     * Fetch data from external API
     * @return data from API
     */
    public String fetchData() {
        return externalApi.getData();
    }
    
    /**
     * Get user information
     * @param userId user ID
     * @return user data
     */
    public String getUserInfo(int userId) {
        String userData = externalApi.getUserData(userId);
        return "User: " + userData;
    }
    
    /**
     * Check if account has sufficient balance
     * @param accountId account ID
     * @param requiredAmount required amount
     * @return true if balance is sufficient
     */
    public boolean hasSufficientBalance(String accountId, double requiredAmount) {
        double balance = externalApi.getBalance(accountId);
        return balance >= requiredAmount;
    }
    
    /**
     * Process transaction
     * @param data transaction data
     * @return success status
     */
    public boolean processTransaction(String data) {
        return externalApi.sendData(data);
    }
}