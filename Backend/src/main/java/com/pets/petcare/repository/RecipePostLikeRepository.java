package com.pets.petcare.repository;

import com.pets.petcare.Entity.RecipePostLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipePostLikeRepository extends JpaRepository<RecipePostLikeEntity, Long> {
    Optional<RecipePostLikeEntity> findByPostId(Long postId);
}
