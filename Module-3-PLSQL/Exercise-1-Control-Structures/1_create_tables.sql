-- ============================================
-- EXERCISE 1: PL/SQL CONTROL STRUCTURES
-- Database Schema Creation
-- ============================================

-- Drop existing tables (if any)
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE Loans';
   EXECUTE IMMEDIATE 'DROP TABLE Customers';
EXCEPTION
   WHEN OTHERS THEN
      NULL;
END;
/

-- ============================================
-- TABLE 1: CUSTOMERS
-- ============================================

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

-- ============================================
-- TABLE 2: LOANS
-- ============================================

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

-- ============================================
-- CREATE INDEXES
-- ============================================

CREATE INDEX idx_customers_age ON Customers(Age);
CREATE INDEX idx_customers_balance ON Customers(Balance);
CREATE INDEX idx_loans_duedate ON Loans(DueDate);
CREATE INDEX idx_loans_customerid ON Loans(CustomerId);

-- ============================================
-- COMMIT CHANGES
-- ============================================

COMMIT;

-- Display confirmation message
BEGIN
    DBMS_OUTPUT.PUT_LINE('✓ Tables created successfully!');
    DBMS_OUTPUT.PUT_LINE('  • Customers table');
    DBMS_OUTPUT.PUT_LINE('  • Loans table');
    DBMS_OUTPUT.PUT_LINE('  • Indexes created');
END;
/