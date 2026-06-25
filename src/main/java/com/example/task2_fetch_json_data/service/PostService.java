package com.example.task2_fetch_json_data.service;

import com.example.task2_fetch_json_data.DTO.PostDto;
import com.example.task2_fetch_json_data.entity.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

public interface PostService  {

    public ResponseEntity<List<PostDto>> getAllData();
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") UUID id);
}
