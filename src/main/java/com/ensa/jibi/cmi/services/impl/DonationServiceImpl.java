package com.ensa.jibi.cmi.services.impl;

import com.ensa.jibi.cmi.domain.dto.creanceDto.DonationDto;
import com.ensa.jibi.cmi.domain.entities.creance.Donation;
import com.ensa.jibi.cmi.mappers.impl.DonationMapper;
import com.ensa.jibi.cmi.repositories.CreancierRepository;
import com.ensa.jibi.cmi.repositories.DonationRepository;
import com.ensa.jibi.cmi.services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DonationServiceImpl implements DonationService {
    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private CreancierRepository creancierRepository;
    @Autowired
    private DonationMapper donationMapper;

    @Override
    public DonationDto createDonation(DonationDto donationDto) {
        Long creancierId = donationDto.getCreancier().getId();
        boolean creancierExists = creancierRepository.existsById(creancierId);

        if (creancierExists) {
            Donation donation = donationMapper.mapFrom(donationDto);
            donation.setCreancier(creancierRepository.findById(creancierId).get());
            Donation savedDonation = donationRepository.save(donation);
            return donationMapper.mapTo(savedDonation);
        } else {
            throw new IllegalArgumentException("Creancier does not exist");
        }
    }

    @Override
    public DonationDto getDonation(Long id) {
        Optional<Donation> donation = donationRepository.findById(id);
        return donation.map(donationMapper::mapTo).orElse(null);
    }

    @Override
    public List<DonationDto> getAllDonations() {
        return donationRepository.findAll().stream()
                .map(donation -> donationMapper.mapTo(donation)).collect(Collectors.toList());
    }

    @Override
    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }
}
