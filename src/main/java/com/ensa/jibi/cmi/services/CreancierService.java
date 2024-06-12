package com.ensa.jibi.cmi.services;

import com.ensa.jibi.cmi.domain.dto.CreancierDto;
import com.ensa.jibi.cmi.domain.entities.Creancier;

import java.util.List;

public interface CreancierService {
    Long countCreanciers();
    public List<CreancierDto> getAllCreanciers();
    //public CreancierDto getCreancier(Long id);
    public CreancierDto save(CreancierDto creancierDto);
    public CreancierDto updateCreancier(Long id, CreancierDto creancierDto);
    public void deleteCreancier(Long id);
    public List<Creancier> getCreanciers();
    public CreancierDto getCreancier(Long id);
    public List<Creancier> getCreanciersByCategorie(String categorie);

}
