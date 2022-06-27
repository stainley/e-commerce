package com.salapp.ecommerce.apigateway.controller;

import com.salapp.ecommerce.api.dto.product.ProductRequest;
import com.salapp.ecommerce.api.dto.product.ProductResponse;
import com.salapp.ecommerce.api.dto.user.UserRequest;
import com.salapp.ecommerce.api.dto.user.UserResponse;
import com.salapp.ecommerce.api.exception.ProductNotFoundException;
import com.salapp.ecommerce.api.rest.composite.EcommerceApi;
import com.salapp.ecommerce.api.util.ServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApiController implements EcommerceApi {

    private final ServiceUtil serviceUtil;

    private final RestTemplate restTemplate;

    @Value("${product.service}")
    private String URL_PRODUCT;

    private final static String URL_USER = "http://user:8090";

    @Override
    public ResponseEntity<ProductResponse> createProduct(ProductRequest productRequest) {
        log.info("Invoking service name: {}", serviceUtil.getServiceAddress());
        ResponseEntity<ProductResponse> response = this.restTemplate.postForEntity(URL_PRODUCT + "/api/v1/product/create", productRequest, ProductResponse.class);

        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @Override
    public ResponseEntity<ProductResponse> getProductById(Long id) throws ProductNotFoundException {

        ResponseEntity<ProductResponse> entityResponse = this.restTemplate.getForEntity(URL_PRODUCT + "/api/v1/product/" + id, ProductResponse.class);

        if (entityResponse != null) {
            if(entityResponse.getBody().getId() != null) {
                return entityResponse;
            }
        } else {
            throw new ProductNotFoundException("Couldn't find product with Id: [ " + id + " ]");
        }
        return null;
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getProductsToExpire(Date expirationDate) {
        ResponseEntity<ProductResponse> forEntity = this.restTemplate.getForEntity(URL_PRODUCT + "/api/v1/product" + expirationDate, ProductResponse.class);

        return new ResponseEntity<>(List.of(Objects.requireNonNull(forEntity.getBody())), forEntity.getStatusCode());
    }

    @Override
    public ResponseEntity<ProductResponse> updateProduct(ProductRequest productRequest, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteProduct(Long id) {
        this.restTemplate.delete(URL_PRODUCT + "/api/v1/product/" + id);
        return new ResponseEntity<>("Product deleted", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponse> createUser(UserRequest userRequest) {
        ResponseEntity<UserResponse> response = this.restTemplate.postForEntity(URL_USER + "/api/v1/user/create", userRequest, UserResponse.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserResponse> getUserById(String id) {
        ResponseEntity<UserResponse> response = this.restTemplate.getForEntity(URL_USER + "/api/v1/user/" + id, UserResponse.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponse> getUserByEmail(String email) {
        ResponseEntity<UserResponse> response = this.restTemplate.getForEntity(URL_USER + "/api/v1/user" + email, UserResponse.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        ResponseEntity<? extends List> forEntity = this.restTemplate.getForEntity(URL_USER + "/api/v1/users", List.of(UserResponse.class).getClass());

        return new ResponseEntity<>(forEntity.getBody(), HttpStatus.FOUND);
    }
}
