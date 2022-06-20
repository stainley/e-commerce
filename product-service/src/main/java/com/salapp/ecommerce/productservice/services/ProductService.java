package com.salapp.ecommerce.productservice.services;

import com.salapp.ecommerce.api.dto.product.ProductRequest;
import com.salapp.ecommerce.api.dto.product.ProductResponse;
import com.salapp.ecommerce.productservice.entity.ProductEntity;
import com.salapp.ecommerce.productservice.mapper.ProductEntityMapper;
import com.salapp.ecommerce.productservice.mapper.ProductResponseMapper;
import com.salapp.ecommerce.productservice.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        ProductEntity product = productEntityMapper.mapProductRequestToProductEntity(productRequest);
        product.setCreationDate(new Date());

        ProductEntity productCreated = this.productRepository.save(product);
        log.info("Product created: {}", productCreated);
        return productResponseMapper.mapProductEntityToProductResponse(productCreated);
    }

    @Override
    public Optional<ProductResponse> getProductById(Long id) {
        log.info("Product with ID: {}", id);
        Optional<ProductEntity> result = this.productRepository.findById(id);

        return result.map(productResponseMapper::mapProductEntityToProductResponse);
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<ProductEntity> productToDelete = this.productRepository.findById(id);

        productToDelete.ifPresent(product -> this.productRepository.deleteById(id));
    }

    private final ProductEntityMapper productEntityMapper = (request) -> {
        ProductEntity product = new ProductEntity();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        return product;
    };

    private final ProductResponseMapper productResponseMapper = (entity) ->
            entity.stream().map(product -> ProductResponse.builder()
                            .id(product.getId())
                            .name(product.getName())
                            .description(product.getDescription())
                            .price(product.getPrice())
                            .creationDate(product.getCreationDate())
                            .build())
                    .collect(Collectors.toList());
}
