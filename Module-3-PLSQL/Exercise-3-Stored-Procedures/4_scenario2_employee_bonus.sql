-- ============================================
-- SCENARIO 2: EMPLOYEE BONUS SCHEME
-- Update employee salaries with bonus based on performance
-- ============================================

-- Drop procedure if exists
BEGIN
   EXECUTE IMMEDIATE 'DROP PROCEDURE UpdateEmployeeBonus';
EXCEPTION
   WHEN OTHERS THEN NULL;
END;
/

-- ============================================
-- CREATE STORED PROCEDURE
-- ============================================

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department_id IN INT,
    p_bonus_percentage IN DECIMAL
)
IS
    -- Variables
    v_employees_updated INT := 0;
    v_total_bonus_paid DECIMAL(12, 2) := 0;
    v_new_salary DECIMAL(10, 2);
    v_bonus_amount DECIMAL(10, 2);
    v_dept_name VARCHAR2(100);
    
BEGIN
    DBMS_OUTPUT.PUT_LINE('╔════════════════════════════════════════════════╗');
    DBMS_OUTPUT.PUT_LINE('║  SCENARIO 2: EMPLOYEE BONUS SCHEME             ║');
    DBMS_OUTPUT.PUT_LINE('║  Add bonus to employee salaries                ║');
    DBMS_OUTPUT.PUT_LINE('╚════════════════════════════════════════════════╝');
    DBMS_OUTPUT.PUT_LINE('');
    
    -- Validate parameters
    IF p_department_id IS NULL THEN
        RAISE_APPLICATION_ERROR(-20001, 'Department ID cannot be null');
    END IF;
    
    IF p_bonus_percentage IS NULL OR p_bonus_percentage <= 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Bonus percentage must be greater than 0');
    END IF;
    
    -- Get department name
    BEGIN
        SELECT DepartmentName 
        INTO v_dept_name
        FROM Departments
        WHERE DepartmentId = p_department_id;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20003, 'Department not found');
    END;
    
    DBMS_OUTPUT.PUT_LINE('Department: ' || v_dept_name);
    DBMS_OUTPUT.PUT_LINE('Bonus Percentage: ' || p_bonus_percentage || '%');
    DBMS_OUTPUT.PUT_LINE('');
    
    -- Process all employees in department
    FOR emp_rec IN (
        SELECT EmployeeId, EmployeeName, Salary, PerformanceRating
        FROM Employees
        WHERE DepartmentId = p_department_id
        ORDER BY EmployeeId
    )
    LOOP
        -- Calculate bonus amount
        v_bonus_amount := emp_rec.Salary * (p_bonus_percentage / 100);
        
        -- Calculate new salary
        v_new_salary := emp_rec.Salary + v_bonus_amount;
        
        -- Update employee salary
        UPDATE Employees
        SET Salary = v_new_salary
        WHERE EmployeeId = emp_rec.EmployeeId;
        
        -- Display details
        DBMS_OUTPUT.PUT_LINE('Employee: ' || emp_rec.EmployeeName || 
                           ' (ID: ' || emp_rec.EmployeeId || ')');
        DBMS_OUTPUT.PUT_LINE('  Performance Rating: ' || emp_rec.PerformanceRating || '/5');
        DBMS_OUTPUT.PUT_LINE('  Previous Salary: $' || ROUND(emp_rec.Salary, 2));
        DBMS_OUTPUT.PUT_LINE('  Bonus Amount: $' || ROUND(v_bonus_amount, 2));
        DBMS_OUTPUT.PUT_LINE('  New Salary: $' || ROUND(v_new_salary, 2));
        DBMS_OUTPUT.PUT_LINE('');
        
        -- Increment counters
        v_employees_updated := v_employees_updated + 1;
        v_total_bonus_paid := v_total_bonus_paid + v_bonus_amount;
        
    END LOOP;
    
    -- Commit changes
    COMMIT;
    
    -- Display summary
    DBMS_OUTPUT.PUT_LINE('═════════════════════════════════════════════════');
    DBMS_OUTPUT.PUT_LINE('SUMMARY:');
    DBMS_OUTPUT.PUT_LINE('• Employees updated: ' || v_employees_updated);
    DBMS_OUTPUT.PUT_LINE('• Total bonus paid: $' || ROUND(v_total_bonus_paid, 2));
    DBMS_OUTPUT.PUT_LINE('• Department: ' || v_dept_name);
    DBMS_OUTPUT.PUT_LINE('✓ Employee bonus scheme completed!');
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLCODE || ' - ' || SQLERRM);
        
END UpdateEmployeeBonus;
/

-- ============================================
-- TEST THE PROCEDURE
-- ============================================

DBMS_OUTPUT.PUT_LINE('');
DBMS_OUTPUT.PUT_LINE('TEST 1: Applying 10% bonus to IT Department (ID: 1)');
DBMS_OUTPUT.PUT_LINE('');

BEGIN
    UpdateEmployeeBonus(1, 10);
END;
/

-- ============================================
-- VERIFICATION
-- ============================================

DBMS_OUTPUT.PUT_LINE('');
DBMS_OUTPUT.PUT_LINE('VERIFICATION - Updated Employee Salaries (IT Department):');
DBMS_OUTPUT.PUT_LINE('');

SELECT EmployeeId, EmployeeName, Salary, PerformanceRating
FROM Employees
WHERE DepartmentId = 1
ORDER BY EmployeeId;