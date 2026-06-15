package com.example.task2_fetch_json_data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Company {
    @Column(name = "company_name")
    private String name;
    private String catchPhrase;
    private String bs;

    // Constructors, Getters, Setters
}