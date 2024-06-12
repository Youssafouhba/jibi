package com.ensa.jibi.cmi.controllers;

import com.ensa.jibi.cmi.domain.dto.CreancierDto;
import com.ensa.jibi.cmi.domain.entities.Creancier;
import com.ensa.jibi.cmi.services.CreancierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creanciers")
public class CreancierController {

    @Autowired
    private CreancierService creancierService;

    @GetMapping
    public ResponseEntity<List<CreancierDto>> getAllCreanciers() {
        List<CreancierDto> creanciers = creancierService.getAllCreanciers();
        return ResponseEntity.ok(creanciers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreancierDto> getCreancier(@PathVariable("id") Long id) {
        CreancierDto creancier = creancierService.getCreancier(id);
        if (creancier != null) {
            return ResponseEntity.ok(creancier);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/count")
    public Long countCreanciers() {
        System.out.println(creancierService.countCreanciers());
        return creancierService.countCreanciers();
    }

    @PostMapping
    public ResponseEntity<CreancierDto> createCreancier(@RequestBody CreancierDto creancierDto) {
        CreancierDto createdCreancier = creancierService.save(creancierDto);
        return ResponseEntity.ok(createdCreancier);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreancierDto> updateCreancier(@PathVariable Long id, @RequestBody CreancierDto creancierDto) {
        CreancierDto updatedCreancier = creancierService.updateCreancier(id, creancierDto);
        if (updatedCreancier != null) {
            return ResponseEntity.ok(updatedCreancier);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreancier(@PathVariable Long id) {
        creancierService.deleteCreancier(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/categorie/{categorie}")
    public List<Creancier> getCreanciersByCategorie(@PathVariable("categorie") String categorie) {
        return creancierService.getCreanciersByCategorie(categorie);
    }




}