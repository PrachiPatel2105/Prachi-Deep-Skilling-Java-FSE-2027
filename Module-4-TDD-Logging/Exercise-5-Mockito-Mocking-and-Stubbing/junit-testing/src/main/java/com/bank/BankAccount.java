package com.bank;

/**
 * BankAccount - Simple bank account class
 */
public class BankAccount {
    
    private String accountHolder;
    private double balance;
    private boolean isActive;
    
    /**
     * Constructor
     */
    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.isActive = true;
    }
    
    /**
     * Deposit money
     */
    public void deposit(double amount) {
        if (!isActive) {
            throw new IllegalStateException("Account is not active");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }
    
    /**
     * Withdraw money
     */
    public void withdraw(double amount) {
        if (!isActive) {
            throw new IllegalStateException("Account is not active");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
    }
    
    /**
     * Get current balance
     */
    public double getBalance() {
        return balance;
    }
    
    /**
     * Get account holder name
     */
    public String getAccountHolder() {
        return accountHolder;
    }
    
    /**
     * Close account
     */
    public void closeAccount() {
        isActive = false;
    }
    
    /**
     * Check if account is active
     */
    public boolean isActive() {
        return isActive;
    }
}