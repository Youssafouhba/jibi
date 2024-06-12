package com.ensa.jibi.cmi.services;

import com.ensa.jibi.cmi.domain.dto.ConfirmationPaiementDto;
import com.ensa.jibi.cmi.domain.dto.ConfirmationRequest;

import java.util.List;

public interface ConfirmationPaiementService {
        List<ConfirmationPaiementDto> getAllConfirmationsPaiement();
        ConfirmationPaiementDto getConfirmationPaiement(Long id);
        ConfirmationPaiementDto createConfirmationPaiement(ConfirmationRequest confirmationPaiementDto);
        void deleteConfirmationPaiement(Long id);
        List<ConfirmationPaiementDto> getConfirmationsByComptePaiementId(String comptePaiementId);
}
