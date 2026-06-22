/**
 * ExcelDocument - Concrete implementation for Excel documents
 * Represents a .xlsx or .xls file
 */

public class ExcelDocument implements Document {
    
    private String fileName;
    
    // Constructor
    public ExcelDocument(String fileName) {
        this.fileName = fileName;
    }
    
    @Override
    public void open() {
        System.out.println("📊 Opening Excel document: " + fileName);
        System.out.println("   → Opening in Microsoft Excel...");
    }
    
    @Override
    public void save() {
        System.out.println("💾 Saving Excel document: " + fileName);
        System.out.println("   → Saving as .xlsx format...");
    }
    
    @Override
    public void close() {
        System.out.println("❌ Closing Excel document: " + fileName);
        System.out.println("   → Document closed successfully");
    }
    
    @Override
    public void print() {
        System.out.println("🖨️  Printing Excel document: " + fileName);
        System.out.println("   → Sending to printer...");
    }
    
    @Override
    public String getDocumentType() {
        return "Excel Document (.xlsx)";
    }
}