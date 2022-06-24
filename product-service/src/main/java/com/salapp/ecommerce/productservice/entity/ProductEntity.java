package com.salapp.ecommerce.productservice.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "PRODUCT")
@ToString
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    Date creationDate;

    @Column(name = "EXPIRATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime expirationDate;

}
