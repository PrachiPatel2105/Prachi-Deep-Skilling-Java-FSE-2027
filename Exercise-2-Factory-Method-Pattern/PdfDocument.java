/**
 * PdfDocument - Concrete implementation for PDF documents
 * Represents a .pdf file
 */

public class PdfDocument implements Document {
    
    private String fileName;
    
    // Constructor
    public PdfDocument(String fileName) {
        this.fileName = fileName;
    }
    
    @Override
    public void open() {
        System.out.println("📄 Opening PDF document: " + fileName);
        System.out.println("   → Opening in Adobe Reader...");
    }
    
    @Override
    public void save() {
        System.out.println("💾 Saving PDF document: " + fileName);
        System.out.println("   → Saving as .pdf format...");
    }
    
    @Override
    public void close() {
        System.out.println("❌ Closing PDF document: " + fileName);
        System.out.println("   → Document closed successfully");
    }
    
    @Override
    public void print() {
        System.out.println("🖨️  Printing PDF document: " + fileName);
        System.out.println("   → Sending to printer...");
    }
    
    @Override
    public String getDocumentType() {
        return "PDF Document (.pdf)";
    }
}