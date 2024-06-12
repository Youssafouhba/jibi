package com.ensa.jibi.cmi.mappers.impl;

import com.ensa.jibi.cmi.domain.dto.ImpayeDto;
import com.ensa.jibi.cmi.domain.entities.Impaye;
import com.ensa.jibi.cmi.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImpayeMapper implements Mapper<Impaye, ImpayeDto> {
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ImpayeDto mapTo(Impaye impaye) {
        return modelMapper.map(impaye, ImpayeDto.class);
    }

    @Override
    public Impaye mapFrom(ImpayeDto impayeDto) {
        return modelMapper.map(impayeDto, Impaye.class);
    }
}
