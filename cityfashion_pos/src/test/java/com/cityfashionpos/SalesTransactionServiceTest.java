package com.cityfashionpos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.cityfashionpos.dto.SalesTransactionRequest;
import com.cityfashionpos.dto.SalesTransactionResponse;
import com.cityfashionpos.entity.SalesTransactionEntity;
import com.cityfashionpos.model.PaymentStatus;
import com.cityfashionpos.model.TransactionType;

/**
 * Basic test class to demonstrate SalesTransaction functionality
 * Note: This is a unit test that doesn't require database connection
 */
@SpringBootTest
@ActiveProfiles("test")
public class SalesTransactionServiceTest {

    @Test
    public void testSalesTransactionEntityCreation() {
        // Test entity creation and business logic
        SalesTransactionEntity transaction = new SalesTransactionEntity();
        transaction.setTransactionNumber("ST-00001");
        transaction.setTotalAmount(new BigDecimal("1000.00"));
        transaction.setTaxAmount(new BigDecimal("180.00"));
        transaction.setDiscountAmount(new BigDecimal("50.00"));
        transaction.setReceivedAmount(new BigDecimal("1130.00"));

        // Test calculation methods
        transaction.calculateNetAmount();
        transaction.calculatePaymentStatus();

        assertNotNull(transaction.getTransactionNumber());
        assertEquals(new BigDecimal("1130.00"), transaction.getNetAmount());
        assertEquals(new BigDecimal("0.00"), transaction.getBalanceAmount());
        assertEquals(PaymentStatus.PAID, transaction.getPaymentStatus());
        assertEquals(TransactionType.SALE, transaction.getTransactionType());
    }

    @Test
    public void testSalesTransactionRequestCreation() {
        // Test DTO creation
        SalesTransactionRequest request = new SalesTransactionRequest();
        request.setTotalAmount(new BigDecimal("2000.00"));
        request.setReceivedAmount(new BigDecimal("1500.00"));
        request.setBalanceAmount(new BigDecimal("500.00"));
        request.setPartyName("John Doe");
        request.setTransactionDate(LocalDate.now());

        assertNotNull(request.getTotalAmount());
        assertEquals("John Doe", request.getPartyName());
        assertEquals(TransactionType.SALE, request.getTransactionType());
        assertTrue(request.getBalanceAmount().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    public void testSalesTransactionResponseCreation() {
        // Test response DTO
        SalesTransactionResponse response = new SalesTransactionResponse(true, "Transaction created successfully");
        response.setId(1L);
        response.setTransactionNumber("ST-00001");
        response.setTotalAmount(new BigDecimal("1000.00"));
        response.setPaymentStatus(PaymentStatus.PAID);

        assertTrue(response.isSuccess());
        assertEquals("Transaction created successfully", response.getMessage());
        assertEquals(1L, response.getId());
        assertEquals(PaymentStatus.PAID, response.getPaymentStatus());
    }

    @Test
    public void testPaymentStatusCalculation() {
        SalesTransactionEntity transaction = new SalesTransactionEntity();

        // Test PAID status
        transaction.setNetAmount(new BigDecimal("1000.00"));
        transaction.setReceivedAmount(new BigDecimal("1000.00"));
        transaction.setBalanceAmount(BigDecimal.ZERO);
        transaction.calculatePaymentStatus();
        assertEquals(PaymentStatus.PAID, transaction.getPaymentStatus());

        // Test PARTIAL status
        transaction.setReceivedAmount(new BigDecimal("500.00"));
        transaction.setBalanceAmount(new BigDecimal("500.00"));
        transaction.calculatePaymentStatus();
        assertEquals(PaymentStatus.PARTIAL, transaction.getPaymentStatus());

        // Test UNPAID status
        transaction.setReceivedAmount(BigDecimal.ZERO);
        transaction.setBalanceAmount(new BigDecimal("1000.00"));
        transaction.calculatePaymentStatus();
        assertEquals(PaymentStatus.UNPAID, transaction.getPaymentStatus());
    }

    @Test
    public void testNetAmountCalculation() {
        SalesTransactionEntity transaction = new SalesTransactionEntity();
        transaction.setTotalAmount(new BigDecimal("1000.00"));
        transaction.setTaxAmount(new BigDecimal("180.00"));
        transaction.setDiscountAmount(new BigDecimal("50.00"));
        transaction.setReceivedAmount(new BigDecimal("800.00"));

        transaction.calculateNetAmount();

        // Net amount = Total + Tax - Discount = 1000 + 180 - 50 = 1130
        assertEquals(new BigDecimal("1130.00"), transaction.getNetAmount());
        // Balance = Net - Received = 1130 - 800 = 330
        assertEquals(new BigDecimal("330.00"), transaction.getBalanceAmount());
        assertEquals(PaymentStatus.PARTIAL, transaction.getPaymentStatus());
    }
}
