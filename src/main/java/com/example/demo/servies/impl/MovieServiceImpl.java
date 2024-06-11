package com.example.demo.servies.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.enums.MovieType;
import com.example.demo.model.request.UpsertMovieRequest;
import com.example.demo.repository.*;
import com.example.demo.servies.FileService;
import com.example.demo.servies.MovieService;
import com.github.slugify.Slugify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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



    // MOVIE API
    @Autowired
    DirectorReponsitory directorReponsitory;
    @Autowired
    ActorReponsitory actorReponsitory;
    @Autowired
    GenreReponsitory genreReponsitory;
    @Autowired
    CountryReponsitory countryReponsitory;

    @Override
    public Movie createMovie(UpsertMovieRequest request) {
        Optional<Country> country = countryReponsitory.findById(request.getCountryId());
        List<Genre> genres = genreReponsitory.findAllById(request.getGenreIds());
        List<Actor> actors = actorReponsitory.findAllById(request.getActorIds());
        List<Director> directors = directorReponsitory.findAllById(request.getDirectorIds());
        MovieType movieType = MovieType.valueOf(String.valueOf(request.getMovieType()));

        Movie movie = Movie.builder()
                .name(request.getMovieName())
                .slug(request.getMovieName())
                .description(request.getDescription())
                .releaseYear(request.getReleaseYear())
                .rating(0)
                .type(movieType)
                .status(request.isStatus())
                .trailer(request.getTrailer())
                .createdAt(LocalDate.now())
                .updateAt(LocalDate.now())
                .country(country.get())
                .genres(genres)
                .actors(actors)
                .directors(directors)
                .build();

        movieRepository.save(movie);
        return movie;
    }

    @Override
    public Movie updateMovie(Integer id, UpsertMovieRequest request) {
//        Movie movie = movieRepository.findById(id)
//                .orElseThrow(()-> new ResourceNotFoundException("Movie not found"));
//
//        Slugify slugify = Slugify.builder().build();
//        movie.setName(request.getMovieName());
//        movie.setSlug(slugify.slugify(request.getMovieName()));
//        movie.setDescription(request.getDescription());
//        movie.setReleaseYear(request.getReleaseYear());
//        movie.setType(request.getMovieType());
//        movie.setStatus(request.isStatus());
//        movie.setTrailer(request.getTrailer());
//        movie.setUpdateAt(LocalDate.from(LocalDateTime.now()));
//        movie.setActors(actorService.getActorsById(request.getActorIds()));
//        movie.setDirectors(directorService.getDirectorsById(request.getDirectorIds()));
//        movie.setGenres(genreService.getGenresById(request.getGenreIds()));
//        movie.setCountry(countryService.getCountryById(request.getCountryId()));
//        movieRepository.save(movie);
//        return movie;
        return null;
    }

    @Override
    public void deleteMovie(Integer id) {

    }

    @Override
    public Movie getMovie(Integer id) {
        return null;
    }

    @Autowired
    FileService fileService;

    @Override
    public Object uploadThumbnail(Integer id, MultipartFile file) {
        try {
            Map data = fileService.uploadImage(file);
            String url = (String) data.get("url");
            return url;
        } catch (IOException e) {
            throw new RuntimeException("Error uploading file");
        }
    }

    @Override
    public Object getAllMovie() {
        return movieRepository.findAll();
    }

    @Override
    public Object findById(int id) {
        return movieRepository.findById(id);
    }

//    @Override
//    public List<Movie> findByTypeAndStatusAndOrderByCreateAtDesc(MovieType movieType, boolean status) {
//        return movieRepository.findByTypeAndStatusAndOrderByCreatedAtDesc(movieType, status);
//    }

}
