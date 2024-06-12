package com.ensa.jibi.backend.services;

import com.ensa.jibi.backend.domain.dto.sms.OTPTokenDto;
import com.ensa.jibi.backend.domain.entities.sms.OTPToken;
import com.ensa.jibi.backend.repositories.OTPTokenRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
//leave it! it will mainly be used to confirm transactions!
//it is also used to send passwords to users.
@Service
@Slf4j
public class OTPService {

    @Autowired
    private OTPTokenRepository otpRepository;

    @Value("${twilio.accountSid}")
    private String accountSid;


    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.smsNumber}")
    private String outGoingSmsNumber;

    public OTPService() {
        log.info(accountSid);
    }

    @PostConstruct
    public void init() {
        log.info(accountSid);
    }

    public String sendOTP(String smsNumber) {
        Twilio.init(accountSid, authToken);

        String otpToken = RandomStringUtils.randomAlphanumeric(9);

        // Set the expiration time (1 hour)
        long expirationTimeMillis = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1);

        OTPToken otp = new OTPToken();
        otp.setToken(otpToken);
        otp.setExpirationTime(expirationTimeMillis);
        otpRepository.save(otp);


      String messageWithOTP = "Your OTP token is: " + otpToken + ". This token will expire in 1 hour.";

        Message message = Message.creator(
                new PhoneNumber(smsNumber),
                new PhoneNumber(outGoingSmsNumber),
                messageWithOTP
        ).create();

        return otpToken;
    }

    public String sendOTPNoPhone(String smsNumber) {
        Twilio.init(accountSid, authToken);

        String otpToken = RandomStringUtils.randomAlphanumeric(9);

        // Set the expiration time (1 hour)
        long expirationTimeMillis = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1);

        OTPToken otp = new OTPToken();
        otp.setToken(otpToken);
        otp.setExpirationTime(expirationTimeMillis);
        otpRepository.save(otp);


        String messageWithOTP = "Your OTP token is: " + otpToken + ". This token will expire in 1 hour.";



        return otpToken;
    }


    public boolean verifyOTP(OTPTokenDto otpToken) {
        OTPToken otp = otpRepository.findByToken(otpToken.getToken());
        log.info("token found! " + otp);

        return otp != null && otp.getExpirationTime() > System.currentTimeMillis();
    }


}

