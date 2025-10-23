# Units API Documentation

## Overview
This API provides endpoints to manage units in the CityFashion POS system. The units table stores different measurement units that can be used for products and inventory management.

## Database Schema
The `units` table has the following structure:
- `id` (BIGINT, AUTO_INCREMENT, PRIMARY KEY)
- `unit_name` (VARCHAR(100), NOT NULL) - Full name of the unit
- `unit_abbr` (VARCHAR(50), NOT NULL) - Abbreviation of the unit
- `label` (VARCHAR(150), NOT NULL) - Display label combining name and abbreviation
- `created_at` (TIMESTAMP, NOT NULL, DEFAULT CURRENT_TIMESTAMP)
- `updated_at` (TIMESTAMP, DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP)

## API Endpoints

### Base URL
```
/api/units
```

### 1. Get All Units
**GET** `/api/units/all`

Returns all units in the system.

**Response:**
```json
[
  {
    "id": 1,
    "unitName": "BAGS",
    "unitAbbr": "Bags",
    "label": "BAGS (Bags)",
    "createdAt": "2025-01-15T10:30:00",
    "updatedAt": "2025-01-15T10:30:00"
  },
  {
    "id": 2,
    "unitName": "PIECES",
    "unitAbbr": "Pcs",
    "label": "PIECES (Pcs)",
    "createdAt": "2025-01-15T10:31:00",
    "updatedAt": "2025-01-15T10:31:00"
  },
  {
    "id": 3,
    "unitName": "KILOGRAMS",
    "unitAbbr": "Kg",
    "label": "KILOGRAMS (Kg)",
    "createdAt": "2025-01-15T10:32:00",
    "updatedAt": "2025-01-15T10:32:00"
  }
]
```

### 2. Get Unit by ID
**GET** `/api/units/{id}`

Returns a specific unit by its ID.

**Parameters:**
- `id` (Long) - The ID of the unit

**Response:**
```json
{
  "id": 1,
  "unitName": "BAGS",
  "unitAbbr": "Bags",
  "label": "BAGS (Bags)",
  "createdAt": "2025-01-15T10:30:00",
  "updatedAt": "2025-01-15T10:30:00"
}
```

**Error Responses:**
- `404 Not Found` - Unit not found
- `500 Internal Server Error` - Server error

### 3. Get Unit by Name
**GET** `/api/units/name/{name}`

Returns a specific unit by its name.

**Parameters:**
- `name` (String) - The unit name

**Response:**
```json
{
  "id": 1,
  "unitName": "BAGS",
  "unitAbbr": "Bags",
  "label": "BAGS (Bags)",
  "createdAt": "2025-01-15T10:30:00",
  "updatedAt": "2025-01-15T10:30:00"
}
```

**Error Responses:**
- `404 Not Found` - Unit not found
- `500 Internal Server Error` - Server error

### 4. Get Unit by Abbreviation
**GET** `/api/units/abbr/{abbr}`

Returns a specific unit by its abbreviation.

**Parameters:**
- `abbr` (String) - The unit abbreviation

**Response:**
```json
{
  "id": 1,
  "unitName": "BAGS",
  "unitAbbr": "Bags",
  "label": "BAGS (Bags)",
  "createdAt": "2025-01-15T10:30:00",
  "updatedAt": "2025-01-15T10:30:00"
}
```

**Error Responses:**
- `404 Not Found` - Unit not found
- `500 Internal Server Error` - Server error

### 5. Create New Unit
**POST** `/api/units`

Creates a new unit.

**Request Body:**
```json
{
  "unitName": "CARTONS",
  "unitAbbr": "Ctn",
  "label": "CARTONS (Ctn)"
}
```

**Response:**
```json
{
  "id": 11,
  "unitName": "CARTONS",
  "unitAbbr": "Ctn",
  "label": "CARTONS (Ctn)",
  "createdAt": "2025-01-15T11:00:00",
  "updatedAt": "2025-01-15T11:00:00"
}
```

**Error Responses:**
- `400 Bad Request` - Invalid request data
- `500 Internal Server Error` - Server error

### 6. Update Unit
**PUT** `/api/units/{id}`

Updates an existing unit.

**Parameters:**
- `id` (Long) - The ID of the unit to update

**Request Body:**
```json
{
  "unitName": "BAGS",
  "unitAbbr": "Bags",
  "label": "BAGS (Bags) - Updated"
}
```

**Response:**
```json
{
  "id": 1,
  "unitName": "BAGS",
  "unitAbbr": "Bags",
  "label": "BAGS (Bags) - Updated",
  "createdAt": "2025-01-15T10:30:00",
  "updatedAt": "2025-01-15T11:15:00"
}
```

**Error Responses:**
- `400 Bad Request` - Invalid request data
- `500 Internal Server Error` - Server error

### 7. Delete Unit
**DELETE** `/api/units/{id}`

Deletes a unit.

**Parameters:**
- `id` (Long) - The ID of the unit to delete

**Response:**
- `200 OK` - Unit deleted successfully

**Error Responses:**
- `400 Bad Request` - Invalid request or unit not found
- `500 Internal Server Error` - Server error

## Sample Data
The following sample data is included in the database:

| ID | Unit Name | Unit Abbr | Label |
|----|-----------|-----------|-------|
| 1  | BAGS | Bags | BAGS (Bags) |
| 2  | PIECES | Pcs | PIECES (Pcs) |
| 3  | KILOGRAMS | Kg | KILOGRAMS (Kg) |
| 4  | GRAMS | Gm | GRAMS (Gm) |
| 5  | LITERS | L | LITERS (L) |
| 6  | METERS | M | METERS (M) |
| 7  | CENTIMETERS | Cm | CENTIMETERS (Cm) |
| 8  | DOZEN | Dz | DOZEN (Dz) |
| 9  | PAIRS | Prs | PAIRS (Prs) |
| 10 | BOXES | Box | BOXES (Box) |

## Implementation Details

### Files Created/Modified:
1. **Entity**: `UnitsEntity.java` - JPA entity for the units table
2. **Repository**: `UnitsRepository.java` - Data access layer
3. **Service Interface**: `UnitsService.java` - Business logic interface
4. **Service Implementation**: `UnitsServiceImpl.java` - Business logic implementation
5. **Controller**: `UnitsController.java` - REST API endpoints
6. **SQL Script**: `units_table.sql` - Database schema and sample data
7. **Test**: `UnitsApiTest.java` - API usage examples

### Key Features:
- Automatic timestamp management (`@PrePersist`, `@PreUpdate`)
- Multiple search options (by ID, name, abbreviation)
- Comprehensive CRUD operations
- Error handling and validation
- Cross-origin support for frontend integration
- Transaction management for data consistency

### Usage in Frontend:
The API can be easily integrated into frontend applications to:
- Display unit options in dropdowns
- Manage unit configurations
- Validate unit names and abbreviations
- Support product management with different units
- Display formatted unit labels

## Testing
To test the API endpoints, you can use tools like Postman, curl, or any HTTP client:

```bash
# Get all units
curl -X GET http://localhost:8080/api/units/all

# Get unit by ID
curl -X GET http://localhost:8080/api/units/1

# Get unit by name
curl -X GET http://localhost:8080/api/units/name/BAGS

# Get unit by abbreviation
curl -X GET http://localhost:8080/api/units/abbr/Bags

# Create new unit
curl -X POST http://localhost:8080/api/units \
  -H "Content-Type: application/json" \
  -d '{"unitName": "CARTONS", "unitAbbr": "Ctn", "label": "CARTONS (Ctn)"}'
```
