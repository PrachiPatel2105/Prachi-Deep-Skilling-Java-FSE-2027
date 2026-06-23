-- ============================================
-- SCENARIO 3: LOAN DUE REMINDERS
-- Find loans due within next 30 days
-- Send reminder message for each
-- ============================================

SET ECHO ON;

-- Display header
BEGIN
    DBMS_OUTPUT.PUT_LINE('╔════════════════════════════════════════════════╗');
    DBMS_OUTPUT.PUT_LINE('║  SCENARIO 3: LOAN DUE REMINDERS                ║');
    DBMS_OUTPUT.PUT_LINE('║  Find loans due within 30 days                 ║');
    DBMS_OUTPUT.PUT_LINE('╚════════════════════════════════════════════════╝');
    DBMS_OUTPUT.PUT_LINE('');
END;
/

-- ============================================
-- PL/SQL BLOCK: SCENARIO 3
-- ============================================

DECLARE
    -- Variables
    v_days_threshold INT := 30;
    v_today DATE := TRUNC(SYSDATE);
    v_future_date DATE := v_today + v_days_threshold;
    v_reminders_sent INT := 0;
    v_days_until_due INT;
    v_daily_interest DECIMAL(10, 2);
    
BEGIN
    DBMS_OUTPUT.PUT_LINE('Current Date: ' || TO_CHAR(v_today, 'DD-MON-YYYY'));
    DBMS_OUTPUT.PUT_LINE('Checking for loans due by: ' || TO_CHAR(v_future_date, 'DD-MON-YYYY'));
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('═════════════════════════════════════════════════');
    DBMS_OUTPUT.PUT_LINE('');
    
    -- LOOP: Iterate through all loans
    FOR loan_rec IN (
        SELECT l.LoanId, 
               l.CustomerId, 
               l.LoanAmount, 
               l.InterestRate, 
               l.DueDate,
               c.CustomerName,
               c.Email
        FROM Loans l
        JOIN Customers c ON l.CustomerId = c.CustomerId
        WHERE l.Status = 'Active' 
          AND l.DueDate > v_today  -- Loan not yet due
          AND l.DueDate <= v_future_date  -- Due within 30 days
        ORDER BY l.DueDate ASC
    )
    LOOP
        -- Calculate days until due
        v_days_until_due := loan_rec.DueDate - v_today;
        
        -- Calculate daily interest accrual
        v_daily_interest := (loan_rec.LoanAmount * loan_rec.InterestRate / 100) / 365;
        
        -- DISPLAY: Reminder message
        DBMS_OUTPUT.PUT_LINE('┌─────────────────────────────────────────────┐');
        DBMS_OUTPUT.PUT_LINE('│ LOAN DUE REMINDER                           │');
        DBMS_OUTPUT.PUT_LINE('└─────────────────────────────────────────────┘');
        DBMS_OUTPUT.PUT_LINE('');
        DBMS_OUTPUT.PUT_LINE('To: ' || loan_rec.CustomerName);
        DBMS_OUTPUT.PUT_LINE('Email: ' || loan_rec.Email);
        DBMS_OUTPUT.PUT_LINE('');
        DBMS_OUTPUT.PUT_LINE('Dear ' || loan_rec.CustomerName || ',');
        DBMS_OUTPUT.PUT_LINE('');
        DBMS_OUTPUT.PUT_LINE('This is a friendly reminder that your loan is due soon:');
        DBMS_OUTPUT.PUT_LINE('');
        DBMS_OUTPUT.PUT_LINE('Loan Details:');
        DBMS_OUTPUT.PUT_LINE('  Loan ID: ' || loan_rec.LoanId);
        DBMS_OUTPUT.PUT_LINE('  Amount: $' || ROUND(loan_rec.LoanAmount, 2));
        DBMS_OUTPUT.PUT_LINE('  Interest Rate: ' || loan_rec.InterestRate || '%');
        DBMS_OUTPUT.PUT_LINE('  Daily Interest: $' || ROUND(v_daily_interest, 2));
        DBMS_OUTPUT.PUT_LINE('');
        DBMS_OUTPUT.PUT_LINE('Due Date: ' || TO_CHAR(loan_rec.DueDate, 'DD-MON-YYYY'));
        
        -- CASE statement: Display urgency
        DBMS_OUTPUT.PUT_LINE('Days Until Due: ' || v_days_until_due || ' days ');
        
        IF v_days_until_due <= 7 THEN
            DBMS_OUTPUT.PUT_LINE('⚠️  URGENT: Payment due within 1 week!');
        ELSIF v_days_until_due <= 14 THEN
            DBMS_OUTPUT.PUT_LINE('⚠️  ATTENTION: Payment due within 2 weeks');
        ELSE
            DBMS_OUTPUT.PUT_LINE('ℹ️  Payment due within 30 days');
        END IF;
        
        DBMS_OUTPUT.PUT_LINE('');
        DBMS_OUTPUT.PUT_LINE('Please ensure payment is made on or before the due date');
        DBMS_OUTPUT.PUT_LINE('to avoid late fees and interest penalties.');
        DBMS_OUTPUT.PUT_LINE('');
        DBMS_OUTPUT.PUT_LINE('Thank you for banking with us!');
        DBMS_OUTPUT.PUT_LINE('');
        DBMS_OUTPUT.PUT_LINE('═════════════════════════════════════════════════');
        DBMS_OUTPUT.PUT_LINE('');
        
        -- Increment counter
        v_reminders_sent := v_reminders_sent + 1;
        
    END LOOP;  -- End of loan loop
    
    -- Display summary
    DBMS_OUTPUT.PUT_LINE('SUMMARY:');
    DBMS_OUTPUT.PUT_LINE('• Total reminders sent: ' || v_reminders_sent);
    
    IF v_reminders_sent = 0 THEN
        DBMS_OUTPUT.PUT_LINE('✓ No loans due within 30 days - All good!');
    ELSE
        DBMS_OUTPUT.PUT_LINE('✓ All reminders processed successfully!');
    END IF;
    
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLCODE || ' - ' || SQLERRM);
END;
/

-- ============================================
-- VERIFICATION: Display loans due soon
-- ============================================

DBMS_OUTPUT.PUT_LINE('');
DBMS_OUTPUT.PUT_LINE('VERIFICATION - Loans Due Within 30 Days:');
DBMS_OUTPUT.PUT_LINE('');

SELECT l.LoanId,
       c.CustomerName,
       l.LoanAmount,
       l.InterestRate,
       l.DueDate,
       TRUNC(l.DueDate - SYSDATE) as DaysUntilDue,
       CASE 
           WHEN l.DueDate - TRUNC(SYSDATE) <= 7 THEN '⚠️  URGENT'
           WHEN l.DueDate - TRUNC(SYSDATE) <= 14 THEN '⚠️  SOON'
           ELSE 'ℹ️  OK'
       END as Urgency
FROM Loans l
JOIN Customers c ON l.CustomerId = c.CustomerId
WHERE l.Status = 'Active'
  AND l.DueDate > TRUNC(SYSDATE)
  AND l.DueDate <= TRUNC(SYSDATE) + 30
ORDER BY l.DueDate ASC;