package com.salapp.ecommerce.userservice.mapper;

import com.salapp.ecommerce.api.dto.user.UserRequest;
import com.salapp.ecommerce.userservice.model.UserEntity;

@FunctionalInterface
public interface UserEntityMapper {

    UserEntity mapUserRequestToUserEntity(UserRequest userRequest);

}
