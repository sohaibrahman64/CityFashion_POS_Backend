-- MySQL DDL for product_transactions based on fields in PartyTransactionDTO
-- Note: Although this structure mirrors PartyTransactionDTO, the table name is product_transactions as requested

CREATE TABLE IF NOT EXISTS `product_transactions` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,

  -- Core linkage (party)
  `party_id` BIGINT NULL,

  -- Amounts and balances
  `party_amount` DECIMAL(15,2) NULL,
  `total` DECIMAL(15,2) NULL,
  `party_balance` DECIMAL(15,2) NULL,

  -- Descriptions and references
  `description` VARCHAR(500) NULL,
  `invoice_id` BIGINT NULL,
  `invoice_number` VARCHAR(100) NULL,
  `purchase_id` BIGINT NULL,
  `purchase_bill_number` VARCHAR(100) NULL,
  `party_phone` VARCHAR(25) NULL,
  `reference_id` BIGINT NULL,
  `reference_number` VARCHAR(100) NULL,
  `reference_type` VARCHAR(50) NULL,

  -- Human-readable info
  `party_name` VARCHAR(255) NULL,

  -- Transaction classification and time
  `transaction_type` VARCHAR(50) NOT NULL,
  `date` DATETIME NOT NULL,

  -- Audit fields
  `status` VARCHAR(50) NULL DEFAULT 'COMPLETED',
  `created_by` VARCHAR(100) NULL DEFAULT 'system',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` VARCHAR(100) NULL,
  `updated_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  PRIMARY KEY (`id`),

  -- Helpful indexes
  INDEX `idx_party_id` (`party_id`),
  INDEX `idx_transaction_type` (`transaction_type`),
  INDEX `idx_date` (`date`),
  INDEX `idx_invoice` (`invoice_id`, `invoice_number`),
  INDEX `idx_purchase` (`purchase_id`, `purchase_bill_number`),
  INDEX `idx_reference` (`reference_type`, `reference_id`),
  INDEX `idx_status` (`status`),

  -- Foreign keys (optional, set null on delete)
  CONSTRAINT `fk_pt_party` FOREIGN KEY (`party_id`) REFERENCES `parties`(`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_pt_invoice` FOREIGN KEY (`invoice_id`) REFERENCES `sales_invoice`(`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_pt_purchase` FOREIGN KEY (`purchase_id`) REFERENCES `purchases`(`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='product_transactions shaped by PartyTransactionDTO fields';


