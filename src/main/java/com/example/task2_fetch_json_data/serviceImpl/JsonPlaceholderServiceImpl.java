package com.example.task2_fetch_json_data.serviceImpl;

import com.example.task2_fetch_json_data.service.JsonPlaceholderService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class JsonPlaceholderServiceImpl implements JsonPlaceholderService
{
    private final RestClient restClient;

    public JsonPlaceholderServiceImpl(RestClient restClient)
    {
        this.restClient = restClient;
    }

    @Override
    public List<Map<String, Object>> fetchAllData() {
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(List.class);
    }

    @Override
    public List<Map<String, Object>> fetchAllCommentsDataByPostId(Long postId) {
        return restClient.get()
                .uri("/comments?postId="+postId)
                .retrieve()
                .body(List.class);
    }

    @Override
    public Map<String, Object> fetchDataById(Long id) {
        return restClient.get()
                .uri("/posts/{id}",id)
                .retrieve()
                .body(Map.class);
    }

    @Override
    public List<Map<String, Object>> fetchPlaceholderDataCommentsByPostId(Long postId) {
        return restClient.get()
                .uri("/posts/{postId}/comments",postId)
                .retrieve()
                .body(List.class);
    }

    @Override
    public List<Map<String, Object>> fetchAllComments() {
        return restClient.get()
                .uri("/comments")
                .retrieve()
                .body(List.class);
    }

    @Override
    public List<Map<String, Object>> fetchAllAlbums() {
        return restClient.get()
                .uri("/albums")
                .retrieve()
                .body(List.class);
    }

    @Override
    public List<Map<String, Object>> fetchAllPhotos() {
        return restClient.get()
                .uri("/photos")
                .retrieve()
                .body(List.class);
    }

    @Override
    public List<Map<String, Object>> fetchAllTodos() {
        return restClient.get()
                .uri("/todos")
                .retrieve()
                .body(List.class);
    }

    @Override
    public List<Map<String, Object>> fetchAllUsers() {
        return restClient.get()
                .uri("/users")
                .retrieve()
                .body(List.class);
    }


}
