package com.example.demo.servies.impl;

import com.example.demo.entity.Movie;
import com.example.demo.entity.model.enums.MovieType;
import com.example.demo.repository.MovieRepository;
import com.example.demo.servies.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;
    @Override
    public List<Movie> findByType(MovieType movieType) {
        return movieRepository.findByTypeAndStatusOrderByCreatedAtDesc(movieType, true);
    }
}
