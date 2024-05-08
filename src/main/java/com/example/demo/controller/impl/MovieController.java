package com.example.demo.controller.impl;

import com.example.demo.entity.model.enums.MovieType;
import com.example.demo.servies.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/phim-bo")
    public String phimBo(Model model){
        model.addAttribute("movies", movieService.findByType(MovieType.PHIM_BO));
        return "phim-bo";
    }

    @GetMapping("/phim-le")
    public String phimLe(Model model){
        model.addAttribute("movies", movieService.findByType(MovieType.PHIM_LE));
        return "phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String phimChieuRap(Model model){
        model.addAttribute("movies", movieService.findByType(MovieType.PHIM_CHIEU_RAP));
        return "phim-chieu-rap";
    }

}
