package com.example.demo.repository;

import com.example.demo.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteReponsitory extends JpaRepository<Favorite, Integer> {
}
