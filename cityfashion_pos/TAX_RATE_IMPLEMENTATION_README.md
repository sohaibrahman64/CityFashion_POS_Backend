# Dynamic Tax Rate Selection Implementation

This document describes the implementation of dynamic tax rate selection during invoice item creation in the CityFashion POS Backend.

## Overview

The system now supports dynamic tax rate selection for invoice items, allowing users to:
- Select from predefined tax types (GST, IGST with various rates)
- Apply custom tax percentages
- Automatically calculate tax amounts based on selected tax types
- View all available tax rates via API

## Implementation Details

### 1. Data Transfer Objects (DTOs)

#### NewSalesInvoiceItemRequest
Added new fields for tax handling:
- `taxType`: String - The tax type (e.g., "GST_18", "IGST_12")
- `taxPercent`: Double - Custom tax percentage (optional)
- `taxAmount`: Double - Calculated tax amount (optional)

#### NewSalesInvoiceItemResponse
Added corresponding response fields:
- `taxType`: String - The applied tax type
- `taxPercent`: Double - The tax percentage used
- `taxAmount`: Double - The calculated tax amount

#### NewSalesInvoiceResponse
Added:
- `totalTaxAmount`: Double - Total tax amount for the entire invoice

### 2. Entity Updates

#### NewSalesInvoiceItemEntity
Added database fields:
- `taxType`: String - Tax type stored in database
- `taxAmount`: Double - Tax amount stored in database

### 3. Service Layer

#### TaxRateService Interface
Provides methods for:
- `getAllTaxRates()`: Get all available tax rates
- `getTaxPercentage(String taxType)`: Get percentage for specific tax type
- `calculateTaxAmount(String taxType, Double baseAmount)`: Calculate tax amount
- `getAvailableTaxTypes()`: Get list of available tax types
- `isValidTaxType(String taxType)`: Validate tax type

#### TaxRateServiceImpl
Implementation with predefined tax rates:
- NONE: 0%
- GST_0/IGST_0: 0%
- GST_025/IGST_025: 0.25%
- GST_3/IGST_3: 3%
- GST_5/IGST_5: 5%
- GST_12/IGST_12: 12%
- GST_18/IGST_18: 18%
- GST_28/IGST_28: 28%
- EXEMPT: 0%

#### NewSalesInvoiceService Updates
Enhanced item processing logic:
1. Calculate item subtotal (quantity × price)
2. Apply discount if specified
3. Calculate tax based on tax type or custom percentage
4. Calculate final item total (subtotal - discount + tax)
5. Update running totals including tax amounts

### 4. API Endpoints

#### GET /api/new-sales-invoice/tax-rates
Returns available tax rates and tax types.

**Response:**
```json
{
  "taxRates": {
    "GST_18": 18.0,
    "IGST_12": 12.0,
    "NONE": 0.0
  },
  "availableTaxTypes": ["NONE", "GST_0", "IGST_0", ...],
  "success": true
}
```

#### POST /api/new-sales-invoice/create (Enhanced)
Now accepts tax information in item requests.

**Request Example:**
```json
{
  "customerName": "John Doe",
  "customerPhone": "9876543210",
  "items": [
    {
      "itemName": "Cotton T-Shirt",
      "quantity": 2,
      "price": 599.00,
      "discount": 10.0,
      "taxType": "GST_18",
      "productId": 101
    }
  ],
  "receivedAmount": 2000.00
}
```

**Response Example:**
```json
{
  "invoiceId": 123,
  "invoiceNumber": "RS-00123",
  "subtotalAmount": 1198.00,
  "totalDiscountAmount": 119.80,
  "totalTaxAmount": 194.20,
  "totalAmount": 1272.40,
  "items": [
    {
      "itemName": "Cotton T-Shirt",
      "quantity": 2,
      "price": 599.00,
      "discount": 10.0,
      "discountAmount": 119.80,
      "taxType": "GST_18",
      "taxPercent": 18.0,
      "taxAmount": 194.20,
      "total": 1272.40
    }
  ],
  "success": true
}
```

### 5. Database Schema

#### Required Updates
Run the SQL script `database_schema_tax_updates.sql`:

```sql
ALTER TABLE new_sales_invoice_items 
ADD COLUMN tax_type VARCHAR(20) DEFAULT NULL,
ADD COLUMN tax_amount DECIMAL(10,2) DEFAULT 0.00;
```

## Usage Examples

### 1. Using Predefined Tax Types
```json
{
  "itemName": "Product A",
  "quantity": 1,
  "price": 1000.00,
  "taxType": "GST_18"
}
```
Result: 18% GST applied automatically

### 2. Using Custom Tax Percentage
```json
{
  "itemName": "Product B",
  "quantity": 1,
  "price": 1000.00,
  "taxPercent": 15.0
}
```
Result: 15% custom tax applied

### 3. No Tax
```json
{
  "itemName": "Product C",
  "quantity": 1,
  "price": 1000.00,
  "taxType": "NONE"
}
```
Result: No tax applied

## Tax Calculation Logic

1. **Item Subtotal** = Quantity × Price
2. **Discount Amount** = Subtotal × (Discount% / 100)
3. **Amount After Discount** = Subtotal - Discount Amount
4. **Tax Amount** = Amount After Discount × (Tax% / 100)
5. **Item Total** = Amount After Discount + Tax Amount

## Error Handling

- Invalid tax types are handled gracefully (defaults to 0%)
- Missing tax information defaults to no tax
- All calculations are validated for numeric values
- Database constraints ensure data integrity

## Future Enhancements

1. **Tax Configuration Management**: Admin interface to modify tax rates
2. **Tax Reports**: Generate tax-specific reports
3. **Tax Exemptions**: Support for tax-exempt customers
4. **Multi-currency Support**: Different tax rates for different currencies
5. **Tax Audit Trail**: Track tax changes and modifications

## Testing

The implementation includes comprehensive error handling and validation. Test with various scenarios:
- Valid tax types
- Invalid tax types
- Custom tax percentages
- Mixed tax scenarios
- Edge cases (zero amounts, null values)

## Dependencies

- Spring Boot 2.x
- JPA/Hibernate
- MySQL/PostgreSQL database
- No additional external dependencies required
