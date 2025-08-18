package com.cityfashionpos.service;

import java.time.LocalDateTime;
import java.util.List;

import com.cityfashionpos.dto.ProductTransactionDTO;
import com.cityfashionpos.model.TransactionType;

public interface ProductTransactionService {

    // Create a new product transaction
    ProductTransactionDTO createTransaction(ProductTransactionDTO transactionDTO);

    // Get all transactions for a specific product
    List<ProductTransactionDTO> getTransactionsByProduct(Long productId);

    // Get transactions by product and date range
    List<ProductTransactionDTO> getTransactionsByProductAndDateRange(Long productId, LocalDateTime startDate,
            LocalDateTime endDate);

    // Get transactions by transaction type
    List<ProductTransactionDTO> getTransactionsByType(TransactionType transactionType);

    // Get transactions by date range
    List<ProductTransactionDTO> getTransactionsByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    // Get transaction by ID
    ProductTransactionDTO getTransactionById(Long transactionId);

    // Update transaction
    ProductTransactionDTO updateTransaction(Long transactionId, ProductTransactionDTO transactionDTO);

    // Delete transaction
    void deleteTransaction(Long transactionId);

    // Get transaction summary for a product
    Object getTransactionSummary(Long productId, LocalDateTime startDate, LocalDateTime endDate);

    // Create transaction from stock adjustment
    ProductTransactionDTO createFromStockAdjustment(Long productId, TransactionType type,
            Double quantity, Double unitPrice, String description,
            Long referenceId, String referenceType, String referenceNumber);

    // Create transaction from sale
    ProductTransactionDTO createFromSale(Long productId, Double quantity, Double unitPrice,
            String description, Long invoiceId, String invoiceNumber);

    // Create transaction from purchase
    ProductTransactionDTO createFromPurchase(Long productId, Double quantity, Double unitPrice,
            String description, Long purchaseId, String purchaseNumber);
}
