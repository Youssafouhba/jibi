package com.ensa.jibi.config;


import com.ensa.jibi.backend.domain.entities.Admin;
import com.ensa.jibi.backend.domain.entities.Privilege;
import com.ensa.jibi.backend.domain.entities.Role;
import com.ensa.jibi.backend.repositories.AdminRepository;
import com.ensa.jibi.backend.repositories.UserRepository;
import com.ensa.jibi.backend.services.PrivilegeService;
import com.ensa.jibi.backend.services.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = true;

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PrivilegeService privilegeService;

    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    public SetupDataLoader(UserRepository userRepository, RoleService roleService, PrivilegeService privilegeService, AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.privilegeService = privilegeService;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Privilege readPrivilege
                = privilegeService.createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = privilegeService.createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        Privilege editPrivilege
                = privilegeService.createPrivilegeIfNotFound("EDIT_PRIVILEGE");
        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege,editPrivilege);

        roleService.createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        roleService.createRoleIfNotFound("ROLE_AGENT",Arrays.asList(readPrivilege,writePrivilege));
        roleService.createRoleIfNotFound("ROLE_CLIENT", Arrays.asList(readPrivilege));
        roleService.createRoleIfNotFound("ROLE_CLIENT_PRO", Arrays.asList(readPrivilege));
        Role adminRole = roleService.findByName("ROLE_ADMIN");
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRoles(Arrays.asList(adminRole));
        adminRepository.save(admin);
        alreadySetup = true;
    }

}
