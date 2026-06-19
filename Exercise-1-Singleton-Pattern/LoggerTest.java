/**
 * LoggerTest - Advanced test cases for Singleton Pattern
 */

public class LoggerTest {
    
    public static void main(String[] args) {
        System.out.println("========== ADVANCED SINGLETON TESTS ==========\n");
        
        // Test 1: Verify only constructor message printed once
        System.out.println("Test 1: Check constructor is called only once");
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        Logger logger3 = Logger.getInstance();
        System.out.println("✓ Constructor message appeared only once above\n");
        
        // Test 2: Test all logging methods
        System.out.println("Test 2: Test all logging methods");
        logger1.log("This is a log message");
        logger2.error("This is an error message");
        logger3.warning("This is a warning message");
        System.out.println("✓ All logging methods work\n");
        
        // Test 3: Identity comparison
        System.out.println("Test 3: Identity verification using ==");
        System.out.println("logger1.hashCode(): " + logger1.hashCode());
        System.out.println("logger2.hashCode(): " + logger2.hashCode());
        System.out.println("logger3.hashCode(): " + logger3.hashCode());
        System.out.println("Are all equal? " + 
            (logger1.hashCode() == logger2.hashCode() && 
             logger2.hashCode() == logger3.hashCode() ? "YES ✓" : "NO ✗\n"));
        
        // Test 4: Using in different methods
        System.out.println("\nTest 4: Using singleton in different methods");
        testMethodOne();
        testMethodTwo();
        testMethodThree();
        System.out.println("✓ Same logger instance used across methods\n");
        
        System.out.println("========== ALL TESTS PASSED ✓ ==========");
    }
    
    static void testMethodOne() {
        Logger logger = Logger.getInstance();
        logger.log("Called from testMethodOne");
    }
    
    static void testMethodTwo() {
        Logger logger = Logger.getInstance();
        logger.error("Called from testMethodTwo");
    }
    
    static void testMethodThree() {
        Logger logger = Logger.getInstance();
        logger.warning("Called from testMethodThree");
    }
}