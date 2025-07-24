package com.cityfashionpos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "sales_invoice_items")
public class InvoiceItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(name = "invoice_id")
    private Long invoiceId;    // Reference to Invoice

    @Column(name = "product_id")
    private Long productId;    // Reference to Product
    
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private ProductEntity product;
    
//    @ManyToOne
//    @JoinColumn(name = "invoice_id")
//    private InvoiceEntity invoice;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;      // Unit price at the time of sale

    @Column(name = "total_price")
    private Double total;      // price * quantity
    
    @Column(name = "tax_percent")
    private Double taxPercent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTaxPercent() {
		return taxPercent;
	}

	public void setTaxPercent(Double taxPercent) {
		this.taxPercent = taxPercent;
	}

//	public ProductEntity getProduct() {
//		return product;
//	}
//
//	public void setProduct(ProductEntity product) {
//		this.product = product;
//	}

//	public InvoiceEntity getInvoice() {
//		return invoice;
//	}
//
//	public void setInvoice(InvoiceEntity invoice) {
//		this.invoice = invoice;
//	}
    
}
