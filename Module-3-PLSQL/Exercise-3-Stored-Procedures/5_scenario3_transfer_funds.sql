-- ============================================
-- SCENARIO 3: TRANSFER FUNDS BETWEEN ACCOUNTS
-- Stored Procedure with validation and error handling
-- ============================================

-- Drop procedure if exists
BEGIN
   EXECUTE IMMEDIATE 'DROP PROCEDURE TransferFunds';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

-- ============================================
-- CREATE STORED PROCEDURE
-- ============================================

CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_account_id IN INT,
    p_to_account_id IN INT,
    p_amount IN DECIMAL
)
IS
    -- Variables
    v_from_balance DECIMAL(12, 2);
    v_to_balance DECIMAL(12, 2);
    v_from_account_exists INT;
    v_to_account_exists INT;
    v_new_from_balance DECIMAL(12, 2);
    v_new_to_balance DECIMAL(12, 2);
    
BEGIN
    DBMS_OUTPUT.PUT_LINE('╔════════════════════════════════════════════════╗');
    DBMS_OUTPUT.PUT_LINE('║  SCENARIO 3: TRANSFER FUNDS                   ║');
    DBMS_OUTPUT.PUT_LINE('║  Transfer amount between accounts              ║');
    DBMS_OUTPUT.PUT_LINE('╚════════════════════════════════════════════════╝');
    DBMS_OUTPUT.PUT_LINE('');
    
    -- Validate parameters
    IF p_from_account_id IS NULL OR p_to_account_id IS NULL THEN
        RAISE_APPLICATION_ERROR(-20001, 'Account IDs cannot be null');
    END IF;
    
    IF p_amount IS NULL OR p_amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Transfer amount must be greater than 0');
    END IF;
    
    IF p_from_account_id = p_to_account_id THEN
        RAISE_APPLICATION_ERROR(-20003, 'Cannot transfer to the same account');
    END IF;
    
    -- Check if FROM account exists
    BEGIN
        SELECT Balance INTO v_from_balance
        FROM Accounts
        WHERE AccountId = p_from_account_id;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20004, 'Source account not found');
    END;
    
    -- Check if TO account exists
    BEGIN
        SELECT Balance INTO v_to_balance
        FROM Accounts
        WHERE AccountId = p_to_account_id;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20005, 'Destination account not found');
    END;
    
    -- Check if source account has sufficient balance
    IF v_from_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20006, 
            'Insufficient balance. Available: $' || v_from_balance || 
            ', Requested: $' || p_amount);
    END IF;
    
    DBMS_OUTPUT.PUT_LINE('FROM Account: ' || p_from_account_id);
    DBMS_OUTPUT.PUT_LINE('  Current Balance: $' || ROUND(v_from_balance, 2));
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('TO Account: ' || p_to_account_id);
    DBMS_OUTPUT.PUT_LINE('  Current Balance: $' || ROUND(v_to_balance, 2));
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('Transfer Amount: $' || ROUND(p_amount, 2));
    DBMS_OUTPUT.PUT_LINE('');
    
    -- Calculate new balances
    v_new_from_balance := v_from_balance - p_amount;
    v_new_to_balance := v_to_balance + p_amount;
    
    -- Deduct from source account
    UPDATE Accounts
    SET Balance = v_new_from_balance
    WHERE AccountId = p_from_account_id;
    
    -- Add to destination account
    UPDATE Accounts
    SET Balance = v_new_to_balance
    WHERE AccountId = p_to_account_id;
    
    -- Record transaction
    INSERT INTO Transactions (
        TransactionId, FromAccountId, ToAccountId, Amount,
        TransactionType, Description
    )
    VALUES (
        TRANSACTIONS_SEQ.NEXTVAL, p_from_account_id, p_to_account_id,
        p_amount, 'Transfer',
        'Fund transfer from Account ' || p_from_account_id || 
        ' to Account ' || p_to_account_id
    );
    
    -- Commit transaction
    COMMIT;
    
    -- Display results
    DBMS_OUTPUT.PUT_LINE('TRANSFER COMPLETED:');
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('FROM Account ' || p_from_account_id || ':');
    DBMS_OUTPUT.PUT_LINE('  Previous Balance: $' || ROUND(v_from_balance, 2));
    DBMS_OUTPUT.PUT_LINE('  Amount Transferred: -$' || ROUND(p_amount, 2));
    DBMS_OUTPUT.PUT_LINE('  New Balance: $' || ROUND(v_new_from_balance, 2));
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('TO Account ' || p_to_account_id || ':');
    DBMS_OUTPUT.PUT_LINE('  Previous Balance: $' || ROUND(v_to_balance, 2));
    DBMS_OUTPUT.PUT_LINE('  Amount Received: +$' || ROUND(p_amount, 2));
    DBMS_OUTPUT.PUT_LINE('  New Balance: $' || ROUND(v_new_to_balance, 2));
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('✓ Transfer completed successfully!');
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('❌ TRANSFER FAILED');
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLCODE || ' - ' || SQLERRM);
        
END TransferFunds;
/

-- ============================================
-- TEST THE PROCEDURE - SUCCESSFUL TRANSFER
-- ============================================

DBMS_OUTPUT.PUT_LINE('');
DBMS_OUTPUT.PUT_LINE('TEST 1: Successful Transfer ($10,000 from Account 1001 to 1003)');
DBMS_OUTPUT.PUT_LINE('');

BEGIN
    TransferFunds(1001, 1003, 10000);
END;
/

-- ============================================
-- TEST THE PROCEDURE - INSUFFICIENT BALANCE
-- ============================================

DBMS_OUTPUT.PUT_LINE('');
DBMS_OUTPUT.PUT_LINE('TEST 2: Insufficient Balance (Try to transfer $100,000)');
DBMS_OUTPUT.PUT_LINE('');

BEGIN
    TransferFunds(1001, 1003, 100000);
EXCEPTION
    WHEN OTHERS THEN
        NULL;  -- Error already displayed
END;
/

-- ============================================
-- VERIFICATION
-- ============================================

DBMS_OUTPUT.PUT_LINE('');
DBMS_OUTPUT.PUT_LINE('VERIFICATION - Account Balances After Transfers:');
DBMS_OUTPUT.PUT_LINE('');

SELECT AccountId, EmployeeId, AccountType, Balance
FROM Accounts
WHERE AccountId IN (1001, 1003)
ORDER BY AccountId;

DBMS_OUTPUT.PUT_LINE('');
DBMS_OUTPUT.PUT_LINE('Transaction History:');
DBMS_OUTPUT.PUT_LINE('');

SELECT TransactionId, FromAccountId, ToAccountId, Amount, 
       TransactionDate, TransactionType
FROM Transactions
ORDER BY TransactionId DESC;