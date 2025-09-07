-- SQL statement to alter products_new table to add tax_rate_id foreign key
-- This replaces the tax_type enum column with a foreign key reference to tax_rates table

-- Step 1: Add the new tax_rate_id column
ALTER TABLE products_new 
ADD COLUMN tax_rate_id BIGINT;

-- Step 2: Add foreign key constraint
ALTER TABLE products_new 
ADD CONSTRAINT fk_products_tax_rate 
FOREIGN KEY (tax_rate_id) REFERENCES tax_rates(id);

-- Step 3: (Optional) Drop the old tax_type column if it exists
-- Uncomment the following line if you want to remove the old tax_type column
-- ALTER TABLE products_new DROP COLUMN tax_type;

-- Note: Before dropping the tax_type column, you may want to migrate existing data
-- by mapping the old enum values to appropriate tax_rate records in the tax_rates table