package com.salapp.ecommerce.productservice.controller;

import com.salapp.ecommerce.api.util.ServiceUtil;
import com.salapp.ecommerce.productservice.services.ProductService;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.hamcrest.MockitoHamcrest;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ProductControllerTest {

    private static final Long PRODUCT_ID = 1L;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ServiceUtil serviceUtil;


    @Test
    public void loadContext(){
        System.out.println(mockMvc.getClass().getName());
        System.out.println(productService.getClass().getName());
        System.out.println(serviceUtil.getClass().getName());

    }

    @Test
    void createProduct() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void getProductsToExpire() {
    }

    @Test
    void updateProduct() {
    }

    @DisplayName(value = "should delete a product when product is found")
    @Test
    void deleteProduct() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/product/" + PRODUCT_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.is("product " + PRODUCT_ID + " has been deleted!")));

    }
}