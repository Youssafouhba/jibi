package com.ensa.jibi.cmi.mappers.impl;

import com.ensa.jibi.cmi.domain.dto.creanceDto.DonationDto;
import com.ensa.jibi.cmi.domain.entities.creance.Donation;
import com.ensa.jibi.cmi.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DonationMapper implements Mapper<Donation, DonationDto> {
    @Autowired
    ModelMapper modelMapper;
    @Override
    public DonationDto mapTo(Donation donation) {
        return modelMapper.map(donation, DonationDto.class);
    }

    @Override
    public Donation mapFrom(DonationDto donationDto) {
        return modelMapper.map(donationDto, Donation.class);
    }
}
