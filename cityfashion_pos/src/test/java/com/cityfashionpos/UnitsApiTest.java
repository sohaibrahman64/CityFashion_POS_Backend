package com.cityfashionpos;

import com.cityfashionpos.entity.UnitsEntity;

/**
 * Test class demonstrating the Units API implementation
 * This is not a JUnit test, but rather a demonstration of how to use the API
 */
public class UnitsApiTest {

    public static void main(String[] args) {
        System.out.println("Units API Test");
        System.out.println("==============");
        System.out.println();

        // This demonstrates how to create a Units request
        // In a real application, this would be sent via HTTP POST to the API

        UnitsEntity sampleUnit = createSampleUnit();
        System.out.println("Sample Unit created:");
        System.out.println("ID: " + sampleUnit.getId());
        System.out.println("Unit Name: " + sampleUnit.getUnitName());
        System.out.println("Unit Abbr: " + sampleUnit.getUnitAbbr());
        System.out.println("Label: " + sampleUnit.getLabel());
        System.out.println("Created At: " + sampleUnit.getCreatedAt());
        System.out.println("Updated At: " + sampleUnit.getUpdatedAt());
    }

    private static UnitsEntity createSampleUnit() {
        UnitsEntity unit = new UnitsEntity();
        unit.setId(1L);
        unit.setUnitName("BAGS");
        unit.setUnitAbbr("Bags");
        unit.setLabel("BAGS (Bags)");
        return unit;
    }
}

/*
 * API Usage Examples:
 * 
 * 1. Get All Units:
 * GET /api/units/all
 * 
 * Response:
 * [
 * {
 * "id": 1,
 * "unitName": "BAGS",
 * "unitAbbr": "Bags",
 * "label": "BAGS (Bags)",
 * "createdAt": "2025-01-15T10:30:00",
 * "updatedAt": "2025-01-15T10:30:00"
 * },
 * {
 * "id": 2,
 * "unitName": "PIECES",
 * "unitAbbr": "Pcs",
 * "label": "PIECES (Pcs)",
 * "createdAt": "2025-01-15T10:31:00",
 * "updatedAt": "2025-01-15T10:31:00"
 * },
 * {
 * "id": 3,
 * "unitName": "KILOGRAMS",
 * "unitAbbr": "Kg",
 * "label": "KILOGRAMS (Kg)",
 * "createdAt": "2025-01-15T10:32:00",
 * "updatedAt": "2025-01-15T10:32:00"
 * }
 * ]
 * 
 * 2. Get Unit by ID:
 * GET /api/units/1
 * 
 * Response:
 * {
 * "id": 1,
 * "unitName": "BAGS",
 * "unitAbbr": "Bags",
 * "label": "BAGS (Bags)",
 * "createdAt": "2025-01-15T10:30:00",
 * "updatedAt": "2025-01-15T10:30:00"
 * }
 * 
 * 3. Get Unit by Name:
 * GET /api/units/name/BAGS
 * 
 * Response:
 * {
 * "id": 1,
 * "unitName": "BAGS",
 * "unitAbbr": "Bags",
 * "label": "BAGS (Bags)",
 * "createdAt": "2025-01-15T10:30:00",
 * "updatedAt": "2025-01-15T10:30:00"
 * }
 * 
 * 4. Get Unit by Abbreviation:
 * GET /api/units/abbr/Bags
 * 
 * Response:
 * {
 * "id": 1,
 * "unitName": "BAGS",
 * "unitAbbr": "Bags",
 * "label": "BAGS (Bags)",
 * "createdAt": "2025-01-15T10:30:00",
 * "updatedAt": "2025-01-15T10:30:00"
 * }
 * 
 * 5. Create New Unit:
 * POST /api/units
 * 
 * Request Body:
 * {
 * "unitName": "CARTONS",
 * "unitAbbr": "Ctn",
 * "label": "CARTONS (Ctn)"
 * }
 * 
 * Response:
 * {
 * "id": 11,
 * "unitName": "CARTONS",
 * "unitAbbr": "Ctn",
 * "label": "CARTONS (Ctn)",
 * "createdAt": "2025-01-15T11:00:00",
 * "updatedAt": "2025-01-15T11:00:00"
 * }
 * 
 * 6. Update Unit:
 * PUT /api/units/1
 * 
 * Request Body:
 * {
 * "unitName": "BAGS",
 * "unitAbbr": "Bags",
 * "label": "BAGS (Bags) - Updated"
 * }
 * 
 * Response:
 * {
 * "id": 1,
 * "unitName": "BAGS",
 * "unitAbbr": "Bags",
 * "label": "BAGS (Bags) - Updated",
 * "createdAt": "2025-01-15T10:30:00",
 * "updatedAt": "2025-01-15T11:15:00"
 * }
 * 
 * 7. Delete Unit:
 * DELETE /api/units/1
 * 
 * Response:
 * 200 OK (No content)
 * 
 * Database Schema:
 * CREATE TABLE units (
 * id BIGINT AUTO_INCREMENT PRIMARY KEY,
 * unit_name VARCHAR(100) NOT NULL,
 * unit_abbr VARCHAR(50) NOT NULL,
 * label VARCHAR(150) NOT NULL,
 * created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 * updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
 * );
 * 
 * Sample Data:
 * INSERT INTO units (unit_name, unit_abbr, label) VALUES
 * ('BAGS', 'Bags', 'BAGS (Bags)'),
 * ('PIECES', 'Pcs', 'PIECES (Pcs)'),
 * ('KILOGRAMS', 'Kg', 'KILOGRAMS (Kg)'),
 * ('GRAMS', 'Gm', 'GRAMS (Gm)'),
 * ('LITERS', 'L', 'LITERS (L)'),
 * ('METERS', 'M', 'METERS (M)'),
 * ('CENTIMETERS', 'Cm', 'CENTIMETERS (Cm)'),
 * ('DOZEN', 'Dz', 'DOZEN (Dz)'),
 * ('PAIRS', 'Prs', 'PAIRS (Prs)'),
 * ('BOXES', 'Box', 'BOXES (Box)');
 */
