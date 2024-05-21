package com.example.demo.servies;

import com.example.demo.entity.Favorite;
import com.example.demo.model.request.AddFavoriteRequest;

import java.util.List;

public interface FavoriteService {
    List<Favorite> findByUserIdOrderByCreateAtDesc(Integer userId);

    void createFavorite(AddFavoriteRequest request);

    Favorite getFavorite(AddFavoriteRequest request);

    void deleteFavorite(AddFavoriteRequest request);

    Favorite findByUserIdAndMovieId(Integer userId, Integer movieId);
}
