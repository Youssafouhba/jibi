package com.ensa.jibi.cmi.services;

import com.ensa.jibi.cmi.domain.dto.ImpayeDto;
import com.ensa.jibi.cmi.domain.entities.Impaye;

import java.util.List;

public interface ImpayeService {
    public List<ImpayeDto> getImpayesByFacture(Long numFacture);
    ImpayeDto createImpaye(ImpayeDto impayeDto);
    ImpayeDto getImpaye(Long id);
    ImpayeDto updateImpaye(Long id, ImpayeDto impayeDto);
    void deleteImpaye(Long id);
    List<ImpayeDto> getAllImpayes();
}
