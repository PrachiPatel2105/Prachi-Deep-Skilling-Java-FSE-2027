-- ============================================
-- SCENARIO 1: AGE-BASED INTEREST RATE DISCOUNT
-- Apply 1% discount to loans for customers > 60
-- ============================================

SET ECHO ON;
SET PAGESIZE 0;

-- Display header
BEGIN
    DBMS_OUTPUT.PUT_LINE('╔════════════════════════════════════════════════╗');
    DBMS_OUTPUT.PUT_LINE('║  SCENARIO 1: AGE DISCOUNT (> 60 years old)    ║');
    DBMS_OUTPUT.PUT_LINE('║  Discount: 1% on loan interest rate           ║');
    DBMS_OUTPUT.PUT_LINE('╚════════════════════════════════════════════════╝');
    DBMS_OUTPUT.PUT_LINE('');
END;
/

-- ============================================
-- PL/SQL BLOCK: SCENARIO 1
-- ============================================

DECLARE
    -- Variables
    v_discount DECIMAL(5, 2) := 0.01;  -- 1% discount
    v_customers_updated INT := 0;
    v_loans_updated INT := 0;
    v_original_rate DECIMAL(5, 2);
    v_new_rate DECIMAL(5, 2);
    
BEGIN
    DBMS_OUTPUT.PUT_LINE('Starting interest rate discount application...');
    DBMS_OUTPUT.PUT_LINE('');
    
    -- OUTER LOOP: Iterate through all customers
    FOR customer_rec IN (SELECT CustomerId, CustomerName, Age FROM Customers ORDER BY Age DESC)
    LOOP
        -- IF STATEMENT: Check if customer is above 60
        IF customer_rec.Age > 60 THEN
            
            DBMS_OUTPUT.PUT_LINE('Customer: ' || customer_rec.CustomerName || ' (Age: ' || customer_rec.Age || ')');
            
            -- INNER LOOP: Update all loans for this customer
            FOR loan_rec IN (SELECT LoanId, InterestRate FROM Loans WHERE CustomerId = customer_rec.CustomerId)
            LOOP
                -- Store original rate
                v_original_rate := loan_rec.InterestRate;
                
                -- Calculate new rate (subtract 1%)
                v_new_rate := v_original_rate - v_discount;
                
                -- Update loan with new interest rate
                UPDATE Loans 
                SET InterestRate = v_new_rate
                WHERE LoanId = loan_rec.LoanId;
                
                -- Log the change
                DBMS_OUTPUT.PUT_LINE('  Loan ' || loan_rec.LoanId || ': ' || 
                                    v_original_rate || '% → ' || v_new_rate || '%');
                
                -- Increment counter
                v_loans_updated := v_loans_updated + 1;
            END LOOP;
            
            -- Increment customer counter
            v_customers_updated := v_customers_updated + 1;
            DBMS_OUTPUT.PUT_LINE('');
            
        END IF;  -- End of age check
    END LOOP;  -- End of customer loop
    
    -- Commit all changes
    COMMIT;
    
    -- Display summary
    DBMS_OUTPUT.PUT_LINE('═════════════════════════════════════════════════');
    DBMS_OUTPUT.PUT_LINE('SUMMARY:');
    DBMS_OUTPUT.PUT_LINE('• Customers updated: ' || v_customers_updated);
    DBMS_OUTPUT.PUT_LINE('• Loans updated: ' || v_loans_updated);
    DBMS_OUTPUT.PUT_LINE('• Discount applied: ' || (v_discount * 100) || '%');
    DBMS_OUTPUT.PUT_LINE('✓ Transaction committed successfully!');
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLCODE || ' - ' || SQLERRM);
END;
/

-- ============================================
-- VERIFICATION: Display updated loans
-- ============================================

DBMS_OUTPUT.PUT_LINE('');
DBMS_OUTPUT.PUT_LINE('VERIFICATION - Updated Loan Interest Rates:');
DBMS_OUTPUT.PUT_LINE('');

SELECT c.CustomerId, 
       c.CustomerName, 
       c.Age,
       l.LoanId, 
       l.InterestRate,
       CASE WHEN c.Age > 60 THEN '✓ Discounted' ELSE '  Regular' END AS Status
FROM Customers c
LEFT JOIN Loans l ON c.CustomerId = l.CustomerId
ORDER BY c.Age DESC, c.CustomerId, l.LoanId;