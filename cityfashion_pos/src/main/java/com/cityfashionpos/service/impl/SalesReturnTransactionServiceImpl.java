package com.cityfashionpos.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

import com.cityfashionpos.dto.SalesReturnTransactionRequest;
import com.cityfashionpos.dto.SalesReturnTransactionResponse;
import com.cityfashionpos.entity.NewSalesInvoiceEntity;
import com.cityfashionpos.entity.NewSalesReturnEntity;
import com.cityfashionpos.entity.PartyEntity;
import com.cityfashionpos.entity.SalesReturnTransactionEntity;
import com.cityfashionpos.repository.NewSalesInvoiceRepository;
import com.cityfashionpos.repository.NewSalesReturnRepository;
import com.cityfashionpos.repository.PartyRepository;
import com.cityfashionpos.repository.SalesReturnTransactionRepository;
import com.cityfashionpos.service.SalesReturnTransactionService;

@Service
@Transactional
public class SalesReturnTransactionServiceImpl implements SalesReturnTransactionService {
    private static final Logger logger = LoggerFactory.getLogger(SalesReturnTransactionServiceImpl.class);

    @Autowired
    private SalesReturnTransactionRepository salesReturnTransactionRepository;

    @Autowired
    private NewSalesReturnRepository salesReturnRepository;

    @Autowired
    private NewSalesInvoiceRepository newSalesInvoiceRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Override
    public SalesReturnTransactionResponse createSalesReturnTransaction(SalesReturnTransactionRequest request) {
        try {
            logger.info("Creating sales return transaction for amount: {}", request.getTotalAmount());

            // Generate transaction number if not provided
            if (request.getSalesReturnTransactionNumber() == null
                    || request.getSalesReturnTransactionNumber().isEmpty()) {
                request.setSalesReturnTransactionNumber(generateSalesReturnTransactionNumber());
            }
            SalesReturnTransactionEntity entity = mapRequestToEntity(request);

            entity = salesReturnTransactionRepository.save(entity);

            logger.info("Sales Return Transaction created successfully with ID: {}", entity.getId());
            return mapEntityToResponse(entity, true, "Sales Return Transaction created successfully");
        } catch (Exception e) {
            logger.error("Error creating sales return transaction: {}", e.getMessage(), e);
            return new SalesReturnTransactionResponse(false,
                    "Error creating sales return transaction: " + e.getMessage());
        }
    }

    @Override
    public List<SalesReturnTransactionResponse> getAllSalesReturnTransactions() {
        try {
            List<SalesReturnTransactionEntity> entities = salesReturnTransactionRepository.findAll();
            return entities.stream()
                    .map(entity -> mapEntityToResponse(entity, true, null))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching all sales return transactions: {}", e.getMessage(), e);
            return List.of();
        }
    }

    @Override
    public Map<String, BigDecimal> getTotalPaidAndBalanceAmounts() {
        try {
            // Get current totals (all-time)
            BigDecimal totalBalanceAmount = salesReturnTransactionRepository.getTotalBalanceAmount();
            BigDecimal totalPaidAmount = salesReturnTransactionRepository
                    .getTotalPaidAmount();

            LocalDate firstDayLastMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1);
            LocalDate lastDayLastMonth = firstDayLastMonth
                    .withDayOfMonth(firstDayLastMonth.lengthOfMonth());

            // Ensure values are not null
            totalBalanceAmount = totalBalanceAmount != null ? totalBalanceAmount : BigDecimal.ZERO;
            totalPaidAmount = totalPaidAmount != null ? totalPaidAmount : BigDecimal.ZERO;

            BigDecimal totalSalesReturnAmount = totalBalanceAmount.add(totalPaidAmount);

            // Get last month's totals for comparison
            BigDecimal lastMonthTotalBalanceAmount = salesReturnTransactionRepository
                    .getLastMonthTotalBalanceAmount(
                            firstDayLastMonth.toString(),
                            lastDayLastMonth.toString());
            BigDecimal lastMonthTotalPaidAmount = salesReturnTransactionRepository
                    .getLastMonthTotalPaidAmount(
                            firstDayLastMonth.toString(),
                            lastDayLastMonth.toString());

            // Ensure last month values are not null, default to 0
            lastMonthTotalBalanceAmount = lastMonthTotalBalanceAmount != null ? lastMonthTotalBalanceAmount
                    : BigDecimal.ZERO;
            lastMonthTotalPaidAmount = lastMonthTotalPaidAmount != null
                    ? lastMonthTotalPaidAmount
                    : BigDecimal.ZERO;

            BigDecimal lastMonthTotalSalesReturnAmount = lastMonthTotalBalanceAmount
                    .add(lastMonthTotalPaidAmount);
            // Calculate percentage change
            BigDecimal percentageData = calculatePercentageChangeVsLastMonth(totalSalesReturnAmount,
                    lastMonthTotalSalesReturnAmount);

            Map<String, BigDecimal> totals = new HashMap<>();
            totals.put("totalSalesReturnAmount", totalSalesReturnAmount);
            totals.put("totalBalanceAmount", totalBalanceAmount);
            totals.put("totalPaidAmount", totalPaidAmount);
            totals.put("percentageChange", percentageData);

            logger.info(
                    "Total Sales Return Amount: {}, Total Balance Amount: {}, Total Paid Amount: {}, Last month Sales Return Amount: {}, Percentage change: {}%",
                    totalSalesReturnAmount, totalBalanceAmount, totalPaidAmount,
                    lastMonthTotalSalesReturnAmount, percentageData);
            return totals;
        } catch (Exception e) {
            logger.error("Error calculating total balance and paid amounts: {}", e.getMessage(), e);
            Map<String, BigDecimal> errorMap = new HashMap<>();
            errorMap.put("totalSalesReturnAmount", BigDecimal.ZERO);
            errorMap.put("totalBalanceAmount", BigDecimal.ZERO);
            errorMap.put("totalPaidAmount", BigDecimal.ZERO);
            return errorMap;
        }
    }

    @Override
    public Map<String, BigDecimal> getTotalSalesReturnAmountsByDateRange(LocalDate fromDate, LocalDate toDate) {
        try {
            BigDecimal totalSalesReturnAmount = salesReturnTransactionRepository
                    .getTotalSalesReturnAmountForDateRange(fromDate.toString(),
                            toDate.toString());
            BigDecimal totalBalanceAmount = salesReturnTransactionRepository
                    .getTotalBalanceAmountForDateRange(fromDate.toString(),
                            toDate.toString());
            BigDecimal totalPaidAmount = salesReturnTransactionRepository
                    .getTotalPaidAmountForDateRange(fromDate.toString(),
                            toDate.toString());

            LocalDate firstDayLastMonth = fromDate.minusMonths(1).withDayOfMonth(1);
            LocalDate lastDayLastMonth = firstDayLastMonth
                    .withDayOfMonth(firstDayLastMonth.lengthOfMonth());

            // Ensure values are not null
            totalSalesReturnAmount = totalSalesReturnAmount != null ? totalSalesReturnAmount
                    : BigDecimal.ZERO;
            totalBalanceAmount = totalBalanceAmount != null ? totalBalanceAmount : BigDecimal.ZERO;
            totalPaidAmount = totalPaidAmount != null ? totalPaidAmount : BigDecimal.ZERO;

            BigDecimal lastMonthSalesReturnAmount = salesReturnTransactionRepository
                    .getTotalSalesReturnAmountForDateRange(firstDayLastMonth.toString(),
                            lastDayLastMonth.toString());
            BigDecimal lastMonthBalanceAmount = salesReturnTransactionRepository
                    .getTotalBalanceAmountForDateRange(
                            firstDayLastMonth.toString(),
                            lastDayLastMonth.toString());
            BigDecimal lastMonthPaidAmount = salesReturnTransactionRepository
                    .getTotalPaidAmountForDateRange(
                            firstDayLastMonth.toString(),
                            lastDayLastMonth.toString());

            lastMonthSalesReturnAmount = lastMonthSalesReturnAmount != null
                    ? lastMonthSalesReturnAmount
                    : BigDecimal.ZERO;
            lastMonthBalanceAmount = lastMonthBalanceAmount != null ? lastMonthBalanceAmount
                    : BigDecimal.ZERO;
            lastMonthPaidAmount = lastMonthPaidAmount != null ? lastMonthPaidAmount
                    : BigDecimal.ZERO;

            BigDecimal lastMonthTotalSalesReturnAmount = lastMonthBalanceAmount
                    .add(lastMonthPaidAmount);

            // Calculate percentage change
            BigDecimal percentageData = calculatePercentageChangeVsLastMonth(totalSalesReturnAmount,
                    lastMonthTotalSalesReturnAmount);
            Map<String, BigDecimal> totals = new HashMap<>();
            totals.put("totalSalesReturnAmount", totalSalesReturnAmount);
            totals.put("totalBalanceAmount", totalBalanceAmount);
            totals.put("totalPaidAmount", totalPaidAmount);
            totals.put("percentageChange", percentageData);

            logger.info("Date range totals from {} to {}: Total: {}, Balance: {}, Paid: {}, Percentage Change: {}",
                    fromDate, toDate, totalSalesReturnAmount, totalBalanceAmount,
                    totalPaidAmount, percentageData);
            return totals;
        } catch (Exception e) {
            logger.error("Error calculating totals for date range {} to {}: {}", fromDate, toDate,
                    e.getMessage(), e);
            Map<String, BigDecimal> errorMap = new HashMap<>();
            errorMap.put("totalSalesReturnAmount", BigDecimal.ZERO);
            errorMap.put("totalBalanceAmount", BigDecimal.ZERO);
            errorMap.put("totalPaidAmount", BigDecimal.ZERO);
            errorMap.put("percentageChange", BigDecimal.ZERO);
            return errorMap;
        }
    }

    @Override
    public List<SalesReturnTransactionResponse> getSalesReturnTransactionsByDateRange(LocalDate fromDate,
            LocalDate toDate) {
        try {
            logger.info("Fetching sales return transaction records for date range: {} to {}", fromDate,
                    toDate);

            List<SalesReturnTransactionEntity> transactions = salesReturnTransactionRepository
                    .findByTransactionDateBetween(fromDate.toString(), toDate.toString());

            List<SalesReturnTransactionResponse> salesReturnTransactionRecords = transactions
                    .stream()
                    .map(this::mapSalesReturnTransactionEntityToResponse).collect(Collectors.toList());

            logger.info("Found {} sales return transaction records for date range {} to {}",
                    salesReturnTransactionRecords.size(), fromDate, toDate);

            return salesReturnTransactionRecords;
        } catch (Exception e) {
            logger.error("Error fetching sales return transaction records for date range {} to {}: {}",
                    fromDate, toDate, e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public String generateSalesReturnTransactionNumber() {
        Long maxId = salesReturnTransactionRepository.findMaxTransactionId();
        if (maxId == null) {
            maxId = 0L;
        }
        return String.format("CRED-%05d", maxId + 1);
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

    // Helper methods
    private SalesReturnTransactionEntity mapRequestToEntity(SalesReturnTransactionRequest request) {
        SalesReturnTransactionEntity entity = new SalesReturnTransactionEntity();

        entity.setSalesReturnTransactionNumber(request.getSalesReturnTransactionNumber());

        Optional<NewSalesReturnEntity> salesReturnEntityOpt = salesReturnRepository
                .findById(request.getSalesReturnId());
        if (salesReturnEntityOpt.isPresent()) {
            entity.setSalesReturnEntity(salesReturnEntityOpt.get());
        }

        Optional<NewSalesInvoiceEntity> salesInvoiceEntityOpt = newSalesInvoiceRepository
                .findById(request.getInvoiceId());
        if (salesInvoiceEntityOpt.isPresent()) {
            entity.setNewSalesInvoiceEntity(salesInvoiceEntityOpt.get());
        }
        entity.setSalesReturnTransactionNumber(request.getSalesReturnTransactionNumber());
        Optional<PartyEntity> partyOpt = partyRepository.findById(request.getPartyId());
        if (partyOpt.isPresent()) {
            entity.setPartyEntity(partyOpt.get());
        }
        entity.setPartyName(request.getPartyName());
        entity.setTransactionDate(request.getTransactionDate().toString());
        entity.setTransactionTime(request.getTransactionTime().toString());
        entity.setPaymentStatus(request.getPaymentStatus());
        entity.setTotalAmount(request.getTotalAmount());
        entity.setPaidAmount(request.getPaidAmount());
        entity.setTaxAmount(request.getTaxAmount());
        entity.setDiscountAmount(request.getDiscountAmount());
        entity.setItemCount(request.getItemCount());
        entity.setTotalQuantity(request.getTotalQuantity());
        entity.setNotes(request.getNotes());
        entity.setCreatedBy(request.getCreatedBy());
        entity.setBalanceAmount(request.getBalanceAmount());
        entity.setPaymentMode(request.getPaymentMode());
        entity.setTransactionType(request.getTransactionType());
        return entity;
    }

    private SalesReturnTransactionResponse mapEntityToResponse(SalesReturnTransactionEntity entity,
            boolean success, String message) {
        SalesReturnTransactionResponse response = new SalesReturnTransactionResponse(success, message);

        response.setId(entity.getId());
        response.setSalesReturnTransactionNumber(entity.getSalesReturnTransactionNumber());
        response.setSalesReturnId(entity.getSalesReturnEntity().getId());
        response.setInvoiceId(entity.getNewSalesInvoiceEntity().getId());
        response.setSalesReturnTransactionNumber(entity.getSalesReturnTransactionNumber());
        response.setPartyId(entity.getPartyEntity().getId());
        response.setPartyName(entity.getPartyName());
        response.setTransactionDate(LocalDate.parse(entity.getTransactionDate()));
        response.setTransactionTime(LocalTime.parse(entity.getTransactionTime()));
        response.setTotalAmount(entity.getTotalAmount());
        response.setPaidAmount(entity.getPaidAmount());
        response.setTaxAmount(entity.getTaxAmount());
        response.setDiscountAmount(entity.getDiscountAmount());
        response.setItemCount(entity.getItemCount());
        response.setTotalQuantity(entity.getTotalQuantity());
        response.setNotes(entity.getNotes());
        response.setCreatedAt(LocalDateTime.parse(entity.getCreatedAt()));
        response.setUpdatedAt(LocalDateTime.parse(entity.getUpdatedAt()));
        response.setCreatedBy(entity.getCreatedBy());
        response.setUpdatedBy(entity.getUpdatedBy());
        response.setPaymentMode(entity.getPaymentMode());
        response.setTransactionType(entity.getTransactionType());
        response.setMessage(message);
        response.setSuccess(success);
        response.setBalanceAmount(entity.getBalanceAmount());

        return response;
    }

    private SalesReturnTransactionResponse mapSalesReturnTransactionEntityToResponse(
            SalesReturnTransactionEntity entity) {
        SalesReturnTransactionResponse response = new SalesReturnTransactionResponse();

        response.setId(entity.getId());
        response.setSalesReturnTransactionNumber(entity.getSalesReturnTransactionNumber());
        response.setSalesReturnId(entity.getSalesReturnEntity().getId());
        response.setInvoiceId(entity.getNewSalesInvoiceEntity().getId());
        response.setPartyId(entity.getPartyEntity().getId());
        response.setPartyName(entity.getPartyName());
        response.setTransactionDate(LocalDate.parse(entity.getTransactionDate()));
        response.setTransactionTime(LocalTime.parse(entity.getTransactionTime()));
        response.setTotalAmount(entity.getTotalAmount());
        response.setPaidAmount(entity.getPaidAmount());
        response.setTaxAmount(entity.getTaxAmount());
        response.setDiscountAmount(entity.getDiscountAmount());
        response.setItemCount(entity.getItemCount());
        response.setTotalQuantity(entity.getTotalQuantity());
        response.setNotes(entity.getNotes());
        response.setCreatedAt(LocalDateTime.parse(entity.getCreatedAt()));
        response.setUpdatedAt(LocalDateTime.parse(entity.getUpdatedAt()));
        response.setCreatedBy(entity.getCreatedBy());
        response.setUpdatedBy(entity.getUpdatedBy());
        response.setPaymentMode(entity.getPaymentMode());
        response.setBalanceAmount(entity.getBalanceAmount());
        response.setTransactionType(entity.getTransactionType());
        response.setSuccess(true);
        response.setMessage("Sales Return Transaction fetched successfully");
        response.setPaymentStatus(entity.getPaymentStatus());
        return response;
    }

}
