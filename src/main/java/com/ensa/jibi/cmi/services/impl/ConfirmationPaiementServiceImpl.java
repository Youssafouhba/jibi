package com.ensa.jibi.cmi.services.impl;

import com.ensa.jibi.cmi.domain.dto.ConfirmationPaiementDto;
import com.ensa.jibi.cmi.domain.dto.ConfirmationRequest;
import com.ensa.jibi.cmi.domain.entities.ConfirmationPaiement;
import com.ensa.jibi.cmi.domain.entities.ComptePaiement;
import com.ensa.jibi.cmi.mappers.impl.ConfirmationPaiementMapperImpl;
import com.ensa.jibi.cmi.repositories.ConfirmationPaiementRepository;
import com.ensa.jibi.cmi.repositories.CreanceRepository;
import com.ensa.jibi.cmi.repositories.ComptePaiementRepository;
import com.ensa.jibi.cmi.services.ConfirmationPaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConfirmationPaiementServiceImpl implements ConfirmationPaiementService {
    @Autowired
    private ConfirmationPaiementRepository confirmationPaiementRepository;
    @Autowired
    private ConfirmationPaiementMapperImpl confirmationPaiementMapper;
    @Autowired
    private CreanceRepository creanceRepository;
    @Autowired
    private ComptePaiementRepository comptePaiementRepository;

    @Override
    public List<ConfirmationPaiementDto> getAllConfirmationsPaiement(){
        return confirmationPaiementRepository.findAll().stream()
                .map(confirmationPaiement -> confirmationPaiementMapper.mapTo(confirmationPaiement)).collect(Collectors.toList());
    }

    @Override
    public ConfirmationPaiementDto getConfirmationPaiement(Long id){
        Optional<ConfirmationPaiement> confirmationPaiement = confirmationPaiementRepository.findById(id);
        return confirmationPaiement.map(confirmationPaiementMapper::mapTo).orElse(null);
    }

    @Override
    public ConfirmationPaiementDto createConfirmationPaiement(ConfirmationRequest confirmationRequest){
        Long creanceId = confirmationRequest.getCreanceId();
        String comptePaiementId = confirmationRequest.getCompteId();
        Double montant = confirmationRequest.getMontant();

        boolean creanceExists = creanceRepository.existsById(creanceId);
        boolean comptePaiementExists = comptePaiementRepository.existsById(comptePaiementId);

        if (creanceExists && comptePaiementExists) {
            ComptePaiement comptePaiement = comptePaiementRepository.findById(comptePaiementId).get();

            if (comptePaiement.getSolde() < montant) {
                throw new IllegalArgumentException("Solde insuffisant");
            }

            ConfirmationPaiement confirmationPaiement = new ConfirmationPaiement();
            confirmationPaiement.setCreance(creanceRepository.findById(creanceId).get());
            confirmationPaiement.setCompte(comptePaiement);
            confirmationPaiement.setMontant(montant);
            confirmationPaiement.setDate(confirmationRequest.getDate());

            ConfirmationPaiement savedConfirmationPaiement = confirmationPaiementRepository.save(confirmationPaiement);

            // Update the solde of the comptePaiement
            comptePaiement.setSolde(comptePaiement.getSolde() - montant);
            comptePaiementRepository.save(comptePaiement);

            return confirmationPaiementMapper.mapTo(savedConfirmationPaiement);
        } else {
            throw new IllegalArgumentException("Creance or ComptePaiement does not exist");
        }
    }

    @Override
    public void deleteConfirmationPaiement(Long id){
        confirmationPaiementRepository.deleteById(id);
    }

    @Override
    public List<ConfirmationPaiementDto> getConfirmationsByComptePaiementId(String comptePaiementId) {
        return confirmationPaiementRepository.findByCompte_Id(comptePaiementId).stream()
                .map(confirmationPaiement -> confirmationPaiementMapper.mapTo(confirmationPaiement)).collect(Collectors.toList());
    }
}
