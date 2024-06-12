package com.ensa.jibi.cmi.domain.dto;

import com.ensa.jibi.cmi.domain.dto.creanceDto.FactureDto;
import com.ensa.jibi.cmi.domain.enums.ImpayeType;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImpayeDto {
    private Long id;

    private Double montant;
    private ImpayeType type;
    private LocalDate date;
    private FactureDto facture;
}
