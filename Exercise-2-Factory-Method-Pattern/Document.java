/**
 * Document - Interface for all document types
 * This interface defines the contract that all documents must follow
 */

public interface Document {
    
    // Method to open the document
    void open();
    
    // Method to save the document
    void save();
    
    // Method to close the document
    void close();
    
    // Method to print the document
    void print();
    
    // Method to get document type
    String getDocumentType();
}