package com.example.task2_fetch_json_data.controller;

import com.example.task2_fetch_json_data.entity.Comment;
import com.example.task2_fetch_json_data.entity.Post;
import com.example.task2_fetch_json_data.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/post")
public class PostController
{
    @Autowired
    PostRepository postRepository;

    Logger logger = LoggerFactory.getLogger(PostController.class);

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllData()
    {
        List<Post> result = postRepository.findAll();
        logger.info("getAllData");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Post>> getPostById(@PathVariable("id") UUID id)
    {
        Optional<Post> result = postRepository.findById(id);
        logger.info("getPostById");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
