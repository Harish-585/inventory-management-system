package com.inventory.inventory.service;

import com.inventory.inventory.entity.Product;
import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    void saveProduct(Product product);

    void deleteProduct(Long id);
    void increaseStock(Long id);
    void decreaseStock(Long id);
    List<Product> getLowStockProducts();



}