package com.ensa.jibi.cmi.controllers;

import com.ensa.jibi.cmi.domain.dto.creanceDto.CreanceDto;
import com.ensa.jibi.cmi.services.CreanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creances")
public class CreanceController {

    @Autowired
    private CreanceService creanceService;

    @GetMapping
    public ResponseEntity<List<CreanceDto>> getAllCreances() {
        List<CreanceDto> creances = creanceService.getAllCreances();
        return ResponseEntity.ok(creances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCreance(@PathVariable Long id) {
        Object creance = creanceService.getCreance(id);
        if (creance != null) {
            return ResponseEntity.ok(creance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createCreance(@RequestBody CreanceDto creanceDto) {
        try {
            CreanceDto createdCreance = creanceService.createCreance(creanceDto);
            return ResponseEntity.ok(createdCreance);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/creancier/{id}")
    public ResponseEntity<List<CreanceDto>> getCreanceByCreancierId(@PathVariable Long id) {
        List<CreanceDto> creances = creanceService.getCreanceByCreancierId(id);
        return ResponseEntity.ok(creances);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CreanceDto> updateCreance(@PathVariable Long id, @RequestBody CreanceDto creanceDto) {
        CreanceDto updatedCreance = creanceService.updateCreance(id, creanceDto);
        if (updatedCreance != null) {
            return ResponseEntity.ok(updatedCreance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreance(@PathVariable Long id) {
        creanceService.deleteCreance(id);
        return ResponseEntity.noContent().build();
    }
}
