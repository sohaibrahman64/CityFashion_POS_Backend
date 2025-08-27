-- Database Schema Updates for New Sales Invoice Functionality
-- Run these SQL commands to update your existing database

-- 1. Add discount fields to sales_invoice_items table
ALTER TABLE sales_invoice_items 
ADD COLUMN discount_percent DECIMAL(5,2) DEFAULT 0.00 COMMENT 'Percentage discount for the item',
ADD COLUMN discount_amount DECIMAL(10,2) DEFAULT 0.00 COMMENT 'Calculated discount amount for the item';

-- 2. Add indexes for better performance (optional but recommended)
CREATE INDEX idx_invoice_items_invoice_id ON sales_invoice_items(invoice_id);
CREATE INDEX idx_invoice_items_product_id ON sales_invoice_items(product_id);
CREATE INDEX idx_invoice_customer_id ON sales_invoice(customer_id);
CREATE INDEX idx_invoice_invoice_number ON sales_invoice(invoice_number);
CREATE INDEX idx_customers_phone ON customers(phone);

-- 3. Verify the table structure
DESCRIBE sales_invoice_items;
DESCRIBE sales_invoice;
DESCRIBE customers;

-- 4. Sample data insertion for testing (optional)
-- Insert sample payment statuses if they don't exist
INSERT IGNORE INTO payment_status (status_name, description) VALUES 
('Paid', 'Full payment received'),
('Partially Paid', 'Partial payment received'),
('Pending', 'No payment received');

-- 5. Verify payment statuses
SELECT * FROM payment_status;

-- 6. Sample customer insertion for testing (optional)
INSERT IGNORE INTO customers (name, phone, email, address) VALUES 
('Test Customer', '9876543210', 'test@example.com', 'Test Address');

-- 7. Verify customer insertion
SELECT * FROM customers WHERE phone = '9876543210';

-- Notes:
-- - The discount_percent field stores percentage values (e.g., 10.00 for 10%)
-- - The discount_amount field stores calculated monetary values (e.g., 59.90 for ₹59.90)
-- - Both fields default to 0.00 to maintain backward compatibility
-- - The indexes will improve query performance for large datasets
-- - Run these commands in your MySQL database after backing up your data
