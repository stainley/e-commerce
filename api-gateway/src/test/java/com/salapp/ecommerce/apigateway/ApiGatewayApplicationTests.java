package com.salapp.ecommerce.apigateway;

import com.salapp.ecommerce.api.ApiApplication;
import com.salapp.ecommerce.api.util.ServiceUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ApiGatewayApplicationTests {

    @Autowired
    private ApiApplication apiGatewayApplication;

    @Test
    public void context() {
        Assertions.assertNotNull(apiGatewayApplication);
    }

}
