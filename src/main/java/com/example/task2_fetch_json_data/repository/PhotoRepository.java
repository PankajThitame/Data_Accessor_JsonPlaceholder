package com.example.task2_fetch_json_data.repository;

import com.example.task2_fetch_json_data.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long>
{

}
