package com.example.demo.servies;

import com.example.demo.entity.Reviews;

import java.util.List;

public interface ReviewService {
    List<Reviews> findByMovieIdOrderByDateDesc(Integer movieId);
}
