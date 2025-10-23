# Tax Type API Documentation

## Overview
This API provides endpoints to manage tax types in the CityFashion POS system. The tax type table stores different types of tax configurations that can be applied to products and transactions.

## Database Schema
The `tax_type` table has the following structure:
- `id` (BIGINT, AUTO_INCREMENT, PRIMARY KEY)
- `tax_type` (VARCHAR(100), NOT NULL) - Human-readable tax type name
- `tax_type_code` (VARCHAR(50), NOT NULL, UNIQUE) - Unique code for the tax type
- `created_at` (TIMESTAMP, NOT NULL, DEFAULT CURRENT_TIMESTAMP)
- `updated_at` (TIMESTAMP, DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP)

## API Endpoints

### Base URL
```
/api/tax-types
```

### 1. Get All Tax Types
**GET** `/api/tax-types`

Returns all tax types in the system.

**Response:**
```json
[
  {
    "id": 1,
    "taxType": "With Tax",
    "taxTypeCode": "WITH_TAX",
    "createdAt": "2025-01-15T10:30:00",
    "updatedAt": "2025-01-15T10:30:00"
  },
  {
    "id": 2,
    "taxType": "Without Tax",
    "taxTypeCode": "WITHOUT_TAX",
    "createdAt": "2025-01-15T10:31:00",
    "updatedAt": "2025-01-15T10:31:00"
  }
]
```

### 2. Get Tax Type by ID
**GET** `/api/tax-types/{id}`

Returns a specific tax type by its ID.

**Parameters:**
- `id` (Long) - The ID of the tax type

**Response:**
```json
{
  "id": 1,
  "taxType": "With Tax",
  "taxTypeCode": "WITH_TAX",
  "createdAt": "2025-01-15T10:30:00",
  "updatedAt": "2025-01-15T10:30:00"
}
```

**Error Responses:**
- `404 Not Found` - Tax type not found
- `500 Internal Server Error` - Server error

### 3. Get Tax Type by Code
**GET** `/api/tax-types/code/{code}`

Returns a specific tax type by its code.

**Parameters:**
- `code` (String) - The tax type code

**Response:**
```json
{
  "id": 1,
  "taxType": "With Tax",
  "taxTypeCode": "WITH_TAX",
  "createdAt": "2025-01-15T10:30:00",
  "updatedAt": "2025-01-15T10:30:00"
}
```

**Error Responses:**
- `404 Not Found` - Tax type not found
- `500 Internal Server Error` - Server error

### 4. Create New Tax Type
**POST** `/api/tax-types`

Creates a new tax type.

**Request Body:**
```json
{
  "taxType": "Zero Tax",
  "taxTypeCode": "ZERO_TAX"
}
```

**Response:**
```json
{
  "id": 3,
  "taxType": "Zero Tax",
  "taxTypeCode": "ZERO_TAX",
  "createdAt": "2025-01-15T11:00:00",
  "updatedAt": "2025-01-15T11:00:00"
}
```

**Error Responses:**
- `400 Bad Request` - Invalid request data
- `500 Internal Server Error` - Server error

### 5. Update Tax Type
**PUT** `/api/tax-types/{id}`

Updates an existing tax type.

**Parameters:**
- `id` (Long) - The ID of the tax type to update

**Request Body:**
```json
{
  "taxType": "With Tax (Updated)",
  "taxTypeCode": "WITH_TAX"
}
```

**Response:**
```json
{
  "id": 1,
  "taxType": "With Tax (Updated)",
  "taxTypeCode": "WITH_TAX",
  "createdAt": "2025-01-15T10:30:00",
  "updatedAt": "2025-01-15T11:15:00"
}
```

**Error Responses:**
- `400 Bad Request` - Invalid request data
- `500 Internal Server Error` - Server error

### 6. Delete Tax Type
**DELETE** `/api/tax-types/{id}`

Deletes a tax type.

**Parameters:**
- `id` (Long) - The ID of the tax type to delete

**Response:**
- `200 OK` - Tax type deleted successfully

**Error Responses:**
- `400 Bad Request` - Invalid request or tax type not found
- `500 Internal Server Error` - Server error

## Sample Data
The following sample data is included in the database:

| ID | Tax Type | Tax Type Code |
|----|----------|---------------|
| 1  | With Tax | WITH_TAX |
| 2  | Without Tax | WITHOUT_TAX |
| 3  | Zero Tax | ZERO_TAX |
| 4  | Exempt Tax | EXEMPT_TAX |
| 5  | Reverse Tax | REVERSE_TAX |

## Implementation Details

### Files Created/Modified:
1. **Entity**: `TaxTypeEntity.java` - JPA entity for the tax_type table
2. **Repository**: `TaxTypeRepository.java` - Data access layer
3. **Service Interface**: `TaxTypeService.java` - Business logic interface
4. **Service Implementation**: `TaxTypeServiceImpl.java` - Business logic implementation
5. **Controller**: `TaxTypeController.java` - REST API endpoints
6. **SQL Script**: `tax_type_table.sql` - Database schema and sample data
7. **Test**: `TaxTypeApiTest.java` - API usage examples

### Key Features:
- Automatic timestamp management (`@PrePersist`, `@PreUpdate`)
- Unique constraint on `tax_type_code`
- Comprehensive CRUD operations
- Error handling and validation
- Cross-origin support for frontend integration
- Transaction management for data consistency

### Usage in Frontend:
The API can be easily integrated into frontend applications to:
- Display tax type options in dropdowns
- Manage tax type configurations
- Validate tax type codes
- Support tax calculations based on tax types

## Testing
To test the API endpoints, you can use tools like Postman, curl, or any HTTP client:

```bash
# Get all tax types
curl -X GET http://localhost:8080/api/tax-types

# Get tax type by ID
curl -X GET http://localhost:8080/api/tax-types/1

# Get tax type by code
curl -X GET http://localhost:8080/api/tax-types/code/WITH_TAX

# Create new tax type
curl -X POST http://localhost:8080/api/tax-types \
  -H "Content-Type: application/json" \
  -d '{"taxType": "Custom Tax", "taxTypeCode": "CUSTOM_TAX"}'
```
