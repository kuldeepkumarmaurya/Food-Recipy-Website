package com.pets.petcare.ServiceI;

import com.pets.petcare.DTO.RecipePostDTO;
import com.pets.petcare.DTO.RecipeSearchResponseDTO;
import com.pets.petcare.Entity.RecipePostEntity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface RecipePostServiceI {
    String createRecipePost(RecipePostDTO recipePostDTO, Long userId) throws IOException;
    Optional<RecipePostEntity> getRecipeById(Long id);
    List<RecipeSearchResponseDTO> searchRecipesByName(String name);
    List<RecipeSearchResponseDTO> searchRecipesByIngredient(String ingredient);

    List<RecipeSearchResponseDTO> findAllByCategory(String category);
    List<RecipeSearchResponseDTO> findAllByType(String type);

    List<RecipePostEntity> getFeed();

    List<RecipeSearchResponseDTO> getAll();

    List<RecipeSearchResponseDTO> getAllByUser(String userId);
}
