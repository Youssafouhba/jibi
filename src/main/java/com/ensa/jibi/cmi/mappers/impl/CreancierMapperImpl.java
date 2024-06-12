package com.ensa.jibi.cmi.mappers.impl;

import com.ensa.jibi.cmi.domain.dto.CreancierDto;
import com.ensa.jibi.cmi.domain.entities.Creancier;
import com.ensa.jibi.cmi.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreancierMapperImpl implements Mapper<Creancier, CreancierDto> {
    @Autowired
    ModelMapper modelMapper;
    @Override
    public CreancierDto mapTo(Creancier creancier) {
        return modelMapper.map(creancier, CreancierDto.class);
    }

    @Override
    public Creancier mapFrom(CreancierDto creancierDto) {
        return modelMapper.map(creancierDto, Creancier.class);
    }
}
