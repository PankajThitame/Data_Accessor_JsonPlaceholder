package com.example.task2_fetch_json_data.controller;

import com.example.task2_fetch_json_data.entity.Album;
import com.example.task2_fetch_json_data.repository.AlbumRepository;
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
    AlbumRepository albumRepository;

    @GetMapping("/")
    public ResponseEntity<List<Album>> getAllAlbums()
    {
        List<Album> result = albumRepository.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
