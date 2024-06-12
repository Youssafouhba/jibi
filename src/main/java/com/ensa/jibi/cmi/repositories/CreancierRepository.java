package com.ensa.jibi.cmi.repositories;

import com.ensa.jibi.cmi.domain.entities.Creancier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreancierRepository extends JpaRepository<Creancier,Long> {
    public Creancier getById(long id);
    public List<Creancier> findAllByCategorie(String categorie);

}
