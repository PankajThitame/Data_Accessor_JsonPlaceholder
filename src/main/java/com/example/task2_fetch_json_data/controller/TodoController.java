package com.example.task2_fetch_json_data.controller;

import com.example.task2_fetch_json_data.entity.Todo;
import com.example.task2_fetch_json_data.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/todos")
public class TodoController
{
    @Autowired
    TodoRepository todoRepository;

    @GetMapping("/")
    public ResponseEntity<List<Todo>> getAllTodos()
    {
        List<Todo> result = todoRepository.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
