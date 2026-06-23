-- ============================================
-- EXERCISE 3: STORED PROCEDURES
-- COMPLETE TEST OF ALL THREE SCENARIOS
-- ============================================

SET ECHO ON;
SET LINESIZE 120;

CLEAR SCREEN;

-- ============================================
-- PART 1: SETUP
-- ============================================

PROMPT
PROMPT ╔═══════════════════════════════════════════════════════════╗
PROMPT ║                                                           ║
PROMPT ║    EXERCISE 3: PL/SQL STORED PROCEDURES                  ║
PROMPT ║                                                           ║
PROMPT ║  Three Business Scenarios:                               ║
PROMPT ║  1. Process Monthly Interest                             ║
PROMPT ║  2. Update Employee Bonus                                ║
PROMPT ║  3. Transfer Funds Between Accounts                      ║
PROMPT ║                                                           ║
PROMPT ╚═══════════════════════════════════════════════════════════╝
PROMPT

-- Create tables
PROMPT Creating database schema...

BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE Transactions';
   EXECUTE IMMEDIATE 'DROP TABLE Accounts';
   EXECUTE IMMEDIATE 'DROP TABLE Employees';
   EXECUTE IMMEDIATE 'DROP TABLE Departments';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE Departments (
    DepartmentId INT PRIMARY KEY,
    DepartmentName VARCHAR2(100) NOT NULL,
    Budget DECIMAL(12, 2),
    CONSTRAINT chk_dept_budget CHECK (Budget >= 0)
);

CREATE TABLE Employees (
    EmployeeId INT PRIMARY KEY,
    EmployeeName VARCHAR2(100) NOT NULL,
    DepartmentId INT NOT NULL,
    Salary DECIMAL(10, 2) NOT NULL,
    PerformanceRating DECIMAL(3, 1),
    FOREIGN KEY (DepartmentId) REFERENCES Departments(DepartmentId),
    CONSTRAINT chk_salary CHECK (Salary > 0),
    CONSTRAINT chk_performance CHECK (PerformanceRating >= 1 AND PerformanceRating <= 5)
);

CREATE TABLE Accounts (
    AccountId INT PRIMARY KEY,
    EmployeeId INT NOT NULL,
    AccountType VARCHAR2(20) NOT NULL,
    Balance DECIMAL(12, 2) DEFAULT 0,
    InterestRate DECIMAL(5, 2) DEFAULT 1.0,
    DateOpened DATE DEFAULT SYSDATE,
    FOREIGN KEY (EmployeeId) REFERENCES Employees(EmployeeId),
    CONSTRAINT chk_balance CHECK (Balance >= 0),
    CONSTRAINT chk_interest CHECK (InterestRate >= 0),
    CONSTRAINT chk_account_type CHECK (AccountType IN ('Savings', 'Checking'))
);

CREATE TABLE Transactions (
    TransactionId INT PRIMARY KEY,
    FromAccountId INT,
    ToAccountId INT,
    Amount DECIMAL(12, 2) NOT NULL,
    TransactionDate DATE DEFAULT SYSDATE,
    TransactionType VARCHAR2(20),
    Description VARCHAR2(200),
    FOREIGN KEY (FromAccountId) REFERENCES Accounts(AccountId),
    FOREIGN KEY (ToAccountId) REFERENCES Accounts(AccountId),
    CONSTRAINT chk_amount CHECK (Amount > 0)
);

PROMPT ✓ Tables created

-- Insert sample data
PROMPT Inserting sample data...

INSERT INTO Departments VALUES (1, 'IT Department', 500000);
INSERT INTO Departments VALUES (2, 'HR Department', 300000);
INSERT INTO Departments VALUES (3, 'Finance Department', 600000);
INSERT INTO Departments VALUES (4, 'Operations Department', 400000);

INSERT INTO Employees VALUES (101, 'John Smith', 1, 75000, 4.5);
INSERT INTO Employees VALUES (102, 'Sarah Johnson', 1, 65000, 4.0);
INSERT INTO Employees VALUES (103, 'Michael Davis', 2, 55000, 3.8);
INSERT INTO Employees VALUES (104, 'Emily Wilson', 3, 70000, 4.8);
INSERT INTO Employees VALUES (105, 'Robert Brown', 3, 68000, 4.2);
INSERT INTO Employees VALUES (106, 'Lisa Anderson', 4, 60000, 3.5);
INSERT INTO Employees VALUES (107, 'James Martinez', 1, 72000, 4.1);
INSERT INTO Employees VALUES (108, 'Maria Garcia', 2, 58000, 3.9);

INSERT INTO Accounts VALUES (1001, 101, 'Savings', 50000, 1.5, SYSDATE);
INSERT INTO Accounts VALUES (1002, 101, 'Checking', 15000, 0.5, SYSDATE);
INSERT INTO Accounts VALUES (1003, 102, 'Savings', 35000, 1.5, SYSDATE);
INSERT INTO Accounts VALUES (1004, 102, 'Checking', 8000, 0.5, SYSDATE);
INSERT INTO Accounts VALUES (1005, 103, 'Savings', 25000, 1.5, SYSDATE);
INSERT INTO Accounts VALUES (1006, 104, 'Savings', 60000, 1.5, SYSDATE);
INSERT INTO Accounts VALUES (1007, 104, 'Checking', 20000, 0.5, SYSDATE);
INSERT INTO Accounts VALUES (1008, 105, 'Savings', 45000, 1.5, SYSDATE);
INSERT INTO Accounts VALUES (1009, 106, 'Savings', 30000, 1.5, SYSDATE);
INSERT INTO Accounts VALUES (1010, 107, 'Savings', 55000, 1.5, SYSDATE);
INSERT INTO Accounts VALUES (1011, 107, 'Checking', 12000, 0.5, SYSDATE);
INSERT INTO Accounts VALUES (1012, 108, 'Savings', 28000, 1.5, SYSDATE);

COMMIT;
PROMPT ✓ Sample data inserted

-- ============================================
-- PART 2: CREATE SEQUENCE
-- ============================================

BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE TRANSACTIONS_SEQ';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

CREATE SEQUENCE TRANSACTIONS_SEQ START WITH 1001 INCREMENT BY 1;

-- ============================================
-- PART 3: CREATE STORED PROCEDURES
-- ============================================

PROMPT
PROMPT Creating Stored Procedures...

-- Procedure 1: ProcessMonthlyInterest
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
IS
    v_savings_count INT := 0;
    v_total_interest DECIMAL(12, 2) := 0;
    v_interest_amount DECIMAL(12, 2);
    v_new_balance DECIMAL(12, 2);
BEGIN
    DBMS_OUTPUT.PUT_LINE('╔════════════════════════════════════════════════╗');
    DBMS_OUTPUT.PUT_LINE('║  SCENARIO 1: PROCESS MONTHLY INTEREST          ║');
    DBMS_OUTPUT.PUT_LINE('╚════════════════════════════════════════════════╝');
    DBMS_OUTPUT.PUT_LINE('');
    
    FOR account_rec IN (
        SELECT AccountId, EmployeeId, Balance, InterestRate 
        FROM Accounts 
        WHERE AccountType = 'Savings'
        ORDER BY AccountId
    )
    LOOP
        v_interest_amount := account_rec.Balance * (account_rec.InterestRate / 100);
        v_new_balance := account_rec.Balance + v_interest_amount;
        
        UPDATE Accounts 
        SET Balance = v_new_balance
        WHERE AccountId = account_rec.AccountId;
        
        INSERT INTO Transactions (
            TransactionId, FromAccountId, ToAccountId, Amount, 
            TransactionType, Description
        )
        VALUES (
            TRANSACTIONS_SEQ.NEXTVAL, NULL, account_rec.AccountId, 
            v_interest_amount, 'Interest', 
            'Monthly interest applied at ' || account_rec.InterestRate || '%'
        );
        
        DBMS_OUTPUT.PUT_LINE('Account ' || account_rec.AccountId || ': $' || 
                           ROUND(v_interest_amount, 2) || ' interest added');
        
        v_savings_count := v_savings_count + 1;
        v_total_interest := v_total_interest + v_interest_amount;
    END LOOP;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('✓ ' || v_savings_count || ' accounts processed. ' ||
                       'Total: $' || ROUND(v_total_interest, 2));
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END ProcessMonthlyInterest;
/

-- Procedure 2: UpdateEmployeeBonus
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department_id IN INT,
    p_bonus_percentage IN DECIMAL
)
IS
    v_employees_updated INT := 0;
    v_total_bonus DECIMAL(12, 2) := 0;
    v_new_salary DECIMAL(10, 2);
    v_bonus_amount DECIMAL(10, 2);
BEGIN
    DBMS_OUTPUT.PUT_LINE('╔════════════════════════════════════════════════╗');
    DBMS_OUTPUT.PUT_LINE('║  SCENARIO 2: EMPLOYEE BONUS SCHEME             ║');
    DBMS_OUTPUT.PUT_LINE('╚════════════════════════════════════════════════╝');
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('Department ' || p_department_id || ', Bonus: ' || p_bonus_percentage || '%');
    DBMS_OUTPUT.PUT_LINE('');
    
    FOR emp_rec IN (
        SELECT EmployeeId, EmployeeName, Salary
        FROM Employees
        WHERE DepartmentId = p_department_id
    )
    LOOP
        v_bonus_amount := emp_rec.Salary * (p_bonus_percentage / 100);
        v_new_salary := emp_rec.Salary + v_bonus_amount;
        
        UPDATE Employees
        SET Salary = v_new_salary
        WHERE EmployeeId = emp_rec.EmployeeId;
        
        DBMS_OUTPUT.PUT_LINE(emp_rec.EmployeeName || ': +$' || 
                           ROUND(v_bonus_amount, 2));
        
        v_employees_updated := v_employees_updated + 1;
        v_total_bonus := v_total_bonus + v_bonus_amount;
    END LOOP;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('✓ ' || v_employees_updated || ' employees updated. ' ||
                       'Total bonus: $' || ROUND(v_total_bonus, 2));
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END UpdateEmployeeBonus;
/

-- Procedure 3: TransferFunds
CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_account_id IN INT,
    p_to_account_id IN INT,
    p_amount IN DECIMAL
)
IS
    v_from_balance DECIMAL(12, 2);
    v_to_balance DECIMAL(12, 2);
    v_new_from_balance DECIMAL(12, 2);
    v_new_to_balance DECIMAL(12, 2);
BEGIN
    DBMS_OUTPUT.PUT_LINE('╔════════════════════════════════════════════════╗');
    DBMS_OUTPUT.PUT_LINE('║  SCENARIO 3: TRANSFER FUNDS                   ║');
    DBMS_OUTPUT.PUT_LINE('╚════════════════════════════════════════════════╝');
    DBMS_OUTPUT.PUT_LINE('');
    
    SELECT Balance INTO v_from_balance
    FROM Accounts WHERE AccountId = p_from_account_id;
    
    SELECT Balance INTO v_to_balance
    FROM Accounts WHERE AccountId = p_to_account_id;
    
    IF v_from_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20006, 'Insufficient balance');
    END IF;
    
    v_new_from_balance := v_from_balance - p_amount;
    v_new_to_balance := v_to_balance + p_amount;
    
    UPDATE Accounts SET Balance = v_new_from_balance 
    WHERE AccountId = p_from_account_id;
    
    UPDATE Accounts SET Balance = v_new_to_balance 
    WHERE AccountId = p_to_account_id;
    
    INSERT INTO Transactions (
        TransactionId, FromAccountId, ToAccountId, Amount,
        TransactionType, Description
    )
    VALUES (
        TRANSACTIONS_SEQ.NEXTVAL, p_from_account_id, p_to_account_id,
        p_amount, 'Transfer', 'Fund transfer'
    );
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('From Acc ' || p_from_account_id || ': $' || 
                       ROUND(v_from_balance, 2) || ' → $' || 
                       ROUND(v_new_from_balance, 2));
    DBMS_OUTPUT.PUT_LINE('To Acc ' || p_to_account_id || ': $' || 
                       ROUND(v_to_balance, 2) || ' → $' || 
                       ROUND(v_new_to_balance, 2));
    DBMS_OUTPUT.PUT_LINE('✓ Transfer of $' || ROUND(p_amount, 2) || ' completed');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('❌ ERROR: ' || SQLERRM);
END TransferFunds;
/

PROMPT ✓ Stored Procedures created

-- ============================================
-- PART 4: TEST PROCEDURES
-- ============================================

PROMPT
PROMPT ╔═══════════════════════════════════════════════════════════╗
PROMPT ║                 TESTING PROCEDURES                        ║
PROMPT ╚═══════════════════════════════════════════════════════════╝
PROMPT

BEGIN
    DBMS_OUTPUT.PUT_LINE('');
    ProcessMonthlyInterest();
END;
/

PROMPT
BEGIN
    DBMS_OUTPUT.PUT_LINE('');
    UpdateEmployeeBonus(1, 10);
END;
/

PROMPT
BEGIN
    DBMS_OUTPUT.PUT_LINE('');
    TransferFunds(1001, 1003, 10000);
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

PROMPT Updated Accounts:
SELECT AccountId, EmployeeId, AccountType, Balance FROM Accounts ORDER BY AccountId;

PROMPT
PROMPT Updated Employees (IT Department):
SELECT EmployeeId, EmployeeName, Salary FROM Employees WHERE DepartmentId = 1 ORDER BY EmployeeId;

PROMPT
PROMPT All Transactions:
SELECT TransactionId, FromAccountId, ToAccountId, Amount, TransactionType FROM Transactions ORDER BY TransactionId;