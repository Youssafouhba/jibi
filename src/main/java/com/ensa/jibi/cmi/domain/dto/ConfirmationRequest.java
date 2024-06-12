package com.ensa.jibi.cmi.domain.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ConfirmationRequest {
    private Long id;
    private Long creanceId;
    private String compteId;//numtel
    private Double montant;
    private LocalDate date;
}
