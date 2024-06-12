package com.ensa.jibi.backend.services;

import com.ensa.jibi.backend.domain.dto.AdminDto;
import com.ensa.jibi.backend.domain.dto.AgentDto;
import com.ensa.jibi.backend.domain.entities.Admin;
import com.ensa.jibi.backend.domain.entities.Agent;
import com.ensa.jibi.backend.domain.requests.LoginRequest;
import com.ensa.jibi.backend.mappers.AdminMapper;
import com.ensa.jibi.backend.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private AdminRepository adminRepository;

    private AdminMapper adminMapper;

    @Autowired
    public AdminService(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }
    public AdminDto save(AdminDto adminDto){

        Admin admin = adminRepository.save(adminMapper.mapFrom(adminDto));
        return adminMapper.mapTo(admin);
    }

    public AdminDto getUserByUsernameAndPassword(LoginRequest loginRequest){
        Admin agent = adminRepository.
                findByUsernameAndPassword(loginRequest.getUsername(),
                        loginRequest.getPassword());
        return adminMapper.mapTo(agent);
    }


}
