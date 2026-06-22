package com.example.task2_fetch_json_data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid_id", updatable = false, nullable = false)
    private java.util.UUID uuidId; // 🔑 New Primary Key

    @Column(name = "external_id")
    private Long Id; // 🌐 Old post ID

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "uuid_id") // 🔗 Connects to user's UUID
    private User user;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    // Constructors, Getters, Setters
}