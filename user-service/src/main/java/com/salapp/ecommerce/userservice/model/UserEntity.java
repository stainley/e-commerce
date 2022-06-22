package com.salapp.ecommerce.userservice.model;

import com.salapp.ecommerce.api.dto.address.Address;
import com.salapp.ecommerce.api.dto.address.Gender;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "USER")
public class UserEntity {

    @Id
    private String id;

    private String userName;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;

    private Gender gender;
    private Address address;
}
