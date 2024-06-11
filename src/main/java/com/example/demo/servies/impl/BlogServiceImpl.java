package com.example.demo.servies.impl;

import com.example.demo.entity.Blog;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.request.UpsertBlogRequest;
import com.example.demo.repository.BlogRepository;
import com.example.demo.servies.BlogService;
import com.example.demo.servies.FavoriteService;
import com.example.demo.servies.FileService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private HttpSession session;
    @Autowired
    private FileService fileService;


    @Override
    public Page<Blog> findByStatus(boolean status, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return blogRepository.findByStatus(status, pageRequest);
    }

    @Override
    public Blog findByIdAndSlugAndStatus(int id, String slug, boolean status) {
        return blogRepository.findByIdAndSlugAndStatus(id, slug, status);
    }

    @Override
    public List<Blog> blogNew(boolean status) {
        return blogRepository.findByStatusOrderByCreatedAtDesc(status);
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogRepository.findAll(Sort.by("createdAt").descending());
    }

    @Override
    public List<Blog> getAllBlogByUserId() {
        //TODO: Sử dụng security context holder để lấy tt người dùng ra
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication;
        return blogRepository.findByUser_IdOrderByCreatedAtDesc(user.getId());
    }

    @Override
    public Blog findById(Integer id) {
        return blogRepository.findById(id).get();
    }

    @Override
    public Blog createBlog(UpsertBlogRequest request) {
        //TODO: Sử dụng security context holder để lấy tt người dùng ra
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication;

        Blog blog = Blog.builder()
                .title(request.getTitle())
                .slug(request.getTitle())
                .description(request.getContent())
                .content(request.getContent())
                .thumbnail("alo alo")
                .status(request.isStatus())
                .createdAt(LocalDate.now())
                .updateAt(LocalDate.now())
                .build();

        blogRepository.save(blog);
        return blog;
    }

    @Override
    public Blog updateBlog(Integer id, UpsertBlogRequest request) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        //TODO: Sử dụng security context holder để lấy tt người dùng ra
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication;

        if (!blog.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("User not authorized");
        }

        blog.setTitle(request.getTitle());
        blog.setContent(request.getContent());
        blog.setDescription(request.getDescription());
        blog.setStatus(request.isStatus());
        blog.setUpdateAt(LocalDate.now());
        blogRepository.save(blog);

        return blog;
    }

    @Override
    public void deleteBlog(Integer id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        //TODO: Sử dụng security context holder để lấy tt người dùng ra
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication;

        if (!blog.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("User not authorized");
        }

        blogRepository.delete(blog);
    }

    @Override
    public Blog getBlog(Integer id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        //TODO: Sử dụng security context holder để lấy tt người dùng ra
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication;

        if (!blog.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("User not authorized");
        }

        return blog;
    }

    @Override
    public String uploadThumbnail(Integer id, MultipartFile file) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found"));
        try {
            Map data = fileService.uploadImage(file);
            String url = (String) data.get("url");
            blog.setThumbnail(url);
            blogRepository.save(blog);

            return url;
        } catch (IOException e) {
            throw new RuntimeException("Error uploading file");
        }
    }
}
