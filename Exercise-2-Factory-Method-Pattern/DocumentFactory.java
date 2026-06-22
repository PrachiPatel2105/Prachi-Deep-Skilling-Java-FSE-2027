/**
 * DocumentFactory - Abstract Factory Class
 * This class defines the factory method that subclasses must implement
 * Uses Template Method pattern combined with Factory pattern
 */

public abstract class DocumentFactory {
    
    /**
     * Abstract method that subclasses must implement
     * Each subclass decides which concrete document to create
     */
    protected abstract Document createDocument(String fileName);
    
    /**
     * Non-abstract method that uses the factory method
     * This is the Template Method that all factories follow
     */
    public Document newDocument(String fileName) {
        // Create document using abstract method
        Document document = createDocument(fileName);
        
        // Initialize the document
        System.out.println("\n✓ Document created: " + document.getDocumentType());
        System.out.println("  Filename: " + fileName + "\n");
        
        return document;
    }
}