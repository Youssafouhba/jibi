package com.ensa.jibi.backend.repositories;

import com.ensa.jibi.backend.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
    boolean existsByUsernameAndPassword(String username, String password);
}
