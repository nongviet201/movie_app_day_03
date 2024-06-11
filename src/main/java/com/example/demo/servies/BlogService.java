package com.example.demo.servies;

import com.example.demo.entity.Blog;
import com.example.demo.model.request.UpsertBlogRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlogService {
    Page<Blog> findByStatus(boolean status, int page, int size);
    Blog findByIdAndSlugAndStatus(int id, String slug, boolean status);
    List<Blog> blogNew(boolean status);

    List<Blog> getAllBlog();

    List<Blog> getAllBlogByUserId();

    Blog findById(Integer id);

    Blog createBlog(UpsertBlogRequest request);

    Blog updateBlog(Integer id, UpsertBlogRequest request);

    void deleteBlog(Integer id);

    Blog getBlog(Integer id);


    public String uploadThumbnail(Integer id, MultipartFile file);
}
