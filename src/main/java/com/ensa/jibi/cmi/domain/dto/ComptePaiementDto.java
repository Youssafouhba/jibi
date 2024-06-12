package com.ensa.jibi.cmi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComptePaiementDto {
    private String id;//phone number of client
    private Double solde;
}
