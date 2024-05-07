package com.example.demo.repository;

import com.example.demo.entity.Movies;
import com.example.demo.entity.model.enums.MovieType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movies, Integer> {
    List<Movies> findByName(String name);
    List<Movies> findByNameIgnoreCase(String name);
    List<Movies> findByNameContaining(String name);
    List<Movies> findByNameAndSlug(String name, String slug);
    List<Movies> findByRatingBetween(double min, double max);
//    List<Movies> findByCreateAtAfter(LocalDate createAt);
    List<Movies> findByTypeOrderByRatingDesc(MovieType type);
    List<Movies> findByType(MovieType type, Sort sort);


    List<Movies> findByTypeAndStatusOrderByCreatedAtDesc(MovieType type, boolean status);


    Movies findFirstByTypeOrderByRatingDesc(MovieType type);

    long countByStatus(Boolean status);
    // kiem tra ton tai
    boolean existsByName(String name);

    //phân trang
    Page<Movies> findByStatus(Boolean status, Pageable pageable);


}
