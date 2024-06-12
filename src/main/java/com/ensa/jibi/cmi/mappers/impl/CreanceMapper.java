package com.ensa.jibi.cmi.mappers.impl;

import com.ensa.jibi.cmi.domain.dto.creanceDto.CreanceDto;
import com.ensa.jibi.cmi.domain.entities.creance.Creance;
import com.ensa.jibi.cmi.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreanceMapper implements Mapper<Creance, CreanceDto> {
    @Autowired
    ModelMapper modelMapper;

    @Override
    public CreanceDto mapTo(Creance creance) {
        return modelMapper.map(creance, CreanceDto.class);
    }

    @Override
    public Creance mapFrom(CreanceDto creanceDto) {
        return modelMapper.map(creanceDto, Creance.class);
    }
}
