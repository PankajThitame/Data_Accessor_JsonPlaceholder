package com.example.task2_fetch_json_data.controller;

import com.example.task2_fetch_json_data.entity.User;
import com.example.task2_fetch_json_data.repository.UserRepository;
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
    UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> result = userRepository.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
