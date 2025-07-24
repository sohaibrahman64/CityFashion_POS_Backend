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
@Table(name = "invoice_payments")
public class InvoicePaymentEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private InvoiceEntity invoiceId;

    @ManyToOne
    @JoinColumn(name = "payment_mode_id")
    private PaymentModeEntity paymentMode;

    @Column(name = "amount_paid")
    private Double amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InvoiceEntity getInvoice() {
		return invoiceId;
	}

	public void setInvoice(InvoiceEntity invoice) {
		this.invoiceId = invoice;
	}

	public PaymentModeEntity getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentModeEntity paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
    
}
