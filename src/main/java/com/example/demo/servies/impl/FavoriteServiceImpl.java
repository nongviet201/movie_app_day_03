package com.example.demo.servies.impl;

import com.example.demo.entity.Favorite;
import com.example.demo.entity.Movie;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.request.AddFavoriteRequest;
import com.example.demo.repository.FavoriteReponsitory;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.UserReponsitory;
import com.example.demo.servies.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteReponsitory favoriteReponsitory;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    UserReponsitory userReponsitory;

    @Override
    public List<Favorite> findByUserIdOrderByCreateAtDesc(Integer userId) {
        return favoriteReponsitory.findByUserIdOrderByCreateAtDesc(userId);
    }

    @Override
    public void createFavorite(AddFavoriteRequest request) {
        // kiểm tra xem phim đã có trong danh sách chưa
        Favorite existingFavorite = favoriteReponsitory.findByUser_IdAndMovie_Id(request.getUserId(), request.getMovieId());
        if (existingFavorite != null) {
            throw new BadRequestException("Phim đã tồn tại trong danh sách yêu thích");
        }

        User user = userReponsitory.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        Favorite favorite = Favorite.builder()
                .createAt(LocalDate.now())
                .updateAt(LocalDate.now())
                .user(user)
                .movie(movie)
                .build();

        favoriteReponsitory.save(favorite);
    }

    @Override
    public Favorite getFavorite(Integer userId, Integer movieId) {
        return favoriteReponsitory.findByUser_IdAndMovie_Id(userId, movieId);
    }

    @Override
    public void deleteFavorite(int id) {
        Favorite favorite = favoriteReponsitory.findById(id);
        favoriteReponsitory.delete(favorite);
    }

    @Override
    public Favorite findByUserIdAndMovieId(Integer userId, Integer movieId) {
        return favoriteReponsitory.findByUser_IdAndMovie_Id(userId, movieId);
    }
}
