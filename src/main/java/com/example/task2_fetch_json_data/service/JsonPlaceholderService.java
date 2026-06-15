package com.example.task2_fetch_json_data.service;

import com.example.task2_fetch_json_data.DTO.*;

import java.util.List;
import java.util.Map;

public interface JsonPlaceholderService
{
    public List<Map<String, Object>> fetchAllData();
    public List<Map<String, Object>> fetchAllCommentsDataByPostId(Long postId);
    public List<Map<String, Object>> fetchPlaceholderDataCommentsByPostId(Long postId);
    public Map<String, Object> fetchDataById(Long id);
    public List<Map<String, Object>> fetchAllComments();
    public List<Map<String, Object>> fetchAllAlbums();
    public List<Map<String, Object>> fetchAllPhotos();
    public List<Map<String, Object>> fetchAllTodos();
    public List<Map<String, Object>> fetchAllUsers();

    //fetch data from json placeholder to database

    public List<UserDto> fetchUsersFromWeb();
    public List<PostDto> fetchPostsFromWeb();
    public List<CommentDto> fetchCommentsFromWeb();
    public List<PhotoDto> fetchPhotosFromWeb();
    public List<AlbumDto> fetchAlbumsFromWeb();
    public List<TodoDto> fetchTodosFromWeb();
    public void syncAndSaveToPostgres();
}
