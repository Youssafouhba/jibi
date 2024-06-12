package com.ensa.jibi.cmi.mappers.impl;

import com.ensa.jibi.cmi.domain.dto.ConfirmationPaiementDto;
import com.ensa.jibi.cmi.domain.entities.ConfirmationPaiement;
import com.ensa.jibi.cmi.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfirmationPaiementMapperImpl implements Mapper<ConfirmationPaiement, ConfirmationPaiementDto> {
    @Autowired
    ModelMapper modelMapper;

    @Override
    public ConfirmationPaiementDto mapTo(ConfirmationPaiement confirmationPaiement) {
        return modelMapper.map(confirmationPaiement, ConfirmationPaiementDto.class);
    }

    @Override
    public ConfirmationPaiement mapFrom(ConfirmationPaiementDto confirmationPaiementDto) {
        return modelMapper.map(confirmationPaiementDto, ConfirmationPaiement.class);
    }
}
