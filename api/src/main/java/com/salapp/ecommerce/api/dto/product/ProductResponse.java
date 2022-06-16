package com.salapp.ecommerce.api.dto.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
public class ProductResponse {

    Long id;

    @NotNull
    String name;

    String description;

    @JsonFormat(pattern = "dd-MM-yy hh:mm:ss")
    Date creationDate;

}
