package com.niakhtu.backend.niakhtu.services;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationService {


    private JavaMailSender mailSender;

    public EmailVerificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendVerificationCodeByEmail(String toEmail, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Code de vérification");
        message.setText("Votre code de vérification est : " + verificationCode);
        mailSender.send(message);
    }
}
