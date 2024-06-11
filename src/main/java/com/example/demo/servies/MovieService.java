package com.example.demo.servies;

import com.example.demo.entity.Movie;
import com.example.demo.model.enums.MovieType;
import com.example.demo.model.request.UpsertMovieRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieService {
    List<Movie> findByTypeAndStatusOrderByRatingAsc(MovieType movieType);

    Page<Movie> findByType(MovieType movieType, boolean status, int page, int size);

    List<Movie> findByStatusOrderByRatingDesc(boolean status);

    Movie findByIdAndSlug(int id, String slug);

    Movie createMovie(UpsertMovieRequest request);

    Movie updateMovie(Integer id, UpsertMovieRequest request);

    void deleteMovie(Integer id);

    Movie getMovie(Integer id);

    Object uploadThumbnail(Integer id, MultipartFile file);

    Object getAllMovie();

    Object findById(int id);

//    List<Movie> findByTypeAndStatusAndOrderByCreateAtDesc(MovieType movieType, boolean status);
}
