package com.example.task2_fetch_json_data.serviceImpl;

import com.example.task2_fetch_json_data.DTO.AlbumDto;
import com.example.task2_fetch_json_data.controller.AlbumController;
import com.example.task2_fetch_json_data.entity.Album;
import com.example.task2_fetch_json_data.repository.AlbumRepository;
import com.example.task2_fetch_json_data.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    ModelMapper modelMapper;

    public ResponseEntity<List<AlbumDto>> getAllAlbums()
    {
        List<Album> result = albumRepository.findAll();

        return new ResponseEntity<>(result.stream()
                .map(album -> modelMapper.map(album, AlbumDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
