package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reviews")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto tăng id
    Integer id;
    String content;
    double rating;
    int userId;
    int movieId;
    LocalDate createAt;
    LocalDate updateAt;
}
