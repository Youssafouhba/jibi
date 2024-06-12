package com.ensa.jibi.backend.repositories;

import com.ensa.jibi.backend.domain.entities.Admin;
import com.ensa.jibi.backend.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsernameAndPassword(String username, String password);

}
