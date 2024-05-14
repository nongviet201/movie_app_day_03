package com.example.demo.servies.impl;

import com.example.demo.entity.Movie;
import com.example.demo.entity.model.enums.MovieType;
import com.example.demo.repository.MovieRepository;
import com.example.demo.servies.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;
    @Override
    public List<Movie> findByTypeAndStatusOrderByRatingAsc(MovieType movieType) {
        return movieRepository.findByTypeAndStatusOrderByRatingAsc(movieType, true);
    }

    @Override
    public Page<Movie> findByType(MovieType movieType, boolean status , int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return movieRepository.findByTypeAndStatus(movieType ,status, pageRequest);
    }

    @Override
    public List<Movie> findByStatusOrderByRatingDesc(boolean status) {
        return movieRepository.findByStatusOrderByRatingDesc(status);
    }

    @Override
    public Movie findByIdAndSlug(int id, String slug) {
        return movieRepository.findByIdAndSlug(id, slug);
    }

//    @Override
//    public List<Movie> findByTypeAndStatusAndOrderByCreateAtDesc(MovieType movieType, boolean status) {
//        return movieRepository.findByTypeAndStatusAndOrderByCreatedAtDesc(movieType, status);
//    }

}
