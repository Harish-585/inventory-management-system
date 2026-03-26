package com.inventory.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestMailController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/test-mail")
    public String sendTestMail() {

        try {

            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo("majjiharish69@gmail.com");   // your gmail
            message.setSubject("Inventory Alert Mail");
            message.setText("This email is from Inventory Project");

            mailSender.send(message);

            return "Email Sent Successfully";

        } catch (Exception e) {

            e.printStackTrace();

            return "Email Failed: " + e.getMessage();
        }
    }
}