package com.example.task2_fetch_json_data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.UUID) // Tells Hibernate to handle UUID generation automatically
    @Column(name = "uuid_id", updatable = false, nullable = false)
    private java.util.UUID uuidId; // 🔑 Changed from 'id' to 'uuidId'

    @Column(name = "external_id")
    private Long externalId; // 🌐 Stores the old JSONPlaceholder ID

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
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Album> albums;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Todo> todos;

    // Constructors, Getters, Setters
}