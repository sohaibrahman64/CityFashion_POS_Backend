-- Create discount_type table
CREATE TABLE IF NOT EXISTS discount_type (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    discount_type VARCHAR(100) NOT NULL,
    discount_type_code VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert sample data
INSERT INTO discount_type (discount_type, discount_type_code) VALUES 
('Percentage', 'PERCENTAGE'),
('Fixed Amount', 'FIXED_AMOUNT'),
('Buy One Get One', 'BOGO'),
('Buy Two Get One', 'BTGO'),
('No Discount', 'NO_DISCOUNT')
ON DUPLICATE KEY UPDATE 
    discount_type = VALUES(discount_type),
    updated_at = CURRENT_TIMESTAMP;
