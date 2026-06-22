import java.util.Arrays;

/**
 * Main - Test class for Search Algorithms
 * Demonstrates Linear and Binary Search with performance analysis
 */

public class Main {
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║  E-COMMERCE SEARCH ALGORITHM COMPARISON        ║");
        System.out.println("║  Linear Search vs Binary Search                ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        // Create sample products (UNSORTED for linear search)
        Product[] unsortedProducts = {
            new Product(105, "Laptop", "Electronics", 999.99),
            new Product(203, "Book", "Education", 29.99),
            new Product(401, "Phone", "Electronics", 599.99),
            new Product(102, "Headphones", "Electronics", 199.99),
            new Product(507, "Monitor", "Electronics", 349.99),
            new Product(301, "Keyboard", "Electronics", 89.99),
            new Product(604, "Mouse", "Electronics", 49.99),
            new Product(708, "USB Cable", "Accessories", 9.99),
            new Product(201, "Notebook", "Stationery", 4.99),
            new Product(810, "Monitor Stand", "Accessories", 39.99)
        };
        
        // Create sorted products for binary search
        Product[] sortedProducts = unsortedProducts.clone();
        Arrays.sort(sortedProducts);
        
        // ============ TEST 1: LINEAR SEARCH ============
        System.out.println("━━━ TEST 1: LINEAR SEARCH (Unsorted Array) ━━━");
        System.out.println("Time Complexity: O(n)");
        System.out.println("Array Size: " + unsortedProducts.length + " products\n");
        
        testLinearSearch(unsortedProducts, 507);  // Best case (found early)
        testLinearSearch(unsortedProducts, 810);  // Worst case (found at end)
        testLinearSearch(unsortedProducts, 999);  // Worst case (not found)
        
        // ============ TEST 2: BINARY SEARCH ============
        System.out.println("\n" + "═".repeat(50) + "\n");
        System.out.println("━━━ TEST 2: BINARY SEARCH (Sorted Array) ━━━");
        System.out.println("Time Complexity: O(log n)");
        System.out.println("Array Size: " + sortedProducts.length + " products");
        System.out.println("Is sorted? " + (SearchAlgorithms.isSorted(sortedProducts) ? "YES ✓" : "NO ✗"));
        System.out.println();
        
        printSortedArray(sortedProducts);
        
        testBinarySearch(sortedProducts, 507);
        testBinarySearch(sortedProducts, 102);
        testBinarySearch(sortedProducts, 810);
        testBinarySearch(sortedProducts, 999);
        
        // ============ TEST 3: PERFORMANCE COMPARISON ============
        System.out.println("\n" + "═".repeat(50) + "\n");
        System.out.println("━━━ TEST 3: PERFORMANCE COMPARISON ━━━\n");
        
        compareSearchMethods(unsortedProducts, sortedProducts, 507);
        compareSearchMethods(unsortedProducts, sortedProducts, 810);
        compareSearchMethods(unsortedProducts, sortedProducts, 999);
        
        // ============ TEST 4: LARGE DATASET ANALYSIS ============
        System.out.println("\n" + "═".repeat(50) + "\n");
        System.out.println("━━━ TEST 4: LARGE DATASET ANALYSIS ━━━\n");
        
        analyzeLargeDataset(1000);
        analyzeLargeDataset(10000);
        analyzeLargeDataset(100000);
        
        // ============ TEST 5: COMPLEXITY ANALYSIS ============
        System.out.println("\n" + "═".repeat(50) + "\n");
        System.out.println("━━━ TEST 5: TIME COMPLEXITY ANALYSIS ━━━\n");
        printComplexityAnalysis();
        
        // ============ RECOMMENDATION ============
        System.out.println("\n" + "═".repeat(50) + "\n");
        System.out.println("━━━ RECOMMENDATION FOR E-COMMERCE PLATFORM ━━━\n");
        printRecommendation();
        
        System.out.println("\n" + "═".repeat(50));
        System.out.println("ANALYSIS COMPLETE ✓");
        System.out.println("═".repeat(50));
    }
    
    /**
     * Test linear search with specific product ID
     */
    private static void testLinearSearch(Product[] products, int searchId) {
        int index = SearchAlgorithms.linearSearch(products, searchId);
        int comparisons = SearchAlgorithms.getComparisonCount();
        
        System.out.println("Searching for Product ID: " + searchId);
        if (index != -1) {
            System.out.println("✓ Found at index: " + index);
            System.out.println("  Product: " + products[index]);
        } else {
            System.out.println("✗ Product not found");
        }
        System.out.println("Comparisons made: " + comparisons + " (O(n))");
        System.out.println();
    }
    
    /**
     * Test binary search with specific product ID
     */
    private static void testBinarySearch(Product[] products, int searchId) {
        int index = SearchAlgorithms.binarySearch(products, searchId);
        int comparisons = SearchAlgorithms.getComparisonCount();
        
        System.out.println("Searching for Product ID: " + searchId);
        if (index != -1) {
            System.out.println("✓ Found at index: " + index);
            System.out.println("  Product: " + products[index]);
        } else {
            System.out.println("✗ Product not found");
        }
        System.out.println("Comparisons made: " + comparisons + " (O(log n))");
        System.out.println();
    }
    
    /**
     * Compare performance of both search methods
     */
    private static void compareSearchMethods(Product[] unsorted, Product[] sorted, int searchId) {
        System.out.println("Searching for Product ID: " + searchId);
        System.out.println("────────────────────────────");
        
        // Linear search
        SearchAlgorithms.linearSearch(unsorted, searchId);
        int linearComparisons = SearchAlgorithms.getComparisonCount();
        
        // Binary search
        SearchAlgorithms.binarySearch(sorted, searchId);
        int binaryComparisons = SearchAlgorithms.getComparisonCount();
        
        System.out.printf("Linear Search: %d comparisons (O(n))%n", linearComparisons);
        System.out.printf("Binary Search: %d comparisons (O(log n))%n", binaryComparisons);
        System.out.printf("Speed improvement: %.1fx faster with Binary Search%n", 
            (double) linearComparisons / binaryComparisons);
        System.out.println();
    }
    
    /**
     * Analyze performance on large datasets
     */
    private static void analyzeLargeDataset(int size) {
        System.out.println("Dataset Size: " + size + " products");
        System.out.println("─────────────────────────");
        
        // Create large sorted dataset
        Product[] products = new Product[size];
        for (int i = 0; i < size; i++) {
            products[i] = new Product(i, "Product" + i, "Category", 100.0);
        }
        
        int searchId = size / 2;  // Search for middle element
        
        // Linear search
        SearchAlgorithms.linearSearch(products, searchId);
        int linearComparisons = SearchAlgorithms.getComparisonCount();
        
        // Binary search
        SearchAlgorithms.binarySearch(products, searchId);
        int binaryComparisons = SearchAlgorithms.getComparisonCount();
        
        System.out.printf("Linear Search:  %d comparisons (O(n))%n", linearComparisons);
        System.out.printf("Binary Search:  %d comparisons (O(log n))%n", binaryComparisons);
        System.out.printf("Speed improvement: %.1fx faster%n", 
            (double) linearComparisons / binaryComparisons);
        System.out.println();
    }
    
    /**
     * Print complexity analysis table
     */
    private static void printComplexityAnalysis() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║           TIME COMPLEXITY COMPARISON                       ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║ Array Size │ Linear Search │ Binary Search │ Speed Gain   ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        
        int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
        
        for (int size : sizes) {
            int linearOps = size;  // Worst case
            int binaryOps = (int) (Math.log(size) / Math.log(2));  // log₂(n)
            double speedGain = (double) linearOps / binaryOps;
            
            System.out.printf("║ %10d │ %13d │ %13d │ %11.1fx ║%n", 
                size, linearOps, binaryOps, speedGain);
        }
        
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
    
    /**
     * Print recommendation for e-commerce platform
     */
    private static void printRecommendation() {
        System.out.println("✓ USE BINARY SEARCH FOR:");
        System.out.println("  • Product search by ID (if IDs are sequential)");
        System.out.println("  • Quick lookups in large catalogs (1M+ products)");
        System.out.println("  • Real-time search responses needed");
        System.out.println("  • Server performance is critical");
        System.out.println();
        System.out.println("✓ USE LINEAR SEARCH FOR:");
        System.out.println("  • Small datasets (< 100 items)");
        System.out.println("  • Unsorted data that arrives dynamically");
        System.out.println("  • One-time searches");
        System.out.println("  • When sorting cost is not worth it");
        System.out.println();
        System.out.println("✓ BEST PRACTICE FOR E-COMMERCE:");
        System.out.println("  1. Keep products sorted by ID in database");
        System.out.println("  2. Use Binary Search for quick lookups");
        System.out.println("  3. For multiple fields (name, price), use:");
        System.out.println("     • Hash tables (O(1) average case)");
        System.out.println("     • Indexed databases (B-trees)");
        System.out.println("     • Full-text search engines (Elasticsearch)");
    }
    
    /**
     * Print sorted array
     */
    private static void printSortedArray(Product[] products) {
        System.out.println("Sorted Products by ID:");
        for (int i = 0; i < products.length; i++) {
            System.out.println("  [" + i + "] ID: " + products[i].getProductId() + 
                             " - " + products[i].getProductName());
        }
        System.out.println();
    }
}