package com.example.demo.repository;

import com.example.demo.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeReponsitory extends JpaRepository<Episode, Integer> {
    List<Episode> findByMovieIdOrderByDisplayOrder(Integer movieId);
}
