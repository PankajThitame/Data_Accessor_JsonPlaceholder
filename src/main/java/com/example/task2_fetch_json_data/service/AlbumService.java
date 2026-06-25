package com.example.task2_fetch_json_data.service;

import com.example.task2_fetch_json_data.DTO.AlbumDto;
import com.example.task2_fetch_json_data.entity.Album;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AlbumService {

    public ResponseEntity<List<AlbumDto>> getAllAlbums();
}
