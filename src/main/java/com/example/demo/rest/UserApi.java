package com.example.demo.rest;

import com.example.demo.entity.Favorite;
import com.example.demo.model.request.AddFavoriteRequest;
import com.example.demo.model.request.UpdateUserPasswordRequest;
import com.example.demo.model.request.UpdateUserRequest;
import com.example.demo.servies.FavoriteService;
import com.example.demo.servies.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/user")
@RequiredArgsConstructor
public class UserApi {
    @Autowired
    private UserService userService;
    @Autowired
    private FavoriteService favoriteService;

    // Sửa thông tin user
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserRequest request) {
        userService.updateUser(request);
        return ResponseEntity.ok("update successfully");
    }

    // Sửa password user
    @PutMapping("/update/password")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody UpdateUserPasswordRequest request) {
        userService.updatePassword(request);
        return ResponseEntity.ok("update successfully");
    }

    // Thêm phim vào danh sách yeu thích
    @PostMapping("/favorite/add")
    public ResponseEntity<?> addFavorite(@Valid @RequestBody AddFavoriteRequest request) {
        favoriteService.createFavorite(request);
        return ResponseEntity.ok("add favorite successfully");
    }

//     Xóa khỏi danh sách yêu thích
    @DeleteMapping("/favorite/del/{id}")
    public ResponseEntity<String> delFavorite(@Valid @PathVariable Integer id) {
        favoriteService.deleteFavorite(id);
        return ResponseEntity.ok("delete successfully");
    }

    @GetMapping("/favorite/get/{userId}/{movieId}")
    public ResponseEntity<?> getFavorite(@Valid @PathVariable Integer userId, @Valid @PathVariable Integer movieId) {
        Favorite favorite = favoriteService.getFavorite(userId, movieId);
        return ResponseEntity.ok(favorite);
    }
}
