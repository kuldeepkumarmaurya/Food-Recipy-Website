package com.pets.petcare.ServiceI;

import com.pets.petcare.DTO.CommentResponceDto;
import com.pets.petcare.Entity.CommentEntity;

import java.util.List;

public interface CommentServiceI {
    CommentEntity addComment(Long postId, Long userId, String commentText);

    List<CommentResponceDto> getCommentsByPostId(Long postId);

    void deleteComment(Long commentId);
}
