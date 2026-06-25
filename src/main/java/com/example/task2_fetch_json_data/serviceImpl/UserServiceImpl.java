package com.example.task2_fetch_json_data.serviceImpl;

import com.example.task2_fetch_json_data.DTO.UserDto;
import com.example.task2_fetch_json_data.controller.UserController;
import com.example.task2_fetch_json_data.entity.User;
import com.example.task2_fetch_json_data.repository.UserRepository;
import com.example.task2_fetch_json_data.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        List<User> result = userRepository.findAll();
        return new ResponseEntity<>(result.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
