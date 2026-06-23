-- ============================================
-- SCENARIO 2: VIP STATUS PROMOTION
-- Set IsVIP = 'Y' for customers with balance > $10,000
-- ============================================

SET ECHO ON;

-- Display header
BEGIN
    DBMS_OUTPUT.PUT_LINE('╔════════════════════════════════════════════════╗');
    DBMS_OUTPUT.PUT_LINE('║  SCENARIO 2: VIP PROMOTION                    ║');
    DBMS_OUTPUT.PUT_LINE('║  Criteria: Balance > $10,000                  ║');
    DBMS_OUTPUT.PUT_LINE('╚════════════════════════════════════════════════╝');
    DBMS_OUTPUT.PUT_LINE('');
END;
/

-- ============================================
-- PL/SQL BLOCK: SCENARIO 2
-- ============================================

DECLARE
    -- Variables
    v_vip_threshold DECIMAL(10, 2) := 10000;
    v_customers_promoted INT := 0;
    
BEGIN
    DBMS_OUTPUT.PUT_LINE('Starting VIP promotion process...');
    DBMS_OUTPUT.PUT_LINE('VIP Threshold: $' || v_vip_threshold);
    DBMS_OUTPUT.PUT_LINE('');
    
    -- LOOP: Iterate through all customers
    FOR customer_rec IN (SELECT CustomerId, CustomerName, Balance, IsVIP FROM Customers)
    LOOP
        -- IF STATEMENT: Check if balance exceeds threshold
        IF customer_rec.Balance > v_vip_threshold THEN
            
            -- IF-ELSE: Check if already VIP
            IF customer_rec.IsVIP = 'N' THEN
                
                -- Update customer to VIP status
                UPDATE Customers 
                SET IsVIP = 'Y'
                WHERE CustomerId = customer_rec.CustomerId;
                
                DBMS_OUTPUT.PUT_LINE('✓ ' || customer_rec.CustomerName || 
                                    ' → Promoted to VIP');
                DBMS_OUTPUT.PUT_LINE('  Balance: $' || customer_rec.Balance);
                
                v_customers_promoted := v_customers_promoted + 1;
                
            ELSE
                -- Already VIP
                DBMS_OUTPUT.PUT_LINE('• ' || customer_rec.CustomerName || 
                                    ' → Already VIP');
                DBMS_OUTPUT.PUT_LINE('  Balance: $' || customer_rec.Balance);
            END IF;  -- End of existing VIP check
            
        ELSE
            -- Not eligible for VIP
            DBMS_OUTPUT.PUT_LINE('○ ' || customer_rec.CustomerName || 
                                ' → Not eligible for VIP');
            DBMS_OUTPUT.PUT_LINE('  Balance: $' || customer_rec.Balance || 
                                ' (Need: >' || v_vip_threshold || ')');
        END IF;  -- End of balance check
        
        DBMS_OUTPUT.PUT_LINE('');
    END LOOP;  -- End of customer loop
    
    -- Commit changes
    COMMIT;
    
    -- Display summary
    DBMS_OUTPUT.PUT_LINE('═════════════════════════════════════════════════');
    DBMS_OUTPUT.PUT_LINE('SUMMARY:');
    DBMS_OUTPUT.PUT_LINE('• Customers promoted: ' || v_customers_promoted);
    DBMS_OUTPUT.PUT_LINE('✓ VIP promotion completed successfully!');
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLCODE || ' - ' || SQLERRM);
END;
/

-- ============================================
-- VERIFICATION: Display VIP status
-- ============================================

DBMS_OUTPUT.PUT_LINE('');
DBMS_OUTPUT.PUT_LINE('VERIFICATION - Customer VIP Status:');
DBMS_OUTPUT.PUT_LINE('');

SELECT CustomerId,
       CustomerName,
       Balance,
       IsVIP,
       CASE WHEN IsVIP = 'Y' THEN '👑 VIP Member' ELSE '  Regular' END AS Status
FROM Customers
ORDER BY Balance DESC;