package com.ensa.jibi.cmi.domain.dto.creanceDto;

import com.ensa.jibi.cmi.domain.dto.CreancierDto;
import com.ensa.jibi.cmi.domain.entities.Creancier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreanceDto {
    private Long id;
    private String nom;
    private String description;
    private CreancierDto creancier;
}
