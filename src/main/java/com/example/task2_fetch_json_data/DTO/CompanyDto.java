package com.example.task2_fetch_json_data.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CompanyDto(
        String name,
        String catchPhrase,
        String bs
) {}