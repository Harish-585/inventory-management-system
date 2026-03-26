package com.inventory.inventory.controller;

import com.inventory.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/reports")
    public String viewReports(Model model){

        model.addAttribute("products", repository.findAll());

        return "report";
    }

}