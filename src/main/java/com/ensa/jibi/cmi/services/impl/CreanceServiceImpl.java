package com.ensa.jibi.cmi.services.impl;

import com.ensa.jibi.cmi.domain.dto.creanceDto.CreanceDto;
import com.ensa.jibi.cmi.domain.entities.creance.Creance;
import com.ensa.jibi.cmi.domain.entities.creance.Donation;
import com.ensa.jibi.cmi.domain.entities.creance.Facture;
import com.ensa.jibi.cmi.domain.entities.creance.Recharge;
import com.ensa.jibi.cmi.mappers.impl.CreanceMapper;
import com.ensa.jibi.cmi.mappers.impl.CreancierMapperImpl;
import com.ensa.jibi.cmi.repositories.CreanceRepository;
import com.ensa.jibi.cmi.repositories.CreancierRepository;
import com.ensa.jibi.cmi.services.CreanceService;
import com.ensa.jibi.cmi.services.DonationService;
import com.ensa.jibi.cmi.services.FactureService;
import com.ensa.jibi.cmi.services.RechargeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CreanceServiceImpl implements CreanceService {
    @Autowired
    private CreanceRepository creanceRepository;
    @Autowired
    private CreanceMapper creanceMapper;
    @Autowired
    private CreancierRepository creancierRepository;
    @Autowired
    private CreancierMapperImpl creancierMapper;
    private final DonationService donationService;
    private final FactureService factureService;
    private final RechargeService rechargeService;

    @Override
    public List<CreanceDto> getAllCreances(){
        return creanceRepository.findAll().stream()
                .map(creance -> creanceMapper.mapTo(creance)).collect(Collectors.toList());
    }

    @Override
    public Object getCreance(Long id){
        Creance creance = creanceRepository.findById(id).get();
        if(creance != null){
            if(creance instanceof Donation){
                return donationService.getDonation(id);
            } else if (creance instanceof Recharge) {
                return rechargeService.getRecharge(id);
            } else if (creance instanceof Facture) {
                return factureService.getFacture(id);
            }
        }
        return null;
    }

    @Override
    public CreanceDto createCreance(CreanceDto creanceDto){
        Long creancierId = creanceDto.getCreancier().getId();
        boolean creancierExists = creancierRepository.existsById(creancierId);

        if (creancierExists) {
            Creance creance = creanceMapper.mapFrom(creanceDto);
            creance.setCreancier(creancierRepository.
                    findById(creancierId).
                    get());
            Creance savedCreance = creanceRepository.save(creance);
            return creanceMapper.mapTo(savedCreance);
        } else {
            throw new IllegalArgumentException("Creancier does not exist");
        }
    }

    @Override
    public CreanceDto updateCreance(Long id, CreanceDto creanceDto){
        if(creanceRepository.existsById(id)){
            Creance creance = creanceMapper.mapFrom(creanceDto);
            creance.setId(id);
            Creance updatedCreance = creanceRepository.save(creance);
            return creanceMapper.mapTo(updatedCreance);
        }
        return null;
    }
    @Override
    public List<CreanceDto> getCreanceByCreancierId(Long id) {
        return creanceRepository.findByCreancier_Id(id).stream()
                .map(creance -> creanceMapper.mapTo(creance)).collect(Collectors.toList());
    }
    @Override
    public void deleteCreance(Long id){
        creanceRepository.deleteById(id);
    }
}
