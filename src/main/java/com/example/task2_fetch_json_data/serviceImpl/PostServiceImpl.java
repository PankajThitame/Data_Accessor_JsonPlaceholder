package com.example.task2_fetch_json_data.serviceImpl;

import com.example.task2_fetch_json_data.DTO.PostDto;
import com.example.task2_fetch_json_data.DTO.TodoDto;
import com.example.task2_fetch_json_data.entity.Post;
import com.example.task2_fetch_json_data.repository.PostRepository;
import com.example.task2_fetch_json_data.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<List<PostDto>> getAllData() {

        List<Post> result = postRepository.findAll();
        return new ResponseEntity<>(result.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostDto> getPostById(UUID id) {
        Post result = postRepository.findById(id).orElse(null);
        return new ResponseEntity<>(modelMapper.map(result, PostDto.class), HttpStatus.OK);
    }
}
