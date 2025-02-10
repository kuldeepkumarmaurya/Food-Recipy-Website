package com.pets.petcare.ServiceImpl;


import com.pets.petcare.Entity.SavedRecipeEntity;
import com.pets.petcare.Entity.RecipePostEntity;
import com.pets.petcare.ServiceI.SavedRecipeServiceI;
import com.pets.petcare.repository.SavedRecipeRepository;
import com.pets.petcare.repository.RecipePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SavedRecipeServiceImpl implements SavedRecipeServiceI {

    @Autowired
    private SavedRecipeRepository savedRecipeRepository;

    @Autowired
    private RecipePostRepository recipePostRepository;

    @Override
    public String saveRecipe(Long userId, Long recipeId) {
        // Check if the recipe exists
        Optional<RecipePostEntity> recipeOptional = recipePostRepository.findById(recipeId);
        if (recipeOptional.isPresent()) {
            // Check if the recipe is already saved by the user
            Optional<SavedRecipeEntity> savedRecipeOptional = savedRecipeRepository.findByUserIdAndRecipeId(userId, recipeId);

            if (savedRecipeOptional.isPresent()) {
                // If it exists, remove it
                savedRecipeRepository.delete(savedRecipeOptional.get());
                return "Recipe removed from saved list.";
            } else {
                // If it doesn't exist, save it
                SavedRecipeEntity savedRecipe = new SavedRecipeEntity();
                savedRecipe.setUserId(userId);
                savedRecipe.setRecipeId(recipeId);
                savedRecipe.setSavedAt(LocalDateTime.now());

                savedRecipeRepository.save(savedRecipe);
                return "Recipe saved successfully!";
            }
        } else {
            throw new RuntimeException("Recipe not found");
        }
    }

    @Override
    public List<SavedRecipeEntity> getSavedRecipesByUser(Long userId) {
        return savedRecipeRepository.findByUserId(userId);
    }
}
