package com.salapp.ecommerce.userservice.integration;

import com.salapp.ecommerce.api.dto.address.Gender;
import com.salapp.ecommerce.api.dto.user.UserRequest;
import com.salapp.ecommerce.api.dto.user.UserResponse;
import com.salapp.ecommerce.userservice.controller.UserController;
import com.salapp.ecommerce.userservice.model.UserEntity;
import com.salapp.ecommerce.userservice.repository.UserRepository;
import com.salapp.ecommerce.userservice.services.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.containers.wait.strategy.WaitStrategy;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;
import java.util.Objects;

@Testcontainers
@EnableAutoConfiguration(exclude = {EmbeddedMongoAutoConfiguration.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class ApplicationIT {

    private static String USER_ID = "";
    @Autowired
    private UserController userController;

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest")
            .waitingFor(Wait.defaultWaitStrategy())
            .withReuse(true);

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Order(1)
    @Test
    public void shouldCreateUser() {
        UserRequest userRequest1 = UserRequest.builder()
                .username("root")
                .gender(Gender.MALE)
                .email("root@mail.com")
                .build();

        UserRequest userRequest2 = UserRequest.builder()
                .username("admin")
                .email("admin@mail.com")
                .gender(Gender.FEMALE)
                .build();

        ResponseEntity<UserResponse> response = userController.createUser(userRequest1);
        USER_ID = response.getBody().getId();

        System.out.println("USER ID: " + response.getBody().getId());
        Assertions.assertEquals("root", Objects.requireNonNull(response.getBody()).getUsername());

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        userController.createUser(userRequest2);
    }

    @Order(2)
    @Test
    public void shouldFindAnUserById() {
        System.out.println("Find User by ID: " + USER_ID);
        ResponseEntity<UserResponse> userById = userController.getUserById(USER_ID);
        Assertions.assertEquals("root", userById.getBody().getUsername());
    }

    @Order(3)
    @Test
    public void shouldFindByEmail() {
        ResponseEntity<UserResponse> userByEmail = userController.getUserByEmail("admin@mail.com");

        Assertions.assertEquals(HttpStatus.FOUND, userByEmail.getStatusCode());
        Assertions.assertEquals("admin", userByEmail.getBody().getUsername());
    }

    @Order(4)
    @Test
    void shouldFindAll() {
        ResponseEntity<List<UserResponse>> allUsers = userController.getAllUsers();
        Assertions.assertEquals(2, allUsers.getBody().size());
    }

}
