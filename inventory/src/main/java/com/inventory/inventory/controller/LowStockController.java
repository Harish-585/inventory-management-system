package com.inventory.inventory.controller;

import com.inventory.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LowStockController {

    @Autowired
    private ProductService productService;

    @GetMapping("/low-stock")
    public String lowStock(Model model){

        model.addAttribute("products", productService.getLowStockProducts());

        return "products";
    }
}