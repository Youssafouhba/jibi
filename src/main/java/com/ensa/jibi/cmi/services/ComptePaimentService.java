package com.ensa.jibi.cmi.services;

import com.ensa.jibi.cmi.domain.dto.ComptePaiementDto;

import java.util.List;
import java.util.Optional;

public interface ComptePaimentService {
    ComptePaiementDto save(ComptePaiementDto comptePaiementDto);
    List<ComptePaiementDto> findAll();
    ComptePaiementDto getComptePaiement(String id);
    Optional<ComptePaiementDto> findOne(String id);
    boolean isExists(String id);
    ComptePaiementDto partialUpdate(String id, ComptePaiementDto comptePaiementDto);
    ComptePaiementDto payer(String id, Long creanceId, Double montant);
    void delete(String id);
    boolean existsById(String id);
    ComptePaiementDto rechargeSolde(String numTel, Double montant);
    // Add a method to transfer solde
    public ComptePaiementDto transferSolde(String fromAccountId, String toAccountId, Double amount);

}
