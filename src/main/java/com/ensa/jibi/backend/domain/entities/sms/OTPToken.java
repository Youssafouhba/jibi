package com.ensa.jibi.backend.domain.entities.sms;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

//TODO:: add the expiration time for the otp token
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OTPToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private long expirationTime;

}