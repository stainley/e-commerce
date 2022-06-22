package com.salapp.ecommerce.userservice.integration.services;

import com.salapp.ecommerce.api.dto.address.Gender;
import com.salapp.ecommerce.api.dto.user.UserRequest;
import com.salapp.ecommerce.api.dto.user.UserResponse;
import com.salapp.ecommerce.userservice.model.UserEntity;
import com.salapp.ecommerce.userservice.repository.UserRepository;
import com.salapp.ecommerce.userservice.services.UserService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    private UserRequest userRequest;

    private UserEntity userEntity;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Test
    public void context() {
        Assertions.assertNotNull(service);
        Assertions.assertNotNull(repository);
        Assertions.assertNotNull(mongoTemplate);
    }

    @BeforeEach
    void setUp() {
        userRequest = UserRequest.builder()
                .username("user01")
                .firstName("user")
                .lastName("userlast")
                .gender(Gender.MALE)
                .email("myemail@mail.com")
                .build();

        userEntity = new UserEntity();
        userEntity.setUserName("username");
        userEntity.setGender(Gender.FEMALE);
        userEntity.setEmail("myemail@mail.com");

    }

    @Test
    public void shouldCreateAnUser() {

        UserEntity userEntity = new UserEntity();
        userEntity.setId("1233445");
        userEntity.setEmail("myemail@mail.com");

        Mockito.when(repository.insert(Mockito.any(UserEntity.class)))
                .thenReturn(userEntity);

        UserResponse userResponse = service.createUser(userRequest);
        Assertions.assertNotNull(userResponse);

        Assertions.assertNotNull(userResponse.getId());
        Assertions.assertEquals(Gender.MALE, userRequest.getGender());
    }

    @Test
    public void shouldGetAllUsers() {

        List<UserEntity> userEntities = new ArrayList<>();

        userEntities.add(userEntity);
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUserName("root");
        userEntity2.setGender(Gender.FEMALE);
        userEntity2.setEmail("myemail@mail.com");
        userEntities.add(userEntity2);

        Mockito.when(repository.findAll()).thenReturn(userEntities);
        ResponseEntity<List<UserResponse>> users = service.getUsers();

        Assertions.assertEquals(2, Objects.requireNonNull(users.getBody()).size());

        Optional<String> user = users.getBody().stream().map(UserResponse::getUsername).findFirst();
        user.ifPresent(response -> Assertions.assertEquals("username", response));
        Assertions.assertEquals(HttpStatus.OK, users.getStatusCode());
    }

    @Test
    void shouldFindAnUserByEmail() {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is("root@mail.com"));

        Mockito.when(mongoTemplate.find(query, UserEntity.class))
                .thenReturn(Lists.list(userEntity));

        UserResponse userByEmail = service.getUserByEmail("root@mail.com");
        Assertions.assertEquals("username", userByEmail.getUsername());
    }
}
