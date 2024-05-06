package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto tăng id
    Integer id;
    @Column(nullable = false)
    String content;
    int userId;
    int blogId;
    LocalDate createdAt;
    LocalDate datetime;
}
