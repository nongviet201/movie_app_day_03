package com.example.demo.servies;

import com.example.demo.entity.Movie;
import com.example.demo.entity.model.enums.MovieType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieService {
    List<Movie> findByType(MovieType movieType);

    Page<Movie> findByType(MovieType movieType, boolean status, int page, int size);

    List<Movie> findByStatusOrderByRatingDesc(boolean status);

    Movie findByIdAndSlug(int id, String slug);
}
