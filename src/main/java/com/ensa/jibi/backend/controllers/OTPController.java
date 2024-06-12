package com.ensa.jibi.backend.controllers;

import com.ensa.jibi.backend.domain.dto.sms.OTPTokenDto;
import com.ensa.jibi.backend.domain.entities.sms.OTPToken;
import com.ensa.jibi.backend.domain.entities.sms.SMS;
import com.ensa.jibi.backend.services.OTPService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.angus.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sms")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")

public class OTPController {
    @Autowired
    OTPService OTPService;


    @GetMapping
    public String getSMS(){
        return "Hello World";
    }
    @PostMapping
    public OTPTokenDto postOTP(@RequestBody SMS sendRequest) {
        OTPTokenDto token = new OTPTokenDto();
        token.setToken(OTPService.sendOTP(sendRequest.getDestinationSMSNumber()));
        return token;
    }

    @PostMapping("/verify")
    public boolean verifyOTP(@RequestBody OTPTokenDto otpToken){
//        log.info("Verifying SMS: " + verifyRequest.toString());
        return OTPService.verifyOTP(otpToken);
    }
}
