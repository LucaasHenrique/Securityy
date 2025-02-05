package com.br.studysecurity.service;

import com.br.studysecurity.entity.Products;
import com.br.studysecurity.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository)   {
        this.productRepository = productRepository;
    }

    public List<Products> getAll(){
        return productRepository.findAll();
    }

    public Products getProduct(UUID id)  {
        return productRepository.findById(id).orElse(new Products());
    }

    public Products saveProduct(Products products) {
        return productRepository.save(products);
    }

    public void delete(Products products){
        productRepository.delete(products);
    }
}
