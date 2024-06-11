package com.example.demo.servies.impl;

import com.example.demo.entity.Comment;
import com.example.demo.repository.CommentReponsitory;
import com.example.demo.servies.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentReponsitory commentReponsitory;
    @Override
    public List<Comment> findByBlogId(Integer blogId) {
        return commentReponsitory.findByBlogId(blogId);
    }

    @Override
    public List<Comment> getCommentsByBlogId(Integer id) {
        return commentReponsitory.findByBlogId(id);
    }

    @Override
    public void deleteCommentsByBlogId(Integer id) {
        commentReponsitory.deleteAll(commentReponsitory.findByBlogId(id));
    }
}
