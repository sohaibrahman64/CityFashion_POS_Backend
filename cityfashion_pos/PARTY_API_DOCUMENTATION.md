# Party Management API Documentation

## Overview
This document describes the REST API endpoints for managing parties (customers/suppliers) in the CityFashion POS system.

## Base URL
```
http://localhost:8080/api/parties
```

---

## API Endpoints

### 1. Create Party
Create a new party with all required and optional fields.

**Endpoint:** `POST /api/parties/create`

**Request Body:**
```json
{
  "partyName": "Monish",
  "gstin": "27AABCU9603R1ZM",
  "phoneNumber": "9923536215",
  "gstTypeId": 1,
  "stateId": 1,
  "emailId": "sohaib.rahman64@gmail.com",
  "billingAddress": "XYZ ABC",
  "shippingAddress": "ABC XYZ",
  "enableShipping": true,
  "openingBalance": 25000.00,
  "asOfDate": "2025-08-17",
  "paymentType": "toPay",
  "creditLimitType": "customLimit",
  "customLimit": 50000.00
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "partyName": "Monish",
  "gstin": "27AABCU9603R1ZM",
  "phoneNumber": "9923536215",
  "gstType": {
    "id": 1,
    "gstType": "Regular"
  },
  "state": {
    "id": 1,
    "state": "Maharashtra"
  },
  "emailId": "sohaib.rahman64@gmail.com",
  "billingAddress": "XYZ ABC",
  "shippingAddress": "ABC XYZ",
  "enableShipping": true,
  "openingBalance": 25000.00,
  "asOfDate": "2025-08-17",
  "paymentType": "toPay",
  "creditLimitType": "customLimit",
  "customLimit": 50000.00,
  "isActive": true,
  "createdAt": "2025-01-20T10:30:00",
  "updatedAt": "2025-01-20T10:30:00"
}
```

**Error Responses:**
- `400 Bad Request`: Party name already exists or GSTIN already exists
- `500 Internal Server Error`: Server error

---

### 2. Get All Active Parties
Retrieve all active parties with their complete details.

**Endpoint:** `GET /api/parties/getAll`

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "partyName": "Monish",
    "gstin": "27AABCU9603R1ZM",
    "phoneNumber": "9923536215",
    "gstType": {
      "id": 1,
      "gstType": "Regular"
    },
    "state": {
      "id": 1,
      "state": "Maharashtra"
    },
    "emailId": "sohaib.rahman64@gmail.com",
    "billingAddress": "XYZ ABC",
    "shippingAddress": "ABC XYZ",
    "enableShipping": true,
    "openingBalance": 25000.00,
    "asOfDate": "2025-08-17",
    "paymentType": "toPay",
    "creditLimitType": "customLimit",
    "customLimit": 50000.00,
    "isActive": true,
    "createdAt": "2025-01-20T10:30:00",
    "updatedAt": "2025-01-20T10:30:00"
  }
]
```

---

### 3. Get Party by ID
Retrieve a specific party by its ID.

**Endpoint:** `GET /api/parties/get/{id}`

**Example:** `GET /api/parties/get/1`

**Response (200 OK):**
```json
{
  "id": 1,
  "partyName": "Monish",
  "gstin": "27AABCU9603R1ZM",
  "phoneNumber": "9923536215",
  "gstType": {
    "id": 1,
    "gstType": "Regular"
  },
  "state": {
    "id": 1,
    "state": "Maharashtra"
  },
  "emailId": "sohaib.rahman64@gmail.com",
  "billingAddress": "XYZ ABC",
  "shippingAddress": "ABC XYZ",
  "enableShipping": true,
  "openingBalance": 25000.00,
  "asOfDate": "2025-08-17",
  "paymentType": "toPay",
  "creditLimitType": "customLimit",
  "customLimit": 50000.00,
  "isActive": true,
  "createdAt": "2025-01-20T10:30:00",
  "updatedAt": "2025-01-20T10:30:00"
}
```

**Error Response:**
- `404 Not Found`: Party not found

---

### 4. Update Party
Update an existing party's information.

**Endpoint:** `PUT /api/parties/update/{id}`

**Example:** `PUT /api/parties/update/1`

**Request Body:**
```json
{
  "partyName": "Monish Updated",
  "gstin": "27AABCU9603R1ZM",
  "phoneNumber": "9923536215",
  "gstTypeId": 1,
  "stateId": 1,
  "emailId": "updated.email@gmail.com",
  "billingAddress": "Updated Address",
  "shippingAddress": "Updated Shipping Address",
  "enableShipping": true,
  "openingBalance": 30000.00,
  "asOfDate": "2025-08-17",
  "paymentType": "toReceive",
  "creditLimitType": "noLimit",
  "customLimit": null
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "partyName": "Monish Updated",
  "gstin": "27AABCU9603R1ZM",
  "phoneNumber": "9923536215",
  "gstType": {
    "id": 1,
    "gstType": "Regular"
  },
  "state": {
    "id": 1,
    "state": "Maharashtra"
  },
  "emailId": "updated.email@gmail.com",
  "billingAddress": "Updated Address",
  "shippingAddress": "Updated Shipping Address",
  "enableShipping": true,
  "openingBalance": 30000.00,
  "asOfDate": "2025-08-17",
  "paymentType": "toReceive",
  "creditLimitType": "noLimit",
  "customLimit": null,
  "isActive": true,
  "createdAt": "2025-01-20T10:30:00",
  "updatedAt": "2025-01-20T11:45:00"
}
```

**Error Responses:**
- `404 Not Found`: Party not found
- `400 Bad Request`: Validation error (duplicate name/GSTIN)
- `500 Internal Server Error`: Server error

---

### 5. Delete Party (Soft Delete)
Delete a party by setting its `isActive` flag to false.

**Endpoint:** `DELETE /api/parties/delete/{id}`

**Example:** `DELETE /api/parties/delete/1`

**Response (200 OK):**
```json
"Party deleted successfully"
```

**Error Response:**
- `404 Not Found`: Party not found

---

### 6. Search Parties by Name
Search for parties by name (case-insensitive partial match).

**Endpoint:** `GET /api/parties/search/name?keyword={searchTerm}`

**Example:** `GET /api/parties/search/name?keyword=mon`

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "partyName": "Monish",
    "gstin": "27AABCU9603R1ZM",
    "phoneNumber": "9923536215",
    ...
  }
]
```

---

### 7. Search Party by GSTIN
Search for a party by GSTIN.

**Endpoint:** `GET /api/parties/search/gstin?gstin={gstin}`

**Example:** `GET /api/parties/search/gstin?gstin=27AABCU9603R1ZM`

**Response (200 OK):**
```json
{
  "id": 1,
  "partyName": "Monish",
  "gstin": "27AABCU9603R1ZM",
  ...
}
```

**Error Response:**
- `404 Not Found`: Party with GSTIN not found

---

### 8. Search Parties by Phone Number
Search for parties by phone number.

**Endpoint:** `GET /api/parties/search/phone?phoneNumber={phone}`

**Example:** `GET /api/parties/search/phone?phoneNumber=9923536215`

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "partyName": "Monish",
    "phoneNumber": "9923536215",
    ...
  }
]
```

---

### 9. Search Parties by Email
Search for parties by email ID.

**Endpoint:** `GET /api/parties/search/email?emailId={email}`

**Example:** `GET /api/parties/search/email?emailId=sohaib.rahman64@gmail.com`

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "partyName": "Monish",
    "emailId": "sohaib.rahman64@gmail.com",
    ...
  }
]
```

---

### 10. Check if Party Name Exists
Check if a party name already exists in the system.

**Endpoint:** `GET /api/parties/exists/name?partyName={name}`

**Example:** `GET /api/parties/exists/name?partyName=Monish`

**Response (200 OK):**
```json
true
```
or
```json
false
```

---

### 11. Check if GSTIN Exists
Check if a GSTIN already exists in the system.

**Endpoint:** `GET /api/parties/exists/gstin?gstin={gstin}`

**Example:** `GET /api/parties/exists/gstin?gstin=27AABCU9603R1ZM`

**Response (200 OK):**
```json
true
```
or
```json
false
```

---

## Field Descriptions

### Party Fields

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| partyName | String | Yes | Name of the party |
| gstin | String | No | GST Identification Number |
| phoneNumber | String | No | Contact phone number |
| gstTypeId | Long | No | Reference to GST Type (Regular, Composition, etc.) |
| stateId | Long | No | Reference to State |
| emailId | String | No | Email address |
| billingAddress | String | No | Billing address |
| shippingAddress | String | No | Shipping address |
| enableShipping | Boolean | No | Whether shipping address is enabled (default: false) |
| openingBalance | BigDecimal | No | Opening balance amount |
| asOfDate | LocalDate | No | Date for opening balance |
| paymentType | String | No | "toPay" or "toReceive" (default: "toPay") |
| creditLimitType | String | No | "noLimit" or "customLimit" (default: "noLimit") |
| customLimit | BigDecimal | No | Custom credit limit amount |

### Payment Type Values
- `toPay`: Party owes money to the business
- `toReceive`: Business owes money to the party

### Credit Limit Type Values
- `noLimit`: No credit limit
- `customLimit`: Custom credit limit (requires customLimit value)

---

## Validation Rules

1. **Party Name**: Required, must be unique
2. **GSTIN**: Optional, but must be unique if provided
3. **GST Type**: Must exist in the system if provided
4. **State**: Must exist in the system if provided
5. **Email**: Should be a valid email format (validated by frontend)
6. **Phone**: Should be a valid phone number format (validated by frontend)

---

## Error Handling

All endpoints return appropriate HTTP status codes:

- `200 OK`: Request successful
- `201 Created`: Resource created successfully
- `400 Bad Request`: Invalid input data or validation error
- `404 Not Found`: Resource not found
- `500 Internal Server Error`: Server error

Error messages are returned in the response body as plain text or JSON.

---

## Example Usage with cURL

### Create a Party
```bash
curl -X POST http://localhost:8080/api/parties/create \
  -H "Content-Type: application/json" \
  -d '{
    "partyName": "Monish",
    "gstin": "27AABCU9603R1ZM",
    "phoneNumber": "9923536215",
    "gstTypeId": 1,
    "stateId": 1,
    "emailId": "sohaib.rahman64@gmail.com",
    "billingAddress": "XYZ ABC",
    "openingBalance": 25000.00,
    "asOfDate": "2025-08-17",
    "paymentType": "toPay",
    "creditLimitType": "customLimit",
    "customLimit": 50000.00
  }'
```

### Get All Parties
```bash
curl -X GET http://localhost:8080/api/parties/getAll
```

### Search Parties by Name
```bash
curl -X GET "http://localhost:8080/api/parties/search/name?keyword=mon"
```

### Update a Party
```bash
curl -X PUT http://localhost:8080/api/parties/update/1 \
  -H "Content-Type: application/json" \
  -d '{
    "partyName": "Monish Updated",
    "phoneNumber": "9923536215",
    "emailId": "updated.email@gmail.com"
  }'
```

### Delete a Party
```bash
curl -X DELETE http://localhost:8080/api/parties/delete/1
```

---

## Database Schema

The parties table has the following structure:

```sql
CREATE TABLE parties (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    party_name VARCHAR(255) NOT NULL,
    gstin VARCHAR(15),
    phone_number VARCHAR(20),
    gst_type_id BIGINT,
    state_id BIGINT,
    email_id VARCHAR(255),
    billing_address TEXT,
    shipping_address TEXT,
    enable_shipping BOOLEAN DEFAULT FALSE,
    opening_balance DECIMAL(15,2),
    as_of_date DATE,
    payment_type VARCHAR(20),
    credit_limit_type VARCHAR(20),
    custom_limit DECIMAL(15,2),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (gst_type_id) REFERENCES gst_type(id),
    FOREIGN KEY (state_id) REFERENCES states(id)
);
```

---

## Notes

1. All timestamps are in ISO 8601 format (e.g., "2025-01-20T10:30:00")
2. All dates are in ISO 8601 format (e.g., "2025-08-17")
3. Currency amounts are represented as BigDecimal with 2 decimal places
4. Soft delete is implemented - deleted parties are marked as inactive, not physically removed
5. The API supports CORS with origins set to "*"
6. All endpoints are case-insensitive for search operations

---

## Frontend Integration

### Constants for API Endpoints

Add these to your `Constants.js` file:

```javascript
export const BASE_URL = "http://localhost:8080";

// Party API Endpoints
export const CREATE_PARTY = "api/parties/create";
export const GET_ALL_PARTIES = "api/parties/getAll";
export const GET_PARTY_BY_ID = "api/parties/get";
export const UPDATE_PARTY = "api/parties/update";
export const DELETE_PARTY = "api/parties/delete";
export const SEARCH_PARTIES_BY_NAME = "api/parties/search/name";
export const SEARCH_PARTY_BY_GSTIN = "api/parties/search/gstin";
export const SEARCH_PARTIES_BY_PHONE = "api/parties/search/phone";
export const SEARCH_PARTIES_BY_EMAIL = "api/parties/search/email";
export const CHECK_PARTY_NAME_EXISTS = "api/parties/exists/name";
export const CHECK_GSTIN_EXISTS = "api/parties/exists/gstin";
```

### Example Frontend Usage

```javascript
// Create a party
const createParty = async (partyData) => {
  const response = await fetch(`${BASE_URL}/${CREATE_PARTY}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(partyData)
  });
  return await response.json();
};

// Get all parties
const getAllParties = async () => {
  const response = await fetch(`${BASE_URL}/${GET_ALL_PARTIES}`);
  return await response.json();
};

// Search parties by name
const searchParties = async (keyword) => {
  const response = await fetch(`${BASE_URL}/${SEARCH_PARTIES_BY_NAME}?keyword=${keyword}`);
  return await response.json();
};
```

---

## Support

For issues or questions, please contact the development team.

