package com.example.task2_fetch_json_data.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TodoDto(
        Long id,
        Long userId,
        String title,
        boolean completed
) {}