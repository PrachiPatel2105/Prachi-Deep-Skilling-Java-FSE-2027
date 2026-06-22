/**
 * Main - Test class for Factory Method Pattern
 * Demonstrates creating different document types using factories
 */

public class Main {
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║   FACTORY METHOD PATTERN - DOCUMENT SYSTEM      ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        
        // Test 1: Create Word Document using WordDocumentFactory
        System.out.println("━━━ TEST 1: CREATE WORD DOCUMENT ━━━");
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDoc = wordFactory.newDocument("MyReport.docx");
        wordDoc.open();
        wordDoc.save();
        wordDoc.print();
        wordDoc.close();
        
        System.out.println("\n" + "═".repeat(50) + "\n");
        
        // Test 2: Create PDF Document using PdfDocumentFactory
        System.out.println("━━━ TEST 2: CREATE PDF DOCUMENT ━━━");
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDoc = pdfFactory.newDocument("Invoice.pdf");
        pdfDoc.open();
        pdfDoc.save();
        pdfDoc.print();
        pdfDoc.close();
        
        System.out.println("\n" + "═".repeat(50) + "\n");
        
        // Test 3: Create Excel Document using ExcelDocumentFactory
        System.out.println("━━━ TEST 3: CREATE EXCEL DOCUMENT ━━━");
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDoc = excelFactory.newDocument("Budget.xlsx");
        excelDoc.open();
        excelDoc.save();
        excelDoc.print();
        excelDoc.close();
        
        System.out.println("\n" + "═".repeat(50) + "\n");
        
        // Test 4: Demonstrate polymorphism - array of documents
        System.out.println("━━━ TEST 4: USING POLYMORPHISM ━━━");
        Document[] documents = {
            new WordDocumentFactory().newDocument("Contract.docx"),
            new PdfDocumentFactory().newDocument("Manual.pdf"),
            new ExcelDocumentFactory().newDocument("Sales.xlsx")
        };
        
        System.out.println("Performing operations on all documents:\n");
        for (int i = 0; i < documents.length; i++) {
            System.out.println("--- Document " + (i + 1) + " ---");
            System.out.println("Type: " + documents[i].getDocumentType());
            documents[i].open();
            documents[i].save();
            System.out.println();
        }
        
        System.out.println("═".repeat(50));
        System.out.println("FACTORY METHOD PATTERN DEMONSTRATION COMPLETED ✓");
        System.out.println("═".repeat(50));
    }
}