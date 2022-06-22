package com.salapp.ecommerce.apigateway.controller;

import com.salapp.ecommerce.api.dto.product.ProductRequest;
import com.salapp.ecommerce.api.dto.product.ProductResponse;
import com.salapp.ecommerce.api.dto.user.UserRequest;
import com.salapp.ecommerce.api.dto.user.UserResponse;
import com.salapp.ecommerce.api.rest.composite.EcommerceApi;
import com.salapp.ecommerce.api.util.ServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApiController implements EcommerceApi {

    private final ServiceUtil serviceUtil;

    private final RestTemplate restTemplate;

    private final static String URL_PRODUCT = "http://product:8070";

    private final static String URL_USER = "http://user:8090";

    @Override
    public ResponseEntity<ProductResponse> createProduct(ProductRequest productRequest) {
        log.info("Invoking service name: {}", serviceUtil.getServiceAddress());
        ResponseEntity<ProductResponse> response = this.restTemplate.postForEntity(URL_PRODUCT + "/api/v1/product/create", productRequest, ProductResponse.class);

        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @Override
    public ResponseEntity<ProductResponse> getProductById(Long id) {
        return this.restTemplate.getForEntity(URL_PRODUCT + "/api/v1/product/" + id, ProductResponse.class);
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
        ResponseEntity<UserResponse> response = this.restTemplate.getForEntity(URL_USER + "/api/v1/user?" + email, UserResponse.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        ResponseEntity<? extends List> forEntity = this.restTemplate.getForEntity(URL_USER + "/api/v1/users", List.of(UserResponse.class).getClass());

        return new ResponseEntity<>(forEntity.getBody(), HttpStatus.FOUND);
    }
}
