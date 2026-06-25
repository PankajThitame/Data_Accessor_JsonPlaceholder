package com.example.task2_fetch_json_data.serviceImpl;

import com.example.task2_fetch_json_data.DTO.*;
import com.example.task2_fetch_json_data.entity.*;
import com.example.task2_fetch_json_data.repository.*;
import com.example.task2_fetch_json_data.service.JsonPlaceholderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;


@Service
@AllArgsConstructor
public class JsonPlaceholderServiceImpl implements JsonPlaceholderService
{
    private final RestClient restClient;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PhotoRepository photoRepository;
    private final CommentRepository commentRepository;
    private final AlbumRepository albumRepository;
    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Map<String, Object>> fetchAllData() {
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(List.class);
    }

    public List<PostDto> fetchAllDatap() {
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<PostDto>>() {
                }).getBody();
    }


    public PostDto fetchDataById(String id ) {
        return restClient.get()
                .uri("/posts/{id}",Long.parseLong(id))
                .retrieve()
                .toEntity(PostDto.class).getBody();
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

    @Override
    public List<UserDto> fetchUsersFromWeb() {
        return restClient.get()
                .uri("/users")
                .retrieve()
                .body(new ParameterizedTypeReference<List<UserDto>>() {});
    }

    @Override
    public List<PostDto> fetchPostsFromWeb() {
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<List<PostDto>>() {});
    }

    @Override
    public List<CommentDto> fetchCommentsFromWeb() {
        return restClient.get()
                .uri("/comments")
                .retrieve()
                .body(new ParameterizedTypeReference<List<CommentDto>>() {});
    }

    @Override
    public List<PhotoDto> fetchPhotosFromWeb() {
        return restClient.get()
                .uri("/photos")
                .retrieve()
                .body(new ParameterizedTypeReference<List<PhotoDto>>() {});
    }

    @Override
    public List<AlbumDto> fetchAlbumsFromWeb() {
        return restClient.get()
                .uri("/albums")
                .retrieve()
                .body(new ParameterizedTypeReference<List<AlbumDto>>() {});
    }

    @Override
    public List<TodoDto> fetchTodosFromWeb() {
        return restClient.get()
                .uri("/todos")
                .retrieve()
                .body(new ParameterizedTypeReference<List<TodoDto>>() {});
    }

    @Override
    public void syncAndSaveToPostgres() {

        // ==========================================
        // 1. SYNC USERS (Root entity, no dependencies)
        // ==========================================
        List<UserDto> userDtos = fetchUsersFromWeb();
        for (UserDto uDto : userDtos) {
            User user = new User();
            user.setExternalId(uDto.id()); // Store JSONPlaceholder integer ID in externalId
            user.setName(uDto.name());
            user.setUsername(uDto.username());
            user.setEmail(uDto.email());
            user.setPhone(uDto.phone());
            user.setWebsite(uDto.website());

            if (uDto.address() != null) {
                Address address = modelMapper.map(uDto.address(), Address.class);
                if (uDto.address().geo() != null) {
                    Geo geo = modelMapper.map(uDto.address().geo(), Geo.class);
                    address.setGeo(geo);
                }
                user.setAddress(address);
            }

            if (uDto.company() != null) {
                Company company = modelMapper.map(uDto.company(), Company.class);
                user.setCompany(company);
            }

            userRepository.save(user);
        }

        // ==========================================
        // 2. SYNC POSTS (Depends on User)
        // ==========================================
        List<PostDto> postDtos = fetchPostsFromWeb();
        for (PostDto pDto : postDtos) {
            Post post = new Post();
            post.setExternalId(pDto.id());
            post.setTitle(pDto.title());
            post.setBody(pDto.body());

            // Look up parent User by externalId (JSONPlaceholder integer), not by UUID PK
            userRepository.findByExternalId(pDto.userId()).ifPresent(post::setUser);

            postRepository.save(post);
        }

        // ==========================================
        // 3. SYNC COMMENTS (Depends on Post)
        // ==========================================
        List<CommentDto> commentDtos = fetchCommentsFromWeb();
        for (CommentDto cDto : commentDtos) {
            Comment comment = new Comment();
            comment.setExternalId(cDto.id());
            comment.setName(cDto.name());
            comment.setEmail(cDto.email());
            comment.setBody(cDto.body());

            // Look up parent Post by externalId
            postRepository.findByExternalId(cDto.postId()).ifPresent(comment::setPost);

            commentRepository.save(comment);
        }

        // ==========================================
        // 4. SYNC ALBUMS (Depends on User)
        // ==========================================
        List<AlbumDto> albumDtos = fetchAlbumsFromWeb();
        for (AlbumDto aDto : albumDtos) {
            Album album = new Album();
            album.setExternalId(aDto.id());
            album.setTitle(aDto.title());

            // Look up parent User by externalId
            userRepository.findByExternalId(aDto.userId()).ifPresent(album::setUser);

            albumRepository.save(album);
        }

        // ==========================================
        // 5. SYNC PHOTOS (Depends on Album)
        // ==========================================
        List<PhotoDto> photoDtos = fetchPhotosFromWeb();
        for (PhotoDto phDto : photoDtos) {
            Photo photo = new Photo();
            photo.setExternalId(phDto.id());
            photo.setTitle(phDto.title());
            photo.setUrl(phDto.url());
            photo.setThumbnailUrl(phDto.thumbnailUrl());

            // Look up parent Album by externalId
            albumRepository.findByExternalId(phDto.albumId()).ifPresent(photo::setAlbum);

            photoRepository.save(photo);
        }

        // ==========================================
        // 6. SYNC TODOS (Depends on User)
        // ==========================================
        List<TodoDto> todoDtos = fetchTodosFromWeb();
        for (TodoDto tDto : todoDtos) {
            Todo todo = new Todo();
            todo.setExternalId(tDto.id());
            todo.setTitle(tDto.title());
            todo.setCompleted(tDto.completed());

            // Look up parent User by externalId
            userRepository.findByExternalId(tDto.userId()).ifPresent(todo::setUser);

            todoRepository.save(todo);
        }
    }

    @Scheduled(cron = "0 0 10 * * *", zone = "Asia/Kolkata")
    public void runDailySync() {
        System.out.println("Cron triggered: Starting daily 10 AM database sync...");
        try {
            this.syncAndSaveToPostgres();
            System.out.println("Daily sync completed successfully.");
        } catch (Exception e) {
            System.err.println("Daily sync failed: " + e.getMessage());
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runSyncOnStartup() {
        // Only seed data if the database is empty — prevents duplicate inserts on every restart
        long userCount = userRepository.count();
        if (userCount == 0) {
            System.out.println("🚀 Database is empty. Triggering baseline data synchronization from JSONPlaceholder API...");
            try {
                this.syncAndSaveToPostgres();
                System.out.println("✅ Baseline startup synchronization complete! All tables populated.");
            } catch (Exception e) {
                System.err.println("❌ Startup synchronization failed: " + e.getMessage());
            }
        } else {
            System.out.println("ℹ️ Database already has " + userCount + " users. Skipping startup sync.");
        }
    }
}
