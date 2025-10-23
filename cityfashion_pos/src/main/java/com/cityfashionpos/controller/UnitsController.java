package com.cityfashionpos.controller;

import com.cityfashionpos.entity.UnitsEntity;
import com.cityfashionpos.service.UnitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/units")
@CrossOrigin(origins = "*")
public class UnitsController {

    @Autowired
    private UnitsService unitsService;

    /**
     * Get all units
     * GET /api/units/all
     */
    @GetMapping("/getAllUnits")
    public ResponseEntity<List<UnitsEntity>> getAllUnits() {
        try {
            List<UnitsEntity> units = unitsService.getAllUnits();
            return ResponseEntity.ok(units);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Get unit by ID
     * GET /api/units/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<UnitsEntity> getUnitById(@PathVariable Long id) {
        try {
            UnitsEntity unit = unitsService.getUnitById(id);
            if (unit != null) {
                return ResponseEntity.ok(unit);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Get unit by name
     * GET /api/units/name/{name}
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<UnitsEntity> getUnitByName(@PathVariable String name) {
        try {
            UnitsEntity unit = unitsService.getUnitByName(name);
            if (unit != null) {
                return ResponseEntity.ok(unit);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Get unit by abbreviation
     * GET /api/units/abbr/{abbr}
     */
    @GetMapping("/abbr/{abbr}")
    public ResponseEntity<UnitsEntity> getUnitByAbbr(@PathVariable String abbr) {
        try {
            UnitsEntity unit = unitsService.getUnitByAbbr(abbr);
            if (unit != null) {
                return ResponseEntity.ok(unit);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Create a new unit
     * POST /api/units
     */
    @PostMapping
    public ResponseEntity<UnitsEntity> createUnit(@RequestBody UnitsEntity unit) {
        try {
            UnitsEntity createdUnit = unitsService.createUnit(unit);
            return ResponseEntity.ok(createdUnit);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Update an existing unit
     * PUT /api/units/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<UnitsEntity> updateUnit(@PathVariable Long id, @RequestBody UnitsEntity unit) {
        try {
            unit.setId(id);
            UnitsEntity updatedUnit = unitsService.updateUnit(unit);
            return ResponseEntity.ok(updatedUnit);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Delete a unit
     * DELETE /api/units/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnit(@PathVariable Long id) {
        try {
            unitsService.deleteUnit(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
