package com.salapp.ecommerce.userservice.services;

import com.salapp.ecommerce.api.dto.user.UserRequest;
import com.salapp.ecommerce.api.dto.user.UserResponse;
import com.salapp.ecommerce.userservice.mapper.UserEntityMapper;
import com.salapp.ecommerce.userservice.mapper.UserResponseMapper;
import com.salapp.ecommerce.userservice.model.UserEntity;
import com.salapp.ecommerce.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final MongoTemplate mongoTemplate;

    @Override
    public UserResponse createUser(UserRequest userRequest) {

        UserEntityMapper userEntityMapper = (request) -> {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName(request.getUsername());
            userEntity.setEmail(request.getEmail());
            userEntity.setFirstName(request.getFirstName());
            userEntity.setLastName(request.getLastName());
            userEntity.setAddress(request.getAddressRequest());
            userEntity.setGender(userRequest.getGender());
            return userEntity;
        };

        UserEntity insert = this.userRepository.insert(userEntityMapper.mapUserRequestToUserEntity(userRequest));

        return UserResponse.builder()
                .id(insert.getId())
                .username(userRequest.getUsername())
                .build();
    }

    @Override
    public UserResponse getUserById(String id) {
        Optional<UserEntity> result = this.userRepository.findById(id);

        return result.isPresent() ? userResponseMapper.mapUserEntityToUserResponse(result.get()) : UserResponse.builder().build();
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        Query query = new Query();
            query.addCriteria(Criteria.where("email").is(email));

        Optional<UserEntity> userEntity = this.mongoTemplate.find(query, UserEntity.class).stream().findFirst();

        return mapUser.apply(userEntity.orElse(null));
    }

    @Override
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserEntity> users = this.userRepository.findAll();

        return new ResponseEntity<>(UserResponseMapper.mapUserEntityToUserResponse(users), HttpStatus.OK);
    }

    private final UserResponseMapper userResponseMapper = (entity) ->
            UserResponse.builder()
                    .username(entity.getUserName())
                    .firstName(entity.getFirstName())
                    .lastName(entity.getLastName())
                    .gender(entity.getGender())
                    .email(entity.getEmail())
                    .build();

    private final Function<UserEntity, UserResponse> mapUser = (entity) ->
            UserResponse.builder()
                    .username(entity.getUserName())
                    .firstName(entity.getFirstName())
                    .lastName(entity.getLastName())
                    .email(entity.getEmail())
                    .gender(entity.getGender())
                    .build();
}
