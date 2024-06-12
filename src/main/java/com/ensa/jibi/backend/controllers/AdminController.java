package com.ensa.jibi.backend.controllers;

import com.ensa.jibi.backend.domain.dto.AdminDto;
import com.ensa.jibi.backend.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<AdminDto> createAdmin(@RequestBody AdminDto admin) {
       return  new ResponseEntity<>(adminService.save(admin), HttpStatus.CREATED);
    }
}
