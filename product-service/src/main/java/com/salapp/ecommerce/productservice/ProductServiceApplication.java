package com.salapp.ecommerce.productservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.salapp.ecommerce")
public class ProductServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ProductServiceApplication.class, args);

        try {
            log.info("Product Service hostname: {}", InetAddress.getLocalHost().getHostName());

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

    }

}
