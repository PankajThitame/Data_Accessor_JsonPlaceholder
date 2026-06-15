package com.example.task2_fetch_json_data.repository;

import com.example.task2_fetch_json_data.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long>
{

}
