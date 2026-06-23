-- ============================================
-- SCENARIO 1: PROCESS MONTHLY INTEREST
-- Stored Procedure to calculate and apply interest
-- ============================================

-- Drop procedure if exists
BEGIN
   EXECUTE IMMEDIATE 'DROP PROCEDURE ProcessMonthlyInterest';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

-- ============================================
-- CREATE STORED PROCEDURE
-- ============================================

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
IS
    -- Variables
    v_savings_count INT := 0;
    v_total_interest DECIMAL(12, 2) := 0;
    v_interest_amount DECIMAL(12, 2);
    v_new_balance DECIMAL(12, 2);
    
BEGIN
    DBMS_OUTPUT.PUT_LINE('╔════════════════════════════════════════════════╗');
    DBMS_OUTPUT.PUT_LINE('║  SCENARIO 1: PROCESS MONTHLY INTEREST          ║');
    DBMS_OUTPUT.PUT_LINE('║  Apply 1% interest to all savings accounts     ║');
    DBMS_OUTPUT.PUT_LINE('╚════════════════════════════════════════════════╝');
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('Starting monthly interest processing...');
    DBMS_OUTPUT.PUT_LINE('');
    
    -- Process all savings accounts
    FOR account_rec IN (
        SELECT AccountId, EmployeeId, Balance, InterestRate 
        FROM Accounts 
        WHERE AccountType = 'Savings'
        ORDER BY AccountId
    )
    LOOP
        -- Calculate interest (1% per month)
        v_interest_amount := account_rec.Balance * (account_rec.InterestRate / 100);
        
        -- Calculate new balance
        v_new_balance := account_rec.Balance + v_interest_amount;
        
        -- Update account balance
        UPDATE Accounts 
        SET Balance = v_new_balance
        WHERE AccountId = account_rec.AccountId;
        
        -- Record transaction
        INSERT INTO Transactions (
            TransactionId, FromAccountId, ToAccountId, Amount, 
            TransactionType, Description
        )
        VALUES (
            TRANSACTIONS_SEQ.NEXTVAL, NULL, account_rec.AccountId, 
            v_interest_amount, 'Interest', 
            'Monthly interest applied at ' || account_rec.InterestRate || '%'
        );
        
        -- Display details
        DBMS_OUTPUT.PUT_LINE('Account ' || account_rec.AccountId || ':');
        DBMS_OUTPUT.PUT_LINE('  Previous Balance: $' || ROUND(account_rec.Balance, 2));
        DBMS_OUTPUT.PUT_LINE('  Interest Rate: ' || account_rec.InterestRate || '%');
        DBMS_OUTPUT.PUT_LINE('  Interest Amount: $' || ROUND(v_interest_amount, 2));
        DBMS_OUTPUT.PUT_LINE('  New Balance: $' || ROUND(v_new_balance, 2));
        DBMS_OUTPUT.PUT_LINE('');
        
        -- Increment counters
        v_savings_count := v_savings_count + 1;
        v_total_interest := v_total_interest + v_interest_amount;
        
    END LOOP;
    
    -- Commit all changes
    COMMIT;
    
    -- Display summary
    DBMS_OUTPUT.PUT_LINE('═════════════════════════════════════════════════');
    DBMS_OUTPUT.PUT_LINE('SUMMARY:');
    DBMS_OUTPUT.PUT_LINE('• Savings accounts processed: ' || v_savings_count);
    DBMS_OUTPUT.PUT_LINE('• Total interest applied: $' || ROUND(v_total_interest, 2));
    DBMS_OUTPUT.PUT_LINE('✓ Monthly interest processing completed!');
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLCODE || ' - ' || SQLERRM);
        
END ProcessMonthlyInterest;
/

-- ============================================
-- CREATE SEQUENCE FOR TRANSACTIONS
-- ============================================

BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE TRANSACTIONS_SEQ';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

CREATE SEQUENCE TRANSACTIONS_SEQ START WITH 1001 INCREMENT BY 1;

-- ============================================
-- TEST THE PROCEDURE
-- ============================================

DBMS_OUTPUT.PUT_LINE('');
DBMS_OUTPUT.PUT_LINE('Executing ProcessMonthlyInterest...');
DBMS_OUTPUT.PUT_LINE('');

BEGIN
    ProcessMonthlyInterest();
END;
/

-- ============================================
-- VERIFICATION
-- ============================================

DBMS_OUTPUT.PUT_LINE('');
DBMS_OUTPUT.PUT_LINE('VERIFICATION - Updated Account Balances:');
DBMS_OUTPUT.PUT_LINE('');

SELECT AccountId, EmployeeId, AccountType, Balance, InterestRate
FROM Accounts
WHERE AccountType = 'Savings'
ORDER BY AccountId;