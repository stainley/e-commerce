package com.salapp.ecommerce.productservice.services;

import com.salapp.ecommerce.api.dto.product.ProductRequest;
import com.salapp.ecommerce.api.dto.product.ProductResponse;
import com.salapp.ecommerce.api.exception.ProductNotFoundException;
import com.salapp.ecommerce.productservice.entity.ProductEntity;
import com.salapp.ecommerce.productservice.mapper.ProductEntityMapper;
import com.salapp.ecommerce.productservice.mapper.ProductResponseMapper;
import com.salapp.ecommerce.productservice.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
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

        ProductEntity productCreated = this.productRepository.save(product);
        log.info("Product created: {}", productCreated);
        return productResponseMapper.mapProductEntityToProductResponse(productCreated);
    }

    @Override
    public Optional<ProductResponse> getProductById(Long id) throws ProductNotFoundException {
        log.info("Product with ID: {}", id);
        Optional<ProductEntity> result = this.productRepository.findById(id);
        if (result.isEmpty()) {
            throw new ProductNotFoundException("Product Id: " + id + " not Found");
        }
        return result.map(productResponseMapper::mapProductEntityToProductResponse);
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<ProductEntity> productToDelete = this.productRepository.findById(id);

        productToDelete.ifPresent(product -> this.productRepository.deleteById(id));
    }

    @Transactional
    @Override
    public List<ProductResponse> getProductToExpire(LocalDateTime expiration) {
        List<ProductEntity> productsToExpire = this.productRepository.getProductsToExpire(expiration);

        if (productsToExpire.size() > 0) {
            return productResponseMapper.mapProductEntityToProductResponse(productsToExpire);
        }

        return List.of(ProductResponse.builder().build());
    }

    private final ProductEntityMapper productEntityMapper = (request) -> {
        ProductEntity product = new ProductEntity();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCreationDate(new Date());
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
