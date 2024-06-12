package com.ensa.jibi.backend.controllers;

import com.ensa.jibi.backend.domain.dto.AdminDto;
import com.ensa.jibi.backend.domain.dto.AgentDto;
import com.ensa.jibi.backend.domain.dto.ClientDto;
import com.ensa.jibi.backend.domain.dto.UserDto;
import com.ensa.jibi.backend.domain.entities.Admin;
import com.ensa.jibi.backend.domain.entities.Agent;
import com.ensa.jibi.backend.domain.entities.User;
import com.ensa.jibi.backend.domain.requests.LoginRequest;
import com.ensa.jibi.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser( @RequestBody UserDto userDto) {
        UserDto createdUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = userService.getAllUsers();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        User userDtos = userService.getUserById(id);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @PostMapping("/log")
    public ResponseEntity<Object> loginUser(@RequestBody LoginRequest loginRequest) {
        if (userService.isAdmin(loginRequest)) {
            AdminDto admin = userService.getAdminByUsernameAndPassword(loginRequest);
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } else if (userService.isAgent(loginRequest)) {
            AgentDto agent = userService.getAgentByUsernameAndPassword(loginRequest);
            return new ResponseEntity<>(agent, HttpStatus.OK);
        } else if (userService.isClient(loginRequest)) {
            ClientDto client = userService.getClientByUsernameAndPassword(loginRequest);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User Not Found", HttpStatus.UNAUTHORIZED);
        }
    }



    // You can add other API endpoints for specific user functionalities
}
