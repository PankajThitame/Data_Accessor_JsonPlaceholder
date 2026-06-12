package com.example.task2_fetch_json_data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class JsonPlaceholderConfig
{
    @Bean
    public RestClient restClient()
    {
        return RestClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }
}
