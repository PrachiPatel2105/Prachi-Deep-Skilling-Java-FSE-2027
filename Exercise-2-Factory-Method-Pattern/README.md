# Exercise 2: Implementing the Factory Method Pattern

## 📋 Problem Statement

You are developing a document management system that needs to create different types of documents (e.g., Word, PDF, Excel). Use the Factory Method Pattern to achieve this.

---

## 🎯 Objectives

1. ✅ Create a Document interface
2. ✅ Implement concrete document classes (Word, PDF, Excel)
3. ✅ Create abstract DocumentFactory
4. ✅ Create concrete factory classes for each document type
5. ✅ Test the factory method implementation
6. ✅ Demonstrate polymorphism with factory pattern

---

## 🔍 Factory Method Pattern Concept

### What is Factory Method Pattern?
A creational design pattern that provides an interface for creating objects, but lets subclasses decide which class to instantiate.

### Key Components:
1. **Product Interface** (Document)
   - Defines the interface for objects the factory creates

2. **Concrete Products** (WordDocument, PdfDocument, ExcelDocument)
   - Implement the product interface
   - Define specific document types

3. **Factory (Abstract)** (DocumentFactory)
   - Declares the factory method
   - Contains logic for using the factory method

4. **Concrete Factories** (WordDocumentFactory, PdfDocumentFactory, ExcelDocumentFactory)
   - Implements the factory method
   - Creates specific product objects

### When to use?
- Need to create objects but don't know exact type at compile time
- Want to delegate creation logic to subclasses
- Want to decouple object creation from usage
- Real-world examples: Document systems, UI toolkits, database drivers

### Advantages:
- ✅ Separates object creation from usage
- ✅ Easy to add new product types
- ✅ Follows Open/Closed Principle
- ✅ Reduces coupling between classes
- ✅ Promotes code reusability

### Disadvantages:
- ❌ More classes needed
- ❌ Slightly more complex than direct instantiation
- ❌ Overkill for simple scenarios

---

## 💻 Code Structure

### Files Created:

1. **Document.java** - Interface defining document contract
2. **WordDocument.java** - Word document implementation
3. **PdfDocument.java** - PDF document implementation
4. **ExcelDocument.java** - Excel document implementation
5. **DocumentFactory.java** - Abstract factory class
6. **WordDocumentFactory.java** - Concrete factory for Word
7. **PdfDocumentFactory.java** - Concrete factory for PDF
8. **ExcelDocumentFactory.java** - Concrete factory for Excel
9. **Main.java** - Test class

---

## 🏗️ Architecture Diagram