package com.pets.petcare.Controller;

import com.pets.petcare.Entity.SavedRecipeEntity;
import com.pets.petcare.ServiceI.SavedRecipeServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipePostSaveController {

    @Autowired
    private SavedRecipeServiceI savedRecipeServiceI;

    @PostMapping("/save")
    public String saveRecipe(@RequestParam Long userId, @RequestParam Long recipeId) {
        return savedRecipeServiceI.saveRecipe(userId, recipeId);
    }

    @GetMapping("/get-saved")
    public List<SavedRecipeEntity> getSavedRecipes(@RequestParam Long userId) {
        return savedRecipeServiceI.getSavedRecipesByUser(userId);
    }
}
