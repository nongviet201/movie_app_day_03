package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "gendes")
public class Gendes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto tăng id
    Integer id;
    String name;
    String slug;

}
