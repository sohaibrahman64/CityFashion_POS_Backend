package com.cityfashionpos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductRequestDTO {
	private String name;
	private String hsn;
	private String category;
	private String code;
	private UnitDTO unit;
	private String imageData;

	private PricingDTO pricing;
	private StockDTO stock;

	// Nested DTOs
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
		private String salePriceType; // "WITHOUT_TAX" or "WITH_TAX"
		private BigDecimal discountAmount;
		private String discountType; // "PERCENTAGE" or "AMOUNT"

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

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
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
	
}
