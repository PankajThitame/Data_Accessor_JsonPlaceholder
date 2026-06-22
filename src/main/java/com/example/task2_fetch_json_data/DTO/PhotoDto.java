package com.example.task2_fetch_json_data.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PhotoDto(
        UUID id,
        UUID albumId,
        String title,
        String url,
        String thumbnailUrl
) {}