package com.ensa.jibi.cmi.domain.dto;

import com.ensa.jibi.cmi.domain.dto.creanceDto.CreanceDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationPaiementDto {
    private Long id;
    private Double montant;
    private ComptePaiementDto compte;
    private CreanceDto creance;
    private LocalDate date;
}