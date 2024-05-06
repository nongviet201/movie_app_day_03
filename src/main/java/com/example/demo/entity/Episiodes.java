package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "episiodes")
public class Episiodes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto tăng id
    Integer id;
    String name;
    double duration;
    String VideoUrl;
    int order;
    int movieId;
    LocalDate createAt;
    LocalDate updateAt;
}
