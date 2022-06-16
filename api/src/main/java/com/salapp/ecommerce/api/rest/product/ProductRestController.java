package com.salapp.ecommerce.api.rest.product;

import com.salapp.ecommerce.api.dto.product.ProductResponse;
import com.salapp.ecommerce.api.dto.product.ProductRequest;
import io.swagger.v3.oas.annotations.OpenAPI30;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@OpenAPI30
@RequestMapping("/api/v1/product")
public interface ProductRestController {

    @Operation(description = "Create product", summary = "Operation to create a product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Product Created",
                    content = {@Content(mediaType = "application/json")}
            )
    })
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest);

    @Operation(summary = "Get product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the product", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductRequest.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid Product Id", content = @Content)

    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResponse> getProductById(@Parameter(description = "product id to search") @PathVariable Long id);


    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable Long id);
}
