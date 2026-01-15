package com.cityfashionpos.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

import com.cityfashionpos.model.PaymentStatus;
import com.cityfashionpos.model.TransactionType;

@Entity
@Table(name = "sales_order_transaction")
public class SalesOrderTransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sales_order_transaction_number", unique = true, nullable = false)
    private String salesOrderTransactionNumber;

    @Column(name = "sales_order_number")
    private String salesOrderNumber;

    @Column(name = "party_name")
    private String partyName;

    @Column(name = "order_date", nullable = false)
    private String orderDate;

    @Column(name = "due_date", nullable = false)
    private String dueDate;

    @Column(name = "sales_order_status", nullable = false)
    private String salesOrderStatus;

    @Column(name = "total_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(name = "advance_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal advanceAmount = BigDecimal.ZERO;

    @Column(name = "tax_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal taxAmount = BigDecimal.ZERO;

    @Column(name = "discount_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Column(name = "balance_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal balanceAmount = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType = TransactionType.SALES_ORDER;

    @Column(name = "item_count", nullable = false)
    private Integer itemCount = 0;

    @Column(name = "total_quantity", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalQuantity = BigDecimal.ZERO;

    @Column(name = "profit_margin", precision = 15, scale = 2)
    private BigDecimal profitMargin;

    @Column(name = "cost_of_goods_sold", precision = 15, scale = 2)
    private BigDecimal costOfGoodsSold;

    @Column(name = "notes", length = 1000)
    private String notes;

    @Column(name = "created_at", nullable = false, updatable = false)
    private String createdAt = LocalDateTime.now().toString();

    @Column(name = "updated_at", nullable = false)
    private String updatedAt = LocalDateTime.now().toString();

    @Column(name = "created_by")
    private String createdBy = "system";

    @Column(name = "updated_by")
    private String updatedBy = "system";

    // Relationships
    @ManyToOne
    @JoinColumn(name = "party_id")
    private PartyEntity partyEntity;

    @ManyToOne
    @JoinColumn(name = "sales_order_id")
    private NewSalesOrderEntity salesOrderEntity;

    // Default constructor
    public SalesOrderTransactionEntity() {
        this.orderDate = LocalDate.now().toString();
        // calculatePaymentStatus();
    }

    // Constructor with basic fields
    public SalesOrderTransactionEntity(String salesOrderNumber, BigDecimal totalAmount,
            BigDecimal advanceAmount, BigDecimal balanceAmount) {
        this();
        this.salesOrderNumber = salesOrderNumber;
        this.totalAmount = totalAmount;
        this.advanceAmount = advanceAmount;
        this.balanceAmount = balanceAmount;
        // calculatePaymentStatus();
    }

    // Business logic methods
    public PaymentStatus calculatePaymentStatus(BigDecimal advanceAmount, BigDecimal balanceAmount) {
        PaymentStatus paymentStatus = PaymentStatus.UNPAID;
        if (balanceAmount == null || balanceAmount.compareTo(BigDecimal.ZERO) == 0) {
            paymentStatus = PaymentStatus.PAID;
        } else if (advanceAmount != null && advanceAmount.compareTo(BigDecimal.ZERO) > 0) {
            paymentStatus = PaymentStatus.PARTIAL;
        } else {
            paymentStatus = PaymentStatus.UNPAID;
        }
        return paymentStatus;
    }

    public void calculateNetAmount() {
        BigDecimal grossAmount = totalAmount.add(taxAmount);
        this.totalAmount = grossAmount.subtract(discountAmount);
        this.balanceAmount = totalAmount.subtract(advanceAmount);
        // calculatePaymentStatus();
    }

    public void calculateProfitMargin() {
        if (costOfGoodsSold != null && totalAmount.compareTo(BigDecimal.ZERO) > 0) {
            this.profitMargin = totalAmount.subtract(costOfGoodsSold);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalesOrderTransactionNumber() {
        return salesOrderTransactionNumber;
    }

    public void setSalesOrderTransactionNumber(String salesOrderTransactionNumber) {
        this.salesOrderTransactionNumber = salesOrderTransactionNumber;
    }

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(String salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(BigDecimal advanceAmount) {
        this.advanceAmount = advanceAmount;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
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

    public PartyEntity getPartyEntity() {
        return partyEntity;
    }

    public void setPartyEntity(PartyEntity party) {
        this.partyEntity = party;
    }

    public NewSalesOrderEntity getSalesOrderEntity() {
        return salesOrderEntity;
    }

    public void setSalesOrderEntity(NewSalesOrderEntity salesOrderEntity) {
        this.salesOrderEntity = salesOrderEntity;
    }

    public String getSalesOrderStatus() {
        return salesOrderStatus;
    }

    public void setSalesOrderStatus(String orderStatus) {
        this.salesOrderStatus = orderStatus;
    }
}
