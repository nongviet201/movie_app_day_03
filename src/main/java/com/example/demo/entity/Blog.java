package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto tăng id
    Integer id;
    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String slug;
    @Column(nullable = false)
    String description;
    String content;
    String thumbnail;
    String status;
    @Column(nullable = false)
    String userId;
    LocalDate createdAt;
    LocalDate datetime;
}
