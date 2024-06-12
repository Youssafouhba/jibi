package com.ensa.jibi.cmi.domain.dto.creanceDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FactureDto extends CreanceDto{
    private Long numFacture;
    private String email;
}
