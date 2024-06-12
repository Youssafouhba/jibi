package com.ensa.jibi.cmi.controllers;

import com.ensa.jibi.cmi.domain.dto.creanceDto.FactureDto;
import com.ensa.jibi.cmi.services.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factures")
public class FactureController {

    private final FactureService factureService;

    @Autowired
    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    @GetMapping
    public ResponseEntity<List<FactureDto>> getAllFactures() {
        List<FactureDto> factures = factureService.getAllFactures();
        return new ResponseEntity<>(factures, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactureDto> getFacture(@PathVariable Long id) {
        FactureDto factureDto = factureService.getFacture(id);
        if (factureDto != null) {
            return new ResponseEntity<>(factureDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createFacture(@RequestBody FactureDto factureDto) {
        try {
            FactureDto createdFactureDto = factureService.createFacture(factureDto);
            return ResponseEntity.ok(createdFactureDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactureDto> updateFacture(@PathVariable Long id, @RequestBody FactureDto factureDto) {
        FactureDto updatedFactureDto = factureService.updateFacture(id, factureDto);
        if (updatedFactureDto != null) {
            return new ResponseEntity<>(updatedFactureDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacture(@PathVariable Long id) {
        factureService.deleteFacture(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
