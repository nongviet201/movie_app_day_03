package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/dashboard")
public class DashBoardController {

    @GetMapping
    public String getDashBoardPage(Model model) {
        return "/admin/dashboard/dashboard";
    }
}
