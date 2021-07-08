package com.example.Practica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.Properties;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String USERNAME;
    @Value("${spring.mail.password}")
    private String PASSWORD;


    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String toAddress, String subject,
                         String text) {


            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(USERNAME);
            message.setTo(toAddress);
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);

            System.out.println("Email Done");


    }

}
