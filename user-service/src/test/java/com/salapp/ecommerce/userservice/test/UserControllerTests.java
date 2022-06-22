package com.salapp.ecommerce.userservice.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salapp.ecommerce.api.dto.address.Gender;
import com.salapp.ecommerce.api.dto.user.UserRequest;
import com.salapp.ecommerce.api.dto.user.UserResponse;
import com.salapp.ecommerce.userservice.controller.UserController;
import com.salapp.ecommerce.userservice.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.exceptions.misusing.MockitoConfigurationException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = UserController.class)
public class UserControllerTests {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private UserResponse userResponse;

    @Test
    public void context() {
        Assertions.assertNotNull(userService);
        Assertions.assertNotNull(mockMvc);
        Assertions.assertNotNull(objectMapper);
    }

    @BeforeEach
    public void loadData() {
        userResponse = UserResponse.builder()
                .username("root")
                .firstName("John")
                .lastName("Smith")
                .email("myemail@mail.com")
                .gender(Gender.FEMALE)
                .build();
    }

    @Test
    public void shouldCreateAnUser() throws Exception {
        UserRequest userRequest = UserRequest.builder()
                .email("myemail@mail.com")
                .username("root")
                .gender(Gender.FEMALE)
                .build();

        Mockito.when(userService.createUser(Mockito.any(UserRequest.class)))
                .thenReturn(userResponse);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/user/create")
                .content(objectMapper.writeValueAsString(userRequest))
                .contentType(MediaType.APPLICATION_JSON));

        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username", is("root")))
                .andExpect(jsonPath("$.email", is("myemail@mail.com")))
                .andExpect(jsonPath("$.gender", is(Gender.FEMALE.name())));
    }


}
