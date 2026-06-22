package com.example.task2_fetch_json_data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid_id", updatable = false, nullable = false)
    private java.util.UUID uuidId; // 🔑 New Primary Key

    @Column(name = "external_id")
    private Long externalId; // 🌐 Old post ID

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "uuid_id")
    private User user;

    private String title;
    private boolean completed;

    // Constructors, Getters, Setters
}