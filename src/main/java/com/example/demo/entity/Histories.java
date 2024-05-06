package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "histories")
public class Histories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto tăng id
    Integer id;
//    int userId;
//    int movieId;
//    int episiodeId;
    double duration;
    LocalDate createAt;
}
