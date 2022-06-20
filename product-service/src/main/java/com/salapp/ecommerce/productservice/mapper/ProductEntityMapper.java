package com.salapp.ecommerce.productservice.mapper;

import com.salapp.ecommerce.api.dto.product.ProductRequest;
import com.salapp.ecommerce.productservice.entity.ProductEntity;

@FunctionalInterface
public interface ProductEntityMapper {

    ProductEntity mapProductRequestToProductEntity(ProductRequest productRequest);

}
