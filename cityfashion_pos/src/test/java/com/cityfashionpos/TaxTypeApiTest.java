package com.cityfashionpos;

import com.cityfashionpos.entity.TaxTypeEntity;

/**
 * Test class demonstrating the Tax Type API implementation
 * This is not a JUnit test, but rather a demonstration of how to use the API
 */
public class TaxTypeApiTest {

    public static void main(String[] args) {
        System.out.println("Tax Type API Test");
        System.out.println("=================");
        System.out.println();

        // This demonstrates how to create a Tax Type request
        // In a real application, this would be sent via HTTP POST to the API

        TaxTypeEntity sampleTaxType = createSampleTaxType();
        System.out.println("Sample Tax Type created:");
        System.out.println("ID: " + sampleTaxType.getId());
        System.out.println("Tax Type: " + sampleTaxType.getTaxType());
        System.out.println("Tax Type Code: " + sampleTaxType.getTaxTypeCode());
        System.out.println("Created At: " + sampleTaxType.getCreatedAt());
        System.out.println("Updated At: " + sampleTaxType.getUpdatedAt());
    }

    private static TaxTypeEntity createSampleTaxType() {
        TaxTypeEntity taxType = new TaxTypeEntity();
        taxType.setId(1L);
        taxType.setTaxType("With Tax");
        taxType.setTaxTypeCode("WITH_TAX");
        return taxType;
    }
}

/*
 * API Usage Examples:
 * 
 * 1. Get All Tax Types:
 * GET /api/tax-types
 * 
 * Response:
 * [
 * {
 * "id": 1,
 * "taxType": "With Tax",
 * "taxTypeCode": "WITH_TAX",
 * "createdAt": "2025-01-15T10:30:00",
 * "updatedAt": "2025-01-15T10:30:00"
 * },
 * {
 * "id": 2,
 * "taxType": "Without Tax",
 * "taxTypeCode": "WITHOUT_TAX",
 * "createdAt": "2025-01-15T10:31:00",
 * "updatedAt": "2025-01-15T10:31:00"
 * }
 * ]
 * 
 * 2. Get Tax Type by ID:
 * GET /api/tax-types/1
 * 
 * Response:
 * {
 * "id": 1,
 * "taxType": "With Tax",
 * "taxTypeCode": "WITH_TAX",
 * "createdAt": "2025-01-15T10:30:00",
 * "updatedAt": "2025-01-15T10:30:00"
 * }
 * 
 * 3. Get Tax Type by Code:
 * GET /api/tax-types/code/WITH_TAX
 * 
 * Response:
 * {
 * "id": 1,
 * "taxType": "With Tax",
 * "taxTypeCode": "WITH_TAX",
 * "createdAt": "2025-01-15T10:30:00",
 * "updatedAt": "2025-01-15T10:30:00"
 * }
 * 
 * 4. Create New Tax Type:
 * POST /api/tax-types
 * 
 * Request Body:
 * {
 * "taxType": "Zero Tax",
 * "taxTypeCode": "ZERO_TAX"
 * }
 * 
 * Response:
 * {
 * "id": 3,
 * "taxType": "Zero Tax",
 * "taxTypeCode": "ZERO_TAX",
 * "createdAt": "2025-01-15T11:00:00",
 * "updatedAt": "2025-01-15T11:00:00"
 * }
 * 
 * 5. Update Tax Type:
 * PUT /api/tax-types/1
 * 
 * Request Body:
 * {
 * "taxType": "With Tax (Updated)",
 * "taxTypeCode": "WITH_TAX"
 * }
 * 
 * Response:
 * {
 * "id": 1,
 * "taxType": "With Tax (Updated)",
 * "taxTypeCode": "WITH_TAX",
 * "createdAt": "2025-01-15T10:30:00",
 * "updatedAt": "2025-01-15T11:15:00"
 * }
 * 
 * 6. Delete Tax Type:
 * DELETE /api/tax-types/1
 * 
 * Response:
 * 200 OK (No content)
 * 
 * Database Schema:
 * CREATE TABLE tax_type (
 * id BIGINT AUTO_INCREMENT PRIMARY KEY,
 * tax_type VARCHAR(100) NOT NULL,
 * tax_type_code VARCHAR(50) NOT NULL UNIQUE,
 * created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 * updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
 * );
 * 
 * Sample Data:
 * INSERT INTO tax_type (tax_type, tax_type_code) VALUES
 * ('With Tax', 'WITH_TAX'),
 * ('Without Tax', 'WITHOUT_TAX'),
 * ('Zero Tax', 'ZERO_TAX');
 */
