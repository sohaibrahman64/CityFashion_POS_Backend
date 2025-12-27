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
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.ProformaInvoiceTransactionRequest;
import com.cityfashionpos.dto.ProformaInvoiceTransactionResponse;
import com.cityfashionpos.entity.ProformaInvoiceTransactionEntity;
import com.cityfashionpos.repository.NewProformaInvoiceRepository;
import com.cityfashionpos.repository.PartyRepository;
import com.cityfashionpos.repository.ProformaInvoiceTransactionRepository;
import com.cityfashionpos.service.ProformaInvoiceTransactionService;

@Service
@Transactional
public class ProformaInvoiceTransactionImpl implements ProformaInvoiceTransactionService {
        private static final Logger logger = LoggerFactory.getLogger(ProformaInvoiceTransactionImpl.class);

        @Autowired
        private ProformaInvoiceTransactionRepository proformaInvoiceTransactionRepository;

        @Autowired
        private NewProformaInvoiceRepository proformaInvoiceRespository;

        @Autowired
        private PartyRepository partyRepository;

        @Override
        public ProformaInvoiceTransactionResponse createProformaInvoiceTransaction(
                        ProformaInvoiceTransactionRequest request) {
                try {
                        logger.info("Creating proforma invoice transaction for amount: {}", request.getTotalAmount());

                        // Generate transaction number if not provided
                        if (request.getTransactionNumber() == null || request.getTransactionNumber().isEmpty()) {
                                request.setTransactionNumber(generateProformaInvoiceTransactionNumber());
                        }
                        ProformaInvoiceTransactionEntity entity = mapRequestToEntity(request);

                        entity = proformaInvoiceTransactionRepository.save(entity);

                        logger.info("Estimate Quotation Transaction created successfully with ID: {}", entity.getId());
                        return mapEntityToResponse(entity, true, "Proforma Invoice Transaction created successfully");
                } catch (Exception e) {
                        logger.error("Error creating proforma invoice transaction: {}", e.getMessage(), e);
                        return new ProformaInvoiceTransactionResponse(false,
                                        "Error creating sales transaction: " + e.getMessage());
                }
        }

        @Override
        public List<ProformaInvoiceTransactionResponse> getAllProformaInvoiceTransactions() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getAllProformaInvoiceTransactions'");
        }

        @Override
        public String generateProformaInvoiceTransactionNumber() {
                Long maxId = proformaInvoiceTransactionRepository.findMaxTransactionId();
                if (maxId == null) {
                        maxId = 0L;
                }
                return String.format("PI-%05d", maxId + 1);
        }

        @Override
        public Map<String, BigDecimal> getTotalProformaInvoiceAmountsByDateRange(LocalDate fromDate, LocalDate toDate) {
                try {
                        BigDecimal totalProformaInvoiceAmount = proformaInvoiceTransactionRepository
                                        .getTotalProformaInvoiceAmountForDateRange(fromDate.toString(),
                                                        toDate.toString());
                        BigDecimal totalOpenAmount = proformaInvoiceTransactionRepository
                                        .getTotalOpenAmountForDateRange(fromDate.toString(),
                                                        toDate.toString());
                        BigDecimal totalConvertedAmount = proformaInvoiceTransactionRepository
                                        .getTotalConvertedAmountForDateRange(fromDate.toString(),
                                                        toDate.toString());

                        LocalDate firstDayLastMonth = fromDate.minusMonths(1).withDayOfMonth(1);
                        LocalDate lastDayLastMonth = firstDayLastMonth
                                        .withDayOfMonth(firstDayLastMonth.lengthOfMonth());

                        // Ensure values are not null
                        totalProformaInvoiceAmount = totalProformaInvoiceAmount != null ? totalProformaInvoiceAmount
                                        : BigDecimal.ZERO;
                        totalOpenAmount = totalOpenAmount != null ? totalOpenAmount : BigDecimal.ZERO;
                        totalConvertedAmount = totalConvertedAmount != null ? totalConvertedAmount : BigDecimal.ZERO;

                        BigDecimal lastMonthProformaInvoiceAmount = proformaInvoiceTransactionRepository
                                        .getTotalProformaInvoiceAmountForDateRange(firstDayLastMonth.toString(),
                                                        lastDayLastMonth.toString());
                        BigDecimal lastMonthOpenAmount = proformaInvoiceTransactionRepository
                                        .getTotalOpenAmountForDateRange(
                                                        firstDayLastMonth.toString(),
                                                        lastDayLastMonth.toString());
                        BigDecimal lastMonthConvertedAmount = proformaInvoiceTransactionRepository
                                        .getTotalConvertedAmountForDateRange(
                                                        firstDayLastMonth.toString(),
                                                        lastDayLastMonth.toString());

                        lastMonthProformaInvoiceAmount = lastMonthProformaInvoiceAmount != null
                                        ? lastMonthProformaInvoiceAmount
                                        : BigDecimal.ZERO;
                        lastMonthOpenAmount = lastMonthOpenAmount != null ? lastMonthOpenAmount : BigDecimal.ZERO;
                        lastMonthConvertedAmount = lastMonthConvertedAmount != null ? lastMonthConvertedAmount
                                        : BigDecimal.ZERO;

                        BigDecimal lastMonthTotalProformaInvoiceAmount = lastMonthOpenAmount
                                        .add(lastMonthConvertedAmount);

                        // Calculate percentage change
                        BigDecimal percentageData = calculatePercentageChangeVsLastMonth(totalProformaInvoiceAmount,
                                        lastMonthTotalProformaInvoiceAmount);
                        Map<String, BigDecimal> totals = new HashMap<>();
                        totals.put("totalProformaInvoiceAmount", totalProformaInvoiceAmount);
                        totals.put("totalOpenAmount", totalOpenAmount);
                        totals.put("totalConvertedAmount", totalConvertedAmount);
                        totals.put("percentageChange", percentageData);

                        logger.info("Date range totals from {} to {}: Total: {}, Open: {}, Converted: {}",
                                        fromDate, toDate, totalProformaInvoiceAmount, totalOpenAmount,
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
                        BigDecimal totalOpenAmount = proformaInvoiceTransactionRepository.getTotalOpenAmount();
                        BigDecimal totalConvertedAmount = proformaInvoiceTransactionRepository
                                        .getTotalConvertedAmount();

                        LocalDate firstDayLastMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1);
                        LocalDate lastDayLastMonth = firstDayLastMonth
                                        .withDayOfMonth(firstDayLastMonth.lengthOfMonth());

                        // Ensure values are not null
                        totalOpenAmount = totalOpenAmount != null ? totalOpenAmount : BigDecimal.ZERO;
                        totalConvertedAmount = totalConvertedAmount != null ? totalConvertedAmount : BigDecimal.ZERO;

                        BigDecimal totalProformaInvoiceAmount = totalOpenAmount.add(totalConvertedAmount);

                        // Get last month's totals for comparison
                        BigDecimal lastMonthTotalOpenAmount = proformaInvoiceTransactionRepository
                                        .getLastMonthTotalOpenAmount(
                                                        firstDayLastMonth.toString(),
                                                        lastDayLastMonth.toString());
                        BigDecimal lastMonthTotalConvertedAmount = proformaInvoiceTransactionRepository
                                        .getLastMonthTotalConvertedAmount(
                                                        firstDayLastMonth.toString(),
                                                        lastDayLastMonth.toString());

                        // Ensure last month values are not null, default to 0
                        lastMonthTotalOpenAmount = lastMonthTotalOpenAmount != null ? lastMonthTotalOpenAmount
                                        : BigDecimal.ZERO;
                        lastMonthTotalConvertedAmount = lastMonthTotalConvertedAmount != null
                                        ? lastMonthTotalConvertedAmount
                                        : BigDecimal.ZERO;

                        BigDecimal lastMonthTotalProformaInvoiceAmount = lastMonthTotalOpenAmount
                                        .add(lastMonthTotalConvertedAmount);

                        // Calculate percentage change
                        BigDecimal percentageData = calculatePercentageChangeVsLastMonth(totalProformaInvoiceAmount,
                                        lastMonthTotalProformaInvoiceAmount);

                        Map<String, BigDecimal> totals = new HashMap<>();
                        totals.put("totalProformaInvoiceAmount", totalProformaInvoiceAmount);
                        totals.put("totalOpenAmount", totalOpenAmount);
                        totals.put("totalConvertedAmount", totalConvertedAmount);
                        totals.put("percentageChange", percentageData);

                        logger.info(
                                        "Total Proforma Invoice Amount: {}, Total Open Amount: {}, Total Converted amount: {}, Last month Proforma Invoice: {}, Percentage change: {}%",
                                        totalProformaInvoiceAmount, totalOpenAmount, totalConvertedAmount,
                                        lastMonthTotalProformaInvoiceAmount, percentageData);
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
        public List<ProformaInvoiceTransactionResponse> getProformaInvoiceTransactionByDateRange(LocalDate fromDate,
                        LocalDate toDate) {
                try {
                        logger.info("Fetching proforma invoice transaction records for date range: {} to {}", fromDate,
                                        toDate);

                        List<ProformaInvoiceTransactionEntity> transactions = proformaInvoiceTransactionRepository
                                        .findByTransactionDateBetween(fromDate.toString(), toDate.toString());

                        List<ProformaInvoiceTransactionResponse> proformaInvoiceTransactionRecords = transactions
                                        .stream()
                                        .map(this::mapProformaInvoiceEntityToResponse).collect(Collectors.toList());

                        logger.info("Found {} estimate quotation records for date range {} to {}",
                                        proformaInvoiceTransactionRecords.size(), fromDate, toDate);

                        return proformaInvoiceTransactionRecords;
                } catch (Exception e) {
                        logger.error("Error fetching estimate quotation transaction records for date range {} to {}: {}",
                                        fromDate, toDate, e.getMessage(), e);
                        return Collections.emptyList();
                }
        }

        // Helper methods
        private ProformaInvoiceTransactionEntity mapRequestToEntity(ProformaInvoiceTransactionRequest request) {
                ProformaInvoiceTransactionEntity entity = new ProformaInvoiceTransactionEntity();

                entity.setTransactionNumber(request.getTransactionNumber());
                entity.setProformaInvoiceId(request.getProformaInvoiceId());
                entity.setProformaInvoiceNumber(request.getProformaInvoiceNumber());
                entity.setPartyId(request.getPartyId());
                entity.setPartyName(request.getPartyName());
                entity.setTransactionDate(request.getTransactionDate().toString());
                entity.setTransactionTime(request.getTransactionTime().toString());
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

        private ProformaInvoiceTransactionResponse mapEntityToResponse(ProformaInvoiceTransactionEntity entity,
                        boolean success, String message) {
                ProformaInvoiceTransactionResponse response = new ProformaInvoiceTransactionResponse(success, message);

                response.setId(entity.getId());
                response.setTransactionNumber(entity.getTransactionNumber());
                response.setProformaInvoiceId(entity.getProformaInvoiceId());
                response.setProformaInvoiceNumber(entity.getProformaInvoiceNumber());
                response.setPartyId(entity.getPartyId());
                response.setPartyName(entity.getPartyName());
                response.setTransactionDate(LocalDate.parse(entity.getTransactionDate()));
                response.setTransactionTime(LocalTime.parse(entity.getTransactionTime().toString()));
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
                response.setBalanceAmount(entity.getBalanceAmount());

                return response;
        }

        private ProformaInvoiceTransactionResponse mapProformaInvoiceEntityToResponse(
                        ProformaInvoiceTransactionEntity entity) {
                ProformaInvoiceTransactionResponse response = new ProformaInvoiceTransactionResponse();

                response.setId(entity.getId());
                response.setTransactionNumber(entity.getTransactionNumber());
                response.setProformaInvoiceId(entity.getProformaInvoiceId());
                response.setProformaInvoiceNumber(entity.getProformaInvoiceNumber());
                response.setPartyId(entity.getPartyId());
                response.setPartyName(entity.getPartyName());
                response.setTransactionDate(LocalDate.parse(entity.getTransactionDate()));
                response.setTransactionTime(LocalTime.parse(entity.getTransactionTime()));
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

                response.setBalanceAmount(entity.getBalanceAmount());

                return response;
        }

}
