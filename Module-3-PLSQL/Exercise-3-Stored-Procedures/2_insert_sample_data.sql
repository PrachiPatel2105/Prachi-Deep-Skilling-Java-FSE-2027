-- ============================================
-- EXERCISE 3: STORED PROCEDURES
-- Sample Data Insertion
-- ============================================

SET ECHO ON;

-- ============================================
-- INSERT DEPARTMENTS
-- ============================================

INSERT INTO Departments (DepartmentId, DepartmentName, Budget) VALUES
(1, 'IT Department', 500000);

INSERT INTO Departments (DepartmentId, DepartmentName, Budget) VALUES
(2, 'HR Department', 300000);

INSERT INTO Departments (DepartmentId, DepartmentName, Budget) VALUES
(3, 'Finance Department', 600000);

INSERT INTO Departments (DepartmentId, DepartmentName, Budget) VALUES
(4, 'Operations Department', 400000);

-- ============================================
-- INSERT EMPLOYEES
-- ============================================

INSERT INTO Employees (EmployeeId, EmployeeName, DepartmentId, Salary, PerformanceRating) VALUES
(101, 'John Smith', 1, 75000, 4.5);

INSERT INTO Employees (EmployeeId, EmployeeName, DepartmentId, Salary, PerformanceRating) VALUES
(102, 'Sarah Johnson', 1, 65000, 4.0);

INSERT INTO Employees (EmployeeId, EmployeeName, DepartmentId, Salary, PerformanceRating) VALUES
(103, 'Michael Davis', 2, 55000, 3.8);

INSERT INTO Employees (EmployeeId, EmployeeName, DepartmentId, Salary, PerformanceRating) VALUES
(104, 'Emily Wilson', 3, 70000, 4.8);

INSERT INTO Employees (EmployeeId, EmployeeName, DepartmentId, Salary, PerformanceRating) VALUES
(105, 'Robert Brown', 3, 68000, 4.2);

INSERT INTO Employees (EmployeeId, EmployeeName, DepartmentId, Salary, PerformanceRating) VALUES
(106, 'Lisa Anderson', 4, 60000, 3.5);

INSERT INTO Employees (EmployeeId, EmployeeName, DepartmentId, Salary, PerformanceRating) VALUES
(107, 'James Martinez', 1, 72000, 4.1);

INSERT INTO Employees (EmployeeId, EmployeeName, DepartmentId, Salary, PerformanceRating) VALUES
(108, 'Maria Garcia', 2, 58000, 3.9);

-- ============================================
-- INSERT ACCOUNTS
-- ============================================

-- John Smith - 2 accounts
INSERT INTO Accounts (AccountId, EmployeeId, AccountType, Balance, InterestRate) VALUES
(1001, 101, 'Savings', 50000, 1.5);

INSERT INTO Accounts (AccountId, EmployeeId, AccountType, Balance, InterestRate) VALUES
(1002, 101, 'Checking', 15000, 0.5);

-- Sarah Johnson - 2 accounts
INSERT INTO Accounts (AccountId, EmployeeId, AccountType, Balance, InterestRate) VALUES
(1003, 102, 'Savings', 35000, 1.5);

INSERT INTO Accounts (AccountId, EmployeeId, AccountType, Balance, InterestRate) VALUES
(1004, 102, 'Checking', 8000, 0.5);

-- Michael Davis - 1 account
INSERT INTO Accounts (AccountId, EmployeeId, AccountType, Balance, InterestRate) VALUES
(1005, 103, 'Savings', 25000, 1.5);

-- Emily Wilson - 2 accounts
INSERT INTO Accounts (AccountId, EmployeeId, AccountType, Balance, InterestRate) VALUES
(1006, 104, 'Savings', 60000, 1.5);

INSERT INTO Accounts (AccountId, EmployeeId, AccountType, Balance, InterestRate) VALUES
(1007, 104, 'Checking', 20000, 0.5);

-- Robert Brown - 1 account
INSERT INTO Accounts (AccountId, EmployeeId, AccountType, Balance, InterestRate) VALUES
(1008, 105, 'Savings', 45000, 1.5);

-- Lisa Anderson - 1 account
INSERT INTO Accounts (AccountId, EmployeeId, AccountType, Balance, InterestRate) VALUES
(1009, 106, 'Savings', 30000, 1.5);

-- James Martinez - 2 accounts
INSERT INTO Accounts (AccountId, EmployeeId, AccountType, Balance, InterestRate) VALUES
(1010, 107, 'Savings', 55000, 1.5);

INSERT INTO Accounts (AccountId, EmployeeId, AccountType, Balance, InterestRate) VALUES
(1011, 107, 'Checking', 12000, 0.5);

-- Maria Garcia - 1 account
INSERT INTO Accounts (AccountId, EmployeeId, AccountType, Balance, InterestRate) VALUES
(1012, 108, 'Savings', 28000, 1.5);

-- ============================================
-- COMMIT CHANGES
-- ============================================

COMMIT;

BEGIN
    DBMS_OUTPUT.PUT_LINE('✓ Sample data inserted successfully!');
    DBMS_OUTPUT.PUT_LINE('  • 4 departments inserted');
    DBMS_OUTPUT.PUT_LINE('  • 8 employees inserted');
    DBMS_OUTPUT.PUT_LINE('  • 12 accounts inserted');
END;
/

-- Display inserted data
SELECT * FROM Departments;
SELECT * FROM Employees;
SELECT * FROM Accounts;