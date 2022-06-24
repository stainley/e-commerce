package com.salapp.ecommerce.api.config;

import com.salapp.ecommerce.api.exception.RestResponseStatusExceptionResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(0, new RestResponseStatusExceptionResolver());
    }
}
