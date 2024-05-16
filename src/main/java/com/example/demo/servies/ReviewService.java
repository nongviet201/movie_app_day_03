package com.example.demo.servies;

import com.example.demo.entity.Reviews;
import com.example.demo.model.request.UpsertReviewRequest;

import java.util.List;

public interface ReviewService {
    List<Reviews> findByMovieIdOrderByDateDesc(Integer movieId);

    Reviews createReview(UpsertReviewRequest request);

    Reviews updateReview(Integer id, UpsertReviewRequest request);

    void deleteReview(Integer id);

    Reviews getReview(Integer id);
}
