package com.cityfashionpos.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StockAdjustmentResponse {
	private Long id;
	private Long productId;
	private String productName;
	private String adjustmentType;
	private Integer quantity;
	private BigDecimal atPrice;
	private String description;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private String adjustmentDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;

	private String createdBy;
	private BigDecimal totalValue;
	private Integer newStockQuantity;

	// Constructors
	public StockAdjustmentResponse() {
	}

	public StockAdjustmentResponse(Long id, Long productId, String productName, String adjustmentType,
			Integer quantity, BigDecimal atPrice, String description, String adjustmentDate, LocalDateTime createdAt,
			String createdBy, BigDecimal totalValue, Integer newStockQuantity) {
		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.adjustmentType = adjustmentType;
		this.quantity = quantity;
		this.atPrice = atPrice;
		this.description = description;
		this.adjustmentDate = adjustmentDate;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.totalValue = totalValue;
		this.newStockQuantity = newStockQuantity;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public String getAdjustmentDate() {
		return adjustmentDate;
	}

	public void setAdjustmentDate(String adjustmentDate) {
		this.adjustmentDate = adjustmentDate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public Integer getNewStockQuantity() {
		return newStockQuantity;
	}

	public void setNewStockQuantity(Integer newStockQuantity) {
		this.newStockQuantity = newStockQuantity;
	}

}
