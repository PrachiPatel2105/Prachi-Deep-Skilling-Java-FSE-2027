/**
 * WordDocument - Concrete implementation for Word documents
 * Represents a .docx or .doc file
 */

public class WordDocument implements Document {
    
    private String fileName;
    
    // Constructor
    public WordDocument(String fileName) {
        this.fileName = fileName;
    }
    
    @Override
    public void open() {
        System.out.println("📄 Opening Word document: " + fileName);
        System.out.println("   → Opening in Microsoft Word...");
    }
    
    @Override
    public void save() {
        System.out.println("💾 Saving Word document: " + fileName);
        System.out.println("   → Saving as .docx format...");
    }
    
    @Override
    public void close() {
        System.out.println("❌ Closing Word document: " + fileName);
        System.out.println("   → Document closed successfully");
    }
    
    @Override
    public void print() {
        System.out.println("🖨️  Printing Word document: " + fileName);
        System.out.println("   → Sending to printer...");
    }
    
    @Override
    public String getDocumentType() {
        return "Word Document (.docx)";
    }
}