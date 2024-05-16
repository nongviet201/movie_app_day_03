package com.example.demo.servies;

import com.example.demo.entity.Blog;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogService {
    Page<Blog> findByStatus(boolean status, int page, int size);
    Blog findByIdAndSlugAndStatus(int id, String slug, boolean status);
    List<Blog> blogNew(boolean status);

}
