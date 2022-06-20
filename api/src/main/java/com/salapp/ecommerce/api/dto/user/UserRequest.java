package com.salapp.ecommerce.api.dto.user;

import com.salapp.ecommerce.api.dto.address.Address;
import com.salapp.ecommerce.api.dto.address.Gender;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserRequest {

    private String username;
    private String firstName;
    private String lastName;

    @NotNull
    @Email
    private String email;
    private Address addressRequest;
    private Gender gender;

}
