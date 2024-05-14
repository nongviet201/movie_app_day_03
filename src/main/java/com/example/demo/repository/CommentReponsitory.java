package com.example.demo.repository;

import com.example.demo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentReponsitory extends JpaRepository<Comment, Integer> {
    List<Comment> findByBlogId(Integer blogId);
}
