package com.cityfashionpos.model;

public enum TransactionType {
    SALE("Sale"),
    PURCHASE("Purchase"),
    STOCK_ADJUSTMENT("Stock Adjustment"),
    STOCK_TRANSFER("Stock Transfer"),
    RETURN_SALE("Return Sale"),
    RETURN_PURCHASE("Return Purchase"),
    REFUND("Refund"),
    ADJUSTMENT("Adjustment"),
    DAMAGE_LOSS("Damage/Loss"),
    EXPIRY_WRITEOFF("Expiry Write-off"),
    OPENING_STOCK("Opening Stock"),
    CLOSING_STOCK("Closing Stock"),
    INVENTORY_COUNT("Inventory Count"),
    OTHER("Other");

    private final String displayName;

    TransactionType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
