package com.example.demo.servies.impl;

import com.example.demo.entity.Reviews;
import com.example.demo.repository.ReviewReponsitory;
import com.example.demo.servies.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewReponsitory reviewReponsitory;

    @Override
    public List<Reviews> findByMovieIdOrderByDateDesc(Integer movieId) {
        return reviewReponsitory.findByMovieIdOrderByCreateAtDesc(movieId);
    }
}
