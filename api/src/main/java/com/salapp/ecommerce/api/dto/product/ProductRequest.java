package com.salapp.ecommerce.api.dto.product;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Negative;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class ProductRequest {

    @NotNull(message = "Product name cannot be empty")
    @Max(value = 100, message = "Maximum length name")
    String name;

    String description;

    @Negative(message = "Price must be positive")
    BigDecimal price;

}
