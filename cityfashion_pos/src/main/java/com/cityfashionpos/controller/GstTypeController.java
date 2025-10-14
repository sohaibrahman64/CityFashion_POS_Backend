package com.cityfashionpos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.GstTypeDto;
import com.cityfashionpos.service.GstTypeService;

@RestController
@RequestMapping("/api/gst-type")
@CrossOrigin(origins = "*")
public class GstTypeController {

    @Autowired
    private GstTypeService gstTypeService;

    /**
     * Get all active GST types
     */
    @GetMapping("/active")
    public ResponseEntity<List<GstTypeDto>> getAllActiveGstTypes() {
        List<GstTypeDto> gstTypes = gstTypeService.getAllActiveGstTypes();
        return ResponseEntity.ok(gstTypes);
    }

    /**
     * Get all GST types (including inactive)
     */
    @GetMapping("/all")
    public ResponseEntity<List<GstTypeDto>> getAllGstTypes() {
        List<GstTypeDto> gstTypes = gstTypeService.getAllGstTypes();
        return ResponseEntity.ok(gstTypes);
    }
}
