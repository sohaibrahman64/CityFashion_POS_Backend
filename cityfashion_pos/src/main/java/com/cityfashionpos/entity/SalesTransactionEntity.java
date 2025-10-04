package com.cityfashionpos.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.cityfashionpos.model.PaymentStatus;
import com.cityfashionpos.model.TransactionType;

@Entity
@Table(name = "sales_transactions")
public class SalesTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_number", unique = true, nullable = false)
    private String transactionNumber;

    @Column(name = "invoice_id")
    private Long invoiceId;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;

    @Column(name = "transaction_time", nullable = false)
    private LocalTime transactionTime;

    @Column(name = "total_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(name = "tax_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal taxAmount = BigDecimal.ZERO;

    @Column(name = "discount_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Column(name = "net_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal netAmount = BigDecimal.ZERO;

    @Column(name = "received_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal receivedAmount = BigDecimal.ZERO;

    @Column(name = "balance_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal balanceAmount = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    @Column(name = "payment_mode")
    private String paymentMode;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType = TransactionType.SALE;

    @Column(name = "item_count", nullable = false)
    private Integer itemCount = 0;

    @Column(name = "total_quantity", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalQuantity = BigDecimal.ZERO;

    @Column(name = "profit_margin", precision = 15, scale = 2)
    private BigDecimal profitMargin;

    @Column(name = "cost_of_goods_sold", precision = 15, scale = 2)
    private BigDecimal costOfGoodsSold;

    @Column(name = "sales_person_id")
    private Long salesPersonId;

    @Column(name = "sales_person_name")
    private String salesPersonName;

    @Column(name = "notes", length = 1000)
    private String notes;

    @Column(name = "status", nullable = false)
    private String status = "COMPLETED";

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "created_by")
    private String createdBy = "system";

    @Column(name = "updated_by")
    private String updatedBy = "system";

    // Relationships
    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "invoice_id", insertable = false, updatable = false)
    private NewSalesInvoiceEntity invoice;

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Default constructor
    public SalesTransactionEntity() {
        this.transactionDate = LocalDate.now();
        this.transactionTime = LocalTime.now();
    }

    // Constructor with basic fields
    public SalesTransactionEntity(String transactionNumber, BigDecimal totalAmount,
            BigDecimal receivedAmount, BigDecimal balanceAmount) {
        this();
        this.transactionNumber = transactionNumber;
        this.totalAmount = totalAmount;
        this.receivedAmount = receivedAmount;
        this.balanceAmount = balanceAmount;
        this.netAmount = totalAmount;
        calculatePaymentStatus();
    }

    // Business logic methods
    public void calculatePaymentStatus() {
        if (balanceAmount == null || balanceAmount.compareTo(BigDecimal.ZERO) == 0) {
            this.paymentStatus = PaymentStatus.PAID;
        } else if (receivedAmount != null && receivedAmount.compareTo(BigDecimal.ZERO) > 0) {
            this.paymentStatus = PaymentStatus.PARTIAL;
        } else {
            this.paymentStatus = PaymentStatus.UNPAID;
        }
    }

    public void calculateNetAmount() {
        BigDecimal grossAmount = totalAmount.add(taxAmount);
        this.netAmount = grossAmount.subtract(discountAmount);
        this.balanceAmount = netAmount.subtract(receivedAmount);
        calculatePaymentStatus();
    }

    public void calculateProfitMargin() {
        if (costOfGoodsSold != null && netAmount.compareTo(BigDecimal.ZERO) > 0) {
            this.profitMargin = netAmount.subtract(costOfGoodsSold);
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LocalTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public BigDecimal getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(BigDecimal totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(BigDecimal profitMargin) {
        this.profitMargin = profitMargin;
    }

    public BigDecimal getCostOfGoodsSold() {
        return costOfGoodsSold;
    }

    public void setCostOfGoodsSold(BigDecimal costOfGoodsSold) {
        this.costOfGoodsSold = costOfGoodsSold;
    }

    public Long getSalesPersonId() {
        return salesPersonId;
    }

    public void setSalesPersonId(Long salesPersonId) {
        this.salesPersonId = salesPersonId;
    }

    public String getSalesPersonName() {
        return salesPersonName;
    }

    public void setSalesPersonName(String salesPersonName) {
        this.salesPersonName = salesPersonName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public NewSalesInvoiceEntity getInvoice() {
        return invoice;
    }

    public void setInvoice(NewSalesInvoiceEntity invoice) {
        this.invoice = invoice;
    }

    @Override
    public String toString() {
        return "SalesTransactionEntity{" +
                "id=" + id +
                ", transactionNumber='" + transactionNumber + '\'' +
                ", transactionDate=" + transactionDate +
                ", netAmount=" + netAmount +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
