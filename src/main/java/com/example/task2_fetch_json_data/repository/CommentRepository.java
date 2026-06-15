package com.example.task2_fetch_json_data.repository;

import com.example.task2_fetch_json_data.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
