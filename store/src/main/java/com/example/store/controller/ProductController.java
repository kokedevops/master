package com.example.store.controller;

import com.example.store.dto.ProductDTO;
import com.example.store.model.Product;
import com.example.store.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    
    @PostMapping("/withoutDTO")
    public ResponseEntity<Product> createNewProduct(@RequestBody Product product) {
        Product newProduct = productService.saveWithoutDTO(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {
        ProductDTO newProductDto = productService.saveProduct(product);
        return new ResponseEntity<>(newProductDto, HttpStatus.CREATED);
    }

}
