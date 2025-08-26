package com.example.store.service;

import com.example.store.dto.ProductDTO;
import com.example.store.mapper.ProductMapper;
import com.example.store.model.Product;
import com.example.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    
    public Product saveWithoutDTO(Product product) {
        return productRepository.save(product);
    }

    public ProductDTO saveProduct(Product product) {
        productRepository.save(product);
        return productMapper.toDTO(product);
    }
    
}
