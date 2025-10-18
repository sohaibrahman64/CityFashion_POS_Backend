-- Create parties table for managing customers and suppliers
-- This table stores party information including GST details, addresses, and credit information

CREATE TABLE IF NOT EXISTS parties (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    
    -- Basic Information
    party_name VARCHAR(255) NOT NULL,
    gstin VARCHAR(15) UNIQUE,
    phone_number VARCHAR(20),
    
    -- GST and State Information
    gst_type_id BIGINT,
    state_id BIGINT,
    
    -- Contact Information
    email_id VARCHAR(255),
    
    -- Address Information
    billing_address TEXT,
    shipping_address TEXT,
    enable_shipping BOOLEAN DEFAULT FALSE,
    
    -- Credit & Balance Information
    opening_balance DECIMAL(15,2) DEFAULT 0.00,
    as_of_date DATE,
    payment_type VARCHAR(20) DEFAULT 'toPay', -- 'toPay' or 'toReceive'
    credit_limit_type VARCHAR(20) DEFAULT 'noLimit', -- 'noLimit' or 'customLimit'
    custom_limit DECIMAL(15,2),
    
    -- Status and Audit Fields
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    -- Foreign Keys
    CONSTRAINT fk_party_gst_type FOREIGN KEY (gst_type_id) REFERENCES gst_type(id) ON DELETE SET NULL,
    CONSTRAINT fk_party_state FOREIGN KEY (state_id) REFERENCES states(id) ON DELETE SET NULL,
    
    -- Indexes for better query performance
    INDEX idx_party_name (party_name),
    INDEX idx_gstin (gstin),
    INDEX idx_phone_number (phone_number),
    INDEX idx_email_id (email_id),
    INDEX idx_is_active (is_active),
    INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Add comments to table and columns
ALTER TABLE parties COMMENT = 'Stores party (customer/supplier) information with GST, address, and credit details';

ALTER TABLE parties MODIFY COLUMN id BIGINT AUTO_INCREMENT COMMENT 'Primary key';
ALTER TABLE parties MODIFY COLUMN party_name VARCHAR(255) NOT NULL COMMENT 'Name of the party (customer/supplier)';
ALTER TABLE parties MODIFY COLUMN gstin VARCHAR(15) UNIQUE COMMENT 'GST Identification Number';
ALTER TABLE parties MODIFY COLUMN phone_number VARCHAR(20) COMMENT 'Contact phone number';
ALTER TABLE parties MODIFY COLUMN gst_type_id BIGINT COMMENT 'Reference to GST type (Regular, Composition, etc.)';
ALTER TABLE parties MODIFY COLUMN state_id BIGINT COMMENT 'Reference to state';
ALTER TABLE parties MODIFY COLUMN email_id VARCHAR(255) COMMENT 'Email address';
ALTER TABLE parties MODIFY COLUMN billing_address TEXT COMMENT 'Billing address';
ALTER TABLE parties MODIFY COLUMN shipping_address TEXT COMMENT 'Shipping address (if different from billing)';
ALTER TABLE parties MODIFY COLUMN enable_shipping BOOLEAN DEFAULT FALSE COMMENT 'Whether shipping address is enabled';
ALTER TABLE parties MODIFY COLUMN opening_balance DECIMAL(15,2) DEFAULT 0.00 COMMENT 'Opening balance amount';
ALTER TABLE parties MODIFY COLUMN as_of_date DATE COMMENT 'Date for opening balance';
ALTER TABLE parties MODIFY COLUMN payment_type VARCHAR(20) DEFAULT 'toPay' COMMENT 'Payment type: toPay or toReceive';
ALTER TABLE parties MODIFY COLUMN credit_limit_type VARCHAR(20) DEFAULT 'noLimit' COMMENT 'Credit limit type: noLimit or customLimit';
ALTER TABLE parties MODIFY COLUMN custom_limit DECIMAL(15,2) COMMENT 'Custom credit limit amount';
ALTER TABLE parties MODIFY COLUMN is_active BOOLEAN DEFAULT TRUE COMMENT 'Whether party is active';
ALTER TABLE parties MODIFY COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Record creation timestamp';
ALTER TABLE parties MODIFY COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Record update timestamp';

-- Insert sample data (optional - for testing)
-- Uncomment the following lines to insert sample data

/*
INSERT INTO parties (
    party_name, 
    gstin, 
    phone_number, 
    gst_type_id, 
    state_id, 
    email_id, 
    billing_address, 
    opening_balance, 
    as_of_date, 
    payment_type, 
    credit_limit_type,
    custom_limit
) VALUES 
(
    'Sample Party 1',
    '27AABCU9603R1ZM',
    '9923536215',
    1,
    1,
    'sample1@example.com',
    '123 Main Street, Mumbai',
    25000.00,
    '2025-08-17',
    'toPay',
    'customLimit',
    50000.00
),
(
    'Sample Party 2',
    '29ABCDE1234F1Z5',
    '9876543210',
    2,
    2,
    'sample2@example.com',
    '456 Park Avenue, Delhi',
    15000.00,
    '2025-08-17',
    'toReceive',
    'noLimit',
    NULL
);
*/

-- Verify table creation
SELECT 
    TABLE_NAME,
    TABLE_COMMENT,
    ENGINE,
    TABLE_COLLATION
FROM 
    INFORMATION_SCHEMA.TABLES
WHERE 
    TABLE_SCHEMA = DATABASE()
    AND TABLE_NAME = 'parties';

-- Show table structure
DESCRIBE parties;

