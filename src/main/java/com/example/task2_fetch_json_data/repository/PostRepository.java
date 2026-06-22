package com.example.task2_fetch_json_data.repository;

import com.example.task2_fetch_json_data.entity.Post;
import com.example.task2_fetch_json_data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID>
{

}
