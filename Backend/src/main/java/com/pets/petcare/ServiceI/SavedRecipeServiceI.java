package com.pets.petcare.ServiceI;

import com.pets.petcare.Entity.SavedRecipeEntity;

import java.util.List;

public interface SavedRecipeServiceI {
    String saveRecipe(Long userId, Long recipeId);

    List<SavedRecipeEntity> getSavedRecipesByUser(Long userId);
}
