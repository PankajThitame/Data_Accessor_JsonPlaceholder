package com.example.task2_fetch_json_data.controller;

import com.example.task2_fetch_json_data.DTO.AlbumDto;
import com.example.task2_fetch_json_data.entity.Album;
import com.example.task2_fetch_json_data.repository.AlbumRepository;
import com.example.task2_fetch_json_data.service.AlbumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/albums")
public class AlbumController
{
    @Autowired
    AlbumService albumService;

    Logger logger = LoggerFactory.getLogger(AlbumController.class);

    @GetMapping("/")
    public ResponseEntity<List<AlbumDto>> getAllAlbums()
    {
        logger.info("Album Controller getAllAlbums");
        return albumService.getAllAlbums();
    }
}
