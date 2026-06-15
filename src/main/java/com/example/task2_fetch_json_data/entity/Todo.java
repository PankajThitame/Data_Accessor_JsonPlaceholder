package com.example.task2_fetch_json_data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "todos")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    private boolean completed;

    // Constructors, Getters, Setters
}