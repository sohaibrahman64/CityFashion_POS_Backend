package com.cityfashionpos.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.StateDto;
import com.cityfashionpos.entity.StateEntity;
import com.cityfashionpos.repository.StateRepository;
import com.cityfashionpos.service.StateService;

@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StateRepository stateRepository;

    @Override
    public List<StateDto> getAllActiveStates() {
        List<StateEntity> states = stateRepository.findAllActiveStates();
        return states.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StateDto> getAllStates() {
        List<StateEntity> states = stateRepository.findAllStates();
        return states.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Map StateEntity to StateDto
     */
    private StateDto mapToDto(StateEntity entity) {
        StateDto dto = new StateDto();
        dto.setId(entity.getId());
        dto.setState(entity.getState());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setIsActive(entity.getIsActive());
        return dto;
    }
}
