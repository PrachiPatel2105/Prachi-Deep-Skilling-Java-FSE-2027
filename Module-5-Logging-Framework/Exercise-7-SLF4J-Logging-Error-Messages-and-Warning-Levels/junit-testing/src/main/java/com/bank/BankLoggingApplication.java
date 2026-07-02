package com.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BankLoggingApplication - Real-world banking application logging example
 *
 * Demonstrates:
 * - Logging user actions
 * - Logging business operations
 * - Error and warning logging
 * - Exception logging
 */
public class BankLoggingApplication {

    private static final Logger logger = LoggerFactory.getLogger(BankLoggingApplication.class);

    private String accountId;
    private double balance;

    /**
     * Constructor
     */
    public BankLoggingApplication(String accountId, double initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
        logger.info("Bank account created - Account: {}, Balance: ${}", accountId, initialBalance);
    }

    /**
     * Deposit money
     */
    public void deposit(double amount) {
        logger.debug("Deposit request received - Amount: ${}", amount);

        if (amount <= 0) {
            logger.error("Error: Deposit failed - Invalid amount: ${}", amount);
            logger.warn("Warning: Negative or zero deposit attempted");
            return;
        }

        balance += amount;
        logger.info("Deposit successful - Account: {}, Amount: ${}, New Balance: ${}",
                accountId, amount, balance);
        logger.debug("Current balance after deposit: ${}", balance);
    }

    /**
     * Withdraw money
     */
    public void withdraw(double amount) {
        logger.debug("Withdrawal request received - Amount: ${}", amount);

        if (amount <= 0) {
            logger.error("Error: Withdrawal failed - Invalid amount: ${}", amount);
            return;
        }

        if (amount > balance) {
            logger.error("Error: Insufficient funds - Requested: ${}, Available: ${}", amount, balance);
            logger.warn("Warning: Withdrawal attempted with insufficient balance");
            return;
        }

        balance -= amount;
        logger.info("Withdrawal successful - Account: {}, Amount: ${}, New Balance: ${}",
                accountId, amount, balance);
    }

    /**
     * Transfer money to another account
     */
    public void transfer(BankLoggingApplication recipient, double amount) {
        logger.info("Transfer initiated - From: {}, To: {}, Amount: ${}",
                this.accountId, recipient.accountId, amount);
        logger.debug("Source balance before transfer: ${}", this.balance);

        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("Transfer amount must be positive");
            }

            if (amount > this.balance) {
                throw new IllegalArgumentException("Insufficient funds for transfer");
            }

            this.balance -= amount;
            recipient.balance += amount;

            logger.info("Transfer successful - From: {}, To: {}, Amount: ${}",
                    this.accountId, recipient.accountId, amount);
            logger.debug("Source new balance: ${}, Recipient new balance: ${}",
                    this.balance, recipient.balance);

        } catch (IllegalArgumentException e) {
            logger.error("Transfer failed - {}", e.getMessage(), e);
            logger.warn("Warning: Transfer operation failed for accounts {} and {}",
                    this.accountId, recipient.accountId);
        }
    }

    /**
     * Get current balance
     */
    public double getBalance() {
        logger.debug("Balance inquiry - Account: {}, Balance: ${}", accountId, balance);
        return balance;
    }

    public static void main(String[] args) {
        System.out.println("\n========== BANK LOGGING APPLICATION ==========\n");

        logger.info("========== Bank Application Started ==========");

        // Create accounts
        BankLoggingApplication account1 = new BankLoggingApplication("ACC001", 5000.0);
        BankLoggingApplication account2 = new BankLoggingApplication("ACC002", 3000.0);

        logger.info("--- Testing Deposits ---");
        account1.deposit(1000.0);
        account1.deposit(-500.0);  // Error case

        logger.info("\n--- Testing Withdrawals ---");
        account1.withdraw(500.0);
        account1.withdraw(10000.0);  // Error case

        logger.info("\n--- Testing Transfers ---");
        account1.transfer(account2, 2000.0);
        account1.transfer(account2, 10000.0);  // Error case

        logger.info("\n--- Final Balances ---");
        logger.info("Account 1 ({}) - Balance: ${}", "ACC001", account1.getBalance());
        logger.info("Account 2 ({}) - Balance: ${}", "ACC002", account2.getBalance());

        logger.info("========== Bank Application Ended ==========\n");
    }
}