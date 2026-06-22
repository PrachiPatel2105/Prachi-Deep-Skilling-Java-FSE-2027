/**
 * PdfDocumentFactory - Concrete Factory for PDF documents
 * Implements the factory method to create PdfDocument objects
 */

public class PdfDocumentFactory extends DocumentFactory {
    
    @Override
    protected Document createDocument(String fileName) {
        return new PdfDocument(fileName);
    }
}