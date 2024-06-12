package com.ensa.jibi.cmi.repositories;

import com.ensa.jibi.cmi.domain.dto.creanceDto.RechargeDto;
import com.ensa.jibi.cmi.domain.entities.creance.Recharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RechargeRepository extends JpaRepository<Recharge, Long> {
}
