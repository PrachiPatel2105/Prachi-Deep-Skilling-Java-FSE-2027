# Exercise 7: SLF4J Exercise 1 - Logging Error Messages and Warning Levels

## Objective
Learn to use SLF4J for logging with different severity levels and configurations.

## What is SLF4J?

SLF4J (Simple Logging Facade for Java) is:
- A logging facade/abstraction layer
- Allows switching between logging implementations
- Decouples application from logging implementation
- Works with Logback, Log4j, etc.

## Why Use SLF4J?

1. **Flexibility** - Switch logging implementations without code changes
2. **Abstraction** - Application doesn't depend on specific logging library
3. **Performance** - Lazy string formatting with parameterized messages
4. **Simplicity** - Easy-to-use API

## Logging Levels (Severity)

From least to most severe:

### 1. TRACE
- Most detailed level
- Used for tracing execution flow
- Usually disabled in production
- Example: Entering/exiting methods

```java
logger.trace("Entering method with parameters: {}", params);
```

### 2. DEBUG
- Debug information
- For development and troubleshooting
- Usually disabled in production
- Example: Variable values, method calls

```java
logger.debug("Processing order with ID: {}", orderId);
```

### 3. INFO (Default)
- Informational messages
- Business-level events
- Enabled in production
- Example: Application started, user logged in

```java
logger.info("User logged in successfully: {}", username);
```

### 4. WARN
- Warning messages
- Something unexpected but not critical
- Indicates potential problems
- Example: Deprecated usage, low resources

```java
logger.warn("Low memory available: {}MB", freeMemory);
```

### 5. ERROR
- Error messages
- Something went wrong
- Application can continue
- Example: Failed operations, exceptions

```java
logger.error("Database connection failed", exception);
```

### 6. FATAL/OFF
- Fatal errors (some implementations)
- Application must stop
- Nothing gets logged (OFF)

## SLF4J Dependencies

```xml
<!-- SLF4J API -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.30</version>
</dependency>

<!-- Logback (SLF4J Implementation) -->
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.3</version>
</dependency>
```

## Logback Configuration (logback.xml)

```xml
<configuration>
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} %-5level %logger - %msg%n</pattern>
        </encoder>
    </appender>
    
    <appender name="FILE" class="ch.qos.logback.core.FileAppender">
        <file>logs/application.log</file>
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} %-5level %logger - %msg%n</pattern>
        </encoder>
    </appender>
    
    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="FILE"/>
    </root>
    
    <logger name="com.bank" level="DEBUG"/>
</configuration>
```

## Using SLF4J

### 1. Get Logger
```java
private static final Logger logger = LoggerFactory.getLogger(ClassName.class);
```

### 2. Log with Different Levels
```java
logger.trace("Trace message");
logger.debug("Debug message");
logger.info("Info message");
logger.warn("Warning message");
logger.error("Error message");
logger.error("Error with exception", exception);
```

### 3. Use Parameterized Messages (Efficient)
```java
// Good - No string concatenation
logger.info("User {} logged in from {}", username, ip);

// Avoid - String concatenation
logger.info("User " + username + " logged in from " + ip);
```

## Classes Created

### LoggingExample
- Demonstrates all logging levels
- Shows exception logging
- Shows method-level logging

### BankLoggingApplication
- Real-world banking application
- Demonstrates practical logging scenarios
- Shows logging for business operations

## Logging Pattern
**Output Example:**
## Log Outputs

### Console Output
Logged to console (stdout) in real-time.

### File Output
Logged to `logs/application.log` file.

## Best Practices

### 1. Use Appropriate Levels
- TRACE: Method entry/exit
- DEBUG: Variable values, execution flow
- INFO: Business events, milestones
- WARN: Unusual but recoverable situations
- ERROR: Failures and exceptions

### 2. Use Parameterized Messages
```java
// GOOD
logger.info("User {} logged in at {}", username, timestamp);

// BAD
logger.info("User " + username + " logged in at " + timestamp);
```

### 3. Include Context
```java
logger.error("Payment failed for account {}: {}", accountId, error);
```

### 4. Log Exceptions Properly
```java
try {
    // code
} catch (Exception e) {
    logger.error("Operation failed", e);  // Include exception
}
```

### 5. Don't Log Sensitive Data
```java
// BAD
logger.info("User password: {}", password);

// GOOD
logger.info("Authentication attempt for user: {}", username);
```

## Running the Examples

### Run LoggingExample
### Run BankLoggingApplication
## Files Created

- `src/main/java/com/bank/LoggingExample.java` - Logging levels demo
- `src/main/java/com/bank/BankLoggingApplication.java` - Real-world example
- `src/main/resources/logback.xml` - Logging configuration
- `README.md` - This file
- `logs/application.log` - Generated log file

## Real-World Applications

### Banking
- Log all transactions
- Log login attempts
- Log transfer operations
- Log errors

### E-commerce
- Log orders
- Log payments
- Log user actions
- Log system errors

### Healthcare
- Log patient records access
- Log appointments
- Log medical procedures
- Log system operations

## Log Levels Decision Tree
## Configuration Tips

### Increase Debugging
Change in logback.xml:
```xml
<root level="DEBUG">
```

### Filter by Package
```xml
<logger name="com.bank" level="DEBUG"/>
<logger name="org.springframework" level="WARN"/>
```

### Use Different Appenders
```xml
<appender name="ERROR_FILE" class="ch.qos.logback.core.FileAppender">
    <file>logs/errors.log</file>
</appender>

<logger name="com.bank" level="ERROR">
    <appender-ref ref="ERROR_FILE"/>
</logger>
```

## Key Learnings

✅ Understand SLF4J facade vs implementation
✅ Know the 5 logging levels and when to use each
✅ Use parameterized messages for efficiency
✅ Configure logging with logback.xml
✅ Log exceptions with context
✅ Don't log sensitive information
✅ Use appropriate log levels
✅ Real-world logging patterns

## Next Steps

- Exercise 8: SLF4J - Advanced Logging Configuration
- Spring Core: Dependency Injection
- Spring Data JPA
- Spring REST

---

**Exercise Status:** ✅ COMPLETED

**Date Completed:** [Your Date]

**Time Taken:** [Your Time]

**Difficulty Level:** ⭐⭐⭐☆☆ (3/5 - Intermediate)

**Your Understanding:** [Rate 1-10]

---

*This is SLF4J Exercise 1 of Module 5: Logging Framework*
*Part of Digital Nurture 5.0 Deep Skilling Program*