package com.cityfashionpos.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.service.BarcodeService;

@RestController
@RequestMapping("/api/barcode")
@CrossOrigin(origins = "*")
public class BarcodeController {

    @Autowired
    private BarcodeService barcodeService;

    @GetMapping("/generateBarcode")
    public Map<String, String> generateBarcode() {
        String generated = barcodeService.generateNextBarcode();
        return Map.of("barcode", generated);
    }
}