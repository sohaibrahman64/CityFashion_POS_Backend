package com.cityfashionpos.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.NewSalesOrderRequest;
import com.cityfashionpos.dto.NewSalesOrderResponse;
import com.cityfashionpos.repository.NewSalesOrderRepository;
import com.cityfashionpos.service.NewSalesOrderService;

@RestController
@RequestMapping("/api/sales-order")
@CrossOrigin(origins = "*")
public class NewSalesOrderController {

    @Autowired
    private NewSalesOrderService newSalesOrderService;

    @Autowired
    private NewSalesOrderRepository newSalesOrderRepository;

    @PostMapping("/create")
    public ResponseEntity<NewSalesOrderResponse> createNewSalesOrder(
            @RequestBody NewSalesOrderRequest request) {
        try {
            NewSalesOrderResponse response = newSalesOrderService.createNewSalesOrder(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            NewSalesOrderResponse errorResponse = new NewSalesOrderResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Error processing request: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/sales-order-number")
    public ResponseEntity<Map<String, String>> generateSalesOrderNumber() {
        Long latestId = newSalesOrderRepository.findMaxSalesOrderId();

        if (latestId == null) {
            latestId = 0L;
        }

        String nextSalesOrderNumber = String.format("SO-%05d", latestId + 1);
        Map<String, String> response = new HashMap<>();
        response.put("salesOrderNumber", nextSalesOrderNumber);

        return ResponseEntity.ok(response);
    }
}
