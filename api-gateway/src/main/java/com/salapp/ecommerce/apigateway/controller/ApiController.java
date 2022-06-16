package com.salapp.ecommerce.apigateway.controller;

import com.salapp.ecommerce.api.dto.product.ProductResponse;
import com.salapp.ecommerce.api.dto.product.ProductRequest;
import com.salapp.ecommerce.api.rest.product.ProductRestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController implements ProductRestController {


    @Override
    public ResponseEntity<ProductResponse> createProduct(ProductRequest productRequest) {
        return null;
    }

    @Override
    public ResponseEntity<ProductResponse> getProductById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteProduct(Long id) {
        return null;
    }
}
