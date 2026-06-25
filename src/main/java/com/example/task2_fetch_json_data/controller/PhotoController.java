package com.example.task2_fetch_json_data.controller;

import com.example.task2_fetch_json_data.DTO.PhotoDto;
import com.example.task2_fetch_json_data.entity.Photo;
import com.example.task2_fetch_json_data.repository.PhotoRepository;
import com.example.task2_fetch_json_data.service.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/photos")
public class PhotoController
{
    @Autowired
    PhotoService photoService;

    Logger logger = Logger.getLogger(PhotoController.class.getName());

    @GetMapping("/")
    public ResponseEntity<List<PhotoDto>> getAllPhotos()
    {
        logger.info("getAllPhotos");
        return photoService.getAllPhotos();
    }
}
