package com.micropos.products.repository;


import com.micropos.products.model.Product;

import java.util.List;

public interface ProductRepository {

    public List<Product> allProducts() throws Exception;

    public Product findProduct(String productId) throws Exception;

    public List<Product> searchProducts(String name) throws Exception;
}