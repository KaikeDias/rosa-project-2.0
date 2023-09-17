package com.example.rosa.app.services;

import com.example.rosa.app.DTOs.ProductDTO;
import com.example.rosa.app.entities.Product;
import com.example.rosa.app.enums.ProductStatus;
import com.example.rosa.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findALl() {
        return productRepository.findAll();
    }

    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.name());
        product.setStatus(ProductStatus.AVALIABLE);
        product.setDestination(productDTO.destination());
        product.setProfitabilityRate(productDTO.profitabilityRate());
        product.setMinimalMonths(productDTO.minimalMonths());
        product.setAdministrationTax(productDTO.administrationTax());

        productRepository.save(product);

        return product;
    }

    public Product findOneProduct(UUID id) {
        var product = productRepository.findById(id);

        if(product.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado");
        }else {
            return product.get();
        }
    }

    public Product updateProductStatus(UUID id) {
        var product = findOneProduct(id);

        if(product.getStatus() == ProductStatus.AVALIABLE) {
            product.setStatus(ProductStatus.UNAVALIABLE);
        } else {
            product.setStatus(ProductStatus.AVALIABLE);
        }

        productRepository.save(product);

        return product;
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
