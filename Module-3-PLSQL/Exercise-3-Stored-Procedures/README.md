# Exercise 3: PL/SQL Stored Procedures

## 📋 Problem Statement

You are a bank database developer. Implement three business scenarios using Stored Procedures:

1. **Scenario 1:** Process monthly interest - Calculate and apply 1% interest to all savings accounts
2. **Scenario 2:** Employee bonus scheme - Update salaries with bonus percentage for a department
3. **Scenario 3:** Transfer funds - Transfer money between accounts with validation

---

## 🎯 Objectives

1. ✅ Understand stored procedures and their advantages
2. ✅ Create reusable PL/SQL procedures
3. ✅ Use procedure parameters (IN, OUT, IN OUT)
4. ✅ Implement error handling and validation
5. ✅ Use transactions and COMMIT/ROLLBACK
6. ✅ Create audit trails with transaction logging

---

## 🔄 Understanding Stored Procedures

### What is a Stored Procedure?

A named PL/SQL block **stored in the database** that:
- ✅ Can be called multiple times
- ✅ Is pre-compiled and optimized
- ✅ Runs on the server (faster)
- ✅ Can take parameters
- ✅ Can return results

### Advantages:

```
REUSABILITY: Write once, call many times
PERFORMANCE: Pre-compiled, faster execution
SECURITY: Hide complex logic, grant procedure access
CONSISTENCY: Same logic everywhere
MAINTAINABILITY: Update in one place
```

### Syntax:

```sql
CREATE OR REPLACE PROCEDURE procedure_name (
    parameter1 IN datatype,
    parameter2 OUT datatype,
    parameter3 IN OUT datatype
)
IS
    v_variable datatype;
BEGIN
    -- Procedure logic
    UPDATE table SET column = value;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Success');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END procedure_name;
/
```

### Parameter Types:

```
IN      - Input parameter (read-only, default)
OUT     - Output parameter (write-only)
IN OUT  - Both input and output
```

---

## 💻 Database Schema

### Departments Table
```
DepartmentId (PK)  - Department unique ID
DepartmentName     - Department name
Budget             - Department budget
```

### Employees Table
```
EmployeeId (PK)        - Employee unique ID
EmployeeName           - Employee name
DepartmentId (FK)      - Reference to department
Salary                 - Current salary
PerformanceRating      - Rating 1-5
```

### Accounts Table
```
AccountId (PK)         - Account unique ID
EmployeeId (FK)        - Reference to employee
AccountType            - Savings or Checking
Balance                - Current balance
InterestRate           - Annual interest rate
DateOpened             - Account open date
```

### Transactions Table
```
TransactionId (PK)     - Transaction unique ID
FromAccountId (FK)     - Source account
ToAccountId (FK)       - Destination account
Amount                 - Transaction amount
TransactionDate        - When transaction occurred
TransactionType        - Interest, Transfer, Deposit
Description            - Details
```

---

## 📊 Scenario 1: Process Monthly Interest

### Business Logic

Calculate and apply monthly interest to all savings accounts.

**Formula:** Interest = Balance × (Interest Rate / 100)

### Procedure Signature:

```sql
PROCEDURE ProcessMonthlyInterest
```

### What It Does:

1. Loop through all savings accounts
2. Calculate interest (1% per month)
3. Update account balance
4. Record transaction
5. COMMIT all changes

### Example:

```
Account 1001: Savings
  Previous Balance: $50,000.00
  Interest Rate: 1.5%
  Interest Amount: $750.00
  New Balance: $50,750.00
```

### Key Concepts:

- FOR LOOP with cursor
- Calculations
- INSERT into Transactions table
- COMMIT after loop

---

## 💳 Scenario 2: Employee Bonus Scheme

### Business Logic

Update employee salaries by adding a bonus percentage for a specific department.

**Formula:** New Salary = Old Salary + (Old Salary × Bonus %)

### Procedure Signature:

```sql
PROCEDURE UpdateEmployeeBonus (
    p_department_id IN INT,
    p_bonus_percentage IN DECIMAL
)
```

### Parameters:

- `p_department_id` - Which department to give bonus
- `p_bonus_percentage` - Bonus percentage (e.g., 10 for 10%)

### What It Does:

1. Validate parameters
2. Get department name
3. Loop through employees in department
4. Calculate bonus amount
5. Update salary
6. COMMIT changes

### Example:

```
Department: IT Department
Bonus Percentage: 10%

John Smith (ID: 101)
  Performance Rating: 4.5/5
  Previous Salary: $75,000
  Bonus Amount: $7,500
  New Salary: $82,500
```

### Key Concepts:

- Input parameters
- Error handling (RAISE_APPLICATION_ERROR)
- Parameter validation
- Department lookup
- Multiple updates in loop

---

## 💰 Scenario 3: Transfer Funds

### Business Logic

Transfer money from one account to another with validation.

**Validations:**
1. Source account exists
2. Destination account exists
3. Source account has sufficient balance
4. Transfer amount > 0
5. Not transferring to same account

### Procedure Signature:

```sql
PROCEDURE TransferFunds (
    p_from_account_id IN INT,
    p_to_account_id IN INT,
    p_amount IN DECIMAL
)
```

### Parameters:

- `p_from_account_id` - Source account
- `p_to_account_id` - Destination account
- `p_amount` - Amount to transfer

### What It Does:

1. Validate all parameters
2. Check source account exists and has balance
3. Check destination account exists
4. Deduct from source
5. Add to destination
6. Record transaction
7. COMMIT

### Example:

```
FROM Account 1001:
  Previous Balance: $50,000.00
  Amount Transferred: -$10,000.00
  New Balance: $40,000.00

TO Account 1003:
  Previous Balance: $35,000.00
  Amount Received: +$10,000.00
  New Balance: $45,000.00

✓ Transfer completed successfully!
```

### Error Handling:

```
❌ Insufficient Balance
❌ Account not found
❌ Invalid parameters
```

### Key Concepts:

- Multiple validations
- Error handling with custom errors
- RAISE_APPLICATION_ERROR
- Transaction rollback on error
- Two-phase update (deduct & add)

---

## 🚀 How to Run

### Option 1: Step by Step (Recommended)

```sql
-- Connect to database
@1_create_tables.sql          -- Create tables
@2_insert_sample_data.sql     -- Insert data
@3_scenario1_monthly_interest.sql   -- Test Scenario 1
@4_scenario2_employee_bonus.sql     -- Test Scenario 2
@5_scenario3_transfer_funds.sql     -- Test Scenario 3
```

### Option 2: Run All at Once

```sql
@6_test_all_procedures.sql
```

### Calling Procedures from Your Application:

```sql
-- Simple call
BEGIN
    ProcessMonthlyInterest();
END;
/

-- With parameters
BEGIN
    UpdateEmployeeBonus(1, 10);
END;
/

-- With error handling
BEGIN
    TransferFunds(1001, 1003, 10000);
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Transfer failed: ' || SQLERRM);
END;
/
```

---

## 📈 Stored Procedure Concepts Demonstrated

### 1. Procedure Creation
```sql
CREATE OR REPLACE PROCEDURE name (params) IS
    -- declarations
BEGIN
    -- code
END name;
/
```

### 2. Parameters
```sql
PROCEDURE UpdateEmployeeBonus (
    p_department_id IN INT,      -- Input
    p_bonus_percentage IN DECIMAL -- Input
)
```

### 3. Error Handling
```sql
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
```

### 4. Calling Procedures
```sql
BEGIN
    ProcessMonthlyInterest();
    UpdateEmployeeBonus(1, 10);
    TransferFunds(1001, 1003, 5000);
END;
/
```

### 5. Validation
```sql
IF p_amount <= 0 THEN
    RAISE_APPLICATION_ERROR(-20001, 'Invalid amount');
END IF;
```

### 6. Transactions
```sql
COMMIT;      -- Save all changes
ROLLBACK;    -- Undo all changes
```

---

## 🎓 SOLID Principles Applied

1. **Single Responsibility:**
   - Each procedure has one purpose
   - ProcessMonthlyInterest only processes interest
   - UpdateEmployeeBonus only updates bonuses
   - TransferFunds only transfers funds

2. **Open/Closed:**
   - Easy to add new procedures
   - Existing procedures don't need modification

3. **Liskov Substitution:**
   - All procedures follow same pattern
   - Interchangeable error handling

4. **Interface Segregation:**
   - Parameters are minimal and focused
   - Only required parameters exposed

5. **Dependency Inversion:**
   - Depends on tables (abstraction)
   - Application calls procedure, not raw SQL

---

## 💡 Real-World Applications

### Banking Systems
- ✓ Monthly interest processing
- ✓ Fee calculations
- ✓ Fund transfers
- ✓ Account statements

### HR Systems
- ✓ Salary calculations
- ✓ Bonus distributions
- ✓ Tax calculations
- ✓ Leave balance updates

### E-commerce
- ✓ Order processing
- ✓ Inventory updates
- ✓ Payment processing
- ✓ Customer loyalty points

### Data Warehousing
- ✓ ETL operations
- ✓ Data aggregation
- ✓ Dimension updates
- ✓ Fact table loads

---

## ✅ Checklist

- [x] Understand stored procedures
- [x] Create Departments, Employees, Accounts, Transactions tables
- [x] Insert sample data
- [x] Create ProcessMonthlyInterest procedure
- [x] Create UpdateEmployeeBonus procedure
- [x] Create TransferFunds procedure
- [x] Implement error handling
- [x] Test all procedures
- [x] Verify results
- [x] Document code

---

## 📖 References

- **Oracle PL/SQL Documentation:**
  https://docs.oracle.com/cd/B19306_01/appdev.102/b14261/toc.htm
- **Stored Procedures Tutorial:**
  https://www.oracle.com/database/
- **Error Handling in PL/SQL:**
  https://www.orafaq.com/

---

## 🏆 Learning Outcomes

✅ You now understand:
- How stored procedures work
- Advantages over raw SQL
- Creating reusable code
- Parameter passing (IN, OUT, IN OUT)
- Error handling and validation
- Transaction control
- Audit trails and logging
- Real-world banking scenarios

---

**Exercise Status:** ✅ COMPLETED

**Time Taken:** [Your time here] hours

**Difficulty Level:** ⭐⭐⭐⭐☆ (4/5 - Advanced)

**Your Understanding:** [Rate 1-10]

---

*This is Exercise 3 of Module 3: PL/SQL*
*Part of Digital Nurture 5.0 Deep Skilling Program*