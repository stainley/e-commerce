package com.salapp.ecommerce.productservice.services;

import com.salapp.ecommerce.api.exception.ProductNotFoundException;
import com.salapp.ecommerce.productservice.entity.ProductEntity;
import com.salapp.ecommerce.productservice.repository.IProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ProductServiceApplicationTests {

    @Mock
    private IProductRepository productRepository;

    @InjectMocks
    private ProductService cut;

    @Test
    void loadContext() {
        System.out.println(productRepository.getClass().getName());
        System.out.println(cut.getClass().getName());
    }

    @Test
    void shouldDeleteProduct_whenProductIsFound() throws ProductNotFoundException {
        Mockito.when(productRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(new ProductEntity()));

        cut.deleteProduct(1L);
        Mockito.verify(productRepository).deleteById(1L);
    }

    @Test
    @ExceptionHandler(ProductNotFoundException.class)
    void shouldThrowAndException_whenProductNotFound() {
        Mockito.when(productRepository.findById(any(Long.class)))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ProductNotFoundException.class, () -> cut.deleteProduct(1L));
    }
}
