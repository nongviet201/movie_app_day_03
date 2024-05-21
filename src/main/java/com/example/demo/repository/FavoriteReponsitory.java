package com.example.demo.repository;

import com.example.demo.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteReponsitory extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUserIdOrderByCreateAtDesc(Integer userId);

    Favorite findByUser_IdAndMovie_Id(Integer userId, Integer movieId);
}
