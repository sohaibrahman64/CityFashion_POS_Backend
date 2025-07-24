package com.cityfashionpos.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "sales_invoice")
public class InvoiceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "invoice_number", unique = true)
	private String invoiceNumber; // Example: CFK-00001

	@Column(name = "customer_id")
	private Long customerId;
	
	//@Column(name = "invoice_date")
	//private LocalDateTime invoiceDate = LocalDateTime.now(); // Set current timestamp by default
	
	@Column(name = "invoice_date")
	private LocalDate invoiceDate = LocalDate.now();

	@Column(name = "total_amount")
	private Double totalAmount;

	@Column(name = "paid_amount")
	private Double paidAmount;

	@Column(name = "discount_percent")
	private Double discountPercent;

	@ManyToOne
	@JoinColumn(name = "payment_status_id")
	private PaymentStatusEntity paymentStatus;

	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(name = "updated_at")
	private LocalDateTime updatedAt = LocalDateTime.now();
	
	@Column(name = "tax_amount")
	private Double taxAmount;
	
	@Column(name = "subtotal_amount")
	private Double subtotalAmount;
	
	@Column(name = "discount_amount")
	private Double discountAmount;
	
	@Column(name="due_amount")
	private Double dueAmount;
	
	@OneToMany(mappedBy = "invoiceId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<InvoiceItemEntity> items;
	
	@OneToMany(mappedBy = "invoiceId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<InvoicePaymentEntity> payments;

	@PreUpdate
	public void preUpdate() {
		updatedAt = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	/*
	 * public LocalDateTime getInvoiceDate() { return invoiceDate; }
	 */

	/*
	 * public void setInvoiceDate(LocalDateTime invoiceDate) { this.invoiceDate =
	 * invoiceDate; }
	 */
	
	

	public Double getTotalAmount() {
		return totalAmount;
	}

	public LocalDate getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(LocalDate invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public PaymentStatusEntity getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatusEntity paymentStatus) {
		this.paymentStatus = paymentStatus;
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

	public Double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Double getSubtotalAmount() {
		return subtotalAmount;
	}

	public void setSubtotalAmount(Double subtotalAmount) {
		this.subtotalAmount = subtotalAmount;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
	}

//	public CustomerEntity getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(CustomerEntity customer) {
//		this.customer = customer;
//	}

	public List<InvoiceItemEntity> getItems() {
		return items;
	}

	public void setItems(List<InvoiceItemEntity> items) {
		this.items = items;
	}

	public List<InvoicePaymentEntity> getPayments() {
		return payments;
	}

	public void setPayments(List<InvoicePaymentEntity> payments) {
		this.payments = payments;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
}
