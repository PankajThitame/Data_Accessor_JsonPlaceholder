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

        List<UserDto> userDtos = fetchUsersFromWeb();
        for (UserDto uDto : userDtos) {
            User user = modelMapper.map(uDto, User.class);
            user.setId(uDto.id()); // Manual fallback assignment for Record mapping safety

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
            Post post = modelMapper.map(pDto, Post.class);
            post.setId(pDto.id());

            // Establish Relational Mapping back to the Parent User
            userRepository.findById(pDto.userId()).ifPresent(post::setUser);

            postRepository.save(post);
        }

        // ==========================================
        // 3. SYNC COMMENTS (Depends on Post)
        // ==========================================
        List<CommentDto> commentDtos = fetchCommentsFromWeb();
        for (CommentDto cDto : commentDtos) {
            Comment comment = modelMapper.map(cDto, Comment.class);
            comment.setId(cDto.id());

            // Establish Relational Mapping back to the Parent Post
            postRepository.findById(cDto.postId()).ifPresent(comment::setPost);

            commentRepository.save(comment);
        }

        // ==========================================
        // 4. SYNC ALBUMS (Depends on User)
        // ==========================================
        List<AlbumDto> albumDtos = fetchAlbumsFromWeb();
        for (AlbumDto aDto : albumDtos) {
            Album album = modelMapper.map(aDto, Album.class);
            album.setId(aDto.id());

            // Establish Relational Mapping back to the Parent User
            userRepository.findById(aDto.userId()).ifPresent(album::setUser);

            albumRepository.save(album);
        }

        // ==========================================
        // 5. SYNC PHOTOS (Depends on Album)
        // ==========================================
        List<PhotoDto> photoDtos = fetchPhotosFromWeb();
        for (PhotoDto phDto : photoDtos) {
            Photo photo = modelMapper.map(phDto, Photo.class);
            photo.setId(phDto.id());

            // Establish Relational Mapping back to the Parent Album
            albumRepository.findById(phDto.albumId()).ifPresent(photo::setAlbum);

            photoRepository.save(photo);
        }

        // ==========================================
        // 6. SYNC TODOS (Depends on User)
        // ==========================================
        List<TodoDto> todoDtos = fetchTodosFromWeb();
        for (TodoDto tDto : todoDtos) {
            Todo todo = modelMapper.map(tDto, Todo.class);
            todo.setId(tDto.id());
            todo.setCompleted(tDto.completed()); // Ensure boolean primitive mapping is explicitly set

            // Establish Relational Mapping back to the Parent User
            userRepository.findById(tDto.userId()).ifPresent(todo::setUser);

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

//    @EventListener(ApplicationReadyEvent.class)
//    public void runSyncOnStartup() {
//        System.out.println("🚀 App Initialised: Triggering baseline database synchronization...");
//        try {
//            this.syncAndSaveToPostgres();
//            System.out.println("✅ Baseline startup synchronization complete!");
//        } catch (Exception e) {
//            System.err.println("❌ Startup synchronization failed: " + e.getMessage());
//        }
//    }
}
