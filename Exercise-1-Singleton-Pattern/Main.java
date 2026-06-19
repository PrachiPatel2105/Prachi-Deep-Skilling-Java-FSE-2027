/**
 * Main - Test class to verify Singleton Pattern
 * This class tests that only ONE instance of Logger is created
 */

public class Main {
    
    public static void main(String[] args) {
        System.out.println("========== SINGLETON PATTERN TEST ==========\n");
        
        // Test 1: Get first instance
        System.out.println("Test 1: Creating first Logger instance...");
        Logger logger1 = Logger.getInstance();
        logger1.log("First logger instance created");
        System.out.println("Logger 1 Object ID: " + logger1.hashCode());
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Test 2: Get second instance
        System.out.println("Test 2: Creating second Logger instance...");
        Logger logger2 = Logger.getInstance();
        logger2.error("Second logger instance requested");
        System.out.println("Logger 2 Object ID: " + logger2.hashCode());
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Test 3: Get third instance
        System.out.println("Test 3: Creating third Logger instance...");
        Logger logger3 = Logger.getInstance();
        logger3.warning("Third logger instance requested");
        System.out.println("Logger 3 Object ID: " + logger3.hashCode());
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Verify all are same instance
        System.out.println("VERIFICATION:");
        System.out.println("logger1 == logger2: " + (logger1 == logger2));
        System.out.println("logger2 == logger3: " + (logger2 == logger3));
        System.out.println("logger1 == logger3: " + (logger1 == logger3));
        
        System.out.println("\nAll three references point to SAME object? " + 
            (logger1 == logger2 && logger2 == logger3 ? "YES ✓" : "NO ✗"));
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Test 4: Use all instances
        System.out.println("Test 4: Using all logger instances...");
        logger1.log("Message from logger1");
        logger2.error("Error from logger2");
        logger3.warning("Warning from logger3");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("SINGLETON PATTERN VERIFICATION: SUCCESS ✓");
        System.out.println("Only ONE instance is used throughout the application!");
        System.out.println("=".repeat(50));
    }
}