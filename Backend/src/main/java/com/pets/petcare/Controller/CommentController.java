package com.pets.petcare.Controller;

import com.pets.petcare.DTO.CommentResponceDto;
import com.pets.petcare.Entity.CommentEntity;
import com.pets.petcare.ServiceI.CommentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentServiceI commentService;

    // API to add a comment to a post
    @PostMapping("/add")
    public ResponseEntity<CommentEntity> addComment(@RequestParam Long postId,
                                                    @RequestParam Long userId,
                                                    @RequestParam String commentText) {
        CommentEntity comment = commentService.addComment(postId, userId, commentText);
        return ResponseEntity.ok(comment);
    }

    // API to get all comments for a post
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponceDto>> getCommentsByPost(@PathVariable Long postId) {
        List<CommentResponceDto> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    // API to soft delete a comment
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}
