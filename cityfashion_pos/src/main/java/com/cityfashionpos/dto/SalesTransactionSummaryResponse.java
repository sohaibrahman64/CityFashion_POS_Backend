package com.cityfashionpos.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalesTransactionSummaryResponse {

    private BigDecimal totalSalesAmount = BigDecimal.ZERO;
    private BigDecimal totalReceivedAmount = BigDecimal.ZERO;
    private BigDecimal totalBalanceAmount = BigDecimal.ZERO;
    private BigDecimal percentageChangeVsLastMonth = BigDecimal.ZERO;
    private Long totalTransactions = 0L;
    private String period;
    private String comparisonPeriod;

    // Additional summary fields
    private BigDecimal averageTransactionValue = BigDecimal.ZERO;
    private BigDecimal totalTaxAmount = BigDecimal.ZERO;
    private BigDecimal totalDiscountAmount = BigDecimal.ZERO;
    private Long paidTransactions = 0L;
    private Long partialTransactions = 0L;
    private Long unpaidTransactions = 0L;

    // Success/Error handling
    private boolean success = true;
    private String message;

    // Default constructor
    public SalesTransactionSummaryResponse() {
    }

    // Constructor for error response
    public SalesTransactionSummaryResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Business logic methods
    public void calculatePercentageChange(BigDecimal currentAmount, BigDecimal previousAmount) {
        if (previousAmount == null || previousAmount.compareTo(BigDecimal.ZERO) == 0) {
            if (currentAmount != null && currentAmount.compareTo(BigDecimal.ZERO) > 0) {
                this.percentageChangeVsLastMonth = new BigDecimal("100.00");
            } else {
                this.percentageChangeVsLastMonth = BigDecimal.ZERO;
            }
        } else {
            BigDecimal difference = currentAmount.subtract(previousAmount);
            this.percentageChangeVsLastMonth = difference.divide(previousAmount, 4, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal("100"))
                    .setScale(2, RoundingMode.HALF_UP);
        }
    }

    public void calculateAverageTransactionValue() {
        if (totalTransactions > 0) {
            this.averageTransactionValue = totalSalesAmount.divide(
                    new BigDecimal(totalTransactions), 2, RoundingMode.HALF_UP);
        } else {
            this.averageTransactionValue = BigDecimal.ZERO;
        }
    }

    // Getters and Setters
    public BigDecimal getTotalSalesAmount() {
        return totalSalesAmount;
    }

    public void setTotalSalesAmount(BigDecimal totalSalesAmount) {
        this.totalSalesAmount = totalSalesAmount != null ? totalSalesAmount : BigDecimal.ZERO;
    }

    public BigDecimal getTotalReceivedAmount() {
        return totalReceivedAmount;
    }

    public void setTotalReceivedAmount(BigDecimal totalReceivedAmount) {
        this.totalReceivedAmount = totalReceivedAmount != null ? totalReceivedAmount : BigDecimal.ZERO;
    }

    public BigDecimal getTotalBalanceAmount() {
        return totalBalanceAmount;
    }

    public void setTotalBalanceAmount(BigDecimal totalBalanceAmount) {
        this.totalBalanceAmount = totalBalanceAmount != null ? totalBalanceAmount : BigDecimal.ZERO;
    }

    public BigDecimal getPercentageChangeVsLastMonth() {
        return percentageChangeVsLastMonth;
    }

    public void setPercentageChangeVsLastMonth(BigDecimal percentageChangeVsLastMonth) {
        this.percentageChangeVsLastMonth = percentageChangeVsLastMonth != null ? percentageChangeVsLastMonth
                : BigDecimal.ZERO;
    }

    public Long getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(Long totalTransactions) {
        this.totalTransactions = totalTransactions != null ? totalTransactions : 0L;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getComparisonPeriod() {
        return comparisonPeriod;
    }

    public void setComparisonPeriod(String comparisonPeriod) {
        this.comparisonPeriod = comparisonPeriod;
    }

    public BigDecimal getAverageTransactionValue() {
        return averageTransactionValue;
    }

    public void setAverageTransactionValue(BigDecimal averageTransactionValue) {
        this.averageTransactionValue = averageTransactionValue != null ? averageTransactionValue : BigDecimal.ZERO;
    }

    public BigDecimal getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount != null ? totalTaxAmount : BigDecimal.ZERO;
    }

    public BigDecimal getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public void setTotalDiscountAmount(BigDecimal totalDiscountAmount) {
        this.totalDiscountAmount = totalDiscountAmount != null ? totalDiscountAmount : BigDecimal.ZERO;
    }

    public Long getPaidTransactions() {
        return paidTransactions;
    }

    public void setPaidTransactions(Long paidTransactions) {
        this.paidTransactions = paidTransactions != null ? paidTransactions : 0L;
    }

    public Long getPartialTransactions() {
        return partialTransactions;
    }

    public void setPartialTransactions(Long partialTransactions) {
        this.partialTransactions = partialTransactions != null ? partialTransactions : 0L;
    }

    public Long getUnpaidTransactions() {
        return unpaidTransactions;
    }

    public void setUnpaidTransactions(Long unpaidTransactions) {
        this.unpaidTransactions = unpaidTransactions != null ? unpaidTransactions : 0L;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SalesTransactionSummaryResponse{" +
                "totalSalesAmount=" + totalSalesAmount +
                ", totalReceivedAmount=" + totalReceivedAmount +
                ", totalBalanceAmount=" + totalBalanceAmount +
                ", percentageChangeVsLastMonth=" + percentageChangeVsLastMonth +
                ", totalTransactions=" + totalTransactions +
                ", period='" + period + '\'' +
                ", success=" + success +
                '}';
    }
}
