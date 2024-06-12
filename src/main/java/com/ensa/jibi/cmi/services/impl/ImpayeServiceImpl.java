package com.ensa.jibi.cmi.services.impl;

import com.ensa.jibi.cmi.domain.dto.ImpayeDto;
import com.ensa.jibi.cmi.domain.entities.Impaye;
import com.ensa.jibi.cmi.mappers.impl.ImpayeMapper;
import com.ensa.jibi.cmi.repositories.FactureRepository;
import com.ensa.jibi.cmi.repositories.ImpayeRepository;
import com.ensa.jibi.cmi.services.ImpayeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImpayeServiceImpl implements ImpayeService {
    @Autowired
    private ImpayeRepository impayeRepository;
    @Autowired
    private ImpayeMapper impayeMapper;
    @Autowired
    private FactureRepository factureRepository;

    @Override
    public List<ImpayeDto> getImpayesByFacture(Long numFacture) {
        return impayeRepository.
                findByFacture_NumFacture(numFacture).
        stream().map(impaye -> impayeMapper.mapTo(impaye)).collect(Collectors.toList());
    }
    @Override
    public ImpayeDto createImpaye(ImpayeDto impayeDto) {
        // Check if the Facture exists
        Long factureId = impayeDto.getFacture().getId();
        boolean factureExists = factureRepository.existsById(factureId);

        if (factureExists) {
            // Add more validation logic if needed

            Impaye impaye = impayeMapper.mapFrom(impayeDto);
            impaye.setFacture(factureRepository
                    .findById(factureId)
                    .get());
            Impaye savedImpaye = impayeRepository.save(impaye);
            return impayeMapper.mapTo(savedImpaye);
        } else {
            throw new IllegalArgumentException("Facture does not exist");
        }
    }

    @Override
    public ImpayeDto getImpaye(Long id) {
        Optional<Impaye> impaye = impayeRepository.findById(id);
        return impaye.map(impayeMapper::mapTo).orElse(null);
    }

    @Override
    public ImpayeDto updateImpaye(Long id, ImpayeDto impayeDto) {
        if (impayeRepository.existsById(id)) {
            Impaye impaye = impayeMapper.mapFrom(impayeDto);
            impaye.setId(id);
            Impaye updatedImpaye = impayeRepository.save(impaye);
            return impayeMapper.mapTo(updatedImpaye);
        }
        return null;
    }

    @Override
    public void deleteImpaye(Long id) {
        impayeRepository.deleteById(id);
    }

    @Override
    public List<ImpayeDto> getAllImpayes() {
        return impayeRepository.findAll()
                .stream()
                .map(impayeMapper::mapTo)
                .collect(Collectors.toList());
    }
}
