package com.cityfashionpos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StockAdjustmentRequest {
	private Long productId;

	private String adjustmentType; // "ADD_STOCK" or "REDUCE_STOCK"

	private Integer quantity;

	private BigDecimal atPrice;

	private String description;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate adjustmentDate;

	private String createdBy;

	// Constructors
	public StockAdjustmentRequest() {
	}

	public StockAdjustmentRequest(Long productId, String adjustmentType, Integer quantity, BigDecimal atPrice,
			String description, LocalDate adjustmentDate) {
		this.productId = productId;
		this.adjustmentType = adjustmentType;
		this.quantity = quantity;
		this.atPrice = atPrice;
		this.description = description;
		this.adjustmentDate = adjustmentDate;
	}

	// Getters and Setters
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getAtPrice() {
		return atPrice;
	}

	public void setAtPrice(BigDecimal atPrice) {
		this.atPrice = atPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getAdjustmentDate() {
		return adjustmentDate;
	}

	public void setAdjustmentDate(LocalDate adjustmentDate) {
		this.adjustmentDate = adjustmentDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
