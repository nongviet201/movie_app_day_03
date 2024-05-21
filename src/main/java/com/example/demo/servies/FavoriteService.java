package com.example.demo.servies;

import com.example.demo.entity.Favorite;
import com.example.demo.model.request.AddFavoriteRequest;

import java.util.List;

public interface FavoriteService {
    List<Favorite> findByUserIdOrderByCreateAtDesc(Integer userId);

    void createFavorite(AddFavoriteRequest request);

    Favorite getFavorite(Integer userId, Integer favoriteId);

    void deleteFavorite(int id);

    Favorite findByUserIdAndMovieId(Integer userId, Integer movieId);
}
