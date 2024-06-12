package com.ensa.jibi.cmi.mappers.impl;

import com.ensa.jibi.cmi.domain.dto.creanceDto.RechargeDto;
import com.ensa.jibi.cmi.domain.entities.creance.Recharge;
import com.ensa.jibi.cmi.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RechargeMapper implements Mapper<Recharge, RechargeDto> {
    @Autowired
    ModelMapper modelMapper;
    @Override
    public RechargeDto mapTo(Recharge recharge) {
        return modelMapper.map(recharge, RechargeDto.class);
    }

    @Override
    public Recharge mapFrom(RechargeDto rechargeDto) {
        return modelMapper.map(rechargeDto, Recharge.class);
    }
}
