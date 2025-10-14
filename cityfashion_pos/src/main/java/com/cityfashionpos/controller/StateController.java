package com.cityfashionpos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.StateDto;
import com.cityfashionpos.service.StateService;

@RestController
@RequestMapping("/api/states")
@CrossOrigin(origins = "*")
public class StateController {

    @Autowired
    private StateService stateService;

    /**
     * Get all active states
     */
    @GetMapping("/active")
    public ResponseEntity<List<StateDto>> getAllActiveStates() {
        List<StateDto> states = stateService.getAllActiveStates();
        return ResponseEntity.ok(states);
    }

    /**
     * Get all states (including inactive)
     */
    @GetMapping("/all")
    public ResponseEntity<List<StateDto>> getAllStates() {
        List<StateDto> states = stateService.getAllStates();
        return ResponseEntity.ok(states);
    }
}
