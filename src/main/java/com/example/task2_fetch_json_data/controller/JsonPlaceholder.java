package com.example.task2_fetch_json_data.controller;

import com.example.task2_fetch_json_data.service.JsonPlaceholderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class JsonPlaceholder
{
    final JsonPlaceholderService jsonPlaceholder;

    public JsonPlaceholder(JsonPlaceholderService jsonPlaceholder)
    {
        this.jsonPlaceholder =  jsonPlaceholder;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Map<String, Object>>> getAllData()
    {
        List<Map<String, Object>> result = jsonPlaceholder.fetchAllData();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Map<String, Object>> getAllData(@RequestParam Long postId)
    {
        Map<String, Object> result = jsonPlaceholder.fetchDataById(postId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @GetMapping("/comments")
//    public ResponseEntity<List<Map<String, Object>>> getCommentsDataByPostId(@RequestParam Long postId)
//    {
//        List<Map<String, Object>> result = jsonPlaceholder.fetchAllCommentsDataByPostId(postId);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<Map<String, Object>>> getCommentsByPostId(@PathVariable Long postId)
    {
        List<Map<String, Object>> result = jsonPlaceholder.fetchPlaceholderDataCommentsByPostId(postId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/comments")
    public ResponseEntity<List<Map<String, Object>>> getAllComments()
    {
        List<Map<String, Object>> result = jsonPlaceholder.fetchAllComments();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Map<String, Object>>> getAllAlbums()
    {
        List<Map<String, Object>> result = jsonPlaceholder.fetchAllAlbums();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/photos")
    public ResponseEntity<List<Map<String, Object>>> getAllPhotos()
    {
        List<Map<String, Object>> result = jsonPlaceholder.fetchAllPhotos();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Map<String, Object>>> getAllTodos()
    {
        List<Map<String, Object>> result = jsonPlaceholder.fetchAllTodos();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<Map<String, Object>>> getAllUsers()
    {
        List<Map<String, Object>> result = jsonPlaceholder.fetchAllUsers();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
