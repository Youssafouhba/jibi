package com.ensa.jibi.cmi.services;

import com.ensa.jibi.cmi.domain.dto.creanceDto.DonationDto;

import java.util.List;

public interface DonationService {
    DonationDto createDonation(DonationDto donationDto);
    DonationDto getDonation(Long id);
    List<DonationDto> getAllDonations();
    void deleteDonation(Long id);
}
