package com.ensa.jibi.cmi.services.impl;

import com.ensa.jibi.cmi.domain.dto.creanceDto.FactureDto;
import com.ensa.jibi.cmi.domain.entities.creance.Facture;
import com.ensa.jibi.cmi.mappers.impl.FactureMapper;
import com.ensa.jibi.cmi.repositories.CreancierRepository;
import com.ensa.jibi.cmi.repositories.FactureRepository;
import com.ensa.jibi.cmi.services.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FactureServiceImpl implements FactureService {
    private final FactureRepository factureRepository;
    private final FactureMapper factureMapper;

    private final CreancierRepository creancierRepository;

    @Autowired
    public FactureServiceImpl(FactureRepository factureRepository, FactureMapper factureMapper, CreancierRepository creancierRepository) {
        this.factureRepository = factureRepository;
        this.factureMapper = factureMapper;
        this.creancierRepository = creancierRepository;
    }

    @Override
    public List<FactureDto> getAllFactures() {
        return factureRepository.findAll().stream()
                .map(factureMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public FactureDto getFacture(Long id) {
        return factureMapper.mapTo(factureRepository.findById(id).orElse(null));
    }

    @Override
    public FactureDto createFacture(FactureDto factureDto) {
        Long creancierId = factureDto.getCreancier().getId();

        // Check if the Creancier exists
        if (!creancierRepository.existsById(creancierId)) {
            throw new IllegalArgumentException("Creancier does not exist with ID: " + creancierId);
        }

        Facture facture = factureMapper.mapFrom(factureDto);
        facture.setCreancier(creancierRepository.findById(creancierId).get());
        return factureMapper.mapTo(factureRepository.save(facture));
    }

    @Override
    public FactureDto updateFacture(Long id, FactureDto factureDto) {
        if (factureRepository.existsById(id)) {
            factureDto.setId(id);
            FactureDto updatedFactureDto = factureMapper.mapTo(factureRepository.save(factureMapper.mapFrom(factureDto)));
            return updatedFactureDto;
        } else {
            return null;
        }
    }

    @Override
    public void deleteFacture(Long id) {
        factureRepository.deleteById(id);
    }
}
