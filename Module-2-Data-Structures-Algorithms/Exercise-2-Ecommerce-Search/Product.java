/**
 * Product - Class to represent a product in e-commerce platform
 * Used for testing search algorithms
 */

public class Product implements Comparable<Product> {
    
    private int productId;
    private String productName;
    private String category;
    private double price;
    
    // Constructor
    public Product(int productId, String productName, String category, double price) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
    }
    
    // Getters
    public int getProductId() {
        return productId;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public String getCategory() {
        return category;
    }
    
    public double getPrice() {
        return price;
    }
    
    // Comparator for sorting by productId
    @Override
    public int compareTo(Product other) {
        return Integer.compare(this.productId, other.productId);
    }
    
    // toString method for display
    @Override
    public String toString() {
        return "Product{" +
                "ID=" + productId +
                ", Name='" + productName + '\'' +
                ", Category='" + category + '\'' +
                ", Price=$" + price +
                '}';
    }
}