package com.micropos.products.service;

import com.micropos.products.model.Product;
import com.micropos.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(@Autowired ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> products(){
        try {
            return productRepository.allProducts();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product getProduct(String id){
        try {
            return productRepository.findProduct(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product randomProduct() {
        return null;
    }

    @Override
    public List<Product> searchProducts(String name) {
        try {
            return productRepository.searchProducts(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
