-- Create items table
CREATE TABLE items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    hsn VARCHAR(50),
    unit VARCHAR(50),
    category VARCHAR(100),
    code VARCHAR(50) UNIQUE,
    image_path VARCHAR(255),
    
    -- Pricing details
    sale_price DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    sale_price_type VARCHAR(20), -- without_tax, inclusive, exclusive
    discount_amount DECIMAL(10,2) DEFAULT 0.00,
    discount_type VARCHAR(20), -- amount, percent
    
    -- Purchase and tax details
    purchase_price DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    purchase_price_type VARCHAR(20), -- without_tax, inclusive, exclusive
    tax_rate_id BIGINT,
    
    -- Stock details
    opening_quantity INT DEFAULT 0,
    at_price DECIMAL(10,2) DEFAULT 0.00,
    as_of_date DATE,
    min_stock INT DEFAULT 0,
    location VARCHAR(100),
    
    -- Audit fields
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    -- Foreign key constraints
    FOREIGN KEY (tax_rate_id) REFERENCES tax_rates(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;