package com.salapp.ecommerce.productservice.repository;

import com.salapp.ecommerce.productservice.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends CrudRepository<ProductEntity, Long> {
    
}
