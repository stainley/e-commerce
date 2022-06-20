package com.salapp.ecommerce.userservice.mapper;

import com.salapp.ecommerce.api.dto.user.UserResponse;
import com.salapp.ecommerce.userservice.model.UserEntity;

@FunctionalInterface
public interface UserResponseMapper {

    UserResponse mapUserEntityToUserResponse(UserEntity user);

}
