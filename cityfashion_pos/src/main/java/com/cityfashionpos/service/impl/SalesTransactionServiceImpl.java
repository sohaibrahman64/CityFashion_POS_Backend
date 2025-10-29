package com.cityfashionpos.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import com.cityfashionpos.dto.SalesReportResponse;
import com.cityfashionpos.dto.SalesTransactionRequest;
import com.cityfashionpos.dto.SalesTransactionResponse;
import com.cityfashionpos.dto.SalesTransactionSummaryResponse;
import com.cityfashionpos.entity.NewSalesInvoiceEntity;
import com.cityfashionpos.entity.PartyEntity;
import com.cityfashionpos.entity.SalesTransactionEntity;
import com.cityfashionpos.model.TransactionType;
import com.cityfashionpos.repository.CustomerRepository;
import com.cityfashionpos.repository.NewSalesInvoiceRepository;
import com.cityfashionpos.repository.PartyRepository;
import com.cityfashionpos.repository.SalesTransactionRepository;
import com.cityfashionpos.service.SalesTransactionService;

@Service
@Transactional
public class SalesTransactionServiceImpl implements SalesTransactionService {

    private static final Logger logger = LoggerFactory.getLogger(SalesTransactionServiceImpl.class);

    @Autowired
    private SalesTransactionRepository salesTransactionRepository;

    @Autowired
    private NewSalesInvoiceRepository invoiceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Override
    public SalesTransactionResponse createSalesTransaction(SalesTransactionRequest request) {
        try {
            logger.info("Creating sales transaction for amount: {}", request.getTotalAmount());

            // Generate transaction number if not provided
            if (request.getTransactionNumber() == null || request.getTransactionNumber().isEmpty()) {
                request.setTransactionNumber(generateTransactionNumber());
            }

            // Create entity from request
            SalesTransactionEntity entity = mapRequestToEntity(request);

            // Calculate amounts
            // entity.calculateNetAmount();
            entity.calculateProfitMargin();

            // Save entity
            entity = salesTransactionRepository.save(entity);

            logger.info("Sales transaction created successfully with ID: {}", entity.getId());
            return mapEntityToResponse(entity, true, "Sales transaction created successfully");

        } catch (Exception e) {
            logger.error("Error creating sales transaction: {}", e.getMessage(), e);
            return new SalesTransactionResponse(false, "Error creating sales transaction: " + e.getMessage());
        }
    }

    @Override
    public List<SalesTransactionResponse> getAllSalesTransactions() {
        try {
            List<SalesTransactionEntity> entities = salesTransactionRepository.findAll();
            return entities.stream()
                    .map(entity -> mapEntityToResponse(entity, true, null))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching all sales transactions: {}", e.getMessage(), e);
            return List.of();
        }
    }

    @Override
    public Optional<SalesTransactionResponse> getSalesTransactionById(Long id) {
        try {
            Optional<SalesTransactionEntity> entity = salesTransactionRepository.findById(id);
            return entity.map(e -> mapEntityToResponse(e, true, null));
        } catch (Exception e) {
            logger.error("Error fetching sales transaction by ID {}: {}", id, e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<SalesTransactionResponse> getSalesTransactionByNumber(String transactionNumber) {
        try {
            Optional<SalesTransactionEntity> entity = salesTransactionRepository
                    .findByTransactionNumber(transactionNumber);
            return entity.map(e -> mapEntityToResponse(e, true, null));
        } catch (Exception e) {
            logger.error("Error fetching sales transaction by number {}: {}", transactionNumber, e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public List<SalesTransactionResponse> getCurrentMonthTransactions() {
        try {
            List<SalesTransactionEntity> entities = salesTransactionRepository.findCurrentMonthTransactions();
            return entities.stream()
                    .map(entity -> mapEntityToResponse(entity, true, null))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching current month transactions: {}", e.getMessage(), e);
            return List.of();
        }
    }

    @Override
    public List<SalesTransactionResponse> getLastMonthTransactions() {
        try {
            List<SalesTransactionEntity> entities = salesTransactionRepository.findLastMonthTransactions();
            return entities.stream()
                    .map(entity -> mapEntityToResponse(entity, true, null))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching last month transactions: {}", e.getMessage(), e);
            return List.of();
        }
    }

    @Override
    public List<SalesTransactionResponse> getCurrentQuarterTransactions() {
        try {
            List<SalesTransactionEntity> entities = salesTransactionRepository.findCurrentQuarterTransactions();
            return entities.stream()
                    .map(entity -> mapEntityToResponse(entity, true, null))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching current quarter transactions: {}", e.getMessage(), e);
            return List.of();
        }
    }

    @Override
    public List<SalesTransactionResponse> getCurrentYearTransactions() {
        try {
            List<SalesTransactionEntity> entities = salesTransactionRepository.findCurrentYearTransactions();
            return entities.stream()
                    .map(entity -> mapEntityToResponse(entity, true, null))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching current year transactions: {}", e.getMessage(), e);
            return List.of();
        }
    }

    @Override
    public List<SalesTransactionResponse> getTransactionsByDateRange(LocalDate startDate, LocalDate endDate) {
        try {
            List<SalesTransactionEntity> entities = salesTransactionRepository.findByTransactionDateBetween(startDate,
                    endDate);
            return entities.stream()
                    .map(entity -> mapEntityToResponse(entity, true, null))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching transactions by date range: {}", e.getMessage(), e);
            return List.of();
        }
    }

    @Override
    public List<SalesTransactionResponse> getTransactionsByCustomer(Long customerId) {
        try {
            List<SalesTransactionEntity> entities = salesTransactionRepository.findByPartyId(customerId);
            return entities.stream()
                    .map(entity -> mapEntityToResponse(entity, true, null))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching transactions by customer {}: {}", customerId, e.getMessage(), e);
            return List.of();
        }
    }

    @Override
    public SalesTransactionSummaryResponse getCurrentMonthSummary() {
        try {
            BigDecimal currentMonthSales = salesTransactionRepository.getCurrentMonthTotalSales();
            BigDecimal currentMonthReceived = salesTransactionRepository.getCurrentMonthTotalReceived();
            BigDecimal currentMonthBalance = salesTransactionRepository.getCurrentMonthTotalBalance();
            Long currentMonthCount = salesTransactionRepository.getCurrentMonthTransactionCount();

            BigDecimal lastMonthSales = salesTransactionRepository.getLastMonthTotalSales();

            SalesTransactionSummaryResponse summary = new SalesTransactionSummaryResponse();
            summary.setTotalSalesAmount(currentMonthSales);
            summary.setTotalReceivedAmount(currentMonthReceived);
            summary.setTotalBalanceAmount(currentMonthBalance);
            summary.setTotalTransactions(currentMonthCount);
            summary.setPeriod("Current Month");
            summary.setComparisonPeriod("Last Month");

            summary.calculatePercentageChange(currentMonthSales, lastMonthSales);
            summary.calculateAverageTransactionValue();

            return summary;
        } catch (Exception e) {
            logger.error("Error calculating current month summary: {}", e.getMessage(), e);
            return new SalesTransactionSummaryResponse(false, "Error calculating summary: " + e.getMessage());
        }
    }

    @Override
    public SalesTransactionSummaryResponse getLastMonthSummary() {
        try {
            BigDecimal lastMonthSales = salesTransactionRepository.getLastMonthTotalSales();
            Long lastMonthCount = salesTransactionRepository.getLastMonthTransactionCount();

            SalesTransactionSummaryResponse summary = new SalesTransactionSummaryResponse();
            summary.setTotalSalesAmount(lastMonthSales);
            summary.setTotalTransactions(lastMonthCount);
            summary.setPeriod("Last Month");

            summary.calculateAverageTransactionValue();

            return summary;
        } catch (Exception e) {
            logger.error("Error calculating last month summary: {}", e.getMessage(), e);
            return new SalesTransactionSummaryResponse(false, "Error calculating summary: " + e.getMessage());
        }
    }

    @Override
    public SalesTransactionSummaryResponse getCurrentQuarterSummary() {
        try {
            LocalDate quarterStart = LocalDate.now().withDayOfMonth(1);
            int currentMonth = quarterStart.getMonthValue();
            int quarterStartMonth = ((currentMonth - 1) / 3) * 3 + 1;
            quarterStart = quarterStart.withMonth(quarterStartMonth);
            LocalDate quarterEnd = quarterStart.plusMonths(2)
                    .withDayOfMonth(quarterStart.plusMonths(2).lengthOfMonth());

            return getSummaryByDateRange(quarterStart, quarterEnd);
        } catch (Exception e) {
            logger.error("Error calculating current quarter summary: {}", e.getMessage(), e);
            return new SalesTransactionSummaryResponse(false, "Error calculating summary: " + e.getMessage());
        }
    }

    @Override
    public SalesTransactionSummaryResponse getCurrentYearSummary() {
        try {
            LocalDate yearStart = LocalDate.now().withDayOfYear(1);
            LocalDate yearEnd = LocalDate.now().withDayOfYear(LocalDate.now().lengthOfYear());

            return getSummaryByDateRange(yearStart, yearEnd);
        } catch (Exception e) {
            logger.error("Error calculating current year summary: {}", e.getMessage(), e);
            return new SalesTransactionSummaryResponse(false, "Error calculating summary: " + e.getMessage());
        }
    }

    @Override
    public SalesTransactionSummaryResponse getSummaryByDateRange(LocalDate startDate, LocalDate endDate) {
        try {
            BigDecimal totalSales = salesTransactionRepository.getTotalSalesForDateRange(startDate, endDate);
            BigDecimal totalReceived = salesTransactionRepository.getTotalReceivedForDateRange(startDate, endDate);
            BigDecimal totalBalance = salesTransactionRepository.getTotalBalanceForDateRange(startDate, endDate);

            List<SalesTransactionEntity> transactions = salesTransactionRepository
                    .findByTransactionDateBetween(startDate, endDate);
            Long totalCount = (long) transactions.size();

            SalesTransactionSummaryResponse summary = new SalesTransactionSummaryResponse();
            summary.setTotalSalesAmount(totalSales);
            summary.setTotalReceivedAmount(totalReceived);
            summary.setTotalBalanceAmount(totalBalance);
            summary.setTotalTransactions(totalCount);
            summary.setPeriod(startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " - " +
                    endDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            summary.calculateAverageTransactionValue();

            return summary;
        } catch (Exception e) {
            logger.error("Error calculating summary for date range: {}", e.getMessage(), e);
            return new SalesTransactionSummaryResponse(false, "Error calculating summary: " + e.getMessage());
        }
    }

    @Override
    public SalesTransactionResponse updateSalesTransaction(Long id, SalesTransactionRequest request) {
        try {
            Optional<SalesTransactionEntity> existingEntity = salesTransactionRepository.findById(id);
            if (existingEntity.isEmpty()) {
                return new SalesTransactionResponse(false, "Sales transaction not found with ID: " + id);
            }

            SalesTransactionEntity entity = existingEntity.get();
            updateEntityFromRequest(entity, request);
            entity.calculateNetAmount();
            entity.calculateProfitMargin();

            entity = salesTransactionRepository.save(entity);

            logger.info("Sales transaction updated successfully with ID: {}", entity.getId());
            return mapEntityToResponse(entity, true, "Sales transaction updated successfully");

        } catch (Exception e) {
            logger.error("Error updating sales transaction with ID {}: {}", id, e.getMessage(), e);
            return new SalesTransactionResponse(false, "Error updating sales transaction: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteSalesTransaction(Long id) {
        try {
            if (salesTransactionRepository.existsById(id)) {
                salesTransactionRepository.deleteById(id);
                logger.info("Sales transaction deleted successfully with ID: {}", id);
                return true;
            } else {
                logger.warn("Sales transaction not found with ID: {}", id);
                return false;
            }
        } catch (Exception e) {
            logger.error("Error deleting sales transaction with ID {}: {}", id, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public String generateTransactionNumber() {
        Long maxId = salesTransactionRepository.findMaxTransactionId();
        if (maxId == null) {
            maxId = 0L;
        }
        return String.format("ST-%05d", maxId + 1);
    }

    @Override
    public SalesTransactionResponse createTransactionFromInvoice(Long invoiceId) {
        try {
            Optional<NewSalesInvoiceEntity> invoiceOpt = invoiceRepository.findById(invoiceId);
            if (invoiceOpt.isEmpty()) {
                return new SalesTransactionResponse(false, "Invoice not found with ID: " + invoiceId);
            }

            NewSalesInvoiceEntity invoice = invoiceOpt.get();

            // Check if transaction already exists for this invoice
            Optional<SalesTransactionEntity> existingTransaction = salesTransactionRepository
                    .findByInvoiceId(invoiceId);
            if (existingTransaction.isPresent()) {
                return mapEntityToResponse(existingTransaction.get(), true,
                        "Transaction already exists for this invoice");
            }

            // Create new transaction from invoice
            SalesTransactionEntity transaction = new SalesTransactionEntity();
            transaction.setTransactionNumber(generateTransactionNumber());
            transaction.setInvoiceId(invoice.getId());
            transaction.setInvoiceNumber(invoice.getInvoiceNumber());
            transaction.setPartyId(invoice.getPartyId());
            transaction.setTransactionDate(invoice.getInvoiceDate());
            transaction.setTotalAmount(BigDecimal.valueOf(invoice.getTotalAmount()));
            transaction.setReceivedAmount(BigDecimal.valueOf(invoice.getReceivedAmount()));
            transaction.setBalanceAmount(BigDecimal.valueOf(invoice.getBalanceAmount()));
            transaction.setDiscountAmount(
                    BigDecimal.valueOf(invoice.getDiscountAmount() != null ? invoice.getDiscountAmount() : 0.0));
            transaction.setTransactionType(TransactionType.SALE);

            // Get customer name if available
            if (invoice.getPartyId() != null) {
                Optional<PartyEntity> party = partyRepository.findById(invoice.getPartyId());
                if (party.isPresent()) {
                    transaction.setPartyName(party.get().getPartyName());
                }
            }

            transaction.calculateNetAmount();
            SalesTransactionEntity savedTransaction = salesTransactionRepository.save(transaction);

            logger.info("Transaction created from invoice ID: {}", invoiceId);
            return mapEntityToResponse(savedTransaction, true, "Transaction created from invoice successfully");

        } catch (Exception e) {
            logger.error("Error creating transaction from invoice ID {}: {}", invoiceId, e.getMessage(), e);
            return new SalesTransactionResponse(false, "Error creating transaction from invoice: " + e.getMessage());
        }
    }

    @Override
    public List<SalesTransactionResponse> getTransactionsWithOutstandingBalance() {
        try {
            List<SalesTransactionEntity> entities = salesTransactionRepository.findTransactionsWithOutstandingBalance();
            return entities.stream()
                    .map(entity -> mapEntityToResponse(entity, true, null))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching transactions with outstanding balance: {}", e.getMessage(), e);
            return List.of();
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
    public Map<String, BigDecimal> getTotalReceivedAndBalanceAmounts() {
        try {
            // Get current totals (all-time)
            BigDecimal totalReceived = salesTransactionRepository.getTotalReceivedAmount();
            BigDecimal totalBalance = salesTransactionRepository.getTotalBalanceAmount();

            LocalDate firstDayLastMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1);
            LocalDate lastDayLastMonth = firstDayLastMonth.withDayOfMonth(firstDayLastMonth.lengthOfMonth());

            // Ensure values are not null
            totalReceived = totalReceived != null ? totalReceived : BigDecimal.ZERO;
            totalBalance = totalBalance != null ? totalBalance : BigDecimal.ZERO;

            BigDecimal totalSalesAmount = totalReceived.add(totalBalance);

            // Get last month's totals for comparison
            BigDecimal lastMonthReceived = salesTransactionRepository.getLastMonthTotalReceived(firstDayLastMonth,
                    lastDayLastMonth);
            BigDecimal lastMonthBalance = salesTransactionRepository.getLastMonthTotalBalance(firstDayLastMonth,
                    lastDayLastMonth);

            // Ensure last month values are not null, default to 0
            lastMonthReceived = lastMonthReceived != null ? lastMonthReceived : BigDecimal.ZERO;
            lastMonthBalance = lastMonthBalance != null ? lastMonthBalance : BigDecimal.ZERO;

            BigDecimal lastMonthTotalSales = lastMonthReceived.add(lastMonthBalance);

            // Calculate percentage change
            BigDecimal percentageData = calculatePercentageChangeVsLastMonth(totalSalesAmount, lastMonthTotalSales);

            Map<String, BigDecimal> totals = new HashMap<>();
            totals.put("totalReceivedAmount", totalReceived);
            totals.put("totalBalanceAmount", totalBalance);
            totals.put("totalSalesAmount", totalSalesAmount);
            totals.put("percentageChange", percentageData);

            logger.info(
                    "Total received: {}, Total balance: {}, Total sales amount: {}, Last month sales: {}, Percentage change: {}%",
                    totalReceived, totalBalance, totalSalesAmount, lastMonthTotalSales, percentageData);
            return totals;
        } catch (Exception e) {
            logger.error("Error calculating total received and balance amounts: {}", e.getMessage(), e);
            Map<String, BigDecimal> errorMap = new HashMap<>();
            errorMap.put("totalReceivedAmount", BigDecimal.ZERO);
            errorMap.put("totalBalanceAmount", BigDecimal.ZERO);
            errorMap.put("totalSalesAmount", BigDecimal.ZERO);
            errorMap.put("percentageChange", BigDecimal.ZERO);
            return errorMap;
        }
    }

    @Override
    public Map<String, BigDecimal> getTotalAmountsByDateRange(LocalDate fromDate, LocalDate toDate) {
        try {
            BigDecimal totalSales = salesTransactionRepository.getTotalSalesForDateRange(fromDate, toDate);
            BigDecimal totalReceived = salesTransactionRepository.getTotalReceivedForDateRange(fromDate, toDate);
            BigDecimal totalBalance = salesTransactionRepository.getTotalBalanceForDateRange(fromDate, toDate);

            LocalDate firstDayLastMonth = fromDate.minusMonths(1).withDayOfMonth(1);
            LocalDate lastDayLastMonth = firstDayLastMonth.withDayOfMonth(firstDayLastMonth.lengthOfMonth());

            // Ensure values are not null
            totalSales = totalSales != null ? totalSales : BigDecimal.ZERO;
            totalReceived = totalReceived != null ? totalReceived : BigDecimal.ZERO;
            totalBalance = totalBalance != null ? totalBalance : BigDecimal.ZERO;

            BigDecimal lastMonthSales = salesTransactionRepository.getTotalSalesForDateRange(firstDayLastMonth,
                    lastDayLastMonth);
            BigDecimal lastMonthReceived = salesTransactionRepository.getTotalReceivedForDateRange(firstDayLastMonth,
                    lastDayLastMonth);
            BigDecimal lastMonthBalance = salesTransactionRepository.getTotalBalanceForDateRange(firstDayLastMonth,
                    lastDayLastMonth);

            lastMonthSales = lastMonthSales != null ? lastMonthSales : BigDecimal.ZERO;
            lastMonthReceived = lastMonthReceived != null ? lastMonthReceived : BigDecimal.ZERO;
            lastMonthBalance = lastMonthBalance != null ? lastMonthBalance : BigDecimal.ZERO;

            BigDecimal lastMonthTotalSales = lastMonthReceived.add(lastMonthBalance);

            // Calculate percentage change
            BigDecimal percentageData = calculatePercentageChangeVsLastMonth(totalSales, lastMonthTotalSales);

            Map<String, BigDecimal> totals = new HashMap<>();
            totals.put("totalSalesAmount", totalSales);
            totals.put("totalReceivedAmount", totalReceived);
            totals.put("totalBalanceAmount", totalBalance);
            totals.put("percentageChange", percentageData);

            logger.info("Date range totals from {} to {}: Sales: {}, Received: {}, Balance: {}",
                    fromDate, toDate, totalSales, totalReceived, totalBalance);
            return totals;
        } catch (Exception e) {
            logger.error("Error calculating totals for date range {} to {}: {}", fromDate, toDate, e.getMessage(), e);
            Map<String, BigDecimal> errorMap = new HashMap<>();
            errorMap.put("totalSalesAmount", BigDecimal.ZERO);
            errorMap.put("totalReceivedAmount", BigDecimal.ZERO);
            errorMap.put("totalBalanceAmount", BigDecimal.ZERO);
            return errorMap;
        }
    }

    @Override
    public List<SalesReportResponse> getSalesRecordsByDateRange(LocalDate fromDate, LocalDate toDate) {
        try {
            logger.info("Fetching sales records for date range: {} to {}", fromDate, toDate);

            List<SalesTransactionEntity> transactions = salesTransactionRepository
                    .findByTransactionDateBetween(fromDate, toDate);

            List<SalesReportResponse> salesRecords = transactions.stream()
                    .map(this::mapToSalesReportResponse)
                    .collect(Collectors.toList());

            logger.info("Found {} sales records for date range {} to {}",
                    salesRecords.size(), fromDate, toDate);

            return salesRecords;
        } catch (Exception e) {
            logger.error("Error fetching sales records for date range {} to {}: {}",
                    fromDate, toDate, e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    // Helper method to map SalesTransactionEntity to SalesReportResponse
    private SalesReportResponse mapToSalesReportResponse(SalesTransactionEntity entity) {
        SalesReportResponse response = new SalesReportResponse();
        response.setTransactionId(entity.getId());
        response.setDate(entity.getTransactionDate());
        response.setInvoiceId(entity.getInvoiceId());
        response.setInvoiceNo(entity.getInvoiceNumber());
        response.setCustomerName(entity.getPartyName());
        response.setTransactionType(entity.getTransactionType());
        response.setPaymentMode(entity.getPaymentMode());
        response.setNetAmount(entity.getNetAmount());
        response.setBalanceAmount(entity.getBalanceAmount());
        return response;
    }

    // Helper methods
    private SalesTransactionEntity mapRequestToEntity(SalesTransactionRequest request) {
        SalesTransactionEntity entity = new SalesTransactionEntity();

        entity.setTransactionNumber(request.getTransactionNumber());
        entity.setInvoiceId(request.getInvoiceId());
        entity.setInvoiceNumber(request.getInvoiceNumber());
        entity.setPartyId(request.getPartyId());
        entity.setPartyName(request.getPartyName());
        entity.setTransactionDate(request.getTransactionDate());
        entity.setTransactionTime(request.getTransactionTime());
        entity.setTotalAmount(request.getTotalAmount());
        entity.setTaxAmount(request.getTaxAmount());
        entity.setDiscountAmount(request.getDiscountAmount());
        entity.setNetAmount(request.getNetAmount());
        entity.setReceivedAmount(request.getReceivedAmount());
        entity.setBalanceAmount(request.getBalanceAmount());
        entity.setPaymentMode(request.getPaymentMode());
        entity.setTransactionType(request.getTransactionType());
        entity.setItemCount(request.getItemCount());
        entity.setTotalQuantity(request.getTotalQuantity());
        entity.setCostOfGoodsSold(request.getCostOfGoodsSold());
        entity.setSalesPersonId(request.getSalesPersonId());
        entity.setSalesPersonName(request.getSalesPersonName());
        entity.setNotes(request.getNotes());
        entity.setCreatedBy(request.getCreatedBy());

        return entity;
    }

    private void updateEntityFromRequest(SalesTransactionEntity entity, SalesTransactionRequest request) {
        if (request.getPartyName() != null) {
            entity.setPartyName(request.getPartyName());
        }
        if (request.getTotalAmount() != null) {
            entity.setTotalAmount(request.getTotalAmount());
        }
        if (request.getTaxAmount() != null) {
            entity.setTaxAmount(request.getTaxAmount());
        }
        if (request.getDiscountAmount() != null) {
            entity.setDiscountAmount(request.getDiscountAmount());
        }
        if (request.getReceivedAmount() != null) {
            entity.setReceivedAmount(request.getReceivedAmount());
        }
        if (request.getPaymentMode() != null) {
            entity.setPaymentMode(request.getPaymentMode());
        }
        if (request.getNotes() != null) {
            entity.setNotes(request.getNotes());
        }

        entity.setUpdatedAt(LocalDateTime.now());
        entity.setUpdatedBy(request.getCreatedBy() != null ? request.getCreatedBy() : "system");
    }

    private SalesTransactionResponse mapEntityToResponse(SalesTransactionEntity entity, boolean success,
            String message) {
        SalesTransactionResponse response = new SalesTransactionResponse(success, message);

        response.setId(entity.getId());
        response.setTransactionNumber(entity.getTransactionNumber());
        response.setInvoiceId(entity.getInvoiceId());
        response.setInvoiceNumber(entity.getInvoiceNumber());
        response.setPartyId(entity.getPartyId());
        response.setPartyName(entity.getPartyName());
        response.setTransactionDate(entity.getTransactionDate());
        response.setTransactionTime(entity.getTransactionTime());
        response.setTotalAmount(entity.getTotalAmount());
        response.setTaxAmount(entity.getTaxAmount());
        response.setDiscountAmount(entity.getDiscountAmount());
        response.setNetAmount(entity.getNetAmount());
        response.setReceivedAmount(entity.getReceivedAmount());
        response.setBalanceAmount(entity.getBalanceAmount());
        response.setPaymentStatus(entity.getPaymentStatus());
        response.setPaymentMode(entity.getPaymentMode());
        response.setTransactionType(entity.getTransactionType());
        response.setItemCount(entity.getItemCount());
        response.setTotalQuantity(entity.getTotalQuantity());
        response.setProfitMargin(entity.getProfitMargin());
        response.setCostOfGoodsSold(entity.getCostOfGoodsSold());
        response.setSalesPersonId(entity.getSalesPersonId());
        response.setSalesPersonName(entity.getSalesPersonName());
        response.setNotes(entity.getNotes());
        response.setStatus(entity.getStatus());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        response.setCreatedBy(entity.getCreatedBy());
        response.setUpdatedBy(entity.getUpdatedBy());

        return response;
    }
}
