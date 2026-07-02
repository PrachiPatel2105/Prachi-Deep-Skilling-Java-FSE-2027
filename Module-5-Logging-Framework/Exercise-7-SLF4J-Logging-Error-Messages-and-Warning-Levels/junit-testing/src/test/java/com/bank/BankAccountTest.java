package com.bank;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class BankAccountTest {
    
    private BankAccount account;
    
    @Before
    public void setUp() {
        System.out.println("\n--- SETUP: Creating test BankAccount ---");
        account = new BankAccount("John Doe", 1000.00);
        System.out.println("SETUP: Account created with balance: $" + account.getBalance());
    }
    
    @After
    public void tearDown() {
        System.out.println("--- TEARDOWN: Cleaning up resources ---");
        account = null;
    }
    
    @Test
    public void testDepositMoneyAAAPattern() {
        System.out.println("\n=== Test: Deposit Money (AAA Pattern) ===");
        
        double initialBalance = account.getBalance();
        double depositAmount = 500.00;
        double expectedBalance = initialBalance + depositAmount;
        System.out.println("ARRANGE: Initial balance = $" + initialBalance);
        System.out.println("ARRANGE: Deposit amount = $" + depositAmount);
        System.out.println("ARRANGE: Expected balance = $" + expectedBalance);
        
        account.deposit(depositAmount);
        System.out.println("ACT: Deposited $" + depositAmount);
        
        double actualBalance = account.getBalance();
        assertEquals(expectedBalance, actualBalance, 0.01);
        System.out.println("ASSERT: Balance is correct");
        System.out.println("ASSERT: New balance = $" + actualBalance);
    }
    
    @Test
    public void testWithdrawMoneyAAAPattern() {
        System.out.println("\n=== Test: Withdraw Money (AAA Pattern) ===");
        
        double initialBalance = account.getBalance();
        double withdrawAmount = 300.00;
        double expectedBalance = initialBalance - withdrawAmount;
        System.out.println("ARRANGE: Initial balance = $" + initialBalance);
        System.out.println("ARRANGE: Withdrawal amount = $" + withdrawAmount);
        System.out.println("ARRANGE: Expected balance = $" + expectedBalance);
        
        account.withdraw(withdrawAmount);
        System.out.println("ACT: Withdrew $" + withdrawAmount);
        
        double actualBalance = account.getBalance();
        assertEquals(expectedBalance, actualBalance, 0.01);
        System.out.println("ASSERT: Balance is correct");
        System.out.println("ASSERT: New balance = $" + actualBalance);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInsufficientFundsError() {
        System.out.println("\n=== Test: Insufficient Funds Error ===");
        
        double balance = account.getBalance();
        double withdrawAmount = balance + 100;
        System.out.println("ARRANGE: Current balance = $" + balance);
        System.out.println("ARRANGE: Trying to withdraw = $" + withdrawAmount);
        
        System.out.println("ACT: Attempting withdrawal...");
        account.withdraw(withdrawAmount);
        
        System.out.println("ASSERT: Exception thrown as expected");
    }
    
    @Test
    public void testCloseAccountAAAPattern() {
        System.out.println("\n=== Test: Close Account (AAA Pattern) ===");
        
        assertTrue(account.isActive());
        System.out.println("ARRANGE: Account is active");
        
        account.closeAccount();
        System.out.println("ACT: Closed account");
        
        assertFalse(account.isActive());
        System.out.println("ASSERT: Account is now inactive");
    }
    
    @Test(expected = IllegalStateException.class)
    public void testDepositToClosedAccountError() {
        System.out.println("\n=== Test: Deposit to Closed Account Error ===");
        
        account.closeAccount();
        System.out.println("ARRANGE: Account is closed");
        
        System.out.println("ACT: Attempting deposit to closed account...");
        account.deposit(100.00);
        
        System.out.println("ASSERT: Exception thrown as expected");
    }
    
    @Test
    public void testMultipleOperationsAAAPattern() {
        System.out.println("\n=== Test: Multiple Operations (AAA Pattern) ===");
        
        double balance1 = account.getBalance();
        System.out.println("ARRANGE: Initial balance = $" + balance1);
        
        account.deposit(500);
        System.out.println("ACT: Deposited $500");
        
        double balance2 = account.getBalance();
        account.withdraw(200);
        System.out.println("ACT: Withdrew $200");
        
        double balance3 = account.getBalance();
        account.deposit(100);
        System.out.println("ACT: Deposited $100");
        
        double finalBalance = account.getBalance();
        
        double expectedBalance = 1000 + 500 - 200 + 100;
        assertEquals(expectedBalance, finalBalance, 0.01);
        System.out.println("ASSERT: Final balance = $" + finalBalance);
    }
}