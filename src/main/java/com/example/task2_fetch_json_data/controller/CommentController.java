package com.example.task2_fetch_json_data.controller;

import com.example.task2_fetch_json_data.entity.Comment;
import com.example.task2_fetch_json_data.repository.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CommentController
{
    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllComments()
    {
        List<Comment> result = commentRepository.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable UUID postId)
    {
        List<Comment> result = commentRepository.findByPostId(postId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
