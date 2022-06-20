package com.salapp.ecommerce.userservice;

import com.salapp.ecommerce.api.dto.address.Address;
import com.salapp.ecommerce.api.dto.address.Gender;
import com.salapp.ecommerce.userservice.model.UserEntity;
import com.salapp.ecommerce.userservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UserRepository repository, MongoTemplate mongoTemplate) {
        return args -> {
            Address address = new Address();
            address.setCountry("Dominican Republic");
            address.setCountryCode("DR");
            address.setCity("Santo Domingo");

            String email = "saiyamans@gmail.com";

            UserEntity user = new UserEntity();
            user.setUserName("saiyamans");
            user.setEmail(email);
            user.setGender(Gender.MALE);
            user.setFirstName("Stainley");
            user.setLastName("Lebron");
            user.setAddress(address);


            Query query = new Query();
            query.addCriteria(Criteria.where("email").is(email));
            List<UserEntity> userEntities = mongoTemplate.find(query, UserEntity.class);

            if (userEntities.size() > 1) {
                throw new IllegalStateException("found many user with email " + email);
            }

            if (userEntities.isEmpty()) {
                System.out.println("Inserting user: " + user);
                repository.insert(user);
            }

        };
    }
}
