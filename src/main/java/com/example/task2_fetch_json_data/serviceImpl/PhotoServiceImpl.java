package com.example.task2_fetch_json_data.serviceImpl;

import com.example.task2_fetch_json_data.DTO.PhotoDto;
import com.example.task2_fetch_json_data.entity.Photo;
import com.example.task2_fetch_json_data.repository.PhotoRepository;
import com.example.task2_fetch_json_data.service.PhotoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PhotoServiceImpl implements PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    ModelMapper modelMapper;

    public ResponseEntity<List<PhotoDto>> getAllPhotos()
    {
        List<Photo> result = photoRepository.findAll();

        return new ResponseEntity<>(result.stream()
                .map(photo -> modelMapper.map(photo, PhotoDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
