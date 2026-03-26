package com.inventory.inventory.service;

import com.inventory.inventory.entity.Product;
import com.inventory.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockAlertService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JavaMailSender mailSender;

    // ✅ Runs every 25 hours
    @Scheduled(fixedRate = 90000000)
    public void checkLowStock() {

        List<Product> products = productRepository.findAll();

        for (Product product : products) {

            // ✅ send only once
            if (product.getQuantity() < 5 && !product.isAlertSent()) {

                try {
                    SimpleMailMessage message = new SimpleMailMessage();

                    message.setTo("majjiharish6@gmail.com");
                    message.setSubject("Low Stock Alert 🚨");
                    message.setText(
                            "Product: " + product.getName() +
                                    "\nStock: " + product.getQuantity()
                    );

                    mailSender.send(message);

                    // ✅ mark as sent
                    product.setAlertSent(true);
                    productRepository.save(product);

                    System.out.println("Alert sent for: " + product.getName());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}