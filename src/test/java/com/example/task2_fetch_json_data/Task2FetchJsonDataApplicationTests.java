package com.example.task2_fetch_json_data;

import com.example.task2_fetch_json_data.service.JsonPlaceholderService;
import com.example.task2_fetch_json_data.serviceImpl.JsonPlaceholderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class Task2FetchJsonDataApplicationTests {

    @Autowired
    private JsonPlaceholderServiceImpl service;

    @Test
    void contextLoads() {

        var  map =service.fetchAllDatap();

        Assertions.assertFalse(map.isEmpty());
    }

}
