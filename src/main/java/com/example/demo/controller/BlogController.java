package com.example.demo.controller;


import com.example.demo.servies.BlogService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/blogs")
public class BlogController {
    @Autowired
    BlogService blogService;


    @GetMapping
    public String getIndexPage(Model model) {
        model.addAttribute("blogs", blogService.getAllBlog());
        return "admin/blog/index";
    }

    @GetMapping("/own-blogs")
    public String getOwnBlogPage(Model model) {
        model.addAttribute("blogs", blogService.getAllBlogByUserId());
        return "admin/blog/own";
    }

    @GetMapping("/create")
    public String getCreatePage() {
        return "admin/blog/create";
    }

    @GetMapping("/{id}")
    public String getDetailPage(Model model, @PathVariable Integer id) {
        model.addAttribute("blog", blogService.findById(id));
        return "admin/blog/detail";
    }


}
