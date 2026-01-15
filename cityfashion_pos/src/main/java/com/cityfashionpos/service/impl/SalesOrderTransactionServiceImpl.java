package com.cityfashionpos.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.SalesOrderTransactionRequest;
import com.cityfashionpos.dto.SalesOrderTransactionResponse;
import com.cityfashionpos.entity.NewSalesOrderEntity;
import com.cityfashionpos.entity.PartyEntity;
import com.cityfashionpos.entity.SalesOrderTransactionEntity;
import com.cityfashionpos.repository.NewSalesOrderRepository;
import com.cityfashionpos.repository.PartyRepository;
import com.cityfashionpos.repository.SalesOrderTransactionRepository;
import com.cityfashionpos.service.SalesOrderTransactionService;

@Service
@Transactional
public class SalesOrderTransactionServiceImpl implements SalesOrderTransactionService {
        private static final Logger logger = LoggerFactory.getLogger(SalesOrderTransactionServiceImpl.class);

        @Autowired
        private SalesOrderTransactionRepository salesOrderTransactionRepository;

        @Autowired
        private NewSalesOrderRepository salesOrderRespository;

        @Autowired
        private PartyRepository partyRepository;

        @Override
        public SalesOrderTransactionResponse createSalesOrderTransaction(
                        SalesOrderTransactionRequest request) {
                try {
                        logger.info("Creating sales order transaction for amount: {}", request.getTotalAmount());

                        // Generate transaction number if not provided
                        if (request.getTransactionNumber() == null || request.getTransactionNumber().isEmpty()) {
                                request.setTransactionNumber(generateSalesOrderTransactionNumber());
                        }
                        SalesOrderTransactionEntity entity = mapRequestToEntity(request);

                        entity = salesOrderTransactionRepository.save(entity);

                        logger.info("Sales Order Transaction created successfully with ID: {}", entity.getId());
                        return mapEntityToResponse(entity, true, "Sales Order Transaction created successfully");
                } catch (Exception e) {
                        logger.error("Error creating sales order transaction: {}", e.getMessage(), e);
                        return new SalesOrderTransactionResponse(false,
                                        "Error creating sales transaction: " + e.getMessage());
                }
        }

        @Override
        public List<SalesOrderTransactionResponse> getAllSalesOrderTransactions() {
                try {
                        List<SalesOrderTransactionEntity> entities = salesOrderTransactionRepository.findAll();
                        return entities.stream()
                                        .map(entity -> mapEntityToResponse(entity, true, null))
                                        .collect(Collectors.toList());
                } catch (Exception e) {
                        logger.error("Error fetching all sales transactions: {}", e.getMessage(), e);
                        return List.of();
                }
        }

        @Override
        public String generateSalesOrderTransactionNumber() {
                Long maxId = salesOrderTransactionRepository.findMaxTransactionId();
                if (maxId == null) {
                        maxId = 0L;
                }
                return String.format("ORD-%05d", maxId + 1);
        }

        @Override
        public Map<String, BigDecimal> getTotalSalesOrderAmountsByDateRange(LocalDate fromDate, LocalDate toDate) {
                try {
                        BigDecimal totalSalesOrderAmount = salesOrderTransactionRepository
                                        .getTotalSalesOrderAmountForDateRange(fromDate.toString(),
                                                        toDate.toString());
                        BigDecimal totalOverdueAmount = salesOrderTransactionRepository
                                        .getTotalOverdueAmountForDateRange(fromDate.toString(),
                                                        toDate.toString());
                        BigDecimal totalFulfilledAmount = salesOrderTransactionRepository
                                        .getTotalFulfilledAmountForDateRange(fromDate.toString(),
                                                        toDate.toString());

                        LocalDate firstDayLastMonth = fromDate.minusMonths(1).withDayOfMonth(1);
                        LocalDate lastDayLastMonth = firstDayLastMonth
                                        .withDayOfMonth(firstDayLastMonth.lengthOfMonth());

                        // Ensure values are not null
                        totalSalesOrderAmount = totalSalesOrderAmount != null ? totalSalesOrderAmount
                                        : BigDecimal.ZERO;
                        totalOverdueAmount = totalOverdueAmount != null ? totalOverdueAmount : BigDecimal.ZERO;
                        totalFulfilledAmount = totalFulfilledAmount != null ? totalFulfilledAmount : BigDecimal.ZERO;

                        BigDecimal lastMonthSalesOrderAmount = salesOrderTransactionRepository
                                        .getTotalSalesOrderAmountForDateRange(firstDayLastMonth.toString(),
                                                        lastDayLastMonth.toString());
                        BigDecimal lastMonthOverdueAmount = salesOrderTransactionRepository
                                        .getTotalOverdueAmountForDateRange(
                                                        firstDayLastMonth.toString(),
                                                        lastDayLastMonth.toString());
                        BigDecimal lastMonthFulfilledAmount = salesOrderTransactionRepository
                                        .getTotalFulfilledAmountForDateRange(
                                                        firstDayLastMonth.toString(),
                                                        lastDayLastMonth.toString());

                        lastMonthSalesOrderAmount = lastMonthSalesOrderAmount != null
                                        ? lastMonthSalesOrderAmount
                                        : BigDecimal.ZERO;
                        lastMonthOverdueAmount = lastMonthOverdueAmount != null ? lastMonthOverdueAmount
                                        : BigDecimal.ZERO;
                        lastMonthFulfilledAmount = lastMonthFulfilledAmount != null ? lastMonthFulfilledAmount
                                        : BigDecimal.ZERO;

                        BigDecimal lastMonthTotalSalesOrderAmount = lastMonthOverdueAmount
                                        .add(lastMonthFulfilledAmount);

                        // Calculate percentage change
                        BigDecimal percentageData = calculatePercentageChangeVsLastMonth(totalSalesOrderAmount,
                                        lastMonthTotalSalesOrderAmount);
                        Map<String, BigDecimal> totals = new HashMap<>();
                        totals.put("totalSalesOrderAmount", totalSalesOrderAmount);
                        totals.put("totalOverdueAmount", totalOverdueAmount);
                        totals.put("totalFulfilledAmount", totalFulfilledAmount);
                        totals.put("percentageChange", percentageData);

                        logger.info("Date range totals from {} to {}: Total: {}, Overdue: {}, Fulfilled: {}",
                                        fromDate, toDate, totalSalesOrderAmount, totalOverdueAmount,
                                        totalFulfilledAmount);
                        return totals;
                } catch (Exception e) {
                        logger.error("Error calculating totals for date range {} to {}: {}", fromDate, toDate,
                                        e.getMessage(), e);
                        Map<String, BigDecimal> errorMap = new HashMap<>();
                        errorMap.put("totalSalesOrderAmount", BigDecimal.ZERO);
                        errorMap.put("totalOverdueAmount", BigDecimal.ZERO);
                        errorMap.put("totalFulfilledAmount", BigDecimal.ZERO);
                        return errorMap;
                }
        }

        @Override
        public Map<String, BigDecimal> getTotalFulfilledAndOverdueAmounts() {
                try {
                        // Get current totals (all-time)
                        BigDecimal totalOverdueAmount = salesOrderTransactionRepository.getTotalOverdueAmount();
                        BigDecimal totalFulfilledAmount = salesOrderTransactionRepository
                                        .getTotalFulfilledAmount();

                        LocalDate firstDayLastMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1);
                        LocalDate lastDayLastMonth = firstDayLastMonth
                                        .withDayOfMonth(firstDayLastMonth.lengthOfMonth());

                        // Ensure values are not null
                        totalOverdueAmount = totalOverdueAmount != null ? totalOverdueAmount : BigDecimal.ZERO;
                        totalFulfilledAmount = totalFulfilledAmount != null ? totalFulfilledAmount : BigDecimal.ZERO;

                        BigDecimal totalSalesOrderAmount = totalOverdueAmount.add(totalFulfilledAmount);

                        // Get last month's totals for comparison
                        BigDecimal lastMonthTotalOverdueAmount = salesOrderTransactionRepository
                                        .getLastMonthTotalOverdueAmount(
                                                        firstDayLastMonth.toString(),
                                                        lastDayLastMonth.toString());
                        BigDecimal lastMonthTotalFulfilledAmount = salesOrderTransactionRepository
                                        .getLastMonthTotalFulfilledAmount(
                                                        firstDayLastMonth.toString(),
                                                        lastDayLastMonth.toString());

                        // Ensure last month values are not null, default to 0
                        lastMonthTotalOverdueAmount = lastMonthTotalOverdueAmount != null ? lastMonthTotalOverdueAmount
                                        : BigDecimal.ZERO;
                        lastMonthTotalFulfilledAmount = lastMonthTotalFulfilledAmount != null
                                        ? lastMonthTotalFulfilledAmount
                                        : BigDecimal.ZERO;

                        BigDecimal lastMonthTotalSalesOrderAmount = lastMonthTotalOverdueAmount
                                        .add(lastMonthTotalFulfilledAmount);
                        // Calculate percentage change
                        BigDecimal percentageData = calculatePercentageChangeVsLastMonth(totalSalesOrderAmount,
                                        lastMonthTotalSalesOrderAmount);

                        Map<String, BigDecimal> totals = new HashMap<>();
                        totals.put("totalSalesOrderAmount", totalSalesOrderAmount);
                        totals.put("totalOverdueAmount", totalOverdueAmount);
                        totals.put("totalFulfilledAmount", totalFulfilledAmount);
                        totals.put("percentageChange", percentageData);

                        logger.info(
                                        "Total Sales Order Amount: {}, Total Overdue Amount: {}, Total Fulfilled amount: {}, Last month Sales Order Amount: {}, Percentage change: {}%",
                                        totalSalesOrderAmount, totalOverdueAmount, totalFulfilledAmount,
                                        lastMonthTotalSalesOrderAmount, percentageData);
                        return totals;
                } catch (Exception e) {
                        logger.error("Error calculating total overdue and fulfilled amounts: {}", e.getMessage(), e);
                        Map<String, BigDecimal> errorMap = new HashMap<>();
                        errorMap.put("totalSalesOrderAmount", BigDecimal.ZERO);
                        errorMap.put("totalOverdueAmount", BigDecimal.ZERO);
                        errorMap.put("totalFulfilledAmount", BigDecimal.ZERO);
                        return errorMap;
                }

        }

        @Override
        public BigDecimal calculatePercentageChangeVsLastMonth(BigDecimal currentAmount, BigDecimal lastMonthAmount) {
                if (lastMonthAmount == null || lastMonthAmount.compareTo(BigDecimal.ZERO) == 0) {
                        return currentAmount != null && currentAmount.compareTo(BigDecimal.ZERO) > 0
                                        ? new BigDecimal("100.00")
                                        : BigDecimal.ZERO;
                }

                BigDecimal difference = currentAmount.subtract(lastMonthAmount);
                return difference.divide(lastMonthAmount, 4, RoundingMode.HALF_UP)
                                .multiply(new BigDecimal("100"))
                                .setScale(2, RoundingMode.HALF_UP);
        }

        @Override
        public List<SalesOrderTransactionResponse> getSalesOrderTransactionByDateRange(LocalDate fromDate,
                        LocalDate toDate) {
                try {
                        logger.info("Fetching sales order transaction records for date range: {} to {}", fromDate,
                                        toDate);

                        List<SalesOrderTransactionEntity> transactions = salesOrderTransactionRepository
                                        .findByOrderDateBetween(fromDate.toString(), toDate.toString());

                        List<SalesOrderTransactionResponse> salesOrderTransactionRecords = transactions
                                        .stream()
                                        .map(this::mapSalesOrderEntityToResponse).collect(Collectors.toList());

                        logger.info("Found {} sales order transaction records for date range {} to {}",
                                        salesOrderTransactionRecords.size(), fromDate, toDate);

                        return salesOrderTransactionRecords;
                } catch (Exception e) {
                        logger.error("Error fetching sales order transaction records for date range {} to {}: {}",
                                        fromDate, toDate, e.getMessage(), e);
                        return Collections.emptyList();
                }
        }

        // Helper methods
        private SalesOrderTransactionEntity mapRequestToEntity(SalesOrderTransactionRequest request) {
                SalesOrderTransactionEntity entity = new SalesOrderTransactionEntity();

                entity.setSalesOrderTransactionNumber(request.getTransactionNumber());

                Optional<NewSalesOrderEntity> salesOrderEntityOpt = salesOrderRespository
                                .findById(request.getSalesOrderId());
                if (salesOrderEntityOpt.isPresent()) {
                        entity.setSalesOrderEntity(salesOrderEntityOpt.get());
                }
                entity.setSalesOrderNumber(request.getSalesOrderNumber());
                Optional<PartyEntity> partyOpt = partyRepository.findById(request.getPartyId());
                if (partyOpt.isPresent()) {
                        entity.setPartyEntity(partyOpt.get());
                }
                // entity.setPartyId(request.getPartyId());
                entity.setPartyName(request.getPartyName());
                entity.setOrderDate(request.getOrderDate().toString());
                entity.setDueDate(request.getDueDate().toString());
                entity.setTotalAmount(request.getTotalAmount());
                entity.setAdvanceAmount(request.getAdvanceAmount());
                entity.setTaxAmount(request.getTaxAmount());
                entity.setDiscountAmount(request.getDiscountAmount());
                entity.setItemCount(request.getItemCount());
                entity.setTotalQuantity(request.getTotalQuantity());
                entity.setNotes(request.getNotes());
                entity.setCreatedBy(request.getCreatedBy());
                entity.setBalanceAmount(request.getBalanceAmount());
                entity.setSalesOrderStatus(request.getOrderStatus());
                entity.setPaymentStatus(
                                entity.calculatePaymentStatus(request.getAdvanceAmount(), request.getBalanceAmount()));

                return entity;
        }

        private SalesOrderTransactionResponse mapEntityToResponse(SalesOrderTransactionEntity entity,
                        boolean success, String message) {
                SalesOrderTransactionResponse response = new SalesOrderTransactionResponse(success, message);

                response.setId(entity.getId());
                response.setSalesOrderTransactionNumber(entity.getSalesOrderTransactionNumber());
                response.setSalesOrderId(entity.getSalesOrderEntity().getId());
                response.setSalesOrderNumber(entity.getSalesOrderNumber());
                response.setPartyId(entity.getPartyEntity().getId());
                response.setPartyName(entity.getPartyName());
                response.setOrderDate(LocalDate.parse(entity.getOrderDate()));
                response.setDueDate(LocalDate.parse(entity.getDueDate()));
                response.setTotalAmount(entity.getTotalAmount());
                response.setAdvanceAmount(entity.getAdvanceAmount());
                response.setTaxAmount(entity.getTaxAmount());
                response.setDiscountAmount(entity.getDiscountAmount());
                response.setItemCount(entity.getItemCount());
                response.setTotalQuantity(entity.getTotalQuantity());
                response.setNotes(entity.getNotes());
                response.setStatus(entity.getSalesOrderStatus());
                response.setCreatedAt(LocalDateTime.parse(entity.getCreatedAt()));
                response.setUpdatedAt(LocalDateTime.parse(entity.getUpdatedAt()));
                response.setCreatedBy(entity.getCreatedBy());
                response.setUpdatedBy(entity.getUpdatedBy());

                response.setMessage(message);
                response.setSuccess(success);
                response.setBalanceAmount(entity.getBalanceAmount());

                return response;
        }

        private SalesOrderTransactionResponse mapSalesOrderEntityToResponse(
                        SalesOrderTransactionEntity entity) {
                SalesOrderTransactionResponse response = new SalesOrderTransactionResponse();

                response.setId(entity.getId());
                response.setSalesOrderTransactionNumber(entity.getSalesOrderTransactionNumber());
                response.setSalesOrderId(entity.getSalesOrderEntity().getId());
                response.setSalesOrderNumber(entity.getSalesOrderNumber());
                response.setPartyId(entity.getPartyEntity().getId());
                response.setPartyName(entity.getPartyName());
                response.setOrderDate(LocalDate.parse(entity.getOrderDate()));
                response.setDueDate(LocalDate.parse(entity.getDueDate()));
                response.setTotalAmount(entity.getTotalAmount());
                response.setAdvanceAmount(entity.getAdvanceAmount());
                response.setTaxAmount(entity.getTaxAmount());
                response.setDiscountAmount(entity.getDiscountAmount());
                response.setItemCount(entity.getItemCount());
                response.setTotalQuantity(entity.getTotalQuantity());
                response.setNotes(entity.getNotes());
                response.setStatus(entity.getSalesOrderStatus());
                response.setCreatedAt(LocalDateTime.parse(entity.getCreatedAt()));
                response.setUpdatedAt(LocalDateTime.parse(entity.getUpdatedAt()));
                response.setCreatedBy(entity.getCreatedBy());
                response.setUpdatedBy(entity.getUpdatedBy());

                response.setBalanceAmount(entity.getBalanceAmount());

                return response;
        }

}
