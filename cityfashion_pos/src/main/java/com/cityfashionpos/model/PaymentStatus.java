package com.cityfashionpos.model;

public enum PaymentStatus {
    PAID("Paid"),
    PARTIAL("Partial"),
    UNPAID("Unpaid");

    private final String displayName;

    PaymentStatus(String displayName) {
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
