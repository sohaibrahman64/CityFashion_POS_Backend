package com.cityfashionpos.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.ProductTransactionEntity;
import com.cityfashionpos.model.TransactionType;

@Repository
public interface ProductTransactionRepository extends JpaRepository<ProductTransactionEntity, Long> {

    // Find all transactions for a specific product
    List<ProductTransactionEntity> findByProductIdOrderByTransactionDateDesc(Long productId);

    // Find transactions by product and transaction type
    List<ProductTransactionEntity> findByProductIdAndTransactionTypeOrderByTransactionDateDesc(
            Long productId, TransactionType transactionType);

    // Find transactions by product and date range
    @Query("SELECT pt FROM ProductTransactionEntity pt WHERE pt.product.id = :productId " +
            "AND pt.transactionDate BETWEEN :startDate AND :endDate " +
            "ORDER BY pt.transactionDate DESC")
    List<ProductTransactionEntity> findByProductIdAndDateRange(
            @Param("productId") Long productId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    // Find transactions by transaction type
    List<ProductTransactionEntity> findByTransactionTypeOrderByTransactionDateDesc(TransactionType transactionType);

    // Find transactions by date range
    @Query("SELECT pt FROM ProductTransactionEntity pt WHERE pt.transactionDate BETWEEN :startDate AND :endDate " +
            "ORDER BY pt.transactionDate DESC")
    List<ProductTransactionEntity> findByDateRange(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    // Find transactions by reference type and reference ID
    List<ProductTransactionEntity> findByReferenceTypeAndReferenceId(String referenceType, Long referenceId);

    // Find transactions by status
    List<ProductTransactionEntity> findByStatusOrderByTransactionDateDesc(String status);

    // Find recent transactions (last N days)
    @Query("SELECT pt FROM ProductTransactionEntity pt WHERE pt.transactionDate >= :sinceDate " +
            "ORDER BY pt.transactionDate DESC")
    List<ProductTransactionEntity> findRecentTransactions(@Param("sinceDate") LocalDateTime sinceDate);

    // Count transactions by product and type
    long countByProductIdAndTransactionType(Long productId, TransactionType transactionType);

    // Get transaction summary for a product
    @Query("SELECT SUM(CASE WHEN pt.transactionType IN ('PURCHASE', 'STOCK_ADJUSTMENT', 'OPENING_STOCK') THEN pt.quantity ELSE 0 END) as totalIn, "
            +
            "SUM(CASE WHEN pt.transactionType IN ('SALE', 'DAMAGE_LOSS', 'EXPIRY_WRITEOFF') THEN pt.quantity ELSE 0 END) as totalOut "
            +
            "FROM ProductTransactionEntity pt WHERE pt.product.id = :productId")
    Object[] getProductTransactionSummary(@Param("productId") Long productId);
}
