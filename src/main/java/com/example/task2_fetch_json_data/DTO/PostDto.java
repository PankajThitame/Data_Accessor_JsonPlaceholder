package com.example.task2_fetch_json_data.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PostDto(
        UUID id,
        UUID userId, // Maps the flat user integer reference from the JSON API
        String title,
        String body
) {}