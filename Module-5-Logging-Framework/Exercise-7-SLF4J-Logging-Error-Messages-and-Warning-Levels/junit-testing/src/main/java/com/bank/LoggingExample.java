package com.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LoggingExample - Demonstrates SLF4J logging levels
 *
 * Log Levels (from least to most severe):
 * 1. TRACE - Most detailed, for tracing execution
 * 2. DEBUG - Debug information
 * 3. INFO - Informational messages (default)
 * 4. WARN - Warning messages
 * 5. ERROR - Error messages
 */
public class LoggingExample {

    // Create logger for this class
    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {
        System.out.println("========== SLF4J LOGGING EXAMPLE ==========\n");

        // Demonstrate all logging levels
        demonstrateLoggingLevels();

        // Demonstrate logging with exceptions
        demonstrateExceptionLogging();

        // Demonstrate logging in methods
        demonstrateMethodLogging();
    }

    /**
     * Demonstrate all logging levels
     */
    public static void demonstrateLoggingLevels() {
        logger.info("=== Demonstrating All Logging Levels ===");

        // TRACE - Most detailed
        logger.trace("This is a TRACE level message (most detailed)");

        // DEBUG - Debug information
        logger.debug("This is a DEBUG level message");
        logger.debug("Debug: Application started with parameters");

        // INFO - Informational messages
        logger.info("This is an INFO level message (default level)");
        logger.info("Application initialized successfully");
        logger.info("User logged in: john_doe");

        // WARN - Warning messages
        logger.warn("This is a WARN level message");
        logger.warn("Warning: Low disk space detected");
        logger.warn("Warning: Deprecated method being used");

        // ERROR - Error messages
        logger.error("This is an ERROR level message");
        logger.error("Error: Failed to connect to database");
        logger.error("Error: Invalid user input");

        System.out.println();
    }

    /**
     * Demonstrate exception logging
     */
    public static void demonstrateExceptionLogging() {
        logger.info("=== Demonstrating Exception Logging ===");

        try {
            // Simulate an error
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            logger.error("Error: Division by zero occurred", e);
        }

        try {
            // Simulate array index out of bounds
            int[] array = {1, 2, 3};
            int value = array[10];
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error("Error: Array index out of bounds", e);
            logger.warn("Warning: Invalid array access attempted");
        }

        System.out.println();
    }

    /**
     * Demonstrate logging in methods
     */
    public static void demonstrateMethodLogging() {
        logger.info("=== Demonstrating Method Logging ===");

        // Logging method entry and exit
        logger.debug("Entering processPayment method");
        processPayment("ACC123", 1000.0);
        logger.debug("Exiting processPayment method");

        logger.debug("Entering validateUser method");
        validateUser("john_doe");
        logger.debug("Exiting validateUser method");

        System.out.println();
    }

    /**
     * Process payment with logging
     */
    public static void processPayment(String accountId, double amount) {
        logger.info("Processing payment for account: {}", accountId);
        logger.debug("Payment amount: {}", amount);

        // Simulate processing
        if (amount > 0) {
            logger.info("Payment processed successfully");
        } else {
            logger.error("Payment failed: Invalid amount {}", amount);
        }
    }

    /**
     * Validate user with logging
     */
    public static void validateUser(String username) {
        logger.debug("Validating user: {}", username);

        if (username != null && !username.isEmpty()) {
            logger.info("User validation successful: {}", username);
        } else {
            logger.error("User validation failed: Invalid username");
            logger.warn("Warning: Null or empty username provided");
        }
    }
}