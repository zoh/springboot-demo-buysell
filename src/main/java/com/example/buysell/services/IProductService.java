package com.example.buysell.services;

import com.example.buysell.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> list(String title);

    void saveProduct(Product product);

    void deleteProduct(Long id);

    Product getProductById(Long id);
}

