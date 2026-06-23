# Exercise 1: PL/SQL Control Structures

## 📋 Problem Statement

You are a bank database developer. Implement three business scenarios using PL/SQL control structures:

1. **Scenario 1:** Apply 1% discount to loan interest rates for customers above 60 years old
2. **Scenario 2:** Promote customers to VIP status if balance exceeds $10,000
3. **Scenario 3:** Send reminders for loans due within next 30 days

---

## 🎯 Objectives

1. ✅ Understand PL/SQL control structures (IF, LOOP, CURSOR)
2. ✅ Write PL/SQL blocks with loops
3. ✅ Implement conditional logic (IF/ELSIF/ELSE)
4. ✅ Use cursors to fetch and process data
5. ✅ Apply business logic to database records
6. ✅ Handle transactions and commits

---

## 🔄 PL/SQL Control Structures

### 1. IF / ELSE Statement

**Syntax:**
```sql
IF condition THEN
    -- Execute if true
ELSIF condition THEN
    -- Execute if first condition false, this true
ELSE
    -- Execute if all conditions false
END IF;
```

**Example:**
```sql
IF age > 60 THEN
    discount := 0.01;
ELSIF age > 50 THEN
    discount := 0.005;
ELSE
    discount := 0;
END IF;
```

### 2. FOR LOOP

**Syntax:**
```sql
FOR variable IN start..end LOOP
    -- Repeat code
END LOOP;

-- Or with cursor:
FOR record IN (SELECT * FROM table) LOOP
    -- Process each row
END LOOP;
```

**Example:**
```sql
FOR i IN 1..10 LOOP
    DBMS_OUTPUT.PUT_LINE('Number: ' || i);
END LOOP;
```

### 3. WHILE LOOP

**Syntax:**
```sql
WHILE condition LOOP
    -- Execute while true
    -- Must eventually change condition
END LOOP;
```

**Example:**
```sql
counter := 1;
WHILE counter <= 10 LOOP
    DBMS_OUTPUT.PUT_LINE(counter);
    counter := counter + 1;
END LOOP;
```

### 4. LOOP / EXIT

**Syntax:**
```sql
LOOP
    -- Code
    EXIT WHEN condition;  -- Exit when condition is true
END LOOP;
```

### 5. CURSOR

**Syntax:**
```sql
DECLARE
    CURSOR cursor_name IS SELECT ...;
BEGIN
    FOR record IN cursor_name LOOP
        -- Process record
    END LOOP;
END;
```

---

## 💻 Database Schema

### Customers Table
### Loans Table
---

## 📊 Scenario 1: Age-Based Discount

### Business Logic

- Check all customers
- IF age > 60 THEN apply 1% discount
- Update all loans for that customer

### Algorithm
### Key Concepts

- Nested loops (customer → loans)
- IF statement for age check
- UPDATE statement in loop
- COMMIT after all updates

### Expected Result

Customers over 60:
- John Smith (65) - Discount applied
- Michael Davis (72) - Discount applied
- Robert Brown (61) - Discount applied
- Maria Garcia (68) - Discount applied
- Jennifer Taylor (70) - Discount applied

### Sample Output
---

## 💳 Scenario 2: VIP Promotion

### Business Logic

- Check all customers
- IF balance > $10,000 THEN set IsVIP = 'Y'
- Flag customer as VIP

### Algorithm
### Key Concepts

- Simple loop over customers
- IF/ELSE for eligibility check
- Nested IF for existing VIP check
- CASE statement for output
- UPDATE statement

### Expected Result

Customers promoted to VIP (balance > $10,000):
1. Jennifer Taylor - $20,000
2. Michael Davis - $25,000
3. Maria Garcia - $18,000
4. John Smith - $15,000
5. Robert Brown - $12,000

### Sample Output
---

## 📧 Scenario 3: Loan Reminders

### Business Logic

- Find loans due within 30 days
- Create reminder message for each
- Display urgency level

### Algorithm
### Key Concepts

- Cursor with JOIN (Loans + Customers)
- Date calculations
- CASE statement for urgency
- Formatted output message
- Conditional display

### Expected Result

Loans due within 30 days (with urgency):
---

## 🚀 How to Run

### Option 1: Run Individual Scenarios

```sql
-- Connect to database
sqlplus username/password@database

-- Step 1: Create tables
@1_create_tables.sql

-- Step 2: Insert sample data
@2_insert_sample_data.sql

-- Step 3: Run Scenario 1
@3_scenario1_age_discount.sql

-- Step 4: Run Scenario 2
@4_scenario2_vip_promotion.sql

-- Step 5: Run Scenario 3
@5_scenario3_loan_reminders.sql
```

### Option 2: Run Complete Solution

```sql
@6_complete_solution.sql
```

### Using SQL Developer

1. Open SQL Developer
2. Connect to your database
3. Open script file
4. Click "Run Script" (F5)
5. View results in "Script Output" tab

---

## 📈 PL/SQL Concepts Demonstrated

### 1. Variables & Data Types

```sql
DECLARE
    v_discount DECIMAL(5, 2) := 0.01;
    v_counter INT := 0;
    v_message VARCHAR2(100);
```

### 2. FOR LOOP

```sql
FOR customer_rec IN (SELECT * FROM Customers) LOOP
    -- Process each customer
END LOOP;
```

### 3. IF / ELSIF / ELSE

```sql
IF age > 60 THEN
    -- Apply discount
ELSIF age > 50 THEN
    -- Apply different discount
ELSE
    -- No discount
END IF;
```

### 4. UPDATE Statement

```sql
UPDATE Customers 
SET IsVIP = 'Y' 
WHERE CustomerId = customer_id;
```

### 5. CURSOR LOOP

```sql
FOR loan_rec IN (SELECT * FROM Loans WHERE ...) LOOP
    -- Access loan_rec.LoanId, loan_rec.Amount, etc.
END LOOP;
```

### 6. DBMS_OUTPUT for Display

```sql
DBMS_OUTPUT.PUT_LINE('Customer: ' || customer_name);
```

### 7. COMMIT & ROLLBACK

```sql
COMMIT;  -- Save changes
ROLLBACK;  -- Undo changes
```

### 8. Exception Handling

```sql
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
```

---

## 🎓 SOLID Principles Applied

1. **Single Responsibility:**
   - Each scenario has one purpose
   - Separate scripts for each scenario

2. **Open/Closed:**
   - Easy to add new scenarios
   - Can extend logic without changing existing code

3. **Liskov Substitution:**
   - All loops follow same pattern
   - Interchangeable with variations

4. **Interface Segregation:**
   - Each script is independent
   - Can run individually or together

5. **Dependency Inversion:**
   - Scripts depend on tables (abstraction)
   - Not dependent on specific implementation

---

## 💡 Real-World Applications

### Banking Systems
- Interest rate adjustments
- Customer classifications
- Loan management
- Payment reminders

### E-commerce
- Order processing
- Inventory updates
- Customer promotions
- Shipping notifications

### HR Systems
- Salary calculations
- Employee classifications
- Benefit eligibility
- Leave management

### Inventory Management
- Stock updates
- Reorder alerts
- Price adjustments
- Supplier notifications

---

## ✅ Checklist

- [x] Understand IF/ELSE statements
- [x] Understand FOR loops
- [x] Understand cursors
- [x] Understand CASE statements
- [x] Create database tables
- [x] Insert sample data
- [x] Implement Scenario 1 (age discount)
- [x] Implement Scenario 2 (VIP promotion)
- [x] Implement Scenario 3 (loan reminders)
- [x] Test all scenarios
- [x] Verify results
- [x] Document code

---

## 📖 References

- **Oracle PL/SQL Tutorial:** https://www.oracle.com/database/
- **PL/SQL Language Reference:** Oracle Documentation
- **W3Schools PL/SQL:** https://www.w3schools.com/sql/sql_plsql.asp

---

## 🏆 Learning Outcomes

✅ You now understand:
- PL/SQL control structures (IF, LOOP, CURSOR)
- How to iterate through database records
- How to apply conditional logic in loops
- How to update multiple records
- How to handle transactions
- How to format output with DBMS_OUTPUT
- Real-world database scenarios

---

**Exercise Status:** ✅ COMPLETED

**Time Taken:** [Your time here] hours

**Difficulty Level:** ⭐⭐⭐☆☆ (3/5 - Intermediate)

**Your Understanding:** [Rate 1-10]

---

*This is Exercise 1 of Module 3: PL/SQL*
*Part of Digital Nurture 5.0 Deep Skilling Program*