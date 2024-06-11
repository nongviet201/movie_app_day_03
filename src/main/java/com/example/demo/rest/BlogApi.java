package com.example.demo.rest;


import com.example.demo.entity.Blog;
import com.example.demo.entity.Comment;
import com.example.demo.model.request.UpsertBlogRequest;
import com.example.demo.servies.BlogService;
import com.example.demo.servies.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/admin/blogs") //Những api tra ve JSON thì đặt '/api' ở trước
public class BlogApi {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;

    //Tạo Blogs
    @PostMapping
    public ResponseEntity<?> createBlog(@Valid @RequestBody UpsertBlogRequest request){
        Blog blog = blogService.createBlog(request);
        return new ResponseEntity<>(blog, HttpStatus.CREATED); //201
    }

    //Cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBlog(@Valid @RequestBody UpsertBlogRequest request, @PathVariable Integer id){
        Blog blog = blogService.updateBlog(id, request);

        return ResponseEntity.ok(blog); //200
    }
    //Xóa
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(@Valid @PathVariable Integer id){
        List<Comment> comments = commentService.getCommentsByBlogId(id);
        if (!comments.isEmpty()) {
            commentService.deleteCommentsByBlogId(id);
        }
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build(); // Trả về mã trạng thái 204 khi xóa thành công
    }
    //Get
    @GetMapping("/{id}")
    public ResponseEntity<?> getBlog(@Valid @PathVariable Integer id){
        Blog blog = blogService.getBlog(id);
        return ResponseEntity.ok(blog); //200
    }

    @PostMapping("/{id}/upload-thumbnail")
    public ResponseEntity<?> uploadThumbnail(@PathVariable Integer id,
                                             @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(blogService.uploadThumbnail(id, file));
    }
}
