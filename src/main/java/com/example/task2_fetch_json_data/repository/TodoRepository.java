package com.example.task2_fetch_json_data.repository;

import com.example.task2_fetch_json_data.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>
{
}
