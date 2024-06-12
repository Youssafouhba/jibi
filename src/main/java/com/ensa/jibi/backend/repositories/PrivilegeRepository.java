package com.ensa.jibi.backend.repositories;


import com.ensa.jibi.backend.domain.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {
    Privilege findByName(String name);
}
