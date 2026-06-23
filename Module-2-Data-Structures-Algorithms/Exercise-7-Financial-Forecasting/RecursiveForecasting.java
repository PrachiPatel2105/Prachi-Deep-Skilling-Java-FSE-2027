/**
 * RecursiveForecasting - Implements recursive algorithms for financial forecasting
 * Includes naive recursion and optimized versions
 */

public class RecursiveForecasting {
    
    // Counter for tracking recursive calls
    private static int recursionCount;
    
    // ============ NAIVE RECURSIVE APPROACH ============
    // Time Complexity: O(2^n) - VERY SLOW
    
    /**
     * Naive Recursive: Calculate future value
     * 
     * Formula: FutureValue(n) = CurrentValue * (1 + growthRate)^n
     * 
     * Example:
     * Current Value: $100
     * Growth Rate: 10% (0.1)
     * Periods: 3
     * 
     * FutureValue(3) = 100 * (1.1)^3 = 133.1
     */
    public static double recursiveFutureValue(double currentValue, double growthRate, 
                                             int periodsRemaining) {
        recursionCount = 0;
        return recursiveFutureValueHelper(currentValue, growthRate, periodsRemaining);
    }
    
    private static double recursiveFutureValueHelper(double currentValue, double growthRate, 
                                                    int periodsRemaining) {
        recursionCount++;
        
        // BASE CASE: No more periods to forecast
        if (periodsRemaining == 0) {
            return currentValue;
        }
        
        // RECURSIVE CASE: Calculate next period and recurse
        double nextValue = currentValue * (1 + growthRate);
        return recursiveFutureValueHelper(nextValue, growthRate, periodsRemaining - 1);
    }
    
    // ============ FIBONACCI-STYLE RECURSION ============
    // This demonstrates the problem with naive recursion
    
    /**
     * Fibonacci Sequence (naive recursive)
     * Shows why naive recursion is slow
     * 
     * fib(5) requires 15 function calls!
     * fib(10) requires 177 function calls!
     * fib(50) requires billions of calls - IMPOSSIBLE!
     */
    public static long fibonacciNaive(int n) {
        recursionCount = 0;
        return fibonacciNaiveHelper(n);
    }
    
    private static long fibonacciNaiveHelper(int n) {
        recursionCount++;
        
        // BASE CASES
        if (n <= 1) {
            return n;
        }
        
        // RECURSIVE CASE: Calls itself twice!
        return fibonacciNaiveHelper(n - 1) + fibonacciNaiveHelper(n - 2);
    }
    
    // ============ OPTIMIZED: MEMOIZATION ============
    // Time Complexity: O(n) - MUCH FASTER
    
    /**
     * Memoization: Store results to avoid recalculation
     * 
     * When calculating Fibonacci:
     * - Without memoization: fib(5) → 15 calls
     * - With memoization: fib(5) → 6 calls
     * - With memoization: fib(50) → 51 calls
     */
    public static long fibonacciMemo(int n) {
        recursionCount = 0;
        long[] memo = new long[n + 1];
        return fibonacciMemoHelper(n, memo);
    }
    
    private static long fibonacciMemoHelper(int n, long[] memo) {
        recursionCount++;
        
        // BASE CASES
        if (n <= 1) {
            return n;
        }
        
        // Check if already calculated
        if (memo[n] != 0) {
            return memo[n];
        }
        
        // Calculate and store result
        memo[n] = fibonacciMemoHelper(n - 1, memo) + fibonacciMemoHelper(n - 2, memo);
        return memo[n];
    }
    
    // ============ OPTIMIZED: DYNAMIC PROGRAMMING (ITERATIVE) ============
    // Time Complexity: O(n), Space: O(n)
    // This is the BEST approach for financial forecasting
    
    /**
     * Dynamic Programming (Iterative): Bottom-up approach
     * 
     * Most efficient for production code
     * - Predictable performance
     * - No stack overflow risk
     * - Easy to understand
     */
    public static long fibonacciDP(int n) {
        recursionCount = n;  // Approximate: one operation per period
        
        if (n <= 1) {
            return n;
        }
        
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
    
    // ============ FINANCIAL FORECASTING WITH MEMOIZATION ============
    
    /**
     * Optimized Financial Forecasting using Memoization
     * For compound interest calculations
     */
    public static double optimizedFutureValue(double currentValue, double growthRate, 
                                             int periodsRemaining) {
        recursionCount = 0;
        double[] memo = new double[periodsRemaining + 1];
        return optimizedFutureValueHelper(currentValue, growthRate, periodsRemaining, memo);
    }
    
    private static double optimizedFutureValueHelper(double currentValue, double growthRate, 
                                                    int periodsRemaining, double[] memo) {
        recursionCount++;
        
        // BASE CASE
        if (periodsRemaining == 0) {
            return currentValue;
        }
        
        // Check memo
        if (memo[periodsRemaining] != 0) {
            return memo[periodsRemaining];
        }
        
        // Calculate and store
        double nextValue = currentValue * (1 + growthRate);
        memo[periodsRemaining] = optimizedFutureValueHelper(nextValue, growthRate, 
                                                            periodsRemaining - 1, memo);
        return memo[periodsRemaining];
    }
    
    // ============ MATHEMATICAL FORMULA (BEST APPROACH) ============
    // Time Complexity: O(1) - INSTANT
    
    /**
     * Direct Formula: FutureValue = PresentValue * (1 + rate)^n
     * 
     * This is the BEST approach for financial forecasting
     * Instant calculation, no recursion needed
     */
    public static double formulaFutureValue(double presentValue, double growthRate, 
                                           int periods) {
        recursionCount = 1;  // Just one calculation
        return presentValue * Math.pow(1 + growthRate, periods);
    }
    
    /**
     * Get recursion call count
     */
    public static int getRecursionCount() {
        return recursionCount;
    }
    
    /**
     * Calculate average growth rate from historical data
     */
    public static double calculateAverageGrowthRate(double[] historicalValues) {
        if (historicalValues.length < 2) {
            return 0;
        }
        
        double totalGrowth = 0;
        
        for (int i = 1; i < historicalValues.length; i++) {
            double growth = (historicalValues[i] - historicalValues[i - 1]) / 
                           historicalValues[i - 1];
            totalGrowth += growth;
        }
        
        return totalGrowth / (historicalValues.length - 1);
    }
}