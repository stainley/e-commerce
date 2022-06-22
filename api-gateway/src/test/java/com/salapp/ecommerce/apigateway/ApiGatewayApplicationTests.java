package com.salapp.ecommerce.apigateway;

import com.salapp.ecommerce.api.ApiApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiGatewayApplicationTests {

    @Autowired
    private ApiApplication apiGatewayApplication;

    @Test
    public void context() {
        Assertions.assertNotNull(apiGatewayApplication);
    }

}
