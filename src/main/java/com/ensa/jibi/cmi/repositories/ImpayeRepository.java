package com.ensa.jibi.cmi.repositories;

import com.ensa.jibi.cmi.domain.entities.Impaye;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImpayeRepository extends JpaRepository<Impaye,Long> {
    List<Impaye> findByFacture_NumFacture( Long numFacture);
}
