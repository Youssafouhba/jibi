package com.ensa.jibi.backend.repositories;

import com.ensa.jibi.backend.domain.entities.sms.OTPToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPTokenRepository extends JpaRepository<OTPToken, Long> {
    OTPToken findByToken(String token);
}
