package com.example.task2_fetch_json_data.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;

    @Embedded
    private Geo geo;

    // Constructors, Getters, Setters
}