package com.example.demo.servies;

import com.example.demo.entity.Movie;
import com.example.demo.entity.model.enums.MovieType;

import java.util.List;

public interface MovieService {
    List<Movie> findByType(MovieType movieType);

}
