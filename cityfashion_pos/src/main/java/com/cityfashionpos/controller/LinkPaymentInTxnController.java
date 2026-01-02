package com.cityfashionpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.LinkPaymentInRequest;
import com.cityfashionpos.dto.LinkPaymentInResponse;
import com.cityfashionpos.service.LinkPaymentInTxnService;

@RestController
@RequestMapping("/api/link-payment-in-txn")
public class LinkPaymentInTxnController {

    @Autowired
    private LinkPaymentInTxnService linkPaymentInService;

    @PostMapping("/create")
    public ResponseEntity<LinkPaymentInResponse> linkPaymentInTransaction(@RequestBody LinkPaymentInRequest request) {
        try {
            // Implement the logic to link payment in transaction here

            LinkPaymentInResponse response = linkPaymentInService.createLinkPaymentIn(request);
            response.setSuccess(true);
            response.setMessage("Payment in transaction linked successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LinkPaymentInResponse errorResponse = new LinkPaymentInResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Error linking payment in transaction: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

}
