package com.ensa.jibi.cmi.controllers;

import com.ensa.jibi.cmi.domain.dto.creanceDto.RechargeDto;
import com.ensa.jibi.cmi.services.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recharges")
public class RechargeController {
    private final RechargeService rechargeService;

    @Autowired
    public RechargeController(RechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }

    @GetMapping
    public ResponseEntity<List<RechargeDto>> getAllRecharges() {
        List<RechargeDto> recharges = rechargeService.getAllRecharges();
        return new ResponseEntity<>(recharges, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RechargeDto> getRecharge(@PathVariable Long id) {
        RechargeDto rechargeDto = rechargeService.getRecharge(id);
        if (rechargeDto != null) {
            return new ResponseEntity<>(rechargeDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<RechargeDto> createRecharge(@RequestBody RechargeDto rechargeDto) {
        RechargeDto createdRechargeDto = rechargeService.createRecharge(rechargeDto);
        return new ResponseEntity<>(createdRechargeDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RechargeDto> updateRecharge(@PathVariable Long id, @RequestBody RechargeDto rechargeDto) {
        RechargeDto updatedRechargeDto = rechargeService.updateRecharge(id, rechargeDto);
        if (updatedRechargeDto != null) {
            return new ResponseEntity<>(updatedRechargeDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecharge(@PathVariable Long id) {
        rechargeService.deleteRecharge(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
