package com.ensa.jibi.cmi.repositories;

import com.ensa.jibi.cmi.domain.entities.creance.Creance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreanceRepository extends JpaRepository<Creance,Long> {
    List<Creance> findByCreancier_Id(Long creancierId);
}
