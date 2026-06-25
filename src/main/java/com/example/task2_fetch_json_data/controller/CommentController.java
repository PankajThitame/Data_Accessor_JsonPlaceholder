package com.example.task2_fetch_json_data.controller;

import com.example.task2_fetch_json_data.entity.Comment;
import com.example.task2_fetch_json_data.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    Logger logger = LoggerFactory.getLogger(CommentController.class);

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> result = commentRepository.findAll();

        logger.info("getAllComments==============");

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable UUID postId) {
        List<Comment> result = commentRepository.findByPostUuidId(postId);
        logger.info("getCommentsByPostId==============");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
