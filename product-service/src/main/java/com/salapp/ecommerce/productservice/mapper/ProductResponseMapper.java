package com.salapp.ecommerce.productservice.mapper;


import com.salapp.ecommerce.api.dto.product.ProductResponse;
import com.salapp.ecommerce.productservice.entity.ProductEntity;

import java.util.List;

@FunctionalInterface
public interface ProductResponseMapper {

    List<ProductResponse> mapProductEntityToProductResponse(List<ProductEntity> productEntity);

    default ProductResponse mapProductEntityToProductResponse(ProductEntity productEntity) {
        return ProductResponse.builder().
                id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .creationDate(productEntity.getCreationDate())
                .price(productEntity.getPrice())
                .build();
    }

}
