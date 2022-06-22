package com.salapp.ecommerce.userservice;


import com.salapp.ecommerce.userservice.controller.UserController;
import com.salapp.ecommerce.userservice.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@DataMongoTest
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
public class UserServiceApplicationTests {

    //@Autowired
    private UserService userController;


    //@Test
    public void context() {
        Assertions.assertNotNull(userController);
    }

}
