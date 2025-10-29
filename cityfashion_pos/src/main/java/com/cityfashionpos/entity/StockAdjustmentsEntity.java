package com.cityfashionpos.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.math3.stat.descriptive.summary.Product;

import com.cityfashionpos.model.AdjustmentType;

@Entity
@Table(name = "stock_adjustments")
public class StockAdjustmentsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id", nullable = false)
	private ItemEntity item;

	@Enumerated(EnumType.STRING)
	@Column(name = "adjustment_type", nullable = false)
	private AdjustmentType adjustmentType;

	@Column(name = "quantity", nullable = false, precision = 10, scale = 2)
	private Integer quantity;

	@Column(name = "at_price", precision = 10, scale = 2)
	private BigDecimal atPrice;

	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "adjustment_date", nullable = false)
	private LocalDateTime adjustmentDate;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "created_by", length = 100)
	private String createdBy;

	@Column(name = "total_value", precision = 15, scale = 2)
	private BigDecimal totalValue;

	// Constructors
	public StockAdjustmentsEntity() {
		this.createdAt = LocalDateTime.now();
	}

	public StockAdjustmentsEntity(ItemEntity item, AdjustmentType adjustmentType, Integer quantity,
			BigDecimal atPrice, String description, LocalDateTime adjustmentDate) {
		this();
		this.item = item;
		this.adjustmentType = adjustmentType;
		this.quantity = quantity;
		this.atPrice = atPrice;
		this.description = description;
		this.adjustmentDate = adjustmentDate;
		this.calculateTotalValue();
	}

	// Helper method to calculate total value
	public void calculateTotalValue() {
		if (this.quantity != null && this.atPrice != null) {
			this.totalValue = atPrice.multiply(BigDecimal.valueOf(quantity.longValue()));
		}
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ItemEntity getItem() {
		return item;
	}

	public void setItem(ItemEntity item) {
		this.item = item;
	}

	public AdjustmentType getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(AdjustmentType adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
		this.calculateTotalValue();
	}

	public BigDecimal getAtPrice() {
		return atPrice;
	}

	public void setAtPrice(BigDecimal atPrice) {
		this.atPrice = atPrice;
		this.calculateTotalValue();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getAdjustmentDate() {
		return adjustmentDate;
	}

	public void setAdjustmentDate(LocalDateTime adjustmentDate) {
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
}
