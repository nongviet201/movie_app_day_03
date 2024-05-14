package com.example.demo.servies;

import com.example.demo.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findByBlogId(Integer blogId);
}
