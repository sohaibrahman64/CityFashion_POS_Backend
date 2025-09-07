package com.cityfashionpos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.cityfashionpos.entity.TaxRateEntity;

public class ProductResponseDTO {
	private Long id;
	private String name;
	private String hsn;
	private String category;
	private String code;
	private UnitDTO unit;
	private String imageUrl;

	private PricingDTO pricing;
	private StockDTO stock;
	private PurchasePriceTaxesDTO purchasePriceTaxes;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String createdBy;
	private String updatedBy;

	// Nested DTOs (same as request DTO)
	public static class UnitDTO {
		private String value;
		private String label;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}
	}

	public static class PricingDTO {
		private BigDecimal salePrice;
		private String salePriceType;
		private BigDecimal discountAmount;
		private String discountType;

		public BigDecimal getSalePrice() {
			return salePrice;
		}

		public void setSalePrice(BigDecimal salePrice) {
			this.salePrice = salePrice;
		}

		public String getSalePriceType() {
			return salePriceType;
		}

		public void setSalePriceType(String salePriceType) {
			this.salePriceType = salePriceType;
		}

		public BigDecimal getDiscountAmount() {
			return discountAmount;
		}

		public void setDiscountAmount(BigDecimal discountAmount) {
			this.discountAmount = discountAmount;
		}

		public String getDiscountType() {
			return discountType;
		}

		public void setDiscountType(String discountType) {
			this.discountType = discountType;
		}
	}

	public static class StockDTO {
		private Integer openingQuantity;
		private BigDecimal atPrice;
		private LocalDateTime asOfDate;
		private Integer minStock;

		public Integer getOpeningQuantity() {
			return openingQuantity;
		}

		public void setOpeningQuantity(Integer openingQuantity) {
			this.openingQuantity = openingQuantity;
		}

		public BigDecimal getAtPrice() {
			return atPrice;
		}

		public void setAtPrice(BigDecimal atPrice) {
			this.atPrice = atPrice;
		}

		public LocalDateTime getAsOfDate() {
			return asOfDate;
		}

		public void setAsOfDate(LocalDateTime asOfDate) {
			this.asOfDate = asOfDate;
		}

		public Integer getMinStock() {
			return minStock;
		}

		public void setMinStock(Integer minStock) {
			this.minStock = minStock;
		}
	}

	public static class PurchasePriceTaxesDTO {
		private Long productId;
		private BigDecimal purchasePrice;
		private String purchasePriceType;
		private TaxRateEntity taxRate;

		public Long getProductId() {
			return productId;
		}

		public void setProductId(Long productId) {
			this.productId = productId;
		}

		public BigDecimal getPurchasePrice() {
			return purchasePrice;
		}

		public void setPurchasePrice(BigDecimal purchasePrice) {
			this.purchasePrice = purchasePrice;
		}

		public String getPurchasePriceType() {
			return purchasePriceType;
		}

		public void setPurchasePriceType(String purchasePriceType) {
			this.purchasePriceType = purchasePriceType;
		}

		public TaxRateEntity getTaxRate() {
			return taxRate;
		}

		public void setTaxRate(TaxRateEntity taxRate) {
			this.taxRate = taxRate;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHsn() {
		return hsn;
	}

	public void setHsn(String hsn) {
		this.hsn = hsn;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public UnitDTO getUnit() {
		return unit;
	}

	public void setUnit(UnitDTO unit) {
		this.unit = unit;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public PricingDTO getPricing() {
		return pricing;
	}

	public void setPricing(PricingDTO pricing) {
		this.pricing = pricing;
	}

	public StockDTO getStock() {
		return stock;
	}

	public void setStock(StockDTO stock) {
		this.stock = stock;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public PurchasePriceTaxesDTO getPurchasePriceTaxes() {
		return purchasePriceTaxes;
	}

	public void setPurchasePriceTaxes(PurchasePriceTaxesDTO purchasePriceTaxes) {
		this.purchasePriceTaxes = purchasePriceTaxes;
	}

}
