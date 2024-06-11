package com.example.demo.repository;

import com.example.demo.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EpisodeReponsitory extends JpaRepository<Episode, Integer> {
    List<Episode> findByMovieIdOrderByDisplayOrder(Integer movieId);

    List<Episode> findByMovie_IdAndMovie_StatusOrderByDisplayOrderAsc(Integer movieId, Boolean status);

    Optional<Episode> findByMovie_IdAndMovie_StatusAndDisplayOrder(Integer movieId, Boolean status, Integer displayOrder);
}
