# Sales Transactions Backend Implementation

This document describes the comprehensive backend implementation for the Sales Transactions functionality, which provides complete REST APIs for sales transaction management, reporting, and analytics.

## Overview

The Sales Transactions system provides:
- **Transaction Management**: Create, read, update, delete sales transactions
- **Financial Calculations**: Total Sales Amount, Received Amount, Balance Amount
- **Analytics**: Percentage change vs last month, period comparisons
- **Reporting**: Current month, last month, current quarter, current year, custom date ranges
- **Dashboard**: Comprehensive sales dashboard with key metrics

## Architecture

The implementation follows a layered architecture pattern:

```
Controller → Service → Repository → Entity
    ↓           ↓         ↓         ↓
HTTP Layer  Business   Data      Database
           Logic      Access    Schema
```

## Components

### 1. Database Schema

#### MySQL Table: `sales_transactions`
- **Primary fields**: transaction_number, invoice_id, customer_id, transaction_date
- **Financial fields**: total_amount, tax_amount, discount_amount, net_amount, received_amount, balance_amount
- **Status fields**: payment_status, transaction_type, status
- **Audit fields**: created_at, updated_at, created_by, updated_by
- **Analytics fields**: profit_margin, cost_of_goods_sold

#### Database Views
- `monthly_sales_summary`: Aggregated monthly sales data
- `daily_sales_summary`: Daily sales aggregations

### 2. Entities

#### SalesTransactionEntity
- Complete transaction information with JPA annotations
- Business logic methods for calculations:
  - `calculatePaymentStatus()`: Auto-determines payment status based on amounts
  - `calculateNetAmount()`: Computes net amount after tax and discount
  - `calculateProfitMargin()`: Calculates profit based on COGS
- Relationships with Customer and Invoice entities

#### Enums
- **PaymentStatus**: PAID, PARTIAL, UNPAID
- **TransactionType**: SALE, RETURN, REFUND, ADJUSTMENT (extended existing enum)

### 3. DTOs (Data Transfer Objects)

#### SalesTransactionRequest
- Input validation with Jakarta validation annotations
- Required fields: totalAmount, netAmount, receivedAmount, transactionDate
- Optional fields: customerId, invoiceId, paymentMode, notes

#### SalesTransactionResponse
- Complete transaction details
- Success/error handling with messages
- All entity fields mapped for API responses

#### SalesTransactionSummaryResponse
- Aggregated financial metrics
- Percentage change calculations
- Period comparison data
- Payment status breakdowns

### 4. Repository Layer

#### SalesTransactionRepository
- Extends JpaRepository with custom query methods
- **Date-based queries**: Current month, last month, quarter, year, custom ranges
- **Financial calculations**: Sum queries for amounts by period
- **Analytics queries**: Percentage changes, transaction counts
- **Customer queries**: Transactions by customer, outstanding balances
- **Native SQL**: Used for complex date functions (DATE_SUB, etc.)

#### Key Query Methods
```java
// Period-based transaction retrieval
List<SalesTransactionEntity> findCurrentMonthTransactions()
List<SalesTransactionEntity> findLastMonthTransactions()
List<SalesTransactionEntity> findCurrentQuarterTransactions()
List<SalesTransactionEntity> findCurrentYearTransactions()

// Financial calculations
BigDecimal getCurrentMonthTotalSales()
BigDecimal getLastMonthTotalSales()
BigDecimal getCurrentMonthTotalReceived()
BigDecimal getCurrentMonthTotalBalance()

// Analytics
Long getCurrentMonthTransactionCount()
Long getLastMonthTransactionCount()
```

### 5. Service Layer

#### SalesTransactionServiceImpl
- **Business Logic**: Transaction creation, updates, calculations
- **Data Transformation**: Entity ↔ DTO mapping
- **Error Handling**: Comprehensive exception handling with logging
- **Analytics**: Percentage change calculations, summary generation
- **Integration**: Create transactions from existing invoices

#### Key Service Methods
```java
// CRUD Operations
SalesTransactionResponse createSalesTransaction(SalesTransactionRequest request)
SalesTransactionResponse updateSalesTransaction(Long id, SalesTransactionRequest request)
boolean deleteSalesTransaction(Long id)

// Period-based retrieval
List<SalesTransactionResponse> getCurrentMonthTransactions()
List<SalesTransactionResponse> getTransactionsByDateRange(LocalDate start, LocalDate end)

// Summary and Analytics
SalesTransactionSummaryResponse getCurrentMonthSummary()
BigDecimal calculatePercentageChangeVsLastMonth(BigDecimal current, BigDecimal last)

// Integration
SalesTransactionResponse createTransactionFromInvoice(Long invoiceId)
```

### 6. Controller Layer

#### SalesTransactionsController
- **RESTful APIs**: Complete CRUD operations
- **Period Endpoints**: Multiple date range options
- **Summary Endpoints**: Financial summaries and analytics
- **Dashboard Endpoint**: Comprehensive sales dashboard
- **Error Handling**: HTTP status codes with meaningful error messages

## API Endpoints

### Transaction Management
- `POST /api/sales-transactions/create` - Create new transaction
- `GET /api/sales-transactions/all` - Get all transactions
- `GET /api/sales-transactions/{id}` - Get transaction by ID
- `GET /api/sales-transactions/number/{transactionNumber}` - Get by transaction number
- `PUT /api/sales-transactions/{id}` - Update transaction
- `DELETE /api/sales-transactions/{id}` - Delete transaction

### Period-based Retrieval
- `GET /api/sales-transactions/current-month` - Current month transactions
- `GET /api/sales-transactions/last-month` - Last month transactions
- `GET /api/sales-transactions/current-quarter` - Current quarter transactions
- `GET /api/sales-transactions/current-year` - Current year transactions
- `GET /api/sales-transactions/date-range?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD` - Custom date range

### Financial Summaries
- `GET /api/sales-transactions/summary/current-month` - Current month summary
- `GET /api/sales-transactions/summary/last-month` - Last month summary
- `GET /api/sales-transactions/summary/current-quarter` - Current quarter summary
- `GET /api/sales-transactions/summary/current-year` - Current year summary
- `GET /api/sales-transactions/summary/date-range?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD` - Custom summary

### Utility Endpoints
- `GET /api/sales-transactions/generate-number` - Generate new transaction number
- `POST /api/sales-transactions/create-from-invoice/{invoiceId}` - Create from existing invoice
- `GET /api/sales-transactions/outstanding-balance` - Transactions with outstanding balance
- `GET /api/sales-transactions/customer/{customerId}` - Customer transactions
- `GET /api/sales-transactions/dashboard` - Comprehensive sales dashboard

## Key Features

### 1. Financial Calculations
- **Total Sales Amount**: Sum of all transaction amounts
- **Received Amount**: Total payments received
- **Balance Amount**: Outstanding balance (net_amount - received_amount)
- **Percentage Change**: Comparison with previous period

### 2. Payment Status Management
- **Automatic calculation** based on received vs net amounts
- **PAID**: Balance = 0
- **PARTIAL**: Balance > 0 and Received > 0
- **UNPAID**: Received = 0

### 3. Period Analysis
- Current month vs last month comparison
- Quarter and yearly aggregations
- Custom date range analysis
- Percentage change calculations

### 4. Integration Features
- Create transactions from existing invoices
- Customer relationship mapping
- Audit trail with created/updated timestamps

## Sample API Requests

### Create Transaction
```json
POST /api/sales-transactions/create
{
  "totalAmount": 1000.00,
  "taxAmount": 180.00,
  "discountAmount": 50.00,
  "netAmount": 1130.00,
  "receivedAmount": 1130.00,
  "balanceAmount": 0.00,
  "customerId": 1,
  "customerName": "John Doe",
  "paymentMode": "CASH",
  "itemCount": 3,
  "totalQuantity": 5.00,
  "notes": "Complete payment received"
}
```

### Sample Response
```json
{
  "id": 1,
  "transactionNumber": "ST-00001",
  "transactionDate": "2024-01-15",
  "totalAmount": 1000.00,
  "netAmount": 1130.00,
  "receivedAmount": 1130.00,
  "balanceAmount": 0.00,
  "paymentStatus": "PAID",
  "success": true,
  "message": "Sales transaction created successfully"
}
```

### Summary Response
```json
GET /api/sales-transactions/summary/current-month
{
  "totalSalesAmount": 25000.00,
  "totalReceivedAmount": 22000.00,
  "totalBalanceAmount": 3000.00,
  "percentageChangeVsLastMonth": 15.50,
  "totalTransactions": 45,
  "period": "Current Month",
  "averageTransactionValue": 555.56,
  "paidTransactions": 35,
  "partialTransactions": 8,
  "unpaidTransactions": 2,
  "success": true
}
```

## Error Handling

- **Validation Errors**: 400 Bad Request with validation details
- **Not Found**: 404 with meaningful error messages
- **Server Errors**: 500 with error logging
- **Business Logic Errors**: Appropriate HTTP codes with descriptive messages

## Performance Considerations

- **Database Indexes**: Optimized indexes on transaction_date, customer_id, payment_status
- **Native Queries**: Used for complex date calculations
- **Lazy Loading**: JPA relationships configured for optimal performance
- **Connection Pooling**: HikariCP configuration in application properties

## Security

- **Input Validation**: Jakarta validation on all request DTOs
- **SQL Injection Prevention**: Parameterized queries and JPA
- **Error Information**: Sensitive information not exposed in error messages

## Testing

The implementation includes comprehensive error handling and logging for testing:
- **Unit Testing**: Service layer business logic
- **Integration Testing**: Controller endpoints with MockMvc
- **Database Testing**: Repository layer with @DataJpaTest

## Future Enhancements

1. **Caching**: Redis integration for frequently accessed summaries
2. **Reporting**: Export to PDF/Excel functionality
3. **Notifications**: Alert system for overdue payments
4. **Analytics**: Advanced reporting with charts and graphs
5. **Audit**: Detailed audit trail for all transaction changes

## Dependencies

- Spring Boot 2.x
- Spring Data JPA
- MySQL 8.x
- Jakarta Validation
- SLF4J Logging

This implementation provides a robust, scalable foundation for sales transaction management with comprehensive reporting and analytics capabilities.
