package com.ensa.jibi.cmi.controllers;

import com.ensa.jibi.cmi.domain.dto.ConfirmationPaiementDto;
import com.ensa.jibi.cmi.domain.dto.ConfirmationRequest;
import com.ensa.jibi.cmi.services.ConfirmationPaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/confirmationPaiements")
public class ConfirmationPaiementController {

    @Autowired
    private ConfirmationPaiementService confirmationPaiementService;

    @GetMapping
    public ResponseEntity<List<ConfirmationPaiementDto>> getAllConfirmationsPaiement(){
        List<ConfirmationPaiementDto> confirmationsPaiement = confirmationPaiementService.getAllConfirmationsPaiement();
        return ResponseEntity.ok(confirmationsPaiement);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConfirmationPaiementDto> getConfirmationPaiement(@PathVariable Long id){
        ConfirmationPaiementDto confirmationPaiement = confirmationPaiementService.getConfirmationPaiement(id);
        if(confirmationPaiement != null){
            return ResponseEntity.ok(confirmationPaiement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createConfirmationPaiement(@RequestBody ConfirmationRequest confirmationPaiementDto){
        try {
            ConfirmationPaiementDto createdConfirmationPaiement = confirmationPaiementService.createConfirmationPaiement(confirmationPaiementDto);
            return ResponseEntity.ok(createdConfirmationPaiement);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConfirmationPaiement(@PathVariable Long id){
        confirmationPaiementService.deleteConfirmationPaiement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/comptePaiement/{comptePaiementId}")
    public ResponseEntity<List<ConfirmationPaiementDto>> getConfirmationsByComptePaiementId(@PathVariable String comptePaiementId) {
        List<ConfirmationPaiementDto> confirmationsPaiement = confirmationPaiementService.getConfirmationsByComptePaiementId(comptePaiementId);
        return ResponseEntity.ok(confirmationsPaiement);
    }
}
