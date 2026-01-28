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

import com.cityfashionpos.dto.NewDeliveryChallanRequest;
import com.cityfashionpos.dto.NewDeliveryChallanResponse;
import com.cityfashionpos.repository.NewDeliveryChallanRepository;
import com.cityfashionpos.service.NewDeliveryChallanService;

@RestController
@RequestMapping("/api/delivery-challan")
@CrossOrigin(origins = "*")
public class NewDeliveryChallanController {

    @Autowired
    private NewDeliveryChallanRepository newDeliveryChallanRepository;
    @Autowired
    private NewDeliveryChallanService newDeliveryChallanService;

    @PostMapping("/create")
    public ResponseEntity<NewDeliveryChallanResponse> createNewDeliveryChallan(
            @RequestBody NewDeliveryChallanRequest request) {
        try {
            NewDeliveryChallanResponse response = newDeliveryChallanService.createNewDeliveryChallan(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            NewDeliveryChallanResponse errorResponse = new NewDeliveryChallanResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Error processing request: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/delivery-challan-number")
    public ResponseEntity<Map<String, String>> generateDeliveryChallanNumber() {
        Long latestId = newDeliveryChallanRepository.findMaxDeliveryChallanId();

        if (latestId == null) {
            latestId = 0L;
        }

        String nextDeliveryChallanNumber = String.format("DC-%05d", latestId + 1);
        Map<String, String> response = new HashMap<>();
        response.put("deliveryChallanNumber", nextDeliveryChallanNumber);

        return ResponseEntity.ok(response);
    }
}
