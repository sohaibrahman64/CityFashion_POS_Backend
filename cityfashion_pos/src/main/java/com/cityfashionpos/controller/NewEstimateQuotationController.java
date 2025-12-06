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

import com.cityfashionpos.dto.NewEstimateQuotationRequest;
import com.cityfashionpos.dto.NewEstimateQuotationResponse;
import com.cityfashionpos.repository.NewEstimateQuotationRepository;
import com.cityfashionpos.service.NewEstimateQuotationService;

@RestController
@RequestMapping("/api/estimate-quotation")
@CrossOrigin(origins = "*")
public class NewEstimateQuotationController {
    @Autowired
    private NewEstimateQuotationService newEstimateQuotationService;

    @Autowired
    private NewEstimateQuotationRepository newEstimateQuotationRepository;

    @PostMapping("/create")
    public ResponseEntity<NewEstimateQuotationResponse> createNewEstimateQuotation(
            @RequestBody NewEstimateQuotationRequest request) {
        try {
            NewEstimateQuotationResponse response = newEstimateQuotationService.createNewEstimateQuotation(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            NewEstimateQuotationResponse errorResponse = new NewEstimateQuotationResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Error processing request: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("estimate-number")
    public ResponseEntity<Map<String, String>> generateEstimateNumber() {
        Long latestId = newEstimateQuotationRepository.findMaxEstimateQuotationId();

        if (latestId == null) {
            latestId = 0L;
        }

        String nextEstimateNumber = String.format("EQ-%05d", latestId + 1);
        Map<String, String> response = new HashMap<>();
        response.put("estimateNumber", nextEstimateNumber);

        return ResponseEntity.ok(response);
    }
}
