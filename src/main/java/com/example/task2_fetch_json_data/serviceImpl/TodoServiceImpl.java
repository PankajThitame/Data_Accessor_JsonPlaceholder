package com.example.task2_fetch_json_data.serviceImpl;

import com.example.task2_fetch_json_data.DTO.TodoDto;
import com.example.task2_fetch_json_data.controller.TodoController;
import com.example.task2_fetch_json_data.entity.Todo;
import com.example.task2_fetch_json_data.repository.TodoRepository;
import com.example.task2_fetch_json_data.service.TodoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    ModelMapper modelMapper;

    public ResponseEntity<List<TodoDto>> getAllTodos()
    {
        List<Todo> result = todoRepository.findAll();
        return new ResponseEntity<>(result.stream()
                .map(todo -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
