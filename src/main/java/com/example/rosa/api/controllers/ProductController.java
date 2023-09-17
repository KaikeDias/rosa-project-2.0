package com.example.rosa.api.controllers;

import com.example.rosa.app.DTOs.ProductDTO;
import com.example.rosa.app.entities.Product;
import com.example.rosa.app.services.ProductService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        try {
            var product = productService.createProduct(productDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }

    @GetMapping("/products")
    public ResponseEntity<Object> getAllProducts() {
        try {
            var products = productService.findALl();
            System.out.println(products);
            return ResponseEntity.ok(products);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable UUID id) {
        try {
            var product = productService.findOneProduct(id);
            return ResponseEntity.ok(product);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }

    @PatchMapping("products/{id}")
    public ResponseEntity<Object> changeProductStatus(@PathVariable UUID id) {
        try {
            var product = productService.updateProductStatus(id);
            return ResponseEntity.ok(product);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable UUID id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product deleted succesfully");
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }
}
