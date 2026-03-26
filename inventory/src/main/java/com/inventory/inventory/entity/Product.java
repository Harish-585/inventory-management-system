package com.inventory.inventory.entity;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;

    private int quantity;
    private double price;

    private int reorderLevel;

    private boolean alertSent = false;

    // ✅ Getters & Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return  quantity;
    }

    public double getPrice() {
        return  price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public int getReorderLevel(){
        return reorderLevel;
    }
    public void setReorderLevel(int reoerderLevel){
        this.reorderLevel=reorderLevel;
    }

    public boolean isAlertSent() {
        return alertSent;
    }

    public void setAlertSent(boolean alertSent) {
        this.alertSent = alertSent;
    }
}