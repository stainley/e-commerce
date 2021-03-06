package com.salapp.ecommerce.api.dto.user;

import com.salapp.ecommerce.api.dto.address.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;

}
