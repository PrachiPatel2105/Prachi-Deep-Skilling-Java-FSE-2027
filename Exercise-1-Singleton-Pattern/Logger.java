/**
 * Logger - Singleton Pattern Implementation
 * This class ensures only ONE instance exists throughout the application
 */

public class Logger {
    
    // Private static instance (Only one object of this class)
    private static Logger instance;
    
    // Private constructor (Cannot create object outside this class)
    private Logger() {
        System.out.println("Logger instance created!");
    }
    
    // Public static method to get the single instance
    public static Logger getInstance() {
        // Check if instance doesn't exist
        if (instance == null) {
            // Create it only once
            instance = new Logger();
        }
        // Return the same instance always
        return instance;
    }
    
    // Method to log messages
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
    
    // Method to log errors
    public void error(String message) {
        System.out.println("[ERROR] " + message);
    }
    
    // Method to log warnings
    public void warning(String message) {
        System.out.println("[WARNING] " + message);
    }
}