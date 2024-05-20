package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.model.enums.MovieType;
import com.example.demo.servies.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/tin-tuc")
    public String danhSachBlog(Model model,
                               @RequestParam(required = false, defaultValue = "1") int page,
                               @RequestParam(required = false, defaultValue = "8") int pageSize) {
        Page<Blog> pageData = blogService.findByStatus(true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "danh-sach-blog";
    }

    @GetMapping("/tin-tuc/{id}/{slug}")
    public String blogById(Model model, @PathVariable int id, @PathVariable String slug) {
        Blog blog = blogService.findByIdAndSlugAndStatus(id, slug, true);
        List<Comment> comments = commentService.findByBlogId(id);
        model.addAttribute("comments", comments);
        model.addAttribute("blog", blog);
        return "blog-info";
    }

    @Autowired
    private MovieService movieService;


    @GetMapping("/")
    public String index(Model model) {
        List<Movie> listPhimBo = movieService.findByType(MovieType.PHIM_BO, true, 1, 6).getContent();
        List<Movie> listPhimLe = movieService.findByType(MovieType.PHIM_LE, true, 1, 6).getContent();
        List<Movie> listPhimChieuRap = movieService.findByType(MovieType.PHIM_CHIEU_RAP, true, 1, 6).getContent();
        List<Movie> listPhimHot = movieService.findByStatusOrderByRatingDesc(true).stream().limit(4).toList();
        List<Blog> listBlogHot = blogService.blogNew(true).stream().limit(4).toList();
        model.addAttribute("listPhimBo", listPhimBo);
        model.addAttribute("listPhimLe", listPhimLe);
        model.addAttribute("listPhimChieuRap", listPhimChieuRap);
        model.addAttribute("listPhimHot", listPhimHot);
        model.addAttribute("listBlogHot", listBlogHot);
        return "index";
    }

    @GetMapping("/phim-bo")
    public String phimBo(Model model,
                         @RequestParam(required = false, defaultValue = "1") int page,
                         @RequestParam(required = false, defaultValue = "12") int pageSize){
        Page<Movie> pageData = movieService.findByType(MovieType.PHIM_BO, true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "phim-bo";
    }

    @GetMapping("/phim-le")
    public String phimLe(Model model,
                         @RequestParam(required = false, defaultValue = "1") int page,
                         @RequestParam(required = false, defaultValue = "12") int pageSize){
        Page<Movie> pageData = movieService.findByType(MovieType.PHIM_LE, true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String phimChieuRap(Model model,
                               @RequestParam(required = false, defaultValue = "1") int page,
                               @RequestParam(required = false, defaultValue = "12") int pageSize){
        Page<Movie> pageData = movieService.findByType(MovieType.PHIM_CHIEU_RAP, true, page, pageSize);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "phim-chieu-rap";
    }


    @Autowired
    private EpisodeService episodeService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/phim/{id}/{slug}")
    public String thongTinPhim(Model model, @PathVariable int id, @PathVariable String slug) {
        Movie movie = movieService.findByIdAndSlug(id, slug);
        List<Episode> episode = episodeService.findById(id);
        List<Reviews> reviews = reviewService.findByMovieIdOrderByDateDesc(id);
        List<Movie> phimDeXuat = movieService.findByTypeAndStatusOrderByRatingAsc(movie.getType()).stream().limit(8).toList();
        model.addAttribute("phimDeXuat", phimDeXuat);
        model.addAttribute("movie", movie);
        model.addAttribute("episode", episode);
        model.addAttribute("reviews", reviews);
        return "thong-tin-phim";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }



    @GetMapping("/register")
    public String register() {
        return "register";
    }

}

