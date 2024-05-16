package com.example.demo.servies.impl;

import com.example.demo.entity.Blog;
import com.example.demo.repository.BlogRepository;
import com.example.demo.servies.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;


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


}
