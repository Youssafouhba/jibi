package com.ensa.jibi.backend.services;

import com.ensa.jibi.backend.domain.dto.EmailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String emailFrom;

    public void sendEmail(EmailDto emailDto){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setSubject(emailDto.getSubject());
        message.setTo(emailDto.getDestinationEmail());
        message.setText(emailDto.getBody());

        mailSender.send(message);

        log.info("Sent email to: " + emailDto.getDestinationEmail());

    }
}
