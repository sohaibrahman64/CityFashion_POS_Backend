# New Sales Invoice Backend Implementation

This document describes the backend implementation for the New Sales Invoice functionality, which corresponds to the frontend React component `NewSalesNew.js`.

## Overview

The backend implementation provides a complete REST API for creating sales invoices with the following features:
- Customer management (create/find by phone)
- Item management with quantity, price, and discount
- Automatic calculations (subtotal, discount, total, balance)
- Invoice number generation
- Payment status management
- Number to words conversion (Indian format)

## Architecture

The implementation follows a layered architecture pattern:

```
Controller → Service → Repository → Entity
    ↓           ↓         ↓         ↓
HTTP Layer  Business   Data      Database
           Logic      Access    Schema
```

## Components

### 1. DTOs (Data Transfer Objects)

#### NewSalesInvoiceRequest
- `customerName`: Customer's name
- `customerPhone`: Customer's phone number
- `items`: List of invoice items
- `receivedAmount`: Amount received from customer
- `isFullyReceived`: Boolean indicating if full payment received

#### NewSalesInvoiceResponse
- Complete invoice details including calculated totals
- Amount in words (Indian format)
- Success/error status and messages

### 2. Entities

#### InvoiceEntity (Updated)
- Core invoice information
- Customer details
- Financial totals
- Payment status

#### InvoiceItemEntity (Enhanced)
- **NEW**: `discountPercent` - Percentage discount
- **NEW**: `discountAmount` - Calculated discount amount
- Item details (quantity, price, total)

### 3. Service Layer

#### NewSalesInvoiceService
- **createNewSalesInvoice()**: Main business logic
- **generateInvoiceNumber()**: Auto-generates CFK-00001 format
- **createOrFindCustomer()**: Customer management
- **getPaymentStatus()**: Payment status logic

### 4. Controller

#### NewSalesInvoiceController
- **POST** `/api/new-sales-invoice/create`: Create new invoice
- Cross-origin support for frontend integration
- Error handling and response formatting

### 5. Utilities

#### NumberToWordsConverter
- Converts numbers to Indian English words
- Supports Crore, Lakh, Thousand format
- Handles Rupees and Paisa

## API Endpoints

### Create New Sales Invoice
```
POST /api/new-sales-invoice/create
Content-Type: application/json
```

#### Request Body Example
```json
{
  "customerName": "John Doe",
  "customerPhone": "9876543210",
  "items": [
    {
      "id": 1,
      "itemName": "Cotton T-Shirt",
      "quantity": 2,
      "price": 599.00,
      "discount": 10.0,
      "total": 1078.20,
      "productId": 101
    }
  ],
  "receivedAmount": 2000.00,
  "isFullyReceived": false
}
```

#### Response Example
```json
{
  "invoiceId": 123,
  "invoiceNumber": "CFK-00123",
  "invoiceDate": "2025-01-15",
  "customerName": "John Doe",
  "customerPhone": "9876543210",
  "items": [...],
  "subtotalAmount": 1198.00,
  "totalDiscountAmount": 119.80,
  "totalAmount": 1078.20,
  "receivedAmount": 2000.00,
  "balanceAmount": -921.80,
  "savedAmount": 119.80,
  "amountInWords": "One Thousand Seventy Eight Rupees and Twenty Paisa Only",
  "success": true,
  "message": "Sales invoice created successfully"
}
```

## Database Schema Updates

### Required Table Alterations

```sql
-- Add discount fields to sales_invoice_items table
ALTER TABLE sales_invoice_items 
ADD COLUMN discount_percent DECIMAL(5,2) DEFAULT 0.00,
ADD COLUMN discount_amount DECIMAL(10,2) DEFAULT 0.00;
```

### Table Structure

#### sales_invoice
- `id`: Primary key
- `invoice_number`: Unique invoice number (CFK-00001)
- `customer_id`: Reference to customers table
- `invoice_date`: Date of invoice
- `subtotal_amount`: Sum of all items before discount
- `discount_amount`: Total discount amount
- `total_amount`: Final amount after discount
- `paid_amount`: Amount received
- `due_amount`: Balance amount
- `payment_status_id`: Reference to payment status

#### sales_invoice_items
- `id`: Primary key
- `invoice_id`: Reference to sales_invoice
- `product_id`: Reference to products table
- `quantity`: Item quantity
- `price`: Unit price
- `discount_percent`: Percentage discount
- `discount_amount`: Calculated discount amount
- `total`: Final item total

## Business Logic

### 1. Invoice Number Generation
- Format: `CFK-{5-digit-sequence}`
- Example: CFK-00001, CFK-00002, etc.
- Auto-increments based on latest invoice ID

### 2. Customer Management
- Searches existing customers by phone number
- Creates new customer if not found
- Updates customer name if changed

### 3. Calculations
- **Subtotal**: Sum of (quantity × price) for all items
- **Discount Amount**: Sum of individual item discounts
- **Total Amount**: Subtotal - Total Discount
- **Balance**: Total Amount - Received Amount

### 4. Payment Status
- **Paid**: Full amount received
- **Partially Paid**: Partial amount received
- **Pending**: No amount received

### 5. Discount Handling
- Item-level percentage discounts
- Automatic calculation of discount amounts
- Support for 0% discount (no discount)

## Error Handling

### Service Layer
- Try-catch blocks for database operations
- Validation of input data
- Meaningful error messages

### Controller Layer
- HTTP status code management
- Error response formatting
- Exception handling

## Security Considerations

- Input validation and sanitization
- SQL injection prevention (JPA/Hibernate)
- Cross-origin resource sharing (CORS) configuration
- Transaction management for data integrity

## Testing

### Manual Testing
- Use the provided `NewSalesInvoiceTest.java` for testing
- Test with various scenarios (full payment, partial payment, no discount, etc.)

### API Testing
- Use Postman or similar tools
- Test with valid and invalid data
- Verify response formats and calculations

## Integration with Frontend

### Frontend Requirements
- Send POST request to `/api/new-sales-invoice/create`
- Include all required fields in request body
- Handle success/error responses appropriately

### Data Flow
1. Frontend collects invoice data
2. Sends POST request to backend
3. Backend processes and validates data
4. Creates/updates database records
5. Returns formatted response
6. Frontend displays success/error message

## Future Enhancements

### Potential Improvements
- Product inventory validation
- Tax calculation support
- Multiple payment methods
- Invoice templates
- PDF generation
- Email functionality
- Barcode integration
- Customer loyalty programs

### Performance Optimizations
- Database indexing on frequently queried fields
- Caching for product and customer data
- Batch processing for multiple invoices
- Connection pooling optimization

## Deployment

### Prerequisites
- Java 17+
- Spring Boot 2.7.18
- MySQL 8.0+
- Maven 3.6+

### Build and Run
```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### Configuration
- Update `application.properties` with database credentials
- Configure CORS settings if needed
- Set appropriate logging levels

## Support

For issues or questions regarding this implementation:
1. Check the logs for error details
2. Verify database schema matches requirements
3. Ensure all dependencies are properly configured
4. Test with sample data to isolate issues

## Conclusion

This backend implementation provides a robust, scalable solution for the New Sales Invoice functionality. It follows Spring Boot best practices and includes comprehensive error handling, validation, and business logic to support the frontend requirements.
