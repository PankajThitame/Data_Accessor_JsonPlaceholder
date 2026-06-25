package com.example.task2_fetch_json_data.controller;

import com.example.task2_fetch_json_data.service.JsonPlaceholderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
//@RequestMapping("/api/")
public class JsonPlaceholder
{
    final JsonPlaceholderService jsonPlaceholder;

    Logger logger = Logger.getLogger(JsonPlaceholder.class.getName());

    public JsonPlaceholder(JsonPlaceholderService jsonPlaceholder)
    {
        logger.info("JsonPlaceholder constructor");
        this.jsonPlaceholder =  jsonPlaceholder;
    }

//    @GetMapping("/comments")
//    public ResponseEntity<List<Map<String, Object>>> getCommentsDataByPostId(@RequestParam Long postId)
//    {
//        List<Map<String, Object>> result = jsonPlaceholder.fetchAllCommentsDataByPostId(postId);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }





}
