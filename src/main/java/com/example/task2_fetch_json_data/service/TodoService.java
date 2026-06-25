package com.example.task2_fetch_json_data.service;

import com.example.task2_fetch_json_data.DTO.TodoDto;
import com.example.task2_fetch_json_data.controller.TodoController;
import com.example.task2_fetch_json_data.entity.Todo;
import com.example.task2_fetch_json_data.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface TodoService {

    public ResponseEntity<List<TodoDto>> getAllTodos();
}
