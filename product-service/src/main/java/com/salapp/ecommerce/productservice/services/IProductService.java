package com.salapp.ecommerce.productservice.services;

import com.salapp.ecommerce.api.dto.product.ProductRequest;
import com.salapp.ecommerce.api.dto.product.ProductResponse;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IProductService {

    ProductResponse createProduct(ProductRequest productRequest);

    Optional<ProductResponse> getProductById(Long id);

    void deleteProduct(Long id);
}
