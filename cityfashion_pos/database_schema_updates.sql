-- Tax Rates Table Creation
-- This script creates the tax_rates table for dynamic tax rate selection

-- Create tax_rates table
CREATE TABLE IF NOT EXISTS tax_rates (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    label VARCHAR(50) NOT NULL COMMENT 'Display label for tax rate (e.g., "GST@18%")',
    type ENUM('GST', 'IGST') NOT NULL COMMENT 'Tax type: GST or IGST',
    rate DECIMAL(5,2) NOT NULL COMMENT 'Tax rate percentage (e.g., 18.00)',
    active BOOLEAN NOT NULL DEFAULT TRUE COMMENT 'Whether the rate is usable',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Add tax_rate_id column to new_sales_invoice_items table
ALTER TABLE new_sales_invoice_items 
ADD COLUMN tax_rate_id BIGINT NULL COMMENT 'Reference to tax_rates table',
ADD CONSTRAINT fk_invoice_item_tax_rate 
    FOREIGN KEY (tax_rate_id) REFERENCES tax_rates(id) ON DELETE SET NULL;

-- Modify tax_percent column to use DECIMAL for better precision
ALTER TABLE new_sales_invoice_items 
MODIFY COLUMN tax_percent DECIMAL(5,2) NULL COMMENT 'Snapshot of applied tax rate for historical accuracy';

-- Insert sample tax rates
INSERT INTO tax_rates (label, type, rate, active) VALUES
('GST@0%', 'GST', 0.00, TRUE),
('GST@5%', 'GST', 5.00, TRUE),
('GST@12%', 'GST', 12.00, TRUE),
('GST@18%', 'GST', 18.00, TRUE),
('GST@28%', 'GST', 28.00, TRUE),
('IGST@0%', 'IGST', 0.00, TRUE),
('IGST@5%', 'IGST', 5.00, TRUE),
('IGST@12%', 'IGST', 12.00, TRUE),
('IGST@18%', 'IGST', 18.00, TRUE),
('IGST@28%', 'IGST', 28.00, TRUE);

-- Create index for better performance
CREATE INDEX idx_tax_rates_active_type ON tax_rates(active, type);
CREATE INDEX idx_tax_rates_rate ON tax_rates(rate);
CREATE INDEX idx_invoice_items_tax_rate ON new_sales_invoice_items(tax_rate_id);