package com.cityfashionpos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cityfashionpos.dto.StateDto;

@Service
public interface StateService {

    /**
     * Get all active states
     */
    List<StateDto> getAllActiveStates();

    /**
     * Get all states (including inactive)
     */
    List<StateDto> getAllStates();
}
