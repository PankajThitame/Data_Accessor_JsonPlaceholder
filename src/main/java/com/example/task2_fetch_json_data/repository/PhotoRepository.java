package com.example.task2_fetch_json_data.repository;

import com.example.task2_fetch_json_data.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhotoRepository extends JpaRepository<Photo, UUID>
{

}
