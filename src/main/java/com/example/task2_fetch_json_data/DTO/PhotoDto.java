package com.example.task2_fetch_json_data.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PhotoDto(
        Long id,
        Long albumId,
        String title,
        String url,
        String thumbnailUrl
) {}