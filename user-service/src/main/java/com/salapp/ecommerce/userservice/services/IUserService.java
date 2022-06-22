package com.salapp.ecommerce.userservice.services;

import com.salapp.ecommerce.api.dto.user.UserRequest;
import com.salapp.ecommerce.api.dto.user.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SuppressWarnings("unused")
public interface IUserService {

    UserResponse createUser(UserRequest userRequest);

    UserResponse getUserById(String id);

    UserResponse getUserByEmail(String email);

    ResponseEntity<List<UserResponse>> getUsers();
}
