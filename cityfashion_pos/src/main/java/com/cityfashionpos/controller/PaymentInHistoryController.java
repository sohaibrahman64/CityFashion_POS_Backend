package com.cityfashionpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.PaymentInHistoryRequest;
import com.cityfashionpos.dto.PaymentInHistoryResponse;
import com.cityfashionpos.service.PaymentInHistoryService;

@RestController
@RequestMapping("/api/payment-in-history")

public class PaymentInHistoryController {

    @Autowired
    private PaymentInHistoryService paymentInHistoryService;

    @PostMapping("/create")
    public ResponseEntity<PaymentInHistoryResponse> createPaymentInHistory(
            @RequestBody PaymentInHistoryRequest request) {
        try {
            PaymentInHistoryResponse response = paymentInHistoryService.createPaymentInHistory(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            PaymentInHistoryResponse response = new PaymentInHistoryResponse();
            response.setSuccess(false);
            response.setMessage("Failed to create payment in history: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

}
