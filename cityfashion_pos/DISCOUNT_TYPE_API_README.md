# Discount Type API Documentation

## Overview
This API provides endpoints to manage discount types in the CityFashion POS system. The discount type table stores different types of discount configurations that can be applied to products and transactions.

## Database Schema
The `discount_type` table has the following structure:
- `id` (BIGINT, AUTO_INCREMENT, PRIMARY KEY)
- `discount_type` (VARCHAR(100), NOT NULL) - Human-readable discount type name
- `discount_type_code` (VARCHAR(50), NOT NULL, UNIQUE) - Unique code for the discount type
- `created_at` (TIMESTAMP, NOT NULL, DEFAULT CURRENT_TIMESTAMP)
- `updated_at` (TIMESTAMP, DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP)

## API Endpoints

### Base URL
```
/api/discount-types
```

### 1. Get All Discount Types
**GET** `/api/discount-types/all`

Returns all discount types in the system.

**Response:**
```json
[
  {
    "id": 1,
    "discountType": "Percentage",
    "discountTypeCode": "PERCENTAGE",
    "createdAt": "2025-01-15T10:30:00",
    "updatedAt": "2025-01-15T10:30:00"
  },
  {
    "id": 2,
    "discountType": "Fixed Amount",
    "discountTypeCode": "FIXED_AMOUNT",
    "createdAt": "2025-01-15T10:31:00",
    "updatedAt": "2025-01-15T10:31:00"
  },
  {
    "id": 3,
    "discountType": "Buy One Get One",
    "discountTypeCode": "BOGO",
    "createdAt": "2025-01-15T10:32:00",
    "updatedAt": "2025-01-15T10:32:00"
  }
]
```

### 2. Get Discount Type by ID
**GET** `/api/discount-types/{id}`

Returns a specific discount type by its ID.

**Parameters:**
- `id` (Long) - The ID of the discount type

**Response:**
```json
{
  "id": 1,
  "discountType": "Percentage",
  "discountTypeCode": "PERCENTAGE",
  "createdAt": "2025-01-15T10:30:00",
  "updatedAt": "2025-01-15T10:30:00"
}
```

**Error Responses:**
- `404 Not Found` - Discount type not found
- `500 Internal Server Error` - Server error

### 3. Get Discount Type by Code
**GET** `/api/discount-types/code/{code}`

Returns a specific discount type by its code.

**Parameters:**
- `code` (String) - The discount type code

**Response:**
```json
{
  "id": 1,
  "discountType": "Percentage",
  "discountTypeCode": "PERCENTAGE",
  "createdAt": "2025-01-15T10:30:00",
  "updatedAt": "2025-01-15T10:30:00"
}
```

**Error Responses:**
- `404 Not Found` - Discount type not found
- `500 Internal Server Error` - Server error

### 4. Create New Discount Type
**POST** `/api/discount-types`

Creates a new discount type.

**Request Body:**
```json
{
  "discountType": "Seasonal Discount",
  "discountTypeCode": "SEASONAL"
}
```

**Response:**
```json
{
  "id": 6,
  "discountType": "Seasonal Discount",
  "discountTypeCode": "SEASONAL",
  "createdAt": "2025-01-15T11:00:00",
  "updatedAt": "2025-01-15T11:00:00"
}
```

**Error Responses:**
- `400 Bad Request` - Invalid request data
- `500 Internal Server Error` - Server error

### 5. Update Discount Type
**PUT** `/api/discount-types/{id}`

Updates an existing discount type.

**Parameters:**
- `id` (Long) - The ID of the discount type to update

**Request Body:**
```json
{
  "discountType": "Percentage (Updated)",
  "discountTypeCode": "PERCENTAGE"
}
```

**Response:**
```json
{
  "id": 1,
  "discountType": "Percentage (Updated)",
  "discountTypeCode": "PERCENTAGE",
  "createdAt": "2025-01-15T10:30:00",
  "updatedAt": "2025-01-15T11:15:00"
}
```

**Error Responses:**
- `400 Bad Request` - Invalid request data
- `500 Internal Server Error` - Server error

### 6. Delete Discount Type
**DELETE** `/api/discount-types/{id}`

Deletes a discount type.

**Parameters:**
- `id` (Long) - The ID of the discount type to delete

**Response:**
- `200 OK` - Discount type deleted successfully

**Error Responses:**
- `400 Bad Request` - Invalid request or discount type not found
- `500 Internal Server Error` - Server error

## Sample Data
The following sample data is included in the database:

| ID | Discount Type | Discount Type Code |
|----|---------------|-------------------|
| 1  | Percentage | PERCENTAGE |
| 2  | Fixed Amount | FIXED_AMOUNT |
| 3  | Buy One Get One | BOGO |
| 4  | Buy Two Get One | BTGO |
| 5  | No Discount | NO_DISCOUNT |

## Implementation Details

### Files Created/Modified:
1. **Entity**: `DiscountTypeEntity.java` - JPA entity for the discount_type table
2. **Repository**: `DiscountTypeRepository.java` - Data access layer
3. **Service Interface**: `DiscountTypeService.java` - Business logic interface
4. **Service Implementation**: `DiscountTypeServiceImpl.java` - Business logic implementation
5. **Controller**: `DiscountTypeController.java` - REST API endpoints
6. **SQL Script**: `discount_type_table.sql` - Database schema and sample data
7. **Test**: `DiscountTypeApiTest.java` - API usage examples

### Key Features:
- Automatic timestamp management (`@PrePersist`, `@PreUpdate`)
- Unique constraint on `discount_type_code`
- Comprehensive CRUD operations
- Error handling and validation
- Cross-origin support for frontend integration
- Transaction management for data consistency

### Usage in Frontend:
The API can be easily integrated into frontend applications to:
- Display discount type options in dropdowns
- Manage discount type configurations
- Validate discount type codes
- Support discount calculations based on discount types

## Testing
To test the API endpoints, you can use tools like Postman, curl, or any HTTP client:

```bash
# Get all discount types
curl -X GET http://localhost:8080/api/discount-types/all

# Get discount type by ID
curl -X GET http://localhost:8080/api/discount-types/1

# Get discount type by code
curl -X GET http://localhost:8080/api/discount-types/code/PERCENTAGE

# Create new discount type
curl -X POST http://localhost:8080/api/discount-types \
  -H "Content-Type: application/json" \
  -d '{"discountType": "Custom Discount", "discountTypeCode": "CUSTOM"}'
```
