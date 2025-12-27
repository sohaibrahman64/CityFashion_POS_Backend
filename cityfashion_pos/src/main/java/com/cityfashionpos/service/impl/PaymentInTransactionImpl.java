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

import com.cityfashionpos.dto.PaymentInTransactionRequest;
import com.cityfashionpos.dto.PaymentInTransactionResponse;
import com.cityfashionpos.entity.PaymentInTransactionEntity;
import com.cityfashionpos.repository.PaymentInTransactionRepository;
import com.cityfashionpos.service.PaymentInTransactionService;

@Service
@Transactional
public class PaymentInTransactionImpl implements PaymentInTransactionService {
        private static final Logger logger = LoggerFactory.getLogger(PaymentInTransactionImpl.class);

        @Autowired
        private PaymentInTransactionRepository paymentInTransactionRepository;

        @Override
        public PaymentInTransactionResponse createPaymentInTransaction(PaymentInTransactionRequest request) {
                try {
                        logger.info("Creating payment in transaction for amount: {}", request.getReceivedAmount());

                        // Generate transaction number if not provided
                        if (request.getTransactionNumber() == null || request.getTransactionNumber().isEmpty()) {
                                request.setTransactionNumber(generatePaymentInTransactionNumber());
                        }
                        PaymentInTransactionEntity entity = mapRequestToEntity(request);

                        entity = paymentInTransactionRepository.save(entity);

                        logger.info("Payment In Transaction created successfully with ID: {}", entity.getId());
                        return mapEntityToResponse(entity, true, "Payment In Transaction created successfully");
                } catch (Exception e) {
                        logger.error("Error creating payment in transaction: {}", e.getMessage(), e);
                        return new PaymentInTransactionResponse(false,
                                        "Error creating payment in transaction: " + e.getMessage());
                }
        }

        // Helper methods
        private PaymentInTransactionEntity mapRequestToEntity(PaymentInTransactionRequest request) {
                PaymentInTransactionEntity entity = new PaymentInTransactionEntity();

                entity.setTransactionNumber(request.getTransactionNumber());
                entity.setPaymentReceivedDate(request.getPaymentReceivedDate());
                entity.setReferenceNumber(request.getReferenceNumber());
                entity.setPartyId(request.getPartyId());
                entity.setPartyName(request.getPartyName());
                entity.setTotalAmount(request.getTotalAmount());
                entity.setReceivedAmount(request.getReceivedAmount());
                entity.setPaymentType(request.getPaymentType());
                entity.setPaymentStatus(request.getPaymentStatus());
                entity.setTotalAmount(request.getTotalAmount());
                entity.setDescription(request.getDescription());

                return entity;
        }

        private PaymentInTransactionResponse mapEntityToResponse(PaymentInTransactionEntity entity, boolean success,
                        String message) {
                PaymentInTransactionResponse response = new PaymentInTransactionResponse(success, message);

                response.setId(entity.getId());
                response.setTransactionNumber(entity.getTransactionNumber());
                response.setReferenceNumber(entity.getReferenceNumber());
                response.setPartyId(entity.getPartyId());
                response.setPartyName(entity.getPartyName());
                response.setPaymentReceivedDate(entity.getPaymentReceivedDate());
                response.setTotalAmount(entity.getTotalAmount());
                response.setReceivedAmount(entity.getReceivedAmount());
                response.setDescription(entity.getDescription());
                response.setPaymentType(entity.getPaymentType());
                response.setPaymentStatus(entity.getPaymentStatus());

                return response;
        }

        private PaymentInTransactionResponse mapPaymentInEntityToResponse(PaymentInTransactionEntity entity) {
                PaymentInTransactionResponse response = new PaymentInTransactionResponse();

                response.setId(entity.getId());
                response.setTransactionNumber(entity.getTransactionNumber());
                response.setReferenceNumber(entity.getReferenceNumber());
                response.setPartyId(entity.getPartyId());
                response.setPartyName(entity.getPartyName());
                response.setPaymentReceivedDate(entity.getPaymentReceivedDate());
                response.setTotalAmount(entity.getTotalAmount());
                response.setReceivedAmount(entity.getReceivedAmount());
                response.setDescription(entity.getDescription());
                response.setPaymentType(entity.getPaymentType());
                response.setPaymentStatus(entity.getPaymentStatus());

                return response;
        }

        @Override
        public String generatePaymentInTransactionNumber() {
                Long maxId = paymentInTransactionRepository.findMaxTransactionId();
                if (maxId == null) {
                        maxId = 0L;
                }
                return String.format("%05d", maxId + 1);
        }

        @Override
        public Map<String, BigDecimal> getTotalPaymentInAmountsByDateRange(LocalDate fromDate, LocalDate toDate) {
                try {
                        BigDecimal totalPaymentInAmount = paymentInTransactionRepository
                                        .getTotalPaymentInAmountForDateRange(fromDate.toString(),
                                                        toDate.toString());
                        BigDecimal totalReceivedAmount = paymentInTransactionRepository
                                        .getTotalReceivedAmountForDateRange(fromDate.toString(),
                                                        toDate.toString());

                        LocalDate firstDayLastMonth = fromDate.minusMonths(1).withDayOfMonth(1);
                        LocalDate lastDayLastMonth = firstDayLastMonth
                                        .withDayOfMonth(firstDayLastMonth.lengthOfMonth());

                        // Ensure values are not null
                        totalPaymentInAmount = totalPaymentInAmount != null ? totalPaymentInAmount
                                        : BigDecimal.ZERO;
                        totalReceivedAmount = totalReceivedAmount != null ? totalReceivedAmount : BigDecimal.ZERO;

                        BigDecimal lastMonthPaymentInAmount = paymentInTransactionRepository
                                        .getTotalPaymentInAmountForDateRange(firstDayLastMonth.toString(),
                                                        lastDayLastMonth.toString());
                        BigDecimal lastMonthReceivedAmount = paymentInTransactionRepository
                                        .getTotalReceivedAmountForDateRange(
                                                        firstDayLastMonth.toString(),
                                                        lastDayLastMonth.toString());

                        lastMonthPaymentInAmount = lastMonthPaymentInAmount != null
                                        ? lastMonthPaymentInAmount
                                        : BigDecimal.ZERO;
                        lastMonthReceivedAmount = lastMonthReceivedAmount != null ? lastMonthReceivedAmount
                                        : BigDecimal.ZERO;

                        BigDecimal lastMonthTotalPaymentInAmount = lastMonthPaymentInAmount;

                        // Calculate percentage change
                        BigDecimal percentageData = calculatePercentageChangeVsLastMonth(totalPaymentInAmount,
                                        lastMonthTotalPaymentInAmount);
                        Map<String, BigDecimal> totals = new HashMap<>();
                        totals.put("totalPaymentInAmount", totalPaymentInAmount);
                        totals.put("totalReceivedAmount", totalReceivedAmount);
                        totals.put("percentageChange", percentageData);

                        logger.info("Date range totals from {} to {}: Total: {}, Open: {}, Converted: {}",
                                        fromDate, toDate, totalPaymentInAmount,
                                        totalReceivedAmount);
                        return totals;
                } catch (Exception e) {
                        logger.error("Error calculating totals for date range {} to {}: {}", fromDate, toDate,
                                        e.getMessage(), e);
                        Map<String, BigDecimal> errorMap = new HashMap<>();
                        errorMap.put("totalPaymentInAmount", BigDecimal.ZERO);
                        errorMap.put("totalReceivedAmount", BigDecimal.ZERO);
                        errorMap.put("percentageChange", BigDecimal.ZERO);
                        return errorMap;
                }
        }

        @Override
        public Map<String, BigDecimal> getTotalReceivedAmount() {
                try {
                        // Get current totals (all-time)
                        BigDecimal totalReceivedAmount = paymentInTransactionRepository.getTotalReceivedAmount();

                        LocalDate firstDayLastMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1);
                        LocalDate lastDayLastMonth = firstDayLastMonth
                                        .withDayOfMonth(firstDayLastMonth.lengthOfMonth());

                        // Ensure values are not null
                        totalReceivedAmount = totalReceivedAmount != null ? totalReceivedAmount : BigDecimal.ZERO;

                        BigDecimal totalPaymentInAmount = totalReceivedAmount;

                        // Get last month's totals for comparison
                        BigDecimal lastMonthTotalReceivedAmount = paymentInTransactionRepository
                                        .getLastMonthTotalReceivedAmount(
                                                        firstDayLastMonth.toString(),
                                                        lastDayLastMonth.toString());

                        // Ensure last month values are not null, default to 0
                        lastMonthTotalReceivedAmount = lastMonthTotalReceivedAmount != null
                                        ? lastMonthTotalReceivedAmount
                                        : BigDecimal.ZERO;

                        // Calculate percentage change
                        BigDecimal percentageData = calculatePercentageChangeVsLastMonth(totalPaymentInAmount,
                                        lastMonthTotalReceivedAmount);

                        Map<String, BigDecimal> totals = new HashMap<>();
                        totals.put("totalReceivedAmount", totalReceivedAmount);
                        totals.put("percentageChange", percentageData);

                        logger.info(
                                        "Total Received Amount: {}, Last month Received Amount: {}, Percentage change: {}%",
                                        totalReceivedAmount, lastMonthTotalReceivedAmount, percentageData);
                        return totals;
                } catch (Exception e) {
                        logger.error("Error calculating total open and converted amounts: {}", e.getMessage(), e);
                        Map<String, BigDecimal> errorMap = new HashMap<>();
                        errorMap.put("totalReceivedAmount", BigDecimal.ZERO);
                        errorMap.put("percentageChange", BigDecimal.ZERO);
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
        public List<PaymentInTransactionResponse> getPaymentInTransactionByDateRange(LocalDate fromDate,
                        LocalDate toDate) {
                try {
                        logger.info("Fetching proforma invoice transaction records for date range: {} to {}", fromDate,
                                        toDate);

                        List<PaymentInTransactionEntity> transactions = paymentInTransactionRepository
                                        .findByPaymentReceivedDateBetween(fromDate.toString(), toDate.toString());

                        List<PaymentInTransactionResponse> paymentInTransactionRecords = transactions
                                        .stream()
                                        .map(this::mapPaymentInEntityToResponse).collect(Collectors.toList());

                        logger.info("Found {} estimate quotation records for date range {} to {}",
                                        paymentInTransactionRecords.size(), fromDate, toDate);

                        return paymentInTransactionRecords;
                } catch (Exception e) {
                        logger.error("Error fetching estimate quotation transaction records for date range {} to {}: {}",
                                        fromDate, toDate, e.getMessage(), e);
                        return Collections.emptyList();
                }
        }

}
