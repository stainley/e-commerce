package com.salapp.ecommerce.apigateway.controller;


import com.salapp.ecommerce.api.dto.product.ProductResponse;
import com.salapp.ecommerce.api.exception.ProductNotFoundException;
import com.salapp.ecommerce.api.util.ServiceUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Objects;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@WebMvcTest
public class ApiGatewayTests {

    @Value("${product.service}")
    private String URL_PRODUCT;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceUtil serviceUtil;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ApiController cut;

    @BeforeEach
    public void loadContext() {
        Assertions.assertNotNull(mockMvc);
        Assertions.assertNotNull(serviceUtil);
        System.out.println(mockMvc.getClass().getName());
        System.out.println(serviceUtil.getClass().getName());
        System.out.println(restTemplate.getClass().getName());
        System.out.println(cut.getClass().getName());

        ReflectionTestUtils.setField(cut, "URL_PRODUCT", "http://localhost:8070");

    }

    @Test
    public void givenNotFound_whenGetProduct_thenProductNotFoundException() {

        when(this.restTemplate.getForEntity(URL_PRODUCT + "/api/v1/product/" + 1L, ProductResponse.class))
                .thenReturn(null);

        verifyNoInteractions(restTemplate);
        Assertions.assertThrows(ProductNotFoundException.class, () -> cut.getProductById(1L), "ProductNotFoundException didn't throw it");
    }

    @Test
    public void givenProduct_whenGetProduct_thenReturnProduct() throws Exception {
        when(this.restTemplate.getForEntity(URL_PRODUCT + "/api/v1/product/" + 1L, ProductResponse.class))
                .thenReturn(new ResponseEntity<>(ProductResponse.builder()
                        .creationDate(new Date())
                        .id(1L)
                        .name("iPad")
                        .description("M1")
                        .build(), HttpStatus.OK));

        verifyNoInteractions(restTemplate);

        ResponseEntity<ProductResponse> result = cut.getProductById(1L);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals("iPad", Objects.requireNonNull(result.getBody()).getName());
    }
}
