-- Create units table
CREATE TABLE IF NOT EXISTS units (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    unit_name VARCHAR(100) NOT NULL,
    unit_abbr VARCHAR(50) NOT NULL,
    label VARCHAR(150) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert sample data
INSERT INTO units (unit_name, unit_abbr, label) VALUES 
('BAGS', 'Bags', 'BAGS (Bags)'),
('PIECES', 'Pcs', 'PIECES (Pcs)'),
('KILOGRAMS', 'Kg', 'KILOGRAMS (Kg)'),
('GRAMS', 'Gm', 'GRAMS (Gm)'),
('LITERS', 'L', 'LITERS (L)'),
('METERS', 'M', 'METERS (M)'),
('CENTIMETERS', 'Cm', 'CENTIMETERS (Cm)'),
('DOZEN', 'Dz', 'DOZEN (Dz)'),
('PAIRS', 'Prs', 'PAIRS (Prs)'),
('BOXES', 'Box', 'BOXES (Box)')
ON DUPLICATE KEY UPDATE 
    unit_name = VALUES(unit_name),
    unit_abbr = VALUES(unit_abbr),
    label = VALUES(label),
    updated_at = CURRENT_TIMESTAMP;
