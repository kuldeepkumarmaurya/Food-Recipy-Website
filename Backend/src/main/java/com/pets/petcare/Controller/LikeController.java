package com.pets.petcare.Controller;

import com.pets.petcare.DTO.LikeDTO;
import com.pets.petcare.ServiceI.LikeServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikeServiceI likeService;

    @PostMapping("/toggle/{userId}/{postId}")
    public ResponseEntity<?> toggleLike(@PathVariable Long userId, @PathVariable Long postId) {
        LikeDTO response = likeService.toggleLike(userId, postId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getLikeCount/{postId}")
    public String grtlikecount(@PathVariable Long postId){
        return likeService.getlikecount(postId);
    }
}

