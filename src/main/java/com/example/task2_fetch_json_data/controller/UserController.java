package com.example.task2_fetch_json_data.controller;

import com.example.task2_fetch_json_data.DTO.UserDto;
import com.example.task2_fetch_json_data.entity.User;
import com.example.task2_fetch_json_data.repository.UserRepository;
import com.example.task2_fetch_json_data.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController
{
    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        logger.info("getAllUsers");
        return userService.getAllUsers();
    }
}
