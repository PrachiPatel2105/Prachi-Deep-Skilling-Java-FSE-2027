/**
 * WordDocumentFactory - Concrete Factory for Word documents
 * Implements the factory method to create WordDocument objects
 */

public class WordDocumentFactory extends DocumentFactory {
    
    @Override
    protected Document createDocument(String fileName) {
        return new WordDocument(fileName);
    }
}