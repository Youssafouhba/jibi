package com.ensa.jibi.backend.services;

import com.ensa.jibi.backend.domain.entities.Privilege;
import com.ensa.jibi.backend.domain.entities.Role;
import com.ensa.jibi.backend.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }

    public Role findByName(String roleAdmin) {
        return roleRepository.findByName(roleAdmin);
    }
}
