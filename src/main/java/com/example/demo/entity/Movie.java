package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto tăng id
    Integer id;
    @Column(nullable = false)
    String name;
    String slug;
    @Column(nullable = false)
    String description;
    String poster;
    int release_year;
    double rating;
    @Enumerated(EnumType.STRING)
    MovieType type;
    @Column(nullable = false)
    boolean status;
    String trailer;
    int countryId;
    LocalDate createdAt;
    LocalDate datetime;
}
