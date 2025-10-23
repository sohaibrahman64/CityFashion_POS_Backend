package com.cityfashionpos;

import com.cityfashionpos.entity.DiscountTypeEntity;

/**
 * Test class demonstrating the Discount Type API implementation
 * This is not a JUnit test, but rather a demonstration of how to use the API
 */
public class DiscountTypeApiTest {

    public static void main(String[] args) {
        System.out.println("Discount Type API Test");
        System.out.println("=====================");
        System.out.println();

        // This demonstrates how to create a Discount Type request
        // In a real application, this would be sent via HTTP POST to the API

        DiscountTypeEntity sampleDiscountType = createSampleDiscountType();
        System.out.println("Sample Discount Type created:");
        System.out.println("ID: " + sampleDiscountType.getId());
        System.out.println("Discount Type: " + sampleDiscountType.getDiscountType());
        System.out.println("Discount Type Code: " + sampleDiscountType.getDiscountTypeCode());
        System.out.println("Created At: " + sampleDiscountType.getCreatedAt());
        System.out.println("Updated At: " + sampleDiscountType.getUpdatedAt());
    }

    private static DiscountTypeEntity createSampleDiscountType() {
        DiscountTypeEntity discountType = new DiscountTypeEntity();
        discountType.setId(1L);
        discountType.setDiscountType("Percentage");
        discountType.setDiscountTypeCode("PERCENTAGE");
        return discountType;
    }
}

/*
 * API Usage Examples:
 * 
 * 1. Get All Discount Types:
 * GET /api/discount-types/all
 * 
 * Response:
 * [
 * {
 * "id": 1,
 * "discountType": "Percentage",
 * "discountTypeCode": "PERCENTAGE",
 * "createdAt": "2025-01-15T10:30:00",
 * "updatedAt": "2025-01-15T10:30:00"
 * },
 * {
 * "id": 2,
 * "discountType": "Fixed Amount",
 * "discountTypeCode": "FIXED_AMOUNT",
 * "createdAt": "2025-01-15T10:31:00",
 * "updatedAt": "2025-01-15T10:31:00"
 * },
 * {
 * "id": 3,
 * "discountType": "Buy One Get One",
 * "discountTypeCode": "BOGO",
 * "createdAt": "2025-01-15T10:32:00",
 * "updatedAt": "2025-01-15T10:32:00"
 * }
 * ]
 * 
 * 2. Get Discount Type by ID:
 * GET /api/discount-types/1
 * 
 * Response:
 * {
 * "id": 1,
 * "discountType": "Percentage",
 * "discountTypeCode": "PERCENTAGE",
 * "createdAt": "2025-01-15T10:30:00",
 * "updatedAt": "2025-01-15T10:30:00"
 * }
 * 
 * 3. Get Discount Type by Code:
 * GET /api/discount-types/code/PERCENTAGE
 * 
 * Response:
 * {
 * "id": 1,
 * "discountType": "Percentage",
 * "discountTypeCode": "PERCENTAGE",
 * "createdAt": "2025-01-15T10:30:00",
 * "updatedAt": "2025-01-15T10:30:00"
 * }
 * 
 * 4. Create New Discount Type:
 * POST /api/discount-types
 * 
 * Request Body:
 * {
 * "discountType": "Seasonal Discount",
 * "discountTypeCode": "SEASONAL"
 * }
 * 
 * Response:
 * {
 * "id": 6,
 * "discountType": "Seasonal Discount",
 * "discountTypeCode": "SEASONAL",
 * "createdAt": "2025-01-15T11:00:00",
 * "updatedAt": "2025-01-15T11:00:00"
 * }
 * 
 * 5. Update Discount Type:
 * PUT /api/discount-types/1
 * 
 * Request Body:
 * {
 * "discountType": "Percentage (Updated)",
 * "discountTypeCode": "PERCENTAGE"
 * }
 * 
 * Response:
 * {
 * "id": 1,
 * "discountType": "Percentage (Updated)",
 * "discountTypeCode": "PERCENTAGE",
 * "createdAt": "2025-01-15T10:30:00",
 * "updatedAt": "2025-01-15T11:15:00"
 * }
 * 
 * 6. Delete Discount Type:
 * DELETE /api/discount-types/1
 * 
 * Response:
 * 200 OK (No content)
 * 
 * Database Schema:
 * CREATE TABLE discount_type (
 * id BIGINT AUTO_INCREMENT PRIMARY KEY,
 * discount_type VARCHAR(100) NOT NULL,
 * discount_type_code VARCHAR(50) NOT NULL UNIQUE,
 * created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 * updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
 * );
 * 
 * Sample Data:
 * INSERT INTO discount_type (discount_type, discount_type_code) VALUES
 * ('Percentage', 'PERCENTAGE'),
 * ('Fixed Amount', 'FIXED_AMOUNT'),
 * ('Buy One Get One', 'BOGO'),
 * ('Buy Two Get One', 'BTGO'),
 * ('No Discount', 'NO_DISCOUNT');
 */
