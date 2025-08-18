-- MySQL table schema for product_transactions
-- This table records all product transactions including sales, purchases, adjustments, and other inventory movements

CREATE TABLE IF NOT EXISTS `product_transactions` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `product_id` BIGINT NOT NULL,
  `transaction_type` ENUM('SALE', 'PURCHASE', 'STOCK_ADJUSTMENT', 'STOCK_TRANSFER', 'RETURN_SALE', 'RETURN_PURCHASE', 'DAMAGE_LOSS', 'EXPIRY_WRITEOFF', 'OPENING_STOCK', 'CLOSING_STOCK', 'INVENTORY_COUNT', 'OTHER') NOT NULL,
  `reference_id` BIGINT NULL COMMENT 'ID of the related entity (invoice, purchase, adjustment, etc.)',
  `reference_type` VARCHAR(50) NULL COMMENT 'Type of reference (INVOICE, PURCHASE, ADJUSTMENT, TRANSFER, etc.)',
  `reference_number` VARCHAR(100) NULL COMMENT 'Human-readable reference (INV-001, PO-001, etc.)',
  `quantity` DECIMAL(10,2) NOT NULL COMMENT 'Quantity involved in the transaction',
  `unit_price` DECIMAL(10,2) NULL COMMENT 'Unit price at the time of transaction',
  `total_value` DECIMAL(15,2) NULL COMMENT 'Total value (quantity * unit_price)',
  `description` VARCHAR(500) NULL COMMENT 'Description of the transaction',
  `transaction_date` DATETIME NOT NULL COMMENT 'Date and time of the transaction',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Record creation timestamp',
  `created_by` VARCHAR(100) NULL DEFAULT 'system' COMMENT 'User who created the transaction',
  `notes` VARCHAR(1000) NULL COMMENT 'Additional notes about the transaction',
  `status` VARCHAR(50) NULL DEFAULT 'COMPLETED' COMMENT 'Transaction status (COMPLETED, PENDING, CANCELLED, etc.)',
  PRIMARY KEY (`id`),
  INDEX `idx_product_id` (`product_id`),
  INDEX `idx_transaction_type` (`transaction_type`),
  INDEX `idx_transaction_date` (`transaction_date`),
  INDEX `idx_reference` (`reference_type`, `reference_id`),
  INDEX `idx_status` (`status`),
  INDEX `idx_created_at` (`created_at`),
  CONSTRAINT `fk_product_transactions_product` 
    FOREIGN KEY (`product_id`) REFERENCES `product_entity_new` (`id`) 
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Records all product transactions for inventory tracking and reporting';

-- Insert sample data for testing (optional)
-- INSERT INTO `product_transactions` (`product_id`, `transaction_type`, `quantity`, `unit_price`, `description`, `transaction_date`, `status`) 
-- VALUES 
-- (1, 'OPENING_STOCK', 100.00, 25.00, 'Initial stock setup', NOW(), 'COMPLETED'),
-- (1, 'SALE', -5.00, 30.00, 'Customer sale', NOW(), 'COMPLETED'),
-- (1, 'PURCHASE', 50.00, 22.50, 'Supplier restock', NOW(), 'COMPLETED');

-- Create view for transaction summary (optional)
CREATE OR REPLACE VIEW `product_transaction_summary` AS
SELECT 
    pt.product_id,
    p.name as product_name,
    SUM(CASE WHEN pt.transaction_type IN ('PURCHASE', 'STOCK_ADJUSTMENT', 'OPENING_STOCK', 'RETURN_SALE') THEN pt.quantity ELSE 0 END) as total_in,
    SUM(CASE WHEN pt.transaction_type IN ('SALE', 'DAMAGE_LOSS', 'EXPIRY_WRITEOFF', 'RETURN_PURCHASE') THEN pt.quantity ELSE 0 END) as total_out,
    SUM(CASE WHEN pt.transaction_type IN ('PURCHASE', 'STOCK_ADJUSTMENT', 'OPENING_STOCK', 'RETURN_SALE') THEN pt.quantity ELSE 0 END) - 
    SUM(CASE WHEN pt.transaction_type IN ('SALE', 'DAMAGE_LOSS', 'EXPIRY_WRITEOFF', 'RETURN_PURCHASE') THEN pt.quantity ELSE 0 END) as net_quantity,
    COUNT(*) as total_transactions,
    MAX(pt.transaction_date) as last_transaction_date
FROM `product_transactions` pt
JOIN `product_entity_new` p ON pt.product_id = p.id
GROUP BY pt.product_id, p.name;

-- Create index for better performance on date range queries
CREATE INDEX `idx_product_date_range` ON `product_transactions` (`product_id`, `transaction_date`);

-- Create index for better performance on type and date queries
CREATE INDEX `idx_type_date` ON `product_transactions` (`transaction_type`, `transaction_date`);
