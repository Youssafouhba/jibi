package com.ensa.jibi.cmi.repositories;

import com.ensa.jibi.cmi.domain.entities.Impaye;
import com.ensa.jibi.cmi.domain.entities.creance.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {

}
