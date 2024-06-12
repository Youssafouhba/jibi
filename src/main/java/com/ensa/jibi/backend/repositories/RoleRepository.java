package com.ensa.jibi.backend.repositories;


import com.ensa.jibi.backend.domain.entities.Role;
import com.ensa.jibi.backend.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String roleAdmin);

    Role findRoleByUsersIn(Collection<User> users);

}

