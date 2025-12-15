package com.cityfashionpos.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.PartyRequestDTO;
import com.cityfashionpos.dto.PartyResponseDTO;
import com.cityfashionpos.dto.PartyResponseDTO.GstTypeInfo;
import com.cityfashionpos.dto.PartyResponseDTO.StateInfo;
import com.cityfashionpos.entity.GstTypeEntity;
import com.cityfashionpos.entity.PartyEntity;
import com.cityfashionpos.entity.StateEntity;
import com.cityfashionpos.repository.GstTypeRepository;
import com.cityfashionpos.repository.StateRepository;
import com.cityfashionpos.service.PartyService;

@RestController
@RequestMapping("/api/parties")
@CrossOrigin(origins = "*")
public class PartiesController {

    @Autowired
    private PartyService partyService;

    @Autowired
    private GstTypeRepository gstTypeRepository;

    @Autowired
    private StateRepository stateRepository;

    // Create a new party
    @PostMapping("/create")
    public ResponseEntity<?> createParty(@RequestBody PartyRequestDTO requestDTO) {
        try {
            PartyEntity party = convertToEntity(requestDTO);
            PartyEntity savedParty = partyService.createParty(party);
            PartyResponseDTO responseDTO = convertToResponseDTO(savedParty);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating party: " + e.getMessage());
        }
    }

    // Get all active parties
    @GetMapping("/getAll")
    public ResponseEntity<List<PartyResponseDTO>> getAllParties() {
        List<PartyEntity> parties = partyService.getAllActiveParties();
        List<PartyResponseDTO> responseDTOs = parties.stream().map(this::convertToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    // Get party by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPartyById(@PathVariable Long id) {
        Optional<PartyEntity> party = partyService.getPartyById(id);
        if (party.isPresent()) {
            PartyResponseDTO responseDTO = convertToResponseDTO(party.get());
            return ResponseEntity.ok(responseDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // Update party
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateParty(@PathVariable Long id, @RequestBody PartyRequestDTO requestDTO) {
        try {
            PartyEntity party = convertToEntity(requestDTO);
            PartyEntity updatedParty = partyService.updateParty(id, party);
            PartyResponseDTO responseDTO = convertToResponseDTO(updatedParty);
            return ResponseEntity.ok(responseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating party: " + e.getMessage());
        }
    }

    // Delete party (soft delete)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteParty(@PathVariable Long id) {
        try {
            partyService.deleteParty(id);
            return ResponseEntity.ok().body("Party deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting party: " + e.getMessage());
        }
    }

    // Search parties by name
    @GetMapping("/search/name")
    public ResponseEntity<List<PartyResponseDTO>> searchPartiesByName(@RequestParam String keyword) {
        List<PartyEntity> parties = partyService.searchPartiesByName(keyword);
        List<PartyResponseDTO> responseDTOs = parties.stream().map(this::convertToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    // Search party by GSTIN
    @GetMapping("/search/gstin")
    public ResponseEntity<?> searchPartyByGstin(@RequestParam String gstin) {
        Optional<PartyEntity> party = partyService.searchPartyByGstin(gstin);
        if (party.isPresent()) {
            PartyResponseDTO responseDTO = convertToResponseDTO(party.get());
            return ResponseEntity.ok(responseDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // Search parties by phone number
    @GetMapping("/search/phone")
    public ResponseEntity<List<PartyResponseDTO>> searchPartiesByPhone(@RequestParam String phoneNumber) {
        List<PartyEntity> parties = partyService.searchPartiesByPhone(phoneNumber);
        List<PartyResponseDTO> responseDTOs = parties.stream().map(this::convertToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    // Search parties by email
    @GetMapping("/search/email")
    public ResponseEntity<List<PartyResponseDTO>> searchPartiesByEmail(@RequestParam String emailId) {
        List<PartyEntity> parties = partyService.searchPartiesByEmail(emailId);
        List<PartyResponseDTO> responseDTOs = parties.stream().map(this::convertToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    // Check if party name exists
    @GetMapping("/exists/name")
    public ResponseEntity<Boolean> checkPartyNameExists(@RequestParam String partyName) {
        boolean exists = partyService.existsByPartyName(partyName);
        return ResponseEntity.ok(exists);
    }

    // Check if GSTIN exists
    @GetMapping("/exists/gstin")
    public ResponseEntity<Boolean> checkGstinExists(@RequestParam String gstin) {
        boolean exists = partyService.existsByGstin(gstin);
        return ResponseEntity.ok(exists);
    }

    // Helper method to convert DTO to Entity
    private PartyEntity convertToEntity(PartyRequestDTO dto) {
        PartyEntity party = new PartyEntity();
        party.setPartyName(dto.getPartyName());
        party.setGstin(dto.getGstin());
        party.setPhoneNumber(dto.getPhoneNumber());
        party.setEmailId(dto.getEmailId());
        party.setBillingAddress(dto.getBillingAddress());
        party.setShippingAddress(dto.getShippingAddress());
        party.setEnableShipping(dto.getEnableShipping());
        party.setOpeningBalance(dto.getOpeningBalance());
        party.setUpdatedBalance(dto.getOpeningBalance());
        party.setAsOfDate(dto.getAsOfDate());
        party.setPaymentType(dto.getPaymentType());
        party.setCreditLimitType(dto.getCreditLimitType());
        party.setCustomLimit(dto.getCustomLimit());

        // Set GST Type if provided
        if (dto.getGstTypeId() != null) {
            Optional<GstTypeEntity> gstType = gstTypeRepository.findById(dto.getGstTypeId());
            gstType.ifPresent(party::setGstType);
        }

        // Set State if provided
        if (dto.getStateId() != null) {
            Optional<StateEntity> state = stateRepository.findById(dto.getStateId());
            state.ifPresent(party::setState);
        }

        return party;
    }

    // Helper method to convert Entity to Response DTO
    private PartyResponseDTO convertToResponseDTO(PartyEntity party) {
        PartyResponseDTO dto = new PartyResponseDTO();
        dto.setId(party.getId());
        dto.setPartyName(party.getPartyName());
        dto.setGstin(party.getGstin());
        dto.setPhoneNumber(party.getPhoneNumber());
        dto.setEmailId(party.getEmailId());
        dto.setBillingAddress(party.getBillingAddress());
        dto.setShippingAddress(party.getShippingAddress());
        dto.setEnableShipping(party.getEnableShipping());
        dto.setOpeningBalance(party.getOpeningBalance());
        dto.setAsOfDate(party.getAsOfDate());
        dto.setPaymentType(party.getPaymentType());
        dto.setCreditLimitType(party.getCreditLimitType());
        dto.setCustomLimit(party.getCustomLimit());
        dto.setIsActive(party.getIsActive());
        dto.setCreatedAt(party.getCreatedAt());
        dto.setUpdatedAt(party.getUpdatedAt());

        // Set GST Type info if present
        if (party.getGstType() != null) {
            GstTypeInfo gstTypeInfo = new GstTypeInfo(party.getGstType().getId(), party.getGstType().getGstType());
            dto.setGstType(gstTypeInfo);
        }

        // Set State info if present
        if (party.getState() != null) {
            StateInfo stateInfo = new StateInfo(party.getState().getId(), party.getState().getState());
            dto.setState(stateInfo);
        }

        return dto;
    }
}
