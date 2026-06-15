package com.example.task2_fetch_json_data.entity;

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
public class Geo {
    private String lat;
    private String lng;

    // Constructors, Getters, Setters
}