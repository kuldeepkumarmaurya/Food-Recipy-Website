package com.pets.petcare.Controller;

import com.pets.petcare.DTO.RecipePostDTO;
import com.pets.petcare.DTO.RecipeSearchResponseDTO;
import com.pets.petcare.Entity.RecipePostEntity;
import com.pets.petcare.ServiceI.RecipePostServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipePostServiceI recipePostServiceI;

    @PostMapping("/create/{userId}")
    public ResponseEntity<String> createRecipePost(@ModelAttribute RecipePostDTO recipePostDTO, @PathVariable Long userId) {
        try {
            String responseMessage = recipePostServiceI.createRecipePost(recipePostDTO, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create recipe post: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getRecipeById(@PathVariable Long id) {
        Optional<RecipePostEntity> recipeOptional = recipePostServiceI.getRecipeById(id);
        if (recipeOptional.isPresent()) {
            return ResponseEntity.ok(recipeOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recipe not found or has been deleted.");
        }
    }

    @GetMapping("/searchByName")
    public List<RecipeSearchResponseDTO> searchByName(@RequestParam("name") String name) {
        return recipePostServiceI.searchRecipesByName(name);
    }

    @GetMapping("/searchByIngredient")
    public List<RecipeSearchResponseDTO> searchByIngredient(@RequestParam("ingredient") String ingredient) {
        return recipePostServiceI.searchRecipesByIngredient(ingredient);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<RecipeSearchResponseDTO>> getAllByCategory(@PathVariable String category) {
        List<RecipeSearchResponseDTO> recipes = recipePostServiceI.findAllByCategory(category);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<RecipeSearchResponseDTO>> getAllByType(@PathVariable String type) {
        List<RecipeSearchResponseDTO> recipes = recipePostServiceI.findAllByType(type);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("get/feed")
    public ResponseEntity<List<RecipePostEntity>> getFeed(@PathVariable String type) {
        List<RecipePostEntity> recipes = recipePostServiceI.getFeed();
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<RecipeSearchResponseDTO>> getAll() {
        List<RecipeSearchResponseDTO> recipes = recipePostServiceI.getAll();
        System.out.println(recipes.size());
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/get/all/{userId}")
    public ResponseEntity<List<RecipeSearchResponseDTO>> getAll(@PathVariable String userId) {
        List<RecipeSearchResponseDTO> recipes = recipePostServiceI.getAllByUser(userId);
        System.out.println(recipes.size());
        return ResponseEntity.ok(recipes);
    }
}
