package com.salapp.ecommerce.api.dto.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@ToString
@Data
@Builder
@AllArgsConstructor
public class ProductResponse {

    Long id;

    @NotNull
    String name;

    String description;

    @JsonFormat(pattern = "dd-MM-yy hh:mm:ss")
    Date creationDate;

    BigDecimal price;

    public ProductResponse(){}
}
