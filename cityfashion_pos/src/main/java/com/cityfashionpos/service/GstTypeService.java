package com.cityfashionpos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.GstTypeDto;

@Service
public interface GstTypeService {

    /**
     * Get all active GST types
     */
    List<GstTypeDto> getAllActiveGstTypes();

    /**
     * Get all GST types (including inactive)
     */
    List<GstTypeDto> getAllGstTypes();
}
