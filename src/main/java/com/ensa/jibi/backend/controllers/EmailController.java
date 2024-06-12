package com.ensa.jibi.backend.controllers;

import com.ensa.jibi.backend.domain.dto.EmailDto;
import com.ensa.jibi.backend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "http://localhost:4200")

public class EmailController {
    @Autowired
    EmailService emailService;

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody EmailDto emailDto){
        emailService.sendEmail(emailDto);
        return ResponseEntity.ok("Email sent successfully");
    }
}
