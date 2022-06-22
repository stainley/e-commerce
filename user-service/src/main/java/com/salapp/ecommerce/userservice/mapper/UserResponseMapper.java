package com.salapp.ecommerce.userservice.mapper;

import com.salapp.ecommerce.api.dto.user.UserResponse;
import com.salapp.ecommerce.userservice.model.UserEntity;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface UserResponseMapper {

    UserResponse mapUserEntityToUserResponse(UserEntity user);

    static List<UserResponse> mapUserEntityToUserResponse(List<UserEntity> userEntities) {

        List<UserResponse> userResponses = new ArrayList<>();

        if (userEntities.size() > 0) {
            userEntities.forEach(entity -> {
                UserResponse userResponse = UserResponse.builder()
                        .id(entity.getId())
                        .username(entity.getUserName())
                        .firstName(entity.getFirstName())
                        .lastName(entity.getLastName())
                        .email(entity.getEmail())
                        .build();

                userResponses.add(userResponse);
            });
        }
        return userResponses;
    }
}
