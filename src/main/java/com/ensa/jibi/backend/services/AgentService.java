package com.ensa.jibi.backend.services;

import com.ensa.jibi.backend.domain.dto.AgentDto;
import com.ensa.jibi.backend.domain.entities.Agent;
import com.ensa.jibi.backend.domain.entities.Role;
import com.ensa.jibi.backend.domain.requests.LoginRequest;
import com.ensa.jibi.backend.exceptions.PhoneNumberException;
import com.ensa.jibi.backend.mappers.AgentMapper;
import com.ensa.jibi.backend.repositories.AgentRepository;
import com.ensa.jibi.backend.repositories.ClientRepository;
import com.ensa.jibi.backend.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

import static java.util.Arrays.*;

@Service@AllArgsConstructor
public class AgentService {
    private final AgentRepository agentRepository;
    private final AgentMapper agentMapper;
    private final OTPService otpService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final ClientRepository clientRepository;


    public ResponseEntity<?> save(AgentDto agentDto) {
        String agentPhoneNumber = agentDto.getNumTel();
        if (agentRepository.existsByNumTel(agentPhoneNumber) || clientRepository.existsByNumTel(agentPhoneNumber)) {
            return ResponseEntity.badRequest().body("{\"message\":\"Phone number " + agentPhoneNumber + " already exists.\"}");
        }
        Role agentRole = roleService.findByName("ROLE_AGENT");
        agentDto.setPassword(passwordEncoder.encode(otpService.sendOTPNoPhone(agentDto.getNumTel())));
        Agent agent = agentMapper.mapFrom(agentDto);
        agent.setRoles(asList(agentRole));
        return new ResponseEntity<>(agentMapper.mapTo(agentRepository.save(agent)), HttpStatus.CREATED);
    }



    public AgentDto getUserByUsernameAndPassword(LoginRequest loginRequest){
    Agent agent = agentRepository.
            findByUsernameAndPassword(loginRequest.getUsername(),
                    loginRequest.getPassword());
        return agentMapper.mapTo(agent);
    }

    public boolean existsByNumTel(String numTel) {
        return agentRepository.existsByNumTel(numTel);
    }
}
