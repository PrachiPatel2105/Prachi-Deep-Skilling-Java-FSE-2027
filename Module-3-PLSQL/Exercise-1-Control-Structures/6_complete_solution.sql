-- ============================================
-- EXERCISE 1: PL/SQL CONTROL STRUCTURES
-- COMPLETE SOLUTION - RUN ALL SCENARIOS
-- ============================================

-- Set formatting options
SET ECHO ON;
SET LINESIZE 120;
SET PAGESIZE 50;

-- Clear screen effect
CLEAR SCREEN;

-- ============================================
-- PART 1: SETUP DATABASE
-- ============================================

PROMPT
PROMPT ╔═══════════════════════════════════════════════════════════╗
PROMPT ║                                                           ║
PROMPT ║        EXERCISE 1: PL/SQL CONTROL STRUCTURES              ║
PROMPT ║                                                           ║
PROMPT ║  Three Business Scenarios:                               ║
PROMPT ║  1. Age-based interest rate discount                     ║
PROMPT ║  2. VIP promotion based on balance                       ║
PROMPT ║  3. Loan due reminders                                   ║
PROMPT ║                                                           ║
PROMPT ╚═══════════════════════════════════════════════════════════╝
PROMPT

-- Drop existing tables if any
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE Loans';
   EXECUTE IMMEDIATE 'DROP TABLE Customers';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

-- Create tables
PROMPT Creating database schema...

CREATE TABLE Customers (
    CustomerId INT PRIMARY KEY,
    CustomerName VARCHAR2(100) NOT NULL,
    Age INT NOT NULL,
    Email VARCHAR2(100),
    Balance DECIMAL(10, 2) DEFAULT 0,
    IsVIP CHAR(1) DEFAULT 'N',
    CONSTRAINT chk_age CHECK (Age > 0),
    CONSTRAINT chk_balance CHECK (Balance >= 0),
    CONSTRAINT chk_vip CHECK (IsVIP IN ('Y', 'N'))
);

CREATE TABLE Loans (
    LoanId INT PRIMARY KEY,
    CustomerId INT NOT NULL,
    LoanAmount DECIMAL(12, 2) NOT NULL,
    InterestRate DECIMAL(5, 2) NOT NULL,
    DueDate DATE NOT NULL,
    Status VARCHAR2(20) DEFAULT 'Active',
    FOREIGN KEY (CustomerId) REFERENCES Customers(CustomerId),
    CONSTRAINT chk_interest CHECK (InterestRate > 0),
    CONSTRAINT chk_loan_amount CHECK (LoanAmount > 0),
    CONSTRAINT chk_status CHECK (Status IN ('Active', 'Paid', 'Overdue'))
);

PROMPT ✓ Tables created

-- Insert sample data
PROMPT Inserting sample data...

INSERT INTO Customers VALUES (1, 'John Smith', 65, 'john@email.com', 15000, 'N');
INSERT INTO Customers VALUES (2, 'Sarah Johnson', 45, 'sarah@email.com', 8500, 'N');
INSERT INTO Customers VALUES (3, 'Michael Davis', 72, 'michael@email.com', 25000, 'N');
INSERT INTO Customers VALUES (4, 'Emily Wilson', 38, 'emily@email.com', 5000, 'N');
INSERT INTO Customers VALUES (5, 'Robert Brown', 61, 'robert@email.com', 12000, 'N');
INSERT INTO Customers VALUES (6, 'Lisa Anderson', 52, 'lisa@email.com', 9500, 'N');
INSERT INTO Customers VALUES (7, 'James Martinez', 58, 'james@email.com', 3000, 'N');
INSERT INTO Customers VALUES (8, 'Maria Garcia', 68, 'maria@email.com', 18000, 'N');
INSERT INTO Customers VALUES (9, 'David Lee', 42, 'david@email.com', 7000, 'N');
INSERT INTO Customers VALUES (10, 'Jennifer Taylor', 70, 'jennifer@email.com', 20000, 'N');

INSERT INTO Loans VALUES (101, 1, 50000, 6.5, TRUNC(SYSDATE) + 15, 'Active');
INSERT INTO Loans VALUES (102, 1, 30000, 7.0, TRUNC(SYSDATE) + 25, 'Active');
INSERT INTO Loans VALUES (103, 2, 20000, 6.5, TRUNC(SYSDATE) + 45, 'Active');
INSERT INTO Loans VALUES (104, 3, 100000, 5.5, TRUNC(SYSDATE) + 20, 'Active');
INSERT INTO Loans VALUES (105, 3, 50000, 6.0, TRUNC(SYSDATE) + 28, 'Active');
INSERT INTO Loans VALUES (106, 4, 25000, 7.0, TRUNC(SYSDATE) + 60, 'Active');
INSERT INTO Loans VALUES (107, 5, 40000, 6.5, TRUNC(SYSDATE) + 10, 'Active');
INSERT INTO Loans VALUES (108, 6, 35000, 6.75, TRUNC(SYSDATE) + 35, 'Active');
INSERT INTO Loans VALUES (109, 7, 15000, 7.25, TRUNC(SYSDATE) + 5, 'Active');
INSERT INTO Loans VALUES (110, 8, 75000, 5.75, TRUNC(SYSDATE) + 22, 'Active');
INSERT INTO Loans VALUES (111, 9, 30000, 6.5, TRUNC(SYSDATE) + 40, 'Active');
INSERT INTO Loans VALUES (112, 10, 80000, 6.0, TRUNC(SYSDATE) + 12, 'Active');
INSERT INTO Loans VALUES (113, 10, 50000, 6.5, TRUNC(SYSDATE) + 29, 'Active');

COMMIT;
PROMPT ✓ Sample data inserted (10 customers, 13 loans)

-- ============================================
-- PART 2: SCENARIO 1 - AGE DISCOUNT
-- ============================================

PROMPT
PROMPT ╔═══════════════════════════════════════════════════════════╗
PROMPT ║ SCENARIO 1: AGE-BASED INTEREST RATE DISCOUNT             ║
PROMPT ║ Apply 1% discount for customers over 60 years old         ║
PROMPT ╚═══════════════════════════════════════════════════════════╝

DECLARE
    v_discount DECIMAL(5, 2) := 0.01;
    v_customers_updated INT := 0;
    v_loans_updated INT := 0;
BEGIN
    FOR customer_rec IN (SELECT CustomerId, CustomerName, Age FROM Customers ORDER BY Age DESC)
    LOOP
        IF customer_rec.Age > 60 THEN
            DBMS_OUTPUT.PUT_LINE(customer_rec.CustomerName || ' (Age: ' || customer_rec.Age || ')');
            
            FOR loan_rec IN (SELECT LoanId, InterestRate FROM Loans WHERE CustomerId = customer_rec.CustomerId)
            LOOP
                UPDATE Loans 
                SET InterestRate = InterestRate - v_discount
                WHERE LoanId = loan_rec.LoanId;
                
                DBMS_OUTPUT.PUT_LINE('  ✓ Loan ' || loan_rec.LoanId || ': ' || 
                                    ROUND(loan_rec.InterestRate, 2) || '% → ' || 
                                    ROUND(loan_rec.InterestRate - v_discount, 2) || '%');
                v_loans_updated := v_loans_updated + 1;
            END LOOP;
            v_customers_updated := v_customers_updated + 1;
        END IF;
    END LOOP;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('Summary: ' || v_customers_updated || ' customers, ' || 
                         v_loans_updated || ' loans updated');
END;
/

-- ============================================
-- PART 3: SCENARIO 2 - VIP PROMOTION
-- ============================================

PROMPT
PROMPT ╔═══════════════════════════════════════════════════════════╗
PROMPT ║ SCENARIO 2: VIP PROMOTION                                 ║
PROMPT ║ Promote customers with balance > $10,000 to VIP           ║
PROMPT ╚═══════════════════════════════════════════════════════════╝

DECLARE
    v_vip_threshold DECIMAL(10, 2) := 10000;
    v_customers_promoted INT := 0;
BEGIN
    FOR customer_rec IN (SELECT CustomerId, CustomerName, Balance FROM Customers ORDER BY Balance DESC)
    LOOP
        IF customer_rec.Balance > v_vip_threshold AND customer_rec.Balance > 0 THEN
            UPDATE Customers SET IsVIP = 'Y' WHERE CustomerId = customer_rec.CustomerId;
            DBMS_OUTPUT.PUT_LINE('✓ ' || customer_rec.CustomerName || ' → VIP (Balance: $' || 
                                ROUND(customer_rec.Balance, 2) || ')');
            v_customers_promoted := v_customers_promoted + 1;
        ELSE
            DBMS_OUTPUT.PUT_LINE('○ ' || customer_rec.CustomerName || ' → Regular (Balance: $' || 
                                ROUND(customer_rec.Balance, 2) || ')');
        END IF;
    END LOOP;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('Summary: ' || v_customers_promoted || ' customers promoted to VIP');
END;
/

-- ============================================
-- PART 4: SCENARIO 3 - LOAN REMINDERS
-- ============================================

PROMPT
PROMPT ╔═══════════════════════════════════════════════════════════╗
PROMPT ║ SCENARIO 3: LOAN DUE REMINDERS                            ║
PROMPT ║ Loans due within 30 days                                  ║
PROMPT ╚═══════════════════════════════════════════════════════════╝

DECLARE
    v_reminders_sent INT := 0;
    v_today DATE := TRUNC(SYSDATE);
BEGIN
    FOR loan_rec IN (
        SELECT l.LoanId, c.CustomerName, l.DueDate, 
               TRUNC(l.DueDate - v_today) as DaysUntilDue
        FROM Loans l
        JOIN Customers c ON l.CustomerId = c.CustomerId
        WHERE l.DueDate > v_today AND l.DueDate <= v_today + 30
        ORDER BY l.DueDate ASC
    )
    LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder for: ' || loan_rec.CustomerName);
        DBMS_OUTPUT.PUT_LINE('  Loan ' || loan_rec.LoanId || ' due in ' || loan_rec.DaysUntilDue || ' days');
        
        IF loan_rec.DaysUntilDue <= 7 THEN
            DBMS_OUTPUT.PUT_LINE('  ⚠️  URGENT');
        ELSIF loan_rec.DaysUntilDue <= 14 THEN
            DBMS_OUTPUT.PUT_LINE('  ⚠️  SOON');
        ELSE
            DBMS_OUTPUT.PUT_LINE('  ℹ️  OK');
        END IF;
        v_reminders_sent := v_reminders_sent + 1;
    END LOOP;
    
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('Summary: ' || v_reminders_sent || ' reminders sent');
END;
/

-- ============================================
-- FINAL RESULTS
-- ============================================

PROMPT
PROMPT ╔═══════════════════════════════════════════════════════════╗
PROMPT ║ FINAL RESULTS                                             ║
PROMPT ╚═══════════════════════════════════════════════════════════╝
PROMPT

PROMPT Updated Customers:
SELECT CustomerId, CustomerName, Age, Balance, IsVIP FROM Customers ORDER BY CustomerId;

PROMPT
PROMPT Updated Loans:
SELECT LoanId, CustomerId, LoanAmount, InterestRate, DueDate FROM Loans ORDER BY LoanId;