package com.example.demo.repository;

import com.example.demo.entity.Movie;
import com.example.demo.entity.model.enums.MovieType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByName(String name);
    List<Movie> findByNameIgnoreCase(String name);
    List<Movie> findByNameContaining(String name);
    List<Movie> findByNameAndSlug(String name, String slug);
    List<Movie> findByRatingBetween(double min, double max);
//    List<Movies> findByCreateAtAfter(LocalDate createAt);
    List<Movie> findByTypeOrderByRatingDesc(MovieType type);


    // tim phim de xuất cung the loai
    List<Movie> findByTypeAndStatusAndOrderByCreatedAtDesc(MovieType type, boolean status);

    List<Movie> findByType(MovieType type, Sort sort);

    List<Movie> findByStatusOrderByRatingDesc(boolean status);


    List<Movie> findByTypeAndStatusOrderByCreatedAtDesc(MovieType type, boolean status);

    Movie findByIdAndSlug(int id, String slug);

    // kiem tra ton tai
    boolean existsByName(String name);

    //phân trang
    Page<Movie> findByStatus(Boolean status, Pageable pageable);

    List<Movie> findByTypeAndStatus(MovieType movieType ,Boolean status, Sort sort);

    Page<Movie> findByTypeAndStatus(MovieType movieType, Boolean status, Pageable pageable);

}
