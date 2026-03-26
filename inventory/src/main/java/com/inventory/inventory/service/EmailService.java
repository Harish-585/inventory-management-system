package com.inventory.inventory.service;

public interface EmailService {

    void sendEmail(String to, String subject, String message);

}