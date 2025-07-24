package com.cityfashionpos.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="name", nullable = false)
	private String name;

	@Column(name = "barcode", nullable = false, unique = true)
	private String barcode;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private ProductCategoryEntity category;

	@Column(name = "stock_quantity")
	private Integer stockQuantity;

    @Column(name = "tax_percent")
	private Double tax_percent;

	private String size;

	@Column(name = "created_date")
	private LocalDateTime createdDate = LocalDateTime.now();

	@Column(name = "updated_date")
	private LocalDateTime updatedDate = LocalDateTime.now();

	private Boolean is_active = true;

	@Column(name = "stock_date")
	private LocalDate stockDate;

//	@Column(name = "bill_serial_number")
//	private Integer billSerialNumber;

//	@Column(name = "supplier_name")
//	private String supplierName;

	@Column(name = "buy_price")
	private Double buyPrice;

	@Column(name = "mrp")
	private Double mrp;

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

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}


	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Double getTax_percent() {
		return tax_percent;
	}

	public void setTax_percent(Double tax_percent) {
		this.tax_percent = tax_percent;
	}

	public ProductCategoryEntity getCategory() {
		return category;
	}

	public void setCategory(ProductCategoryEntity category) {
		this.category = category;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public LocalDate getStockDate() {
		return stockDate;
	}

	public void setStockDate(LocalDate stockDate) {
		this.stockDate = stockDate;
	}

//	public Integer getBillSerialNumber() {
//		return billSerialNumber;
//	}
//
//	public void setBillSerialNumber(Integer billSerialNumber) {
//		this.billSerialNumber = billSerialNumber;
//	}
//
//	public String getSupplierName() {
//		return supplierName;
//	}
//
//	public void setSupplierName(String supplierName) {
//		this.supplierName = supplierName;
//	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Double getMrp() {
		return mrp;
	}

	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}

}
