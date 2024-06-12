package com.ensa.jibi.cmi.repositories;

import com.ensa.jibi.cmi.domain.entities.creance.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
}
