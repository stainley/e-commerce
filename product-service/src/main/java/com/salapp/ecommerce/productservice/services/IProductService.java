package com.salapp.ecommerce.productservice.services;

import com.salapp.ecommerce.api.dto.product.ProductRequest;
import com.salapp.ecommerce.api.dto.product.ProductResponse;
import com.salapp.ecommerce.api.exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IProductService {

    ProductResponse createProduct(ProductRequest productRequest);

    Optional<ProductResponse> getProductById(Long id) throws ProductNotFoundException;

    void deleteProduct(Long id);

    List<ProductResponse> getProductToExpire(LocalDateTime expiration);
}
