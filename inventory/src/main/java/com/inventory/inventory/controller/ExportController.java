package com.inventory.inventory.controller;

import com.inventory.inventory.entity.Product;
import com.inventory.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class ExportController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/export/csv")
    public void exportCSV(HttpServletResponse response) {
        try {
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=products.csv");

            PrintWriter writer = response.getWriter();

            // CSV Header
            writer.println("ID,Name,Category,Quantity,Price");

            List<Product> products = productRepository.findAll();

            for (Product p : products) {
                writer.println(
                        p.getId() + "," +
                                p.getName() + "," +
                                p.getCategory() + "," +
                                p.getQuantity() + "," +
                                p.getPrice()
                );
            }

            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}