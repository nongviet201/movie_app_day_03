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
    public String findByType(Model model){
        model.addAttribute("movies", movieService.findByType(MovieType.PHIM_BO));
        return "phim-bo";
    }

}
