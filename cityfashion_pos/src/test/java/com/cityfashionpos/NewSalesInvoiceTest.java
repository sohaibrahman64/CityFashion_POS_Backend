package com.cityfashionpos;

import java.util.ArrayList;
import java.util.List;

import com.cityfashionpos.dto.NewSalesInvoiceRequest;
import com.cityfashionpos.dto.NewSalesInvoiceRequest.NewSalesInvoiceItemRequest;

/**
 * Test class demonstrating the New Sales Invoice backend implementation
 * This is not a JUnit test, but rather a demonstration of how to use the API
 */
public class NewSalesInvoiceTest {

    public static void main(String[] args) {
        // This demonstrates how to create a New Sales Invoice request
        // In a real application, this would be sent via HTTP POST to the API

        NewSalesInvoiceRequest request = createSampleRequest();
        System.out.println("Sample New Sales Invoice Request created:");
        System.out.println("Customer: " + request.getPartyName());
        System.out.println("Phone: " + request.getPartyPhone());
        System.out.println("Items: " + request.getItems().size());
        System.out.println("Received Amount: " + request.getReceivedAmount());
        System.out.println("Fully Received: " + request.getIsFullyReceived());
    }

    private static NewSalesInvoiceRequest createSampleRequest() {
        NewSalesInvoiceRequest request = new NewSalesInvoiceRequest();

        // Set customer information
        request.setPartyName("John Doe");
        request.setPartyPhone("9876543210");

        // Create sample items
        List<NewSalesInvoiceItemRequest> items = new ArrayList<>();

        // Item 1
        NewSalesInvoiceItemRequest item1 = new NewSalesInvoiceItemRequest();
        item1.setId(1L);
        item1.setItemName("Cotton T-Shirt");
        item1.setQuantity(2);
        item1.setPrice(599.00);
        item1.setDiscount(10.0); // 10% discount
        item1.setTotal(1078.20); // (2 * 599) - 10% discount
        item1.setItemId(101L);
        items.add(item1);

        // Item 2
        NewSalesInvoiceItemRequest item2 = new NewSalesInvoiceItemRequest();
        item2.setId(2L);
        item2.setItemName("Denim Jeans");
        item2.setQuantity(1);
        item2.setPrice(1299.00);
        item2.setDiscount(15.0); // 15% discount
        item2.setTotal(1104.15); // (1 * 1299) - 15% discount
        item2.setItemId(102L);
        items.add(item2);

        request.setItems(items);

        // Set payment information
        request.setReceivedAmount(2000.00);
        request.setIsFullyReceived(false); // Partial payment

        return request;
    }
}

/*
 * API Usage Examples:
 * 
 * 1. Create New Sales Invoice:
 * POST /api/new-sales-invoice/create
 * 
 * Request Body:
 * {
 * "customerName": "John Doe",
 * "customerPhone": "9876543210",
 * "items": [
 * {
 * "id": 1,
 * "itemName": "Cotton T-Shirt",
 * "quantity": 2,
 * "price": 599.00,
 * "discount": 10.0,
 * "total": 1078.20,
 * "productId": 101
 * },
 * {
 * "id": 2,
 * "itemName": "Denim Jeans",
 * "quantity": 1,
 * "price": 1299.00,
 * "discount": 15.0,
 * "total": 1104.15,
 * "productId": 102
 * }
 * ],
 * "receivedAmount": 2000.00,
 * "isFullyReceived": false
 * }
 * 
 * Response:
 * {
 * "invoiceId": 123,
 * "invoiceNumber": "CFK-00123",
 * "invoiceDate": "2025-01-15",
 * "customerName": "John Doe",
 * "customerPhone": "9876543210",
 * "items": [...],
 * "subtotalAmount": 2497.00,
 * "totalDiscountAmount": 314.65,
 * "totalAmount": 2182.35,
 * "receivedAmount": 2000.00,
 * "balanceAmount": 182.35,
 * "savedAmount": 314.65,
 * "amountInWords":
 * "Two Thousand One Hundred Eighty Two Rupees and Thirty Five Paisa Only",
 * "success": true,
 * "message": "Sales invoice created successfully"
 * }
 * 
 * 2. Database Schema Updates Required:
 * 
 * ALTER TABLE sales_invoice_items
 * ADD COLUMN discount_percent DECIMAL(5,2) DEFAULT 0.00,
 * ADD COLUMN discount_amount DECIMAL(10,2) DEFAULT 0.00;
 * 
 * 3. Key Features Implemented:
 * - Automatic invoice number generation (CFK-00001 format)
 * - Customer creation/finding by phone number
 * - Item-level discount calculations
 * - Automatic total calculations
 * - Payment status management
 * - Number to words conversion (Indian format)
 * - Transaction management
 * - Error handling and validation
 */
