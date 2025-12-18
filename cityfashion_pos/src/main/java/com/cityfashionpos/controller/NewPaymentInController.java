package com.cityfashionpos.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.NewPaymentInRequest;
import com.cityfashionpos.dto.NewPaymentInResponse;
import com.cityfashionpos.dto.NewProformaInvoiceResponse;
import com.cityfashionpos.repository.NewPaymentInRepository;
import com.cityfashionpos.service.NewPaymentInService;

@RestController
@RequestMapping("/api/payment-in")
public class NewPaymentInController {

    @Autowired
    NewPaymentInRepository newPaymentInRepository;

    @Autowired
    private NewPaymentInService newPaymentInService;

    @PostMapping("/create")
    public ResponseEntity<NewPaymentInResponse> createNewPaymentIn(@RequestBody NewPaymentInRequest request) {
        try {
            NewPaymentInResponse response = newPaymentInService.createNewPaymentIn(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            NewPaymentInResponse errorResponse = new NewPaymentInResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Error processing request: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/receipt-number")
    public ResponseEntity<Map<String, String>> generateReceiptNumber() {
        Long latestId = newPaymentInRepository.findMaxPaymentInId();
        if (latestId == null) {
            latestId = 0L;
        }
        String nextReceiptNumber = String.format("%05d", latestId + 1);

        Map<String, String> response = new HashMap<>();
        response.put("receiptNumber", nextReceiptNumber);

        return ResponseEntity.ok(response);

    }

}
