package com.cityfashionpos.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.GstTypeDto;
import com.cityfashionpos.entity.GstTypeEntity;
import com.cityfashionpos.repository.GstTypeRepository;
import com.cityfashionpos.service.GstTypeService;

@Service
public class GstTypeServiceImpl implements GstTypeService {

    @Autowired
    private GstTypeRepository gstTypeRepository;

    @Override
    public List<GstTypeDto> getAllActiveGstTypes() {
        List<GstTypeEntity> gstTypes = gstTypeRepository.findAllActiveGstTypes();
        return gstTypes.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GstTypeDto> getAllGstTypes() {
        List<GstTypeEntity> gstTypes = gstTypeRepository.findAllGstTypes();
        return gstTypes.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Map GstTypeEntity to GstTypeDto
     */
    private GstTypeDto mapToDto(GstTypeEntity entity) {
        GstTypeDto dto = new GstTypeDto();
        dto.setId(entity.getId());
        dto.setGstType(entity.getGstType());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setIsActive(entity.getIsActive());
        return dto;
    }
}
