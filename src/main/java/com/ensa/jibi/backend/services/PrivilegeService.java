package com.ensa.jibi.backend.services;
import com.ensa.jibi.backend.domain.entities.Privilege;
import com.ensa.jibi.backend.repositories.PrivilegeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService {
    private final PrivilegeRepository privilegeRepository;

    public PrivilegeService(PrivilegeRepository privilegeRepository1) {
        this.privilegeRepository = privilegeRepository1;
    }

    @Transactional
    public Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

}
