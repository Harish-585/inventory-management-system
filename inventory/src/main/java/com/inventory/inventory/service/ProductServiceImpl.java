package com.inventory.inventory.service;

import com.inventory.inventory.entity.Product;
import com.inventory.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EmailService emailService;


    // Save Product
    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    // Get All Products
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Delete Product
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getLowStockProducts() {

        return productRepository.findAll()
                .stream()
                .filter(p -> p.getQuantity() < 10)
                .toList();
    }

    @Override
    public void increaseStock(Long id) {

        Product product = productRepository.findById(id).orElse(null);

        if (product != null) {
            product.setQuantity(product.getQuantity() + 1);
            productRepository.save(product);
        }
    }

    @Override
    public void decreaseStock(Long id) {

        Product product = productRepository.findById(id).orElse(null);

        if (product != null && product.getQuantity() > 0) {

            product.setQuantity(product.getQuantity() - 1);

            productRepository.save(product);

            // LOW STOCK CHECK
            if (product.getQuantity() <= product.getReorderLevel()) {

                emailService.sendEmail(
                        "majjiharish6@mail.com",
                        "Low Stock Alert",
                        "Product " + product.getName() +
                                " stock is low. Remaining quantity: " +
                                product.getQuantity()
                );
            }
        }
    }

}