-- DDL for parties_transactions table

CREATE TABLE IF NOT EXISTS parties_transactions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    party_id BIGINT NOT NULL,
    transaction_type VARCHAR(50) NOT NULL,
    number VARCHAR(100) NOT NULL,
    date DATETIME NOT NULL,
    total DECIMAL(15,2) NOT NULL,
    party_balance DECIMAL(15,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_parties_transactions_party FOREIGN KEY (party_id) REFERENCES parties(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_pt_party_id ON parties_transactions(party_id);
CREATE INDEX idx_pt_date ON parties_transactions(date);
CREATE INDEX idx_pt_number ON parties_transactions(number);


