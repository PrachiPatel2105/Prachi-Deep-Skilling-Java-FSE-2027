/**
 * ExcelDocumentFactory - Concrete Factory for Excel documents
 * Implements the factory method to create ExcelDocument objects
 */

public class ExcelDocumentFactory extends DocumentFactory {
    
    @Override
    protected Document createDocument(String fileName) {
        return new ExcelDocument(fileName);
    }
}