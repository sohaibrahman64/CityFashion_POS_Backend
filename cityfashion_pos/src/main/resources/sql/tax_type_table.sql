-- Create tax_type table
CREATE TABLE IF NOT EXISTS tax_type (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tax_type VARCHAR(100) NOT NULL,
    tax_type_code VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert sample data
INSERT INTO tax_type (tax_type, tax_type_code) VALUES 
('With Tax', 'WITH_TAX'),
('Without Tax', 'WITHOUT_TAX'),
('Zero Tax', 'ZERO_TAX'),
('Exempt Tax', 'EXEMPT_TAX'),
('Reverse Tax', 'REVERSE_TAX')
ON DUPLICATE KEY UPDATE 
    tax_type = VALUES(tax_type),
    updated_at = CURRENT_TIMESTAMP;
