package com.example.task2_fetch_json_data.repository;

import com.example.task2_fetch_json_data.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID>
{
    List<Comment> findByPostId(UUID postId);
}
