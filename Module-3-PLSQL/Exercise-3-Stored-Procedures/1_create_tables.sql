-- ============================================
-- EXERCISE 3: PL/SQL STORED PROCEDURES
-- Database Schema Creation
-- ============================================

-- Drop existing tables (if any)
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE Transactions';
   EXECUTE IMMEDIATE 'DROP TABLE Accounts';
   EXECUTE IMMEDIATE 'DROP TABLE Employees';
   EXECUTE IMMEDIATE 'DROP TABLE Departments';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

-- ============================================
-- TABLE 1: DEPARTMENTS
-- ============================================

CREATE TABLE Departments (
    DepartmentId INT PRIMARY KEY,
    DepartmentName VARCHAR2(100) NOT NULL,
    Budget DECIMAL(12, 2),
    CONSTRAINT chk_dept_budget CHECK (Budget >= 0)
);

-- ============================================
-- TABLE 2: EMPLOYEES
-- ============================================

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

-- ============================================
-- TABLE 3: ACCOUNTS
-- ============================================

CREATE TABLE Accounts (
    AccountId INT PRIMARY KEY,
    EmployeeId INT NOT NULL,
    AccountType VARCHAR2(20) NOT NULL,  -- Savings, Checking
    Balance DECIMAL(12, 2) DEFAULT 0,
    InterestRate DECIMAL(5, 2) DEFAULT 1.0,
    DateOpened DATE DEFAULT SYSDATE,
    FOREIGN KEY (EmployeeId) REFERENCES Employees(EmployeeId),
    CONSTRAINT chk_balance CHECK (Balance >= 0),
    CONSTRAINT chk_interest CHECK (InterestRate >= 0),
    CONSTRAINT chk_account_type CHECK (AccountType IN ('Savings', 'Checking'))
);

-- ============================================
-- TABLE 4: TRANSACTIONS
-- ============================================

CREATE TABLE Transactions (
    TransactionId INT PRIMARY KEY,
    FromAccountId INT,
    ToAccountId INT,
    Amount DECIMAL(12, 2) NOT NULL,
    TransactionDate DATE DEFAULT SYSDATE,
    TransactionType VARCHAR2(20),  -- Transfer, Deposit, Withdrawal, Interest
    Description VARCHAR2(200),
    FOREIGN KEY (FromAccountId) REFERENCES Accounts(AccountId),
    FOREIGN KEY (ToAccountId) REFERENCES Accounts(AccountId),
    CONSTRAINT chk_amount CHECK (Amount > 0)
);

-- ============================================
-- CREATE INDEXES
-- ============================================

CREATE INDEX idx_employees_dept ON Employees(DepartmentId);
CREATE INDEX idx_accounts_employee ON Accounts(EmployeeId);
CREATE INDEX idx_accounts_type ON Accounts(AccountType);
CREATE INDEX idx_transactions_date ON Transactions(TransactionDate);
CREATE INDEX idx_transactions_from ON Transactions(FromAccountId);
CREATE INDEX idx_transactions_to ON Transactions(ToAccountId);

-- ============================================
-- COMMIT CHANGES
-- ============================================

COMMIT;

BEGIN
    DBMS_OUTPUT.PUT_LINE('✓ Tables created successfully!');
    DBMS_OUTPUT.PUT_LINE('  • Departments table');
    DBMS_OUTPUT.PUT_LINE('  • Employees table');
    DBMS_OUTPUT.PUT_LINE('  • Accounts table');
    DBMS_OUTPUT.PUT_LINE('  • Transactions table');
    DBMS_OUTPUT.PUT_LINE('  • Indexes created');
END;
/