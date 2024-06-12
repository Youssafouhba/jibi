package com.ensa.jibi.cmi.mappers.impl;

import com.ensa.jibi.cmi.domain.dto.creanceDto.FactureDto;
import com.ensa.jibi.cmi.domain.entities.creance.Facture;
import com.ensa.jibi.cmi.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactureMapper implements Mapper<Facture, FactureDto> {
    @Autowired
    ModelMapper modelMapper;
    @Override
    public FactureDto mapTo(Facture facture) {
        return modelMapper.map(facture, FactureDto.class);
    }

    @Override
    public Facture mapFrom(FactureDto factureDto) {
        return modelMapper.map(factureDto, Facture.class);
    }
}
