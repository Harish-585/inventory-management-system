package com.inventory.inventory.controller;

import com.inventory.inventory.entity.Product;
import com.inventory.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private ProductService productService;

    @GetMapping("/dashboard")
    public String dashboard(Model model){

        List<Product> products = productService.getAllProducts();

        int totalProducts = products.size();

        double totalValue = products.stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();

        long lowStock = products.stream()
                .filter(p -> p.getQuantity() < 5)
                .count();

        model.addAttribute("products", products);
        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("totalValue", totalValue);
        model.addAttribute("lowStock", lowStock);

        return "dashboard";
    }
}