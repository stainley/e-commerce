package com.salapp.ecommerce.productservice.repository;

import com.salapp.ecommerce.productservice.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
@Repository
public interface IProductRepository extends CrudRepository<ProductEntity, Long> {

    @Query(value = """
        SELECT p.* FROM product.public.product p
        WHERE p.expiration_date = :expire
    """, nativeQuery = true)
    List<ProductEntity> getProductsToExpire(@Param("expire") LocalDateTime expire);


}
