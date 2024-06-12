package com.ensa.jibi.cmi.services;

import com.ensa.jibi.cmi.domain.dto.creanceDto.FactureDto;
import java.util.List;

public interface FactureService {
    List<FactureDto> getAllFactures();
    FactureDto getFacture(Long id);
    FactureDto createFacture(FactureDto factureDto);
    FactureDto updateFacture(Long id, FactureDto factureDto);
    void deleteFacture(Long id);
}
