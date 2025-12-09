package com.cityfashionpos.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.EstimateQuotationTransactionRequest;
import com.cityfashionpos.dto.EstimateQuotationTransactionResponse;
import com.cityfashionpos.entity.EstimateQuotationTransactionEntity;
import com.cityfashionpos.repository.EstimateQuotationTransactionRepository;
import com.cityfashionpos.repository.NewEstimateQuotationRepository;
import com.cityfashionpos.repository.PartyRepository;
import com.cityfashionpos.service.EstimateQuotationTransactionService;

@Service
@Transactional
public class EstimateQuotationTransactionServiceImpl implements EstimateQuotationTransactionService {

    private static final Logger logger = LoggerFactory.getLogger(EstimateQuotationTransactionServiceImpl.class);

    @Autowired
    private EstimateQuotationTransactionRepository estimateQuotationTransactionRepository;

    @Autowired
    private NewEstimateQuotationRepository estimateQuotationRespository;

    @Autowired
    private PartyRepository partyRepository;

    @Override
    public EstimateQuotationTransactionResponse createEstimateQuotationTransaction(
            EstimateQuotationTransactionRequest request) {
        try {
            logger.info("Creating estimate quotation transaction for amount: {}", request.getTotalAmount());

            // Generate transaction number if not provided
            if (request.getTransactionNumber() == null || request.getTransactionNumber().isEmpty()) {
                request.setTransactionNumber(generateEstimateQuotationTransactionNumber());
            }
            EstimateQuotationTransactionEntity entity = mapRequestToEntity(request);

            entity = estimateQuotationTransactionRepository.save(entity);

            logger.info("Estimate Quotation Transaction created successfully with ID: {}", entity.getId());
            return mapEntityToResponse(entity, true, "Estimate Quotation Transaction created successfully");
        } catch (Exception e) {
            logger.error("Error creating sales transaction: {}", e.getMessage(), e);
            return new EstimateQuotationTransactionResponse(false,
                    "Error creating sales transaction: " + e.getMessage());
        }

    }

    @Override
    public List<EstimateQuotationTransactionResponse> getAllEstimateQuotationTransactions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllEstimateQuotationTransactions'");
    }

    @Override
    public String generateEstimateQuotationTransactionNumber() {
        Long maxId = estimateQuotationTransactionRepository.findMaxTransactionId();
        if (maxId == null) {
            maxId = 0L;
        }
        return String.format("EQT-%05d", maxId + 1);
    }

    // Helper methods
    private EstimateQuotationTransactionEntity mapRequestToEntity(EstimateQuotationTransactionRequest request) {
        EstimateQuotationTransactionEntity entity = new EstimateQuotationTransactionEntity();

        entity.setTransactionNumber(request.getTransactionNumber());
        entity.setEstimateQuotationId(request.getEstimateQuotationId());
        entity.setEstimateQuotationNumber(request.getEstimateNumber());
        entity.setPartyId(request.getPartyId());
        entity.setPartyName(request.getPartyName());
        entity.setTransactionDate(request.getTransactionDate());
        entity.setTransactionTime(request.getTransactionTime());
        entity.setTotalAmount(request.getTotalAmount());
        entity.setTaxAmount(request.getTaxAmount());
        entity.setDiscountAmount(request.getDiscountAmount());
        entity.setItemCount(request.getItemCount());
        entity.setTotalQuantity(request.getTotalQuantity());
        entity.setNotes(request.getNotes());
        entity.setCreatedBy(request.getCreatedBy());
        entity.setBalanceAmount(request.getBalanceAmount());

        return entity;
    }

    private EstimateQuotationTransactionResponse mapEntityToResponse(EstimateQuotationTransactionEntity entity,
            boolean success, String message) {
        EstimateQuotationTransactionResponse response = new EstimateQuotationTransactionResponse(success, message);

        response.setId(entity.getId());
        response.setTransactionNumber(entity.getTransactionNumber());
        response.setEstimateQuotationId(entity.getEstimateQuotationId());
        response.setEstimateQuotationNumber(entity.getEstimateQuotationNumber());
        response.setPartyId(entity.getPartyId());
        response.setPartyName(entity.getPartyName());
        response.setTransactionDate(entity.getTransactionDate());
        response.setTransactionTime(entity.getTransactionTime());
        response.setTotalAmount(entity.getTotalAmount());
        response.setTaxAmount(entity.getTaxAmount());
        response.setDiscountAmount(entity.getDiscountAmount());
        response.setItemCount(entity.getItemCount());
        response.setTotalQuantity(entity.getTotalQuantity());
        response.setNotes(entity.getNotes());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        response.setCreatedBy(entity.getCreatedBy());
        response.setUpdatedBy(entity.getUpdatedBy());

        response.setMessage(message);
        response.setSuccess(success);
        response.setBalanceAmount(entity.getBalanceAmount());

        return response;
    }

    private EstimateQuotationTransactionResponse mapEstimateQuotationEntityToResponse(
            EstimateQuotationTransactionEntity entity) {
        EstimateQuotationTransactionResponse response = new EstimateQuotationTransactionResponse();

        response.setId(entity.getId());
        response.setTransactionNumber(entity.getTransactionNumber());
        response.setEstimateQuotationId(entity.getEstimateQuotationId());
        response.setEstimateQuotationNumber(entity.getEstimateQuotationNumber());
        response.setPartyId(entity.getPartyId());
        response.setPartyName(entity.getPartyName());
        response.setTransactionDate(entity.getTransactionDate());
        response.setTransactionTime(entity.getTransactionTime());
        response.setTotalAmount(entity.getTotalAmount());
        response.setTaxAmount(entity.getTaxAmount());
        response.setDiscountAmount(entity.getDiscountAmount());
        response.setItemCount(entity.getItemCount());
        response.setTotalQuantity(entity.getTotalQuantity());
        response.setNotes(entity.getNotes());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        response.setCreatedBy(entity.getCreatedBy());
        response.setUpdatedBy(entity.getUpdatedBy());

        response.setBalanceAmount(entity.getBalanceAmount());

        return response;
    }

    @Override
    public Map<String, BigDecimal> getTotalEstimateQuotationAmountsByDateRange(LocalDate fromDate, LocalDate toDate) {
        try {
            BigDecimal totalEstimateQuotationAmount = estimateQuotationTransactionRepository
                    .getTotalEstimateQuotationAmountForDateRange(fromDate, toDate);
            BigDecimal totalOpenAmount = estimateQuotationTransactionRepository.getTotalOpenAmountForDateRange(fromDate,
                    toDate);
            BigDecimal totalConvertedAmount = estimateQuotationTransactionRepository
                    .getTotalConvertedAmountForDateRange(fromDate,
                            toDate);

            LocalDate firstDayLastMonth = fromDate.minusMonths(1).withDayOfMonth(1);
            LocalDate lastDayLastMonth = firstDayLastMonth.withDayOfMonth(firstDayLastMonth.lengthOfMonth());

            // Ensure values are not null
            totalEstimateQuotationAmount = totalEstimateQuotationAmount != null ? totalEstimateQuotationAmount
                    : BigDecimal.ZERO;
            totalOpenAmount = totalOpenAmount != null ? totalOpenAmount : BigDecimal.ZERO;
            totalConvertedAmount = totalConvertedAmount != null ? totalConvertedAmount : BigDecimal.ZERO;

            BigDecimal lastMonthEstimateQuotationAmount = estimateQuotationTransactionRepository
                    .getTotalEstimateQuotationAmountForDateRange(firstDayLastMonth,
                            lastDayLastMonth);
            BigDecimal lastMonthOpenAmount = estimateQuotationTransactionRepository.getTotalOpenAmountForDateRange(
                    firstDayLastMonth,
                    lastDayLastMonth);
            BigDecimal lastMonthConvertedAmount = estimateQuotationTransactionRepository
                    .getTotalConvertedAmountForDateRange(
                            firstDayLastMonth,
                            lastDayLastMonth);

            lastMonthEstimateQuotationAmount = lastMonthEstimateQuotationAmount != null
                    ? lastMonthEstimateQuotationAmount
                    : BigDecimal.ZERO;
            lastMonthOpenAmount = lastMonthOpenAmount != null ? lastMonthOpenAmount : BigDecimal.ZERO;
            lastMonthConvertedAmount = lastMonthConvertedAmount != null ? lastMonthConvertedAmount : BigDecimal.ZERO;

            BigDecimal lastMonthTotalEstimateQuotationAmount = lastMonthOpenAmount.add(lastMonthConvertedAmount);

            // Calculate percentage change
            BigDecimal percentageData = calculatePercentageChangeVsLastMonth(totalEstimateQuotationAmount,
                    lastMonthTotalEstimateQuotationAmount);
            Map<String, BigDecimal> totals = new HashMap<>();
            totals.put("totalEstimateQuotationAmount", totalEstimateQuotationAmount);
            totals.put("totalOpenAmount", totalOpenAmount);
            totals.put("totalConvertedAmount", totalConvertedAmount);
            totals.put("percentageChange", percentageData);

            logger.info("Date range totals from {} to {}: Sales: {}, Received: {}, Balance: {}",
                    fromDate, toDate, totalEstimateQuotationAmount, totalOpenAmount, totalConvertedAmount);
            return totals;
        } catch (Exception e) {
            logger.error("Error calculating totals for date range {} to {}: {}", fromDate, toDate, e.getMessage(), e);
            Map<String, BigDecimal> errorMap = new HashMap<>();
            errorMap.put("totalEstimateQuotationAmount", BigDecimal.ZERO);
            errorMap.put("totalOpenAmount", BigDecimal.ZERO);
            errorMap.put("totalConvertedAmount", BigDecimal.ZERO);
            return errorMap;
        }
    }

    @Override
    public Map<String, BigDecimal> getTotalOpenAndConvertedAmounts() {
        try {
            // Get current totals (all-time)
            BigDecimal totalOpenAmount = estimateQuotationTransactionRepository.getTotalOpenAmount();
            BigDecimal totalConvertedAmount = estimateQuotationTransactionRepository.getTotalConvertedAmount();

            LocalDate firstDayLastMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1);
            LocalDate lastDayLastMonth = firstDayLastMonth.withDayOfMonth(firstDayLastMonth.lengthOfMonth());

            // Ensure values are not null
            totalOpenAmount = totalOpenAmount != null ? totalOpenAmount : BigDecimal.ZERO;
            totalConvertedAmount = totalConvertedAmount != null ? totalConvertedAmount : BigDecimal.ZERO;

            BigDecimal totalEstimateQuotationAmount = totalOpenAmount.add(totalConvertedAmount);

            // Get last month's totals for comparison
            BigDecimal lastMonthTotalOpenAmount = estimateQuotationTransactionRepository.getLastMonthTotalOpenAmount(
                    firstDayLastMonth,
                    lastDayLastMonth);
            BigDecimal lastMonthTotalConvertedAmount = estimateQuotationTransactionRepository
                    .getLastMonthTotalConvertedAmount(
                            firstDayLastMonth,
                            lastDayLastMonth);

            // Ensure last month values are not null, default to 0
            lastMonthTotalOpenAmount = lastMonthTotalOpenAmount != null ? lastMonthTotalOpenAmount : BigDecimal.ZERO;
            lastMonthTotalConvertedAmount = lastMonthTotalConvertedAmount != null ? lastMonthTotalConvertedAmount
                    : BigDecimal.ZERO;

            BigDecimal lastMonthTotalEstimateQuotationAmount = lastMonthTotalOpenAmount
                    .add(lastMonthTotalConvertedAmount);

            // Calculate percentage change
            BigDecimal percentageData = calculatePercentageChangeVsLastMonth(totalEstimateQuotationAmount,
                    lastMonthTotalEstimateQuotationAmount);

            Map<String, BigDecimal> totals = new HashMap<>();
            totals.put("totalEstimateQuotationAmount", totalEstimateQuotationAmount);
            totals.put("totalOpenAmount", totalOpenAmount);
            totals.put("totalConvertedAmount", totalConvertedAmount);
            totals.put("percentageChange", percentageData);

            logger.info(
                    "Total Estimate Quotation: {}, Total Open Amount: {}, Total Converted amount: {}, Last month Estimate Quotation: {}, Percentage change: {}%",
                    totalEstimateQuotationAmount, totalOpenAmount, totalConvertedAmount,
                    lastMonthTotalEstimateQuotationAmount, percentageData);
            return totals;
        } catch (Exception e) {
            logger.error("Error calculating total open and converted amounts: {}", e.getMessage(), e);
            Map<String, BigDecimal> errorMap = new HashMap<>();
            errorMap.put("totalEstimateQuotationAmount", BigDecimal.ZERO);
            errorMap.put("totalOpenAmount", BigDecimal.ZERO);
            errorMap.put("totalConvertedAmount", BigDecimal.ZERO);
            return errorMap;
        }
    }

    @Override
    public BigDecimal calculatePercentageChangeVsLastMonth(BigDecimal currentAmount, BigDecimal lastMonthAmount) {
        if (lastMonthAmount == null || lastMonthAmount.compareTo(BigDecimal.ZERO) == 0) {
            return currentAmount != null && currentAmount.compareTo(BigDecimal.ZERO) > 0 ? new BigDecimal("100.00")
                    : BigDecimal.ZERO;
        }

        BigDecimal difference = currentAmount.subtract(lastMonthAmount);
        return difference.divide(lastMonthAmount, 4, RoundingMode.HALF_UP)
                .multiply(new BigDecimal("100"))
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public List<EstimateQuotationTransactionResponse> getEstimateQuotationTransactionByDateRange(LocalDate fromDate,
            LocalDate toDate) {
        try {
            logger.info("Fetching estimate quotation transaction records for date range: {} to {}", fromDate, toDate);

            List<EstimateQuotationTransactionEntity> transactions = estimateQuotationTransactionRepository
                    .findByTransactionDateBetween(fromDate, toDate);

            List<EstimateQuotationTransactionResponse> estimateQuotationTransactionRecords = transactions.stream()
                    .map(this::mapEstimateQuotationEntityToResponse).collect(Collectors.toList());

            logger.info("Found {} estimate quotation records for date range {} to {}",
                    estimateQuotationTransactionRecords.size(), fromDate, toDate);

            return estimateQuotationTransactionRecords;
        } catch (Exception e) {
            logger.error("Error fetching estimate quotation transaction records for date range {} to {}: {}",
                    fromDate, toDate, e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
