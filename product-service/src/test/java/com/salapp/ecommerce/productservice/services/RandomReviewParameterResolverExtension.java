package com.salapp.ecommerce.productservice.services;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomReviewParameterResolverExtension implements ParameterResolver {

    private static final List<String> badReviews = List.of("This product was shit I don't like it", "I was reading products and I" +
            " think the product is okay. I have read better products and I think I know what's good", "Good product with good starts and good description. I can recommend for everyone.");

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface RandomReview {

    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.isAnnotated(RandomReview.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return badReviews.get(ThreadLocalRandom.current().nextInt(0, badReviews.size()));
    }
}
