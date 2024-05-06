package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "contries")
public class Countries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto tăng id
    Integer id;
    String name;
    String slug;
}
