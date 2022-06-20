package com.salapp.ecommerce.userservice.services;

import com.salapp.ecommerce.api.dto.user.UserRequest;
import com.salapp.ecommerce.api.dto.user.UserResponse;

public interface IUserService {

    void createUser(UserRequest userRequest);

    UserResponse getUserById(String id);

    UserResponse getUserByEmail(String email);
}
