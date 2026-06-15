package com.example.task2_fetch_json_data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class User {
    @Id
    private Long id;

    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;

    @Embedded
    private Address address;

    @Embedded
    private Company company;

    // CascadeType.ALL deletes all posts, albums, and todos if a user is deleted
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Album> albums;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Todo> todos;

    // Constructors, Getters, Setters
}