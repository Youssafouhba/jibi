package com.ensa.jibi.cmi.services.impl;

import com.ensa.jibi.backend.domain.enums.ClientType;
import com.ensa.jibi.backend.repositories.ClientRepository;
import com.ensa.jibi.cmi.domain.dto.ComptePaiementDto;
import com.ensa.jibi.cmi.domain.entities.ComptePaiement;
import com.ensa.jibi.cmi.mappers.impl.ComptePaiementMapperImpl;
import com.ensa.jibi.cmi.repositories.ComptePaiementRepository;
import com.ensa.jibi.cmi.services.ComptePaimentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComptePaiementServiceImpl implements ComptePaimentService {
    private final ComptePaiementRepository comptePaiementRepository;
    private final ComptePaiementMapperImpl compteMapper;
    private final ClientRepository clientRepository;

    @Autowired
    public ComptePaiementServiceImpl(ComptePaiementRepository comptePaiementRepository,
                                     ComptePaiementMapperImpl compteMapper,
                                     ClientRepository clientRepository) {
        this.comptePaiementRepository = comptePaiementRepository;
        this.compteMapper = compteMapper;
        this.clientRepository = clientRepository;
    }

    @Override
    public ComptePaiementDto save(ComptePaiementDto comptePaiementDto) {
        ComptePaiement comptePaiement = compteMapper.mapFrom(comptePaiementDto);
        return compteMapper.mapTo(comptePaiementRepository.save(comptePaiement));
    }

    @Override
    public List<ComptePaiementDto> findAll() {
        return comptePaiementRepository.findAll()
                .stream()
                .map(compteMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public ComptePaiementDto getComptePaiement(String id) {
        return compteMapper.mapTo(comptePaiementRepository.findComptePaiementById(id));
    }

    @Override
    public Optional<ComptePaiementDto> findOne(String id) {
        return comptePaiementRepository.findById(id)
                .map(compte -> {
                    ComptePaiementDto compteDto = compteMapper.mapTo(compte);
                    return Optional.of(compteDto);
                }).orElseThrow(() -> new RuntimeException("ComptePaiement not found!"));
    }

    @Override
    public boolean isExists(String id) {
        return comptePaiementRepository.existsById(id);
    }

    @Override
    public ComptePaiementDto partialUpdate(String id, ComptePaiementDto comptePaiementDto) {
        return comptePaiementRepository.findById(id).map(
                existingCompte -> {
                    Optional.ofNullable(comptePaiementDto.getSolde())
                            .ifPresent(existingCompte::setSolde);
                    return compteMapper.mapTo(
                            comptePaiementRepository.save(existingCompte));
                }).orElseThrow(() -> new RuntimeException("ComptePaiement not found"));
    }

    @Override
    public ComptePaiementDto payer(String id, Long creanceId, Double montant) {
        Optional<ComptePaiement> optionalComptePaiement = comptePaiementRepository.findById(id);
        if (optionalComptePaiement.isPresent()) {
            ComptePaiement comptePaiement = optionalComptePaiement.get();
            // Logic for paying the creance and updating the solde
            comptePaiementRepository.save(comptePaiement);
            return compteMapper.mapTo(comptePaiement);
        } else {
            throw new EntityNotFoundException("ComptePaiement not found with id: " + id);
        }
    }

    @Override
    public void delete(String id) {
        if (comptePaiementRepository.existsById(id)) {
            comptePaiementRepository.deleteById(id);

            clientRepository.deleteByNumTel(id);
        } else {
            throw new EntityNotFoundException("ComptePaiement not found with id: " + id);
        }
    }

    @Override
    public boolean existsById(String id) {
        return comptePaiementRepository.existsById(id);
    }

    @Override
    public ComptePaiementDto rechargeSolde(String numTel, Double montant) {
        Optional<ComptePaiement> optionalComptePaiement = comptePaiementRepository.findById(numTel);
        if (optionalComptePaiement.isPresent()) {
            ComptePaiement comptePaiement = optionalComptePaiement.get();
            ClientType clientType = clientRepository.findByNumTel(numTel).get().getClientType();

            if (clientType != ClientType.Hsab_PRO && comptePaiement.getSolde() + montant > clientType.getAccountLimit()) {
                throw new IllegalArgumentException("Recharge amount exceeds the limit for client type " + clientType);
            }

            comptePaiement.setSolde(comptePaiement.getSolde() + montant);
            comptePaiementRepository.save(comptePaiement);
            return compteMapper.mapTo(comptePaiement);
        } else {
            throw new EntityNotFoundException("ComptePaiement not found with id: " + numTel);
        }
    }

    @Override
    public ComptePaiementDto transferSolde(String fromAccountId, String toAccountId, Double amount) {
        Optional<ComptePaiement> fromAccountOpt = comptePaiementRepository.findById(fromAccountId);
        Optional<ComptePaiement> toAccountOpt = comptePaiementRepository.findById(toAccountId);

        if (fromAccountOpt.isPresent() && toAccountOpt.isPresent()) {
            ComptePaiement fromAccount = fromAccountOpt.get();
            ComptePaiement toAccount = toAccountOpt.get();

            if (fromAccount.getSolde() < amount) {
                throw new IllegalArgumentException("Insufficient funds in the source account.");
            }

            fromAccount.setSolde(fromAccount.getSolde() - amount);
            toAccount.setSolde(toAccount.getSolde() + amount);

            comptePaiementRepository.save(fromAccount);
            comptePaiementRepository.save(toAccount);

            return compteMapper.mapTo(fromAccount);
        } else {
            throw new EntityNotFoundException("One or both accounts not found.");
        }
    }

}
