package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "directors")
public class Directors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto tăng id
    Integer id;
    String name;
    String avatar;
    String bio;
}
