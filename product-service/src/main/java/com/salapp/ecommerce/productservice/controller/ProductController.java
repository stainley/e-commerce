package com.salapp.ecommerce.productservice.controller;

import com.salapp.ecommerce.api.dto.product.ProductRequest;
import com.salapp.ecommerce.api.dto.product.ProductResponse;
import com.salapp.ecommerce.api.exception.ProductNotFoundException;
import com.salapp.ecommerce.api.rest.product.ProductRestController;
import com.salapp.ecommerce.api.util.ServiceUtil;
import com.salapp.ecommerce.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
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
    public ResponseEntity<ProductResponse> getProductById(Long id) throws ProductNotFoundException {
        log.info("Trying to find a product with id: {}", id);

        Optional<ProductResponse> result = this.productService.getProductById(id);

        return result.map(productResponse -> ResponseEntity.status(HttpStatus.OK).body(productResponse)).orElseThrow();
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getProductsToExpire(Date expirationDate) {
        return null;
    }

    @Override
    public ResponseEntity<ProductResponse> updateProduct(ProductRequest productRequest, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteProduct(Long id) {
        try {
            this.productService.deleteProduct(id);
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(deletedMessage(id), HttpStatus.OK);
    }

    private String deletedMessage(Long id) {
        return """
                {
                    "message" : "product %s has been deleted!"
                }
                """.formatted(id);
    }
}
