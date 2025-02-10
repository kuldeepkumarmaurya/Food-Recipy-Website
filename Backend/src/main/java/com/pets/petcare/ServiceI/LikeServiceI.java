package com.pets.petcare.ServiceI;

import com.pets.petcare.DTO.LikeDTO;
import org.springframework.http.ResponseEntity;

public interface LikeServiceI {
    LikeDTO toggleLike(Long userId, Long postId);

    String getlikecount(Long postId);
}
