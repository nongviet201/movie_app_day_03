package com.example.demo.controller;


import com.example.demo.entity.Movie;
import com.example.demo.model.enums.MovieType;
import com.example.demo.repository.*;
import com.example.demo.servies.BlogService;
import com.example.demo.servies.EpisodeService;
import com.example.demo.servies.MovieService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/admin/movies")
@RequiredArgsConstructor
public class MovieController {
    private final CountryReponsitory countryReponsitory;
    private final DirectorReponsitory directorReponsitory;
    private final ActorReponsitory actorReponsitory;
    private final GenreReponsitory genreReponsitory;
    private final MovieService movieService;
    private final EpisodeReponsitory episodeReponsitory;


    @GetMapping("/create")
    public String getCreatePage(Model model) {
        // tra ve danh sach quoc gia, dao dien, the loai, loai phim
        model.addAttribute("countries", countryReponsitory.findAll());
        model.addAttribute("directors", directorReponsitory.findAll());
        model.addAttribute("actors", actorReponsitory.findAll());
        model.addAttribute("genres", genreReponsitory.findAll());
        model.addAttribute("movieTypes", MovieType.values());
        return "admin/movies/create";
    }

    @GetMapping("")
    public String indexPage(Model model) {
        model.addAttribute("movies", movieService.getAllMovie());
        return "admin/movies/index";
    }

    @Autowired
    EpisodeService episodeService;

    @GetMapping("/{id}")
    public String getDetailPage(Model model, @PathVariable Integer id) {
        Optional<Movie> movieOptional = (Optional<Movie>) movieService.findById(id);
        if (movieOptional.isPresent()) {
            model.addAttribute("movie", movieOptional.get());
            model.addAttribute("countries", countryReponsitory.findAll());
            model.addAttribute("directors", directorReponsitory.findAll());
            model.addAttribute("actors", actorReponsitory.findAll());
            model.addAttribute("genres", genreReponsitory.findAll());
            model.addAttribute("movieTypes", MovieType.values());
            model.addAttribute("episodes", episodeService.findByMovie_IdOrderByDisplayOrderAsc(id));
            return "admin/movies/detail";
        } else {
            return "Movie with ID " + id + " not found";
        }
    }
}
