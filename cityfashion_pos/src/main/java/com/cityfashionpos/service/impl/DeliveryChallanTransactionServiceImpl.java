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

import com.cityfashionpos.dto.DeliveryChallanTransactionRequest;
import com.cityfashionpos.dto.DeliveryChallanTransactionResponse;
import com.cityfashionpos.entity.DeliveryChallanTransactionEntity;
import com.cityfashionpos.entity.NewDeliveryChallanEntity;
import com.cityfashionpos.repository.DeliveryChallanTransactionRepository;
import com.cityfashionpos.repository.NewDeliveryChallanRepository;
import com.cityfashionpos.service.DeliveryChallanTransactionService;

@Service
@Transactional
public class DeliveryChallanTransactionServiceImpl implements DeliveryChallanTransactionService {
    private static final Logger logger = LoggerFactory.getLogger(DeliveryChallanTransactionServiceImpl.class);

    @Autowired
    private DeliveryChallanTransactionRepository deliveryChallanTransactionRepository;

    @Autowired
    private NewDeliveryChallanRepository newDeliveryChallanRepository;

    @Override
    public DeliveryChallanTransactionResponse createDeliveryChallanTransaction(
            DeliveryChallanTransactionRequest request) {
        try {
            logger.info("Creating delivery challan transaction for amount: {}", request.getTotalAmount());

            // Generate transaction number if not provided
            if (request.getTransactionNumber() == null || request.getTransactionNumber().isEmpty()) {
                request.setTransactionNumber(generateDeliveryChallanTransactionNumber());
            }
            DeliveryChallanTransactionEntity entity = mapRequestToEntity(request);

            entity = deliveryChallanTransactionRepository.save(entity);

            logger.info("Delivery Challan Transaction created successfully with ID: {}", entity.getId());
            return mapEntityToResponse(entity, true, "Delivery Challan Transaction created successfully");
        } catch (Exception e) {
            logger.error("Error creating delivery challan transaction: {}", e.getMessage(), e);
            return new DeliveryChallanTransactionResponse(false,
                    "Error creating delivery challan transaction: " + e.getMessage());
        }
    }

    @Override
    public List<DeliveryChallanTransactionResponse> getAllDeliveryChallanTransactions() {
        try {
            List<DeliveryChallanTransactionEntity> entities = deliveryChallanTransactionRepository.findAll();
            return entities.stream()
                    .map(entity -> mapEntityToResponse(entity, true, null))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching all delivery challan transactions: {}", e.getMessage(), e);
            return List.of();
        }
    }

    @Override
    public String generateDeliveryChallanTransactionNumber() {
        Long maxId = deliveryChallanTransactionRepository.findMaxTransactionId();
        if (maxId == null) {
            maxId = 0L;
        }
        return String.format("DC-%05d", maxId + 1);
    }

    @Override
    public Map<String, BigDecimal> getTotalDeliveryChallanAmountsByDateRange(LocalDate fromDate, LocalDate toDate) {
        try {
            BigDecimal totalDeliveryChallanAmount = deliveryChallanTransactionRepository
                    .getTotalDeliveryChallanAmountForDateRange(fromDate.toString(),
                            toDate.toString());
            BigDecimal totalOpenAmount = deliveryChallanTransactionRepository
                    .getTotalOpenAmountForDateRange(fromDate.toString(),
                            toDate.toString());
            BigDecimal totalConvertedAmount = deliveryChallanTransactionRepository
                    .getTotalConvertedAmountForDateRange(fromDate.toString(),
                            toDate.toString());

            LocalDate firstDayLastMonth = fromDate.minusMonths(1).withDayOfMonth(1);
            LocalDate lastDayLastMonth = firstDayLastMonth
                    .withDayOfMonth(firstDayLastMonth.lengthOfMonth());

            // Ensure values are not null
            totalDeliveryChallanAmount = totalDeliveryChallanAmount != null ? totalDeliveryChallanAmount
                    : BigDecimal.ZERO;
            totalOpenAmount = totalOpenAmount != null ? totalOpenAmount : BigDecimal.ZERO;
            totalConvertedAmount = totalConvertedAmount != null ? totalConvertedAmount : BigDecimal.ZERO;

            BigDecimal lastMonthDeliveryChallanAmount = deliveryChallanTransactionRepository
                    .getTotalDeliveryChallanAmountForDateRange(firstDayLastMonth.toString(),
                            lastDayLastMonth.toString());
            BigDecimal lastMonthOpenAmount = deliveryChallanTransactionRepository
                    .getTotalOpenAmountForDateRange(
                            firstDayLastMonth.toString(),
                            lastDayLastMonth.toString());
            BigDecimal lastMonthConvertedAmount = deliveryChallanTransactionRepository
                    .getTotalConvertedAmountForDateRange(
                            firstDayLastMonth.toString(),
                            lastDayLastMonth.toString());

            lastMonthDeliveryChallanAmount = lastMonthDeliveryChallanAmount != null
                    ? lastMonthDeliveryChallanAmount
                    : BigDecimal.ZERO;
            lastMonthOpenAmount = lastMonthOpenAmount != null ? lastMonthOpenAmount : BigDecimal.ZERO;
            lastMonthConvertedAmount = lastMonthConvertedAmount != null ? lastMonthConvertedAmount
                    : BigDecimal.ZERO;

            BigDecimal lastMonthTotalDeliveryChallanAmount = lastMonthOpenAmount
                    .add(lastMonthConvertedAmount);

            // Calculate percentage change
            BigDecimal percentageData = calculatePercentageChangeVsLastMonth(totalDeliveryChallanAmount,
                    lastMonthTotalDeliveryChallanAmount);
            Map<String, BigDecimal> totals = new HashMap<>();
            totals.put("totalDeliveryChallanAmount", totalDeliveryChallanAmount);
            totals.put("totalOpenAmount", totalOpenAmount);
            totals.put("totalConvertedAmount", totalConvertedAmount);
            totals.put("percentageChange", percentageData);

            logger.info("Date range totals from {} to {}: Total: {}, Open: {}, Converted: {}",
                    fromDate, toDate, totalDeliveryChallanAmount, totalOpenAmount,
                    totalConvertedAmount);
            return totals;
        } catch (Exception e) {
            logger.error("Error calculating totals for date range {} to {}: {}", fromDate, toDate,
                    e.getMessage(), e);
            Map<String, BigDecimal> errorMap = new HashMap<>();
            errorMap.put("totalProformaInvoiceAmount", BigDecimal.ZERO);
            errorMap.put("totalOpenAmount", BigDecimal.ZERO);
            errorMap.put("totalConvertedAmount", BigDecimal.ZERO);
            return errorMap;
        }
    }

    @Override
    public Map<String, BigDecimal> getTotalOpenAndConvertedAmounts() {
        try {
            // Get current totals (all-time)
            BigDecimal totalOpenAmount = deliveryChallanTransactionRepository.getTotalOpenAmount();
            BigDecimal totalConvertedAmount = deliveryChallanTransactionRepository
                    .getTotalConvertedAmount();

            LocalDate firstDayLastMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1);
            LocalDate lastDayLastMonth = firstDayLastMonth
                    .withDayOfMonth(firstDayLastMonth.lengthOfMonth());

            // Ensure values are not null
            totalOpenAmount = totalOpenAmount != null ? totalOpenAmount : BigDecimal.ZERO;
            totalConvertedAmount = totalConvertedAmount != null ? totalConvertedAmount : BigDecimal.ZERO;

            BigDecimal totalDeliveryChallanAmount = totalOpenAmount.add(totalConvertedAmount);

            // Get last month's totals for comparison
            BigDecimal lastMonthTotalOpenAmount = deliveryChallanTransactionRepository
                    .getLastMonthTotalOpenAmount(
                            firstDayLastMonth.toString(),
                            lastDayLastMonth.toString());
            BigDecimal lastMonthTotalConvertedAmount = deliveryChallanTransactionRepository
                    .getLastMonthTotalConvertedAmount(
                            firstDayLastMonth.toString(),
                            lastDayLastMonth.toString());

            // Ensure last month values are not null, default to 0
            lastMonthTotalOpenAmount = lastMonthTotalOpenAmount != null ? lastMonthTotalOpenAmount
                    : BigDecimal.ZERO;
            lastMonthTotalConvertedAmount = lastMonthTotalConvertedAmount != null
                    ? lastMonthTotalConvertedAmount
                    : BigDecimal.ZERO;

            BigDecimal lastMonthTotalDeliveryChallanAmount = lastMonthTotalOpenAmount
                    .add(lastMonthTotalConvertedAmount);

            // Calculate percentage change
            BigDecimal percentageData = calculatePercentageChangeVsLastMonth(totalDeliveryChallanAmount,
                    lastMonthTotalDeliveryChallanAmount);

            Map<String, BigDecimal> totals = new HashMap<>();
            totals.put("totalDeliveryChallanAmount", totalDeliveryChallanAmount);
            totals.put("totalOpenAmount", totalOpenAmount);
            totals.put("totalConvertedAmount", totalConvertedAmount);
            totals.put("percentageChange", percentageData);

            logger.info(
                    "Total Delivery Challan Amount: {}, Total Open Amount: {}, Total Converted amount: {}, Last month Delivery Challan Amount: {}, Percentage change: {}%",
                    totalDeliveryChallanAmount, totalOpenAmount, totalConvertedAmount,
                    lastMonthTotalDeliveryChallanAmount, percentageData);
            return totals;
        } catch (Exception e) {
            logger.error("Error calculating total open and converted amounts: {}", e.getMessage(), e);
            Map<String, BigDecimal> errorMap = new HashMap<>();
            errorMap.put("totalDeliveryChallanAmount", BigDecimal.ZERO);
            errorMap.put("totalOpenAmount", BigDecimal.ZERO);
            errorMap.put("totalConvertedAmount", BigDecimal.ZERO);
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
    public List<DeliveryChallanTransactionResponse> getDeliveryChallanTransactionByDateRange(LocalDate fromDate,
            LocalDate toDate) {
        try {
            logger.info("Fetching delivery challan transaction records for date range: {} to {}", fromDate,
                    toDate);

            List<DeliveryChallanTransactionEntity> transactions = deliveryChallanTransactionRepository
                    .findByTransactionDateBetween(fromDate.toString(), toDate.toString());

            List<DeliveryChallanTransactionResponse> deliveryChallanTransactionRecords = transactions
                    .stream()
                    .map(this::mapDeliveryChallanEntityToResponse).collect(Collectors.toList());

            logger.info("Found {} delivery challan transaction records for date range {} to {}",
                    deliveryChallanTransactionRecords.size(), fromDate, toDate);

            return deliveryChallanTransactionRecords;
        } catch (Exception e) {
            logger.error("Error fetching delivery challan transaction records for date range {} to {}: {}",
                    fromDate, toDate, e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    // Helper methods
    private DeliveryChallanTransactionEntity mapRequestToEntity(DeliveryChallanTransactionRequest request) {
        DeliveryChallanTransactionEntity entity = new DeliveryChallanTransactionEntity();

        entity.setTransactionNumber(request.getTransactionNumber());
        Optional<NewDeliveryChallanEntity> deliveryChallanOpt = newDeliveryChallanRepository
                .findById(request.getDeliveryChallanId());
        if (deliveryChallanOpt.isPresent()) {
            NewDeliveryChallanEntity deliveryChallan = deliveryChallanOpt.get();
            entity.setDeliveryChallanEntity(deliveryChallan);
        }
        entity.setBillingAddress(request.getBillingAddress());
        entity.setDeliveryChallanNumber(request.getDeliveryChallanNumber());
        entity.setPartyId(request.getPartyId());
        entity.setPartyName(request.getPartyName());
        entity.setDeliveryChallanInvoiceDate(request.getDeliveryChallanInvoiceDate().toString());
        entity.setDeliveryChallanDueDate(request.getDeliveryChallanDueDate().toString());
        entity.setTotalAmount(request.getTotalAmount());
        entity.setTaxAmount(request.getTaxAmount());
        entity.setDiscountAmount(request.getDiscountAmount());
        entity.setItemCount(request.getItemCount());
        entity.setTotalQuantity(request.getTotalQuantity());
        entity.setNotes(request.getNotes());
        entity.setCreatedBy(request.getCreatedBy());

        return entity;
    }

    private DeliveryChallanTransactionResponse mapEntityToResponse(DeliveryChallanTransactionEntity entity,
            boolean success, String message) {
        DeliveryChallanTransactionResponse response = new DeliveryChallanTransactionResponse(success, message);

        response.setId(entity.getId());
        response.setTransactionNumber(entity.getTransactionNumber());
        response.setTransactionDate(LocalDate.parse(entity.getTransactionDate()));
        response.setDeliveryChallanId(entity.getDeliveryChallanEntity().getId());
        response.setDeliveryChallanNumber(entity.getDeliveryChallanNumber());
        response.setPartyId(entity.getPartyId());
        response.setPartyName(entity.getPartyName());
        response.setDeliveryChallanInvoiceDate(LocalDate.parse(entity.getDeliveryChallanInvoiceDate()));
        response.setDeliveryChallanDueDate(LocalDate.parse(entity.getDeliveryChallanDueDate()));
        response.setTotalAmount(entity.getTotalAmount());
        response.setTaxAmount(entity.getTaxAmount());
        response.setDiscountAmount(entity.getDiscountAmount());
        response.setItemCount(entity.getItemCount());
        response.setTotalQuantity(entity.getTotalQuantity());
        response.setNotes(entity.getNotes());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(LocalDateTime.parse(entity.getCreatedAt()));
        response.setUpdatedAt(LocalDateTime.parse(entity.getUpdatedAt()));
        response.setCreatedBy(entity.getCreatedBy());
        response.setUpdatedBy(entity.getUpdatedBy());

        response.setMessage(message);
        response.setSuccess(success);

        return response;
    }

    private DeliveryChallanTransactionResponse mapDeliveryChallanEntityToResponse(
            DeliveryChallanTransactionEntity entity) {
        DeliveryChallanTransactionResponse response = new DeliveryChallanTransactionResponse();

        response.setId(entity.getId());
        response.setTransactionNumber(entity.getTransactionNumber());
        response.setTransactionDate(LocalDate.parse(entity.getTransactionDate()));
        response.setDeliveryChallanId(entity.getDeliveryChallanEntity().getId());
        response.setDeliveryChallanNumber(entity.getDeliveryChallanNumber());
        response.setBillingAddress(entity.getBillingAddress());
        response.setPartyId(entity.getPartyId());
        response.setPartyName(entity.getPartyName());
        response.setDeliveryChallanInvoiceDate(LocalDate.parse(entity.getDeliveryChallanInvoiceDate()));
        response.setDeliveryChallanDueDate(LocalDate.parse(entity.getDeliveryChallanDueDate()));
        response.setTotalAmount(entity.getTotalAmount());
        response.setTaxAmount(entity.getTaxAmount());
        response.setDiscountAmount(entity.getDiscountAmount());
        response.setItemCount(entity.getItemCount());
        response.setTotalQuantity(entity.getTotalQuantity());
        response.setNotes(entity.getNotes());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(LocalDateTime.parse(entity.getCreatedAt()));
        response.setUpdatedAt(LocalDateTime.parse(entity.getUpdatedAt()));
        response.setCreatedBy(entity.getCreatedBy());
        response.setUpdatedBy(entity.getUpdatedBy());

        return response;
    }

}
