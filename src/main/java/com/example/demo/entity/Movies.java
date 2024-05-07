package com.example.demo.entity;

import com.example.demo.entity.model.enums.MovieType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table (name = "movies")
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto tăng id
    Integer id;
    @Column(nullable = false)
    String name;
    String slug;
    @Column(columnDefinition = "TEXT")
    String description;
    String poster;
    int releaseYear;
    double rating;
    @Enumerated(EnumType.STRING)
    MovieType type;
    boolean status;
    String trailer;
    LocalDate createdAt;
    LocalDate updateAt;
}
