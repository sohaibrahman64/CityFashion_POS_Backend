package com.cityfashionpos.service;

import java.time.LocalDateTime;
import java.util.List;

import com.cityfashionpos.dto.ItemTransactionDTO;
import com.cityfashionpos.dto.ProductTransactionDTO;
import com.cityfashionpos.model.TransactionType;

public interface ItemTransactionService {

    // Create a new product transaction
    ItemTransactionDTO createTransaction(ItemTransactionDTO transactionDTO);

    // Create multiple product transactions
    List<ItemTransactionDTO> createTransactions(List<ItemTransactionDTO> transactionDTOs);

    // Get all transactions for a specific product
    List<ItemTransactionDTO> getTransactionsByItemId(Long itemId);

    // Get transactions by product and date range
    List<ItemTransactionDTO> getTransactionsByProductAndDateRange(Long productId, LocalDateTime startDate,
            LocalDateTime endDate);

    // Get transactions by transaction type
    List<ItemTransactionDTO> getTransactionsByType(TransactionType transactionType);

    // Get transactions by date range
    List<ItemTransactionDTO> getTransactionsByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    // Get transaction by ID
    ItemTransactionDTO getTransactionById(Long transactionId);

    // Update transaction
    ItemTransactionDTO updateTransaction(Long transactionId, ItemTransactionDTO transactionDTO);

    // Delete transaction
    void deleteTransaction(Long transactionId);

    // Get transaction summary for a product
    Object getTransactionSummary(Long productId, LocalDateTime startDate, LocalDateTime endDate);

    // Create transaction from stock adjustment
    ItemTransactionDTO createFromStockAdjustment(Long productId, TransactionType type,
            Double quantity, Double unitPrice, String description,
            Long referenceId, String referenceType, String referenceNumber);

    // Create transaction from sale
    ItemTransactionDTO createFromSale(Long productId, Double quantity, Double unitPrice,
            String description, Long invoiceId, String invoiceNumber);

    // Create transaction from purchase
    ItemTransactionDTO createFromPurchase(Long productId, Double quantity, Double unitPrice,
            String description, Long purchaseId, String purchaseNumber);
}
