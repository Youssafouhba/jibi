package com.ensa.jibi.cmi.controllers;

import com.ensa.jibi.backend.domain.requests.TransferRequest;
import com.ensa.jibi.cmi.domain.dto.ComptePaiementDto;
import com.ensa.jibi.cmi.services.ComptePaimentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptePaiements")
public class ComptePaiementController {

    @Autowired
    private ComptePaimentService comptePaimentService;

    @PostMapping
    public ResponseEntity<ComptePaiementDto> createComptePaiement(@RequestBody ComptePaiementDto comptePaiementDto) {
        ComptePaiementDto createdComptePaiement = comptePaimentService.save(comptePaiementDto);
        return ResponseEntity.ok(createdComptePaiement);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComptePaiementDto> getComptePaiement(@PathVariable String id) {
        return comptePaimentService.findOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ComptePaiementDto>> getAllComptePaiements() {
        List<ComptePaiementDto> comptePaiements = comptePaimentService.findAll();
        return ResponseEntity.ok(comptePaiements);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ComptePaiementDto> partialUpdateComptePaiement(@PathVariable String id, @RequestBody ComptePaiementDto comptePaiementDto) {
        ComptePaiementDto updatedComptePaiement = comptePaimentService.partialUpdate(id, comptePaiementDto);
        return ResponseEntity.ok(updatedComptePaiement);
    }

    @PostMapping("/{id}/payer")
    public ResponseEntity<ComptePaiementDto> payer(@PathVariable String id, @RequestParam Long creanceId, @RequestParam Double montant) {
        ComptePaiementDto updatedComptePaiement = comptePaimentService.payer(id, creanceId, montant);
        return ResponseEntity.ok(updatedComptePaiement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComptePaiement(@PathVariable String id) {
        comptePaimentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/recharge")
    public ResponseEntity<?> rechargeSolde(@PathVariable String id, @RequestParam Double montant) {
        try {
            ComptePaiementDto updatedComptePaiement = comptePaimentService.rechargeSolde(id, montant);
            return ResponseEntity.ok(updatedComptePaiement);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferSolde(@RequestBody TransferRequest transferRequest) {
        try {
            ComptePaiementDto updatedComptePaiement = comptePaimentService.transferSolde(transferRequest.getFromAccount(), transferRequest.getToAccount(), transferRequest.getAmount());
            return ResponseEntity.ok(updatedComptePaiement);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
