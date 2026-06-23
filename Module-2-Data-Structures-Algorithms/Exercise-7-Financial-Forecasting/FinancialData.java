/**
 * FinancialData - Represents financial data for forecasting
 * Contains historical values and growth rates
 */

public class FinancialData {
    
    private double[] historicalValues;
    private double initialValue;
    private double growthRate;
    private int periods;
    
    // Constructor
    public FinancialData(double initialValue, double growthRate, int historicalPeriods) {
        this.initialValue = initialValue;
        this.growthRate = growthRate;
        this.periods = historicalPeriods;
        this.historicalValues = new double[historicalPeriods];
        
        // Generate historical values
        calculateHistoricalValues();
    }
    
    /**
     * Calculate historical values based on growth rate
     * Example: Initial = 100, Growth = 0.1 (10%)
     * Period 1: 100 * 1.1 = 110
     * Period 2: 110 * 1.1 = 121
     * Period 3: 121 * 1.1 = 133.1
     */
    private void calculateHistoricalValues() {
        double currentValue = initialValue;
        
        for (int i = 0; i < periods; i++) {
            currentValue = currentValue * (1 + growthRate);
            historicalValues[i] = currentValue;
        }
    }
    
    // Getters
    public double getInitialValue() {
        return initialValue;
    }
    
    public double getGrowthRate() {
        return growthRate;
    }
    
    public int getPeriods() {
        return periods;
    }
    
    public double[] getHistoricalValues() {
        return historicalValues;
    }
    
    public double getLastValue() {
        return historicalValues[periods - 1];
    }
    
    public double getValueAtPeriod(int period) {
        if (period < 0 || period >= periods) {
            return -1;
        }
        return historicalValues[period];
    }
    
    // Display historical values
    public void displayHistoricalValues() {
        System.out.println("\nHistorical Values:");
        System.out.printf("Initial: $%.2f%n", initialValue);
        
        for (int i = 0; i < periods; i++) {
            System.out.printf("Period %d: $%.2f%n", i + 1, historicalValues[i]);
        }
    }
}