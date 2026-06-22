package com.example.task2_fetch_json_data.controller;

import com.example.task2_fetch_json_data.entity.Photo;
import com.example.task2_fetch_json_data.repository.PhotoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/photos")
public class PhotoController
{
    @Autowired
    PhotoRepository photoRepository;

    @GetMapping("/")
    public ResponseEntity<List<Photo>> getAllPhotos()
    {
        List<Photo> result = photoRepository.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
