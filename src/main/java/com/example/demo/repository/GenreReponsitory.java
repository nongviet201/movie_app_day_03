package com.example.demo.repository;

import com.example.demo.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreReponsitory extends JpaRepository<Genre, Integer> {
}
