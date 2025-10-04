-- MySQL table schema for sales_transactions
-- This table records all sales transactions for reporting and analytics

CREATE TABLE IF NOT EXISTS `sales_transactions` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `transaction_number` VARCHAR(50) NOT NULL UNIQUE COMMENT 'Unique transaction identifier (ST-00001)',
  `invoice_id` BIGINT NULL COMMENT 'Reference to invoice if applicable',
  `invoice_number` VARCHAR(50) NULL COMMENT 'Invoice number for reference',
  `customer_id` BIGINT NULL COMMENT 'Customer ID if applicable',
  `customer_name` VARCHAR(255) NULL COMMENT 'Customer name at time of transaction',
  `transaction_date` DATE NOT NULL COMMENT 'Date of the transaction',
  `transaction_time` TIME NOT NULL DEFAULT CURRENT_TIME COMMENT 'Time of the transaction',
  `total_amount` DECIMAL(15,2) NOT NULL DEFAULT 0.00 COMMENT 'Total sales amount before tax',
  `tax_amount` DECIMAL(15,2) NOT NULL DEFAULT 0.00 COMMENT 'Total tax amount',
  `discount_amount` DECIMAL(15,2) NOT NULL DEFAULT 0.00 COMMENT 'Total discount amount',
  `net_amount` DECIMAL(15,2) NOT NULL DEFAULT 0.00 COMMENT 'Final amount after tax and discount',
  `received_amount` DECIMAL(15,2) NOT NULL DEFAULT 0.00 COMMENT 'Amount received from customer',
  `balance_amount` DECIMAL(15,2) NOT NULL DEFAULT 0.00 COMMENT 'Outstanding balance (net_amount - received_amount)',
  `payment_status` ENUM('PAID', 'PARTIAL', 'UNPAID') NOT NULL DEFAULT 'UNPAID' COMMENT 'Payment status',
  `payment_mode` VARCHAR(50) NULL COMMENT 'Payment method used',
  `transaction_type` ENUM('SALE', 'RETURN', 'REFUND', 'ADJUSTMENT') NOT NULL DEFAULT 'SALE' COMMENT 'Type of transaction',
  `item_count` INT NOT NULL DEFAULT 0 COMMENT 'Number of items in transaction',
  `total_quantity` DECIMAL(10,2) NOT NULL DEFAULT 0.00 COMMENT 'Total quantity of items sold',
  `profit_margin` DECIMAL(15,2) NULL COMMENT 'Calculated profit margin',
  `cost_of_goods_sold` DECIMAL(15,2) NULL COMMENT 'Total cost of goods sold',
  `sales_person_id` BIGINT NULL COMMENT 'ID of the sales person',
  `sales_person_name` VARCHAR(255) NULL COMMENT 'Name of the sales person',
  `notes` VARCHAR(1000) NULL COMMENT 'Additional notes about the transaction',
  `status` VARCHAR(50) NOT NULL DEFAULT 'COMPLETED' COMMENT 'Transaction status',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Record creation timestamp',
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last update timestamp',
  `created_by` VARCHAR(100) NULL DEFAULT 'system' COMMENT 'User who created the transaction',
  `updated_by` VARCHAR(100) NULL DEFAULT 'system' COMMENT 'User who last updated the transaction',
  
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uk_transaction_number` (`transaction_number`),
  INDEX `idx_transaction_date` (`transaction_date`),
  INDEX `idx_customer_id` (`customer_id`),
  INDEX `idx_invoice_id` (`invoice_id`),
  INDEX `idx_payment_status` (`payment_status`),
  INDEX `idx_transaction_type` (`transaction_type`),
  INDEX `idx_status` (`status`),
  INDEX `idx_created_at` (`created_at`),
  INDEX `idx_date_status` (`transaction_date`, `status`),
  INDEX `idx_date_type` (`transaction_date`, `transaction_type`),
  
  CONSTRAINT `fk_sales_transactions_customer` 
    FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) 
    ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_sales_transactions_invoice` 
    FOREIGN KEY (`invoice_id`) REFERENCES `new_sales_invoice` (`id`) 
    ON DELETE SET NULL ON UPDATE CASCADE
    
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Records all sales transactions for reporting and analytics';

-- Create view for monthly sales summary
CREATE OR REPLACE VIEW `monthly_sales_summary` AS
SELECT 
    YEAR(transaction_date) as year,
    MONTH(transaction_date) as month,
    DATE_FORMAT(transaction_date, '%Y-%m') as month_year,
    COUNT(*) as total_transactions,
    SUM(total_amount) as total_sales_amount,
    SUM(received_amount) as total_received_amount,
    SUM(balance_amount) as total_balance_amount,
    SUM(tax_amount) as total_tax_amount,
    SUM(discount_amount) as total_discount_amount,
    SUM(net_amount) as total_net_amount,
    AVG(net_amount) as average_transaction_value,
    SUM(total_quantity) as total_items_sold,
    SUM(CASE WHEN payment_status = 'PAID' THEN 1 ELSE 0 END) as paid_transactions,
    SUM(CASE WHEN payment_status = 'PARTIAL' THEN 1 ELSE 0 END) as partial_transactions,
    SUM(CASE WHEN payment_status = 'UNPAID' THEN 1 ELSE 0 END) as unpaid_transactions
FROM `sales_transactions`
WHERE status = 'COMPLETED' AND transaction_type = 'SALE'
GROUP BY YEAR(transaction_date), MONTH(transaction_date)
ORDER BY year DESC, month DESC;

-- Create view for daily sales summary
CREATE OR REPLACE VIEW `daily_sales_summary` AS
SELECT 
    transaction_date,
    COUNT(*) as total_transactions,
    SUM(total_amount) as total_sales_amount,
    SUM(received_amount) as total_received_amount,
    SUM(balance_amount) as total_balance_amount,
    SUM(tax_amount) as total_tax_amount,
    SUM(discount_amount) as total_discount_amount,
    SUM(net_amount) as total_net_amount,
    AVG(net_amount) as average_transaction_value,
    SUM(total_quantity) as total_items_sold,
    SUM(CASE WHEN payment_status = 'PAID' THEN 1 ELSE 0 END) as paid_transactions,
    SUM(CASE WHEN payment_status = 'PARTIAL' THEN 1 ELSE 0 END) as partial_transactions,
    SUM(CASE WHEN payment_status = 'UNPAID' THEN 1 ELSE 0 END) as unpaid_transactions
FROM `sales_transactions`
WHERE status = 'COMPLETED' AND transaction_type = 'SALE'
GROUP BY transaction_date
ORDER BY transaction_date DESC;

-- Create indexes for better performance on date range queries
CREATE INDEX `idx_date_range_performance` ON `sales_transactions` (`transaction_date`, `status`, `transaction_type`);
CREATE INDEX `idx_amount_analysis` ON `sales_transactions` (`net_amount`, `transaction_date`);
CREATE INDEX `idx_payment_analysis` ON `sales_transactions` (`payment_status`, `transaction_date`, `balance_amount`);

-- Insert sample data for testing (optional - commented out)
-- INSERT INTO `sales_transactions` (
--     `transaction_number`, `invoice_number`, `customer_name`, `transaction_date`, 
--     `total_amount`, `tax_amount`, `discount_amount`, `net_amount`, `received_amount`, 
--     `balance_amount`, `payment_status`, `payment_mode`, `item_count`, `total_quantity`, 
--     `sales_person_name`, `status`
-- ) VALUES 
-- ('ST-00001', 'RS-00001', 'John Doe', CURDATE(), 1000.00, 180.00, 50.00, 1130.00, 1130.00, 0.00, 'PAID', 'CASH', 3, 5.00, 'Sales Person 1', 'COMPLETED'),
-- ('ST-00002', 'RS-00002', 'Jane Smith', CURDATE(), 750.00, 135.00, 25.00, 860.00, 500.00, 360.00, 'PARTIAL', 'CARD', 2, 3.00, 'Sales Person 1', 'COMPLETED'),
-- ('ST-00003', 'RS-00003', 'Bob Johnson', CURDATE() - INTERVAL 1 DAY, 2000.00, 360.00, 100.00, 2260.00, 2260.00, 0.00, 'PAID', 'UPI', 5, 8.00, 'Sales Person 2', 'COMPLETED');
