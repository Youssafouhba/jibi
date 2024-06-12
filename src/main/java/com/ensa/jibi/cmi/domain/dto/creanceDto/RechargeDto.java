package com.ensa.jibi.cmi.domain.dto.creanceDto;

import com.ensa.jibi.cmi.domain.enums.RechargeAmmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class RechargeDto extends CreanceDto{
    private RechargeAmmount montant;
    private String email;
}
