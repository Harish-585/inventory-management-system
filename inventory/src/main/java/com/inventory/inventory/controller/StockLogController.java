package com.inventory.inventory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StockLogController {

    @GetMapping("/stock-logs")
    public String stockLogs(){
        return "products";
    }
}