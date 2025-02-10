package com.pets.petcare.repository;

import com.pets.petcare.Entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    Optional<LikeEntity> findByUserIdAndPostId(Long userId, Long postId);
}
