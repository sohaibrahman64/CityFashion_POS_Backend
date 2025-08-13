package com.cityfashionpos.model;

public enum AdjustmentType {
	ADD_STOCK("Add Stock"), REDUCE_STOCK("Reduce Stock");

	private final String displayName;

	AdjustmentType(String displayName) {
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
