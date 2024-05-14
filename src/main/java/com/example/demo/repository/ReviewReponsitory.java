package com.example.demo.repository;

import com.example.demo.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewReponsitory extends JpaRepository<Reviews, Integer> {
    List<Reviews> findByMovieIdOrderByCreateAtDesc(Integer movieId);
}
