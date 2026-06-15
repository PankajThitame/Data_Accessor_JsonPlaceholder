package com.example.task2_fetch_json_data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "photos")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Photo {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    private String title;
    private String url;
    private String thumbnailUrl;

    // Constructors, Getters, Setters
}