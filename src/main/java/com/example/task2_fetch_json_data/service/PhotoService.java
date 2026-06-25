package com.example.task2_fetch_json_data.service;

import com.example.task2_fetch_json_data.DTO.PhotoDto;
import com.example.task2_fetch_json_data.entity.Photo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PhotoService {

    public ResponseEntity<List<PhotoDto>> getAllPhotos();
}
