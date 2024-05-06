package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "favorites")
public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto tăng id
    Integer id;
//    int userId;
//    int movieId;
    LocalDate createAt;
}
