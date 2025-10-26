package com.cityfashionpos.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cityfashionpos.entity.ItemTransactionEntity;
import com.cityfashionpos.model.TransactionType;

@Repository
public interface ItemTransactionRepository extends JpaRepository<ItemTransactionEntity, Long> {
    // Find all transactions for a specific product
    List<ItemTransactionEntity> findByItemIdOrderByTransactionDateDesc(Long itemId);

    // Find transactions by product and transaction type
    List<ItemTransactionEntity> findByItemIdAndTransactionTypeOrderByTransactionDateDesc(
            Long itemId, TransactionType transactionType);

    // Find transactions by product and date range
    @Query("SELECT ite FROM ItemTransactionEntity ite WHERE ite.item.id = :itemId " +
            "AND ite.transactionDate BETWEEN :startDate AND :endDate " +
            "ORDER BY ite.transactionDate DESC")
    List<ItemTransactionEntity> findByItemIdAndDateRange(
            @Param("itemId") Long itemId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    // Find transactions by transaction type
    List<ItemTransactionEntity> findByTransactionTypeOrderByTransactionDateDesc(TransactionType transactionType);

    // Find transactions by date range
    @Query("SELECT ite FROM ItemTransactionEntity ite WHERE ite.transactionDate BETWEEN :startDate AND :endDate " +
            "ORDER BY ite.transactionDate DESC")
    List<ItemTransactionEntity> findByDateRange(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    // Find transactions by reference type and reference ID
    List<ItemTransactionEntity> findByReferenceTypeAndReferenceId(String referenceType, Long referenceId);

    // Find transactions by status
    List<ItemTransactionEntity> findByStatusOrderByTransactionDateDesc(String status);

    // Find recent transactions (last N days)
    @Query("SELECT ite FROM ItemTransactionEntity ite WHERE ite.transactionDate >= :sinceDate " +
            "ORDER BY ite.transactionDate DESC")
    List<ItemTransactionEntity> findRecentTransactions(@Param("sinceDate") LocalDateTime sinceDate);

    // Count transactions by product and type
    Long countByItemIdAndTransactionType(Long itemId, TransactionType transactionType);

    // Get transaction summary for a product
    @Query("SELECT SUM(CASE WHEN ite.transactionType IN ('PURCHASE', 'STOCK_ADJUSTMENT', 'OPENING_STOCK') THEN ite.quantity ELSE 0 END) as totalIn, "
            +
            "SUM(CASE WHEN ite.transactionType IN ('SALE', 'DAMAGE_LOSS', 'EXPIRY_WRITEOFF') THEN ite.quantity ELSE 0 END) as totalOut "
            +
            "FROM ItemTransactionEntity ite WHERE ite.item.id = :itemId")
    Object[] getProductTransactionSummary(@Param("itemId") Long itemId);
}
