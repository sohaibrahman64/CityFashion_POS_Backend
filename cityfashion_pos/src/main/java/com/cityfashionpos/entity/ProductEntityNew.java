package com.cityfashionpos.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale.Category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cityfashionpos.model.DiscountType;
import com.cityfashionpos.model.PriceType;
import com.cityfashionpos.model.TaxType;

@Entity
@Table(name = "products_new")
public class ProductEntityNew {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "hsn_code")
	private String hsn;

	@Column(name = "product_code")
	private String code;

	@Column(name = "unit_value")
	private String unitValue;
	
	@Column(name = "unit_label")
    private String unitLabel;

	@Column(name = "image_url")
	private String imageUrl;

	// Purchase Price and Taxes fields
	@Column(name = "purchase_price", precision = 10, scale = 2)
	private BigDecimal purchasePrice;

	@Enumerated(EnumType.STRING)
	@Column(name = "purchase_price_type")
	private PriceType purchasePriceType;

	@Enumerated(EnumType.STRING)
	@Column(name = "tax_type")
	private TaxType taxType;

	// Other existing fields...
	@Column(name = "sale_price", precision = 10, scale = 2)
	private BigDecimal salePrice;

	@Enumerated(EnumType.STRING)
	@Column(name = "sale_price_type")
	private PriceType salePriceType;

	@Column(name = "discount_amount", precision = 10, scale = 2)
	private BigDecimal discountAmount;

	@Enumerated(EnumType.STRING)
	@Column(name = "discount_type")
	private DiscountType discountType;

	@Column(name = "opening_quantity")
	private Integer openingQuantity;

	@Column(name = "at_price", precision = 10, scale = 2)
	private BigDecimal atPrice;

	@Column(name = "as_of_date")
	private LocalDateTime asOfDate;

	@Column(name = "min_stock")
	private Integer minStock;

	@Column(name = "location")
	private String location;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private ProductCategoryEntityNew category;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "image_data", columnDefinition = "LONGTEXT")
    private String imageData;
	
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "updated_by")
    private String updatedBy;
	
	// Constructors
	public ProductEntityNew() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

	// Getters and Setters
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

	public String getCode() {
		return code;
	}

	public void setCode(String productCode) {
		this.code = productCode;
	}

	public String getUnit() {
		return unitValue;
	}

	public void setUnit(String unit) {
		this.unitValue = unit;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public PriceType getPurchasePriceType() {
		return purchasePriceType;
	}

	public void setPurchasePriceType(PriceType purchasePriceType) {
		this.purchasePriceType = purchasePriceType;
	}

	public TaxType getTaxType() {
		return taxType;
	}

	public void setTaxType(TaxType taxType) {
		this.taxType = taxType;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public PriceType getSalePriceType() {
		return salePriceType;
	}

	public void setSalePriceType(PriceType salePriceType) {
		this.salePriceType = salePriceType;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ProductCategoryEntityNew getCategory() {
		return category;
	}

	public void setCategory(ProductCategoryEntityNew category) {
		this.category = category;
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

	public String getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(String unitValue) {
		this.unitValue = unitValue;
	}

	public String getUnitLabel() {
		return unitLabel;
	}

	public void setUnitLabel(String unitLabel) {
		this.unitLabel = unitLabel;
	}

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
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
	
	
	
}
