package com.salapp.ecommerce.productservice.controller;

import com.salapp.ecommerce.api.dto.product.ProductRequest;
import com.salapp.ecommerce.api.dto.product.ProductResponse;
import com.salapp.ecommerce.api.rest.product.ProductRestController;
import com.salapp.ecommerce.api.util.ServiceUtil;
import com.salapp.ecommerce.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController implements ProductRestController {

    private final ProductService productService;

    private final ServiceUtil serviceUtil;

    @Override
    public ResponseEntity<ProductResponse> createProduct(ProductRequest productRequest) {
        log.info("Creating a product: {} with service address: {}", productRequest, serviceUtil.getServiceAddress());
        ProductResponse product = this.productService.createProduct(productRequest);

        log.info(serviceUtil.getServiceAddress());
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductResponse> getProductById(Long id) {
        log.info("Trying to find a product with id: {}", id);
        Optional<ProductResponse> result = this.productService.getProductById(id);

        return result.map(productResponse -> new ResponseEntity<>(productResponse, HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<String> deleteProduct(Long id) {
        this.productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
