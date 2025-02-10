package com.pets.petcare.repository;

import com.pets.petcare.Entity.SavedRecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SavedRecipeRepository extends JpaRepository<SavedRecipeEntity, Long> {
    List<SavedRecipeEntity> findByUserId(Long userId);

    // Method to find a saved recipe by userId and recipeId
    Optional<SavedRecipeEntity> findByUserIdAndRecipeId(Long userId, Long recipeId);
}
