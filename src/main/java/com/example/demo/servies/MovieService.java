package com.example.demo.servies;

import com.example.demo.entity.Movies;
import com.example.demo.entity.model.enums.MovieType;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MovieService {
    List<Movies> findByType(MovieType movieType);

}
