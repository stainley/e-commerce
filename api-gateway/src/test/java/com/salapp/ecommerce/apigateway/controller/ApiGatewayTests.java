package com.salapp.ecommerce.apigateway.controller;


import com.salapp.ecommerce.api.dto.product.ProductResponse;
import com.salapp.ecommerce.api.exception.ProductNotFoundException;
import com.salapp.ecommerce.api.util.ServiceUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@WebMvcTest
public class ApiGatewayTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceUtil serviceUtil;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ApiController apiController;


    private MockRestServiceServer mockRestServiceServer;

    @BeforeEach
    public void loadContext() {
        Assertions.assertNotNull(mockMvc);
        Assertions.assertNotNull(serviceUtil);
    }

    @BeforeEach
    public void init() {
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    @ExceptionHandler(ProductNotFoundException.class)
    public void givenNotFound_whenGetProduct_thenProductNotFoundException() throws Exception {

        Mockito.when(restTemplate.getForEntity(ArgumentMatchers.eq("/api/v1/product/1"), ArgumentMatchers.any()))
                .thenReturn(new ResponseEntity<>(ProductResponse.builder()
                        .creationDate(new Date())
                        .id(1L)
                        .name("iPad")
                        .description("M1")
                        .build(), HttpStatus.OK));


                /*.thenReturn(new ResponseEntity<>(ProductResponse.builder()
                        .creationDate(new Date())
                        .id(1L)
                        .name("iPad")
                        .description("M1")
                        .build(), HttpStatus.OK));*/


        Assertions.assertThrows(ProductNotFoundException.class, () -> apiController.getProductById(1L));
    }

    @Test
    public void givenProduct_whenGetProduct_thenReturnProduct() throws Exception {



        Mockito.when(restTemplate.getForEntity(ArgumentMatchers.eq("http://localhost:8070/api/v1/product/1"), ArgumentMatchers.any()))
                .thenReturn(new ResponseEntity<>(ProductResponse.builder()
                        .creationDate(new Date())
                        .id(1L)
                        .name("iPad")
                        .description("M1")
                        .build(), HttpStatus.OK));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/1")
                .contentType(MediaType.APPLICATION_JSON));

        ResponseEntity<ProductResponse> productById = this.apiController.getProductById(1L);
        Assertions.assertNotNull(productById);

        //ResponseEntity<ProductResponse> forEntity = restTemplate.getForEntity("/api/v1/product/1", ProductResponse.class);
    }

}
