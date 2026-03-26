package com.inventory.inventory.controller;

import com.inventory.inventory.entity.Product;
import com.inventory.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    // Show all products
    @GetMapping("/products")
    public String viewProducts(Model model) {

        model.addAttribute("products", productService.getAllProducts());

        return "products";
    }

    // Show add product form
    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {

        model.addAttribute("product", new Product());

        return "add-product";
    }

    // Save product
    @PostMapping("/add-product")
    public String saveProduct(@ModelAttribute Product product) {

        productService.saveProduct(product);

        return "redirect:/products";
    }

    // Increase stock
    @GetMapping("/increase/{id}")
    public String increaseStock(@PathVariable Long id) {
        productService.increaseStock(id);
        return "redirect:/products";
    }


    // Decrease stock
    @GetMapping("/decrease/{id}")
    public String decreaseStock(@PathVariable Long id) {
        productService.decreaseStock(id);
        return "redirect:/products";
    }

}