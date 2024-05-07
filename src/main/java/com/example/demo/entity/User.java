package com.example.demo.entity;

import com.example.demo.entity.model.enums.UserRole;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto tăng id
    Integer id;

    @Column(nullable = false)
    String username;
    @Column(nullable = false)
    String password;
    String avatar;
    String email;
    @Enumerated(EnumType.STRING)
    UserRole role;
    LocalDate createAt;
    LocalDate updateAt;
}
