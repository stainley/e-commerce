package com.salapp.ecommerce.userservice.controller;

import com.salapp.ecommerce.api.dto.user.UserRequest;
import com.salapp.ecommerce.api.dto.user.UserResponse;
import com.salapp.ecommerce.api.rest.user.UserRestController;
import com.salapp.ecommerce.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController implements UserRestController {

    private final UserService userService;


    @Override
    public ResponseEntity<UserResponse> createUser(UserRequest userRequest) {
        this.userService.createUser(userRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserResponse> getUserById(String id) {
        UserResponse result = this.userService.getUserById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponse> getUserByEmail(String email) {
        UserResponse result = this.userService.getUserByEmail(email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
