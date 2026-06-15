package com.example.task2_fetch_json_data.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AddressDto(
        String street,
        String suite,
        String city,
        String zipcode,
        GeoDto geo
) {}