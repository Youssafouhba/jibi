package com.ensa.jibi.cmi.services.impl;

import com.ensa.jibi.cmi.domain.dto.CreancierDto;
import com.ensa.jibi.cmi.domain.entities.Creancier;
import com.ensa.jibi.cmi.mappers.impl.CreancierMapperImpl;
import com.ensa.jibi.cmi.repositories.CreancierRepository;
import com.ensa.jibi.cmi.services.CreancierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreancierServiceImpl implements CreancierService {

    @Autowired
    private CreancierRepository creancierRepository;

    @Autowired
    private CreancierMapperImpl creancierMapper;

    @Override
    public List<CreancierDto> getAllCreanciers(){
        return creancierRepository.findAll().stream()
                .map(creancier -> creancierMapper.mapTo(creancier))
                .collect(Collectors.toList());
    }

    @Override
    public Long countCreanciers(){
        return creancierRepository.count();
    }

    @Override
    public List<Creancier> getCreanciers(){
        return creancierRepository.findAll();
    }

    @Override
    public CreancierDto getCreancier(Long id){
        Optional<Creancier> creancier = creancierRepository.findById(id);
        return creancier.map(creancierMapper::mapTo).orElse(null);
    }

    @Override
    public CreancierDto save(CreancierDto creancierDto){
        Creancier creancier = creancierMapper.mapFrom(creancierDto);
        Creancier savedCreancier = creancierRepository.save(creancier);
        return creancierMapper.mapTo(savedCreancier);
    }

    @Override
    public CreancierDto updateCreancier(Long id, CreancierDto creancierDto) {
        if (creancierRepository.existsById(id)) {
            Creancier creancier = creancierMapper.mapFrom(creancierDto);
            creancier.setId(id);
            Creancier updatedCreancier = creancierRepository.save(creancier);
            return creancierMapper.mapTo(updatedCreancier);
        }
        return null;
    }

    @Override
    public List<Creancier> getCreanciersByCategorie(String categorie){ return creancierRepository.findAllByCategorie(categorie);}

    @Override
    public void deleteCreancier(Long id) {
        creancierRepository.deleteById(id);
    }
}
