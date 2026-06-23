/**
 * SearchAlgorithms - Implements Linear and Binary Search
 * Includes performance analysis and comparison
 */

public class SearchAlgorithms {
    
    // Counter for tracking comparisons
    private static int comparisonCount;
    
    // ============ LINEAR SEARCH ============
    
    /**
     * Linear Search - O(n)
     * Searches from beginning to end
     * Works on unsorted arrays
     * 
     * @param products Array of products
     * @param searchId Product ID to search
     * @return Index of product or -1 if not found
     */
    public static int linearSearch(Product[] products, int searchId) {
        comparisonCount = 0;
        
        for (int i = 0; i < products.length; i++) {
            comparisonCount++;  // Count each comparison
            
            if (products[i].getProductId() == searchId) {
                return i;  // Found at index i
            }
        }
        
        return -1;  // Not found
    }
    
    /**
     * Linear Search by Product Name - O(n)
     */
    public static int linearSearchByName(Product[] products, String productName) {
        comparisonCount = 0;
        
        for (int i = 0; i < products.length; i++) {
            comparisonCount++;
            
            if (products[i].getProductName().equalsIgnoreCase(productName)) {
                return i;
            }
        }
        
        return -1;
    }
    
    // ============ BINARY SEARCH ============
    
    /**
     * Binary Search - O(log n)
     * Divides array in half each time
     * REQUIRES sorted array
     * Much faster than linear search
     * 
     * @param products Sorted array of products
     * @param searchId Product ID to search
     * @return Index of product or -1 if not found
     */
    public static int binarySearch(Product[] products, int searchId) {
        comparisonCount = 0;
        return binarySearchHelper(products, searchId, 0, products.length - 1);
    }
    
    /**
     * Helper method for recursive binary search
     */
    private static int binarySearchHelper(Product[] products, int searchId, 
                                         int left, int right) {
        if (left > right) {
            return -1;  // Not found
        }
        
        int mid = left + (right - left) / 2;
        int midId = products[mid].getProductId();
        
        comparisonCount++;  // Count each comparison
        
        if (midId == searchId) {
            return mid;  // Found
        } else if (midId < searchId) {
            // Search right half
            return binarySearchHelper(products, searchId, mid + 1, right);
        } else {
            // Search left half
            return binarySearchHelper(products, searchId, left, mid - 1);
        }
    }
    
    /**
     * Iterative Binary Search - O(log n)
     * Non-recursive version
     */
    public static int binarySearchIterative(Product[] products, int searchId) {
        comparisonCount = 0;
        int left = 0;
        int right = products.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midId = products[mid].getProductId();
            
            comparisonCount++;
            
            if (midId == searchId) {
                return mid;
            } else if (midId < searchId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    // ============ UTILITY METHODS ============
    
    /**
     * Get number of comparisons made in last search
     */
    public static int getComparisonCount() {
        return comparisonCount;
    }
    
    /**
     * Print array of products
     */
    public static void printProducts(Product[] products) {
        System.out.println("\nProducts in array:");
        for (int i = 0; i < products.length; i++) {
            System.out.println("  [" + i + "] " + products[i]);
        }
    }
    
    /**
     * Check if array is sorted
     */
    public static boolean isSorted(Product[] products) {
        for (int i = 0; i < products.length - 1; i++) {
            if (products[i].getProductId() > products[i + 1].getProductId()) {
                return false;
            }
        }
        return true;
    }
}