package com.ensa.jibi.cmi.services;

import com.ensa.jibi.cmi.domain.dto.creanceDto.RechargeDto;
import java.util.List;

public interface RechargeService {
    List<RechargeDto> getAllRecharges();
    RechargeDto getRecharge(Long id);
    RechargeDto createRecharge(RechargeDto rechargeDto);
    RechargeDto updateRecharge(Long id, RechargeDto rechargeDto);
    void deleteRecharge(Long id);
}
