package com.example.task2_fetch_json_data.service;

import com.example.task2_fetch_json_data.DTO.CommentDto;

import java.util.List;
import java.util.UUID;

public interface CommentService
{
    public List<CommentDto> fetchCommentsFromPostId(UUID postId);

}
