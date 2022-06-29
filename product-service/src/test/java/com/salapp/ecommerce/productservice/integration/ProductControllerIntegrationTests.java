package com.salapp.ecommerce.productservice.integration;

import com.salapp.ecommerce.productservice.entity.ProductEntity;
import com.salapp.ecommerce.productservice.repository.IProductRepository;
import com.salapp.ecommerce.productservice.services.IProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.autoconfigure.webservices.server.AutoConfigureMockWebServiceClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/*@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)*/
public class ProductControllerIntegrationTests {

/*
    private static final Long PRODUCT_ID = 1L;


    @Autowired
    private IProductService productService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductRepository productRepository;

    @Test
    void loadContext() {
        Assertions.assertNotNull(restTemplate);
        Assertions.assertNotNull(productService);

        Assertions.assertNotNull(mockMvc);
    }

    @Test
    void shouldDeleteProduct_whenProductIsFount() throws Exception {

        Mockito.when(productRepository.findById(ArgumentMatchers.anyLong()))
                        .thenReturn(Optional.of(new ProductEntity()));

        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8070/api/v1/product/" + PRODUCT_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.apiError.status", Matchers.is("NO_CONTENT")));
                *//*.andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("product " + PRODUCT_ID + " has been deleted!")));*//*

    }*/
}
