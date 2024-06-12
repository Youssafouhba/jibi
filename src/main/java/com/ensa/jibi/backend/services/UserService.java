package com.ensa.jibi.backend.services;

import com.ensa.jibi.backend.domain.dto.AdminDto;
import com.ensa.jibi.backend.domain.dto.AgentDto;
import com.ensa.jibi.backend.domain.dto.ClientDto;
import com.ensa.jibi.backend.domain.dto.UserDto;
import com.ensa.jibi.backend.domain.entities.Admin;
import com.ensa.jibi.backend.domain.entities.Agent;
import com.ensa.jibi.backend.domain.entities.Client;
import com.ensa.jibi.backend.domain.entities.User;
import com.ensa.jibi.backend.domain.requests.LoginRequest;
import com.ensa.jibi.backend.mappers.UserMapper;
import com.ensa.jibi.backend.repositories.UserRepository;
import com.ensa.jibi.backend.services.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminService adminRepository;

    @Autowired
    private AgentService agentService;

    @Autowired
    private ClientServiceImpl clientService;

//    @Autowired
//    private PasswordEncoder passwordEncoder;


    @Autowired
    private UserMapper userMapper;

    public UserDto createUser(UserDto userDto) {
        User user = userMapper.mapFrom(userDto); // Use mapper to convert UserDto to User
//        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Hash password before saving
        return userMapper.mapTo(userRepository.save(user));
    }



    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::mapTo).collect(Collectors.toList()); // Use mapper to convert User to UserDto
    }

    public User getUserByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }
    public User getUserById(Long id) { return userRepository.findById(id).orElse(null);}

    public User getUserByUsernameAndPassword(LoginRequest loginRequest) {
        return userRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
    }
    public Boolean existsByUsernameAndPassword(LoginRequest loginRequest) {
        return userRepository.
                existsByUsernameAndPassword(loginRequest.getUsername(),
                        loginRequest.getPassword());
    }

    public boolean isAdmin(LoginRequest loginRequest) {
        User user = getUserByUsernameAndPassword(loginRequest);
        return user != null && user instanceof Admin; // Check if user is an Admin instance
    }

    public boolean isAgent(LoginRequest loginRequest) {
        User user = getUserByUsernameAndPassword(loginRequest);
        return user != null && user instanceof Agent; // Check if user is an Agent instance
    }
    public boolean isClient(LoginRequest loginRequest) {
        User user = getUserByUsernameAndPassword(loginRequest);
        return user != null && user instanceof Client; // Check if user is an Client instance
    }

    public boolean isFirstLogin(LoginRequest loginRequest) {
        User user = getUserByUsernameAndPassword(loginRequest);
        return user != null && user.isFirstLogin();
    }



    public AdminDto getAdminByUsernameAndPassword(LoginRequest loginRequest){
        return adminRepository.getUserByUsernameAndPassword(loginRequest);
    }

    public AgentDto getAgentByUsernameAndPassword(LoginRequest loginRequest){
        return agentService.getUserByUsernameAndPassword(loginRequest);
    }

    public ClientDto getClientByUsernameAndPassword(LoginRequest loginRequest){
        return clientService.getClientByUsernameAndPassword(loginRequest);
    }


}
