package com.ensa.jibi.cmi.repositories;

import com.ensa.jibi.cmi.domain.entities.ComptePaiement;
import com.ensa.jibi.cmi.domain.entities.ConfirmationPaiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ConfirmationPaiementRepository extends JpaRepository<ConfirmationPaiement , Long> {
    List<ConfirmationPaiement> findByCompte_Id(String id);
    //the id is the phone number!!!!!!!!!!!
}
