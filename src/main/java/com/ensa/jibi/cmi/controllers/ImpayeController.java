package com.ensa.jibi.cmi.controllers;

import com.ensa.jibi.cmi.domain.dto.ImpayeDto;
import com.ensa.jibi.cmi.domain.entities.Impaye;
import com.ensa.jibi.cmi.services.impl.ImpayeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/impayes")
public class ImpayeController {
    @Autowired
    private ImpayeServiceImpl impayeService;

    @GetMapping("/facture/{id}")
    public List<ImpayeDto> getImpayeByFacture(@PathVariable("id") Long numFacture) {
        return impayeService.getImpayesByFacture(numFacture);
    }

    @PostMapping
    public ResponseEntity<?> createImpaye(@RequestBody ImpayeDto impayeDto) {
        try {
            ImpayeDto createdImpayeDto = impayeService.createImpaye(impayeDto);
            return ResponseEntity.ok(createdImpayeDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImpayeDto> getImpaye(@PathVariable Long id) {
        ImpayeDto impayeDto = impayeService.getImpaye(id);
        if (impayeDto != null) {
            return new ResponseEntity<>(impayeDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImpayeDto> updateImpaye(@PathVariable Long id, @RequestBody ImpayeDto impayeDto) {
        ImpayeDto updatedImpayeDto = impayeService.updateImpaye(id, impayeDto);
        if (updatedImpayeDto != null) {
            return new ResponseEntity<>(updatedImpayeDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImpaye(@PathVariable Long id) {
        impayeService.deleteImpaye(id);
        return ResponseEntity.noContent().build();
    }
}
