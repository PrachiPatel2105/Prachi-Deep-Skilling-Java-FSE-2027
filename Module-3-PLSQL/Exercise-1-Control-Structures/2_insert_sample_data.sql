-- ============================================
-- EXERCISE 1: PL/SQL CONTROL STRUCTURES
-- Sample Data Insertion
-- ============================================

-- Enable output
SET ECHO ON;
SET FEEDBACK ON;

-- ============================================
-- INSERT SAMPLE CUSTOMERS
-- ============================================

INSERT INTO Customers (CustomerId, CustomerName, Age, Email, Balance, IsVIP) VALUES
(1, 'John Smith', 65, 'john@email.com', 15000, 'N');

INSERT INTO Customers (CustomerId, CustomerName, Age, Email, Balance, IsVIP) VALUES
(2, 'Sarah Johnson', 45, 'sarah@email.com', 8500, 'N');

INSERT INTO Customers (CustomerId, CustomerName, Age, Email, Balance, IsVIP) VALUES
(3, 'Michael Davis', 72, 'michael@email.com', 25000, 'N');

INSERT INTO Customers (CustomerId, CustomerName, Age, Email, Balance, IsVIP) VALUES
(4, 'Emily Wilson', 38, 'emily@email.com', 5000, 'N');

INSERT INTO Customers (CustomerId, CustomerName, Age, Email, Balance, IsVIP) VALUES
(5, 'Robert Brown', 61, 'robert@email.com', 12000, 'N');

INSERT INTO Customers (CustomerId, CustomerName, Age, Email, Balance, IsVIP) VALUES
(6, 'Lisa Anderson', 52, 'lisa@email.com', 9500, 'N');

INSERT INTO Customers (CustomerId, CustomerName, Age, Email, Balance, IsVIP) VALUES
(7, 'James Martinez', 58, 'james@email.com', 3000, 'N');

INSERT INTO Customers (CustomerId, CustomerName, Age, Email, Balance, IsVIP) VALUES
(8, 'Maria Garcia', 68, 'maria@email.com', 18000, 'N');

INSERT INTO Customers (CustomerId, CustomerName, Age, Email, Balance, IsVIP) VALUES
(9, 'David Lee', 42, 'david@email.com', 7000, 'N');

INSERT INTO Customers (CustomerId, CustomerName, Age, Email, Balance, IsVIP) VALUES
(10, 'Jennifer Taylor', 70, 'jennifer@email.com', 20000, 'N');

-- ============================================
-- INSERT SAMPLE LOANS
-- ============================================

-- Customer 1: John Smith (65 years old)
INSERT INTO Loans (LoanId, CustomerId, LoanAmount, InterestRate, DueDate, Status) VALUES
(101, 1, 50000, 6.5, TRUNC(SYSDATE) + 15, 'Active');

INSERT INTO Loans (LoanId, CustomerId, LoanAmount, InterestRate, DueDate, Status) VALUES
(102, 1, 30000, 7.0, TRUNC(SYSDATE) + 25, 'Active');

-- Customer 2: Sarah Johnson (45 years old)
INSERT INTO Loans (LoanId, CustomerId, LoanAmount, InterestRate, DueDate, Status) VALUES
(103, 2, 20000, 6.5, TRUNC(SYSDATE) + 45, 'Active');

-- Customer 3: Michael Davis (72 years old)
INSERT INTO Loans (LoanId, CustomerId, LoanAmount, InterestRate, DueDate, Status) VALUES
(104, 3, 100000, 5.5, TRUNC(SYSDATE) + 20, 'Active');

INSERT INTO Loans (LoanId, CustomerId, LoanAmount, InterestRate, DueDate, Status) VALUES
(105, 3, 50000, 6.0, TRUNC(SYSDATE) + 28, 'Active');

-- Customer 4: Emily Wilson (38 years old)
INSERT INTO Loans (LoanId, CustomerId, LoanAmount, InterestRate, DueDate, Status) VALUES
(106, 4, 25000, 7.0, TRUNC(SYSDATE) + 60, 'Active');

-- Customer 5: Robert Brown (61 years old)
INSERT INTO Loans (LoanId, CustomerId, LoanAmount, InterestRate, DueDate, Status) VALUES
(107, 5, 40000, 6.5, TRUNC(SYSDATE) + 10, 'Active');

-- Customer 6: Lisa Anderson (52 years old)
INSERT INTO Loans (LoanId, CustomerId, LoanAmount, InterestRate, DueDate, Status) VALUES
(108, 6, 35000, 6.75, TRUNC(SYSDATE) + 35, 'Active');

-- Customer 7: James Martinez (58 years old)
INSERT INTO Loans (LoanId, CustomerId, LoanAmount, InterestRate, DueDate, Status) VALUES
(109, 7, 15000, 7.25, TRUNC(SYSDATE) + 5, 'Active');

-- Customer 8: Maria Garcia (68 years old)
INSERT INTO Loans (LoanId, CustomerId, LoanAmount, InterestRate, DueDate, Status) VALUES
(110, 8, 75000, 5.75, TRUNC(SYSDATE) + 22, 'Active');

-- Customer 9: David Lee (42 years old)
INSERT INTO Loans (LoanId, CustomerId, LoanAmount, InterestRate, DueDate, Status) VALUES
(111, 9, 30000, 6.5, TRUNC(SYSDATE) + 40, 'Active');

-- Customer 10: Jennifer Taylor (70 years old)
INSERT INTO Loans (LoanId, CustomerId, LoanAmount, InterestRate, DueDate, Status) VALUES
(112, 10, 80000, 6.0, TRUNC(SYSDATE) + 12, 'Active');

INSERT INTO Loans (LoanId, CustomerId, LoanAmount, InterestRate, DueDate, Status) VALUES
(113, 10, 50000, 6.5, TRUNC(SYSDATE) + 29, 'Active');

-- ============================================
-- COMMIT CHANGES
-- ============================================

COMMIT;

-- Display confirmation
BEGIN
    DBMS_OUTPUT.PUT_LINE('✓ Sample data inserted successfully!');
    DBMS_OUTPUT.PUT_LINE('  • 10 customers inserted');
    DBMS_OUTPUT.PUT_LINE('  • 13 loans inserted');
    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('Customers: 1-10');
    DBMS_OUTPUT.PUT_LINE('Ages: 38-72 (Some > 60)');
    DBMS_OUTPUT.PUT_LINE('Balances: $3,000 - $25,000');
END;
/

-- Display inserted data
SELECT * FROM Customers;
SELECT * FROM Loans;