package com.example.task2_fetch_json_data.serviceImpl;

import com.example.task2_fetch_json_data.DTO.CommentDto;
import com.example.task2_fetch_json_data.entity.Comment;
import com.example.task2_fetch_json_data.repository.CommentRepository;
import com.example.task2_fetch_json_data.service.CommentService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.UUID;

public class CommentsServiceImpl implements CommentService
{
    CommentRepository commentRepository;
    private ModelMapper modelMapper;

    @Override
    public List<CommentDto> fetchCommentsFromPostId(UUID postId) {
        // 1. Fetch the raw entity list from PostgreSQL using the post's UUID PK
        List<Comment> comments = commentRepository.findByPostUuidId(postId);

        // 2. Map the entities into your immutable DTO records using ModelMapper
        return comments.stream()
                .map(commentEntity -> {
                    // Map top-level matching fields (id, name, email, body)
                    CommentDto dto = modelMapper.map(commentEntity, CommentDto.class);

                    // Explicitly map externalId (Long) fields for the DTO
                    if (commentEntity.getPost() != null) {
                        dto = new CommentDto(
                                commentEntity.getExternalId(),              // Long: JSONPlaceholder comment id
                                commentEntity.getPost().getExternalId(),    // Long: JSONPlaceholder post id
                                commentEntity.getName(),
                                commentEntity.getEmail(),
                                commentEntity.getBody()
                        );
                    }
                    return dto;
                })
                .toList();
    }
}
