# Exercise 1: Implementing the Singleton Pattern

## 📋 Problem Statement

You need to ensure that a logging utility class in your application has only one instance throughout the application lifecycle to ensure consistent logging.

---

## 🎯 Objectives

1. ✅ Create a Singleton Logger class
2. ✅ Ensure only ONE instance exists throughout the application
3. ✅ Provide a method to get the logger instance
4. ✅ Test the implementation

---

## 🔍 Singleton Pattern Concept

### What is Singleton Pattern?
A design pattern that ensures a class has only ONE instance and provides a global point of access to it.

### When to use?
- Logging utilities (our case)
- Database connections
- Configuration managers
- Thread pools
- Cache managers

### Advantages:
- ✅ Memory efficient (only one object)
- ✅ Global access point
- ✅ Thread-safe access to shared resources
- ✅ Lazy initialization

### Disadvantages:
- ❌ Can hide dependencies
- ❌ Makes testing difficult
- ❌ Can create global state issues

---

## 💻 Code Structure

### Files Created:
1. **Logger.java** - The Singleton class
2. **Main.java** - Test class to verify singleton
3. **README.md** - This documentation

---

## 📝 Implementation Details

### Logger.java

```java
public class Logger {
    
    // Private static instance
    private static Logger instance;
    
    // Private constructor
    private Logger() {
        System.out.println("Logger instance created!");
    }
    
    // Public method to get instance
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    
    // Logging methods
    public void log(String message) { ... }
    public void error(String message) { ... }
    public void warning(String message) { ... }
}
```

### How it works:

1. **Private Constructor:** Prevents direct instantiation
```java
   private Logger() { }
   // Logger logger = new Logger(); ❌ NOT ALLOWED
```

2. **Private Static Instance:** Holds the single instance
```java
   private static Logger instance;
```

3. **getInstance() Method:** Returns the single instance
```java
   Logger logger = Logger.getInstance(); ✅ CORRECT WAY
```

4. **Lazy Initialization:** Creates instance only when first requested
```java
   if (instance == null) {
       instance = new Logger();
   }
```

---

## 🧪 Testing Results

### Test Scenario:
- Created 3 logger references: logger1, logger2, logger3
- All called `Logger.getInstance()`

### Results: