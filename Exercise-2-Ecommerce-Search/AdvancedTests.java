/**
 * AdvancedTests - Advanced testing and analysis
 * Additional performance metrics and edge cases
 */

public class AdvancedTests {
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║     ADVANCED SEARCH ALGORITHM ANALYSIS         ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        // Test with various array sizes
        testWithDifferentSizes();
        
        // Test edge cases
        testEdgeCases();
        
        // Test with random data
        testWithRandomData();
    }
    
    private static void testWithDifferentSizes() {
        System.out.println("━━━ TEST: DIFFERENT ARRAY SIZES ━━━\n");
        
        int[] sizes = {100, 1000, 10000, 100000};
        
        for (int size : sizes) {
            Product[] products = generateProducts(size);
            int searchId = size / 2;
            
            System.out.println("Array Size: " + size);
            
            // Linear
            SearchAlgorithms.linearSearch(products, searchId);
            int linearComp = SearchAlgorithms.getComparisonCount();
            
            // Binary
            SearchAlgorithms.binarySearch(products, searchId);
            int binaryComp = SearchAlgorithms.getComparisonCount();
            
            System.out.println("  Linear: " + linearComp + " comparisons");
            System.out.println("  Binary: " + binaryComp + " comparisons");
            System.out.println("  Ratio:  " + (double)linearComp/binaryComp + "x faster\n");
        }
    }
    
    private static void testEdgeCases() {
        System.out.println("═".repeat(50) + "\n");
        System.out.println("━━━ TEST: EDGE CASES ━━━\n");
        
        Product[] products = {
            new Product(1, "First", "A", 10),
            new Product(2, "Second", "B", 20),
            new Product(3, "Third", "C", 30)
        };
        
        System.out.println("Test 1: Search for first element");
        testSearch(products, 1);
        
        System.out.println("\nTest 2: Search for last element");
        testSearch(products, 3);
        
        System.out.println("\nTest 3: Search for non-existent element");
        testSearch(products, 999);
    }
    
    private static void testWithRandomData() {
        System.out.println("\n" + "═".repeat(50) + "\n");
        System.out.println("━━━ TEST: RANDOM DATA ━━━\n");
        
        // Generate random unsorted array
        int size = 100;
        Product[] unsorted = new Product[size];
        for (int i = 0; i < size; i++) {
            int id = (int)(Math.random() * 1000);
            unsorted[i] = new Product(id, "Product" + i, "Category", 100.0);
        }
        
        // Sort for binary search
        java.util.Arrays.sort(unsorted);
        
        // Search for random element
        int randomId = unsorted[size / 3].getProductId();
        
        System.out.println("Array size: " + size);
        System.out.println("Searching for ID: " + randomId + "\n");
        
        testSearch(unsorted, randomId);
    }
    
    private static void testSearch(Product[] products, int searchId) {
        // Linear
        int linearIdx = SearchAlgorithms.linearSearch(products, searchId);
        int linearComp = SearchAlgorithms.getComparisonCount();
        
        // Binary
        int binaryIdx = SearchAlgorithms.binarySearch(products, searchId);
        int binaryComp = SearchAlgorithms.getComparisonCount();
        
        System.out.println("  Linear: " + linearComp + " comparisons, " + 
                          (linearIdx >= 0 ? "Found at " + linearIdx : "Not found"));
        System.out.println("  Binary: " + binaryComp + " comparisons, " + 
                          (binaryIdx >= 0 ? "Found at " + binaryIdx : "Not found"));
    }
    
    private static Product[] generateProducts(int count) {
        Product[] products = new Product[count];
        for (int i = 0; i < count; i++) {
            products[i] = new Product(i, "Product" + i, "Category", 100.0);
        }
        return products;
    }
}