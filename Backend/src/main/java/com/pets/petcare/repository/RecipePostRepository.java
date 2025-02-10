package com.pets.petcare.repository;

import com.pets.petcare.Entity.RecipePostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecipePostRepository extends JpaRepository<RecipePostEntity, Long> {
    List<RecipePostEntity> findByUserId(Long userId); // Find posts by user ID
    Optional<RecipePostEntity> findByIdAndIsDeletedFalse(Long id);
//    List<RecipePostEntity> findByItemNameContainingIgnoreCase(String name);
//    List<RecipePostEntity> findByIngredientsContainingIgnoreCase(String ingredient);

    @Query("SELECT r FROM RecipePostEntity r WHERE LOWER(r.itemName) LIKE LOWER(CONCAT('%', :name, '%')) AND r.isDeleted = false")
    List<RecipePostEntity> findByNameContainingIgnoreCase(@Param("name") String name);

    @Query("SELECT r FROM RecipePostEntity r WHERE LOWER(r.ingredients) LIKE LOWER(CONCAT('%', :ingredient, '%')) AND r.isDeleted = false")
    List<RecipePostEntity> findByIngredientsContainingIgnoreCase(@Param("ingredient") String ingredient);


    List<RecipePostEntity> findByItemNameContainingIgnoreCase(String name);

    // Fetch all recipes and we'll filter ingredients in the service layer
    List<RecipePostEntity> findAll();

    List<RecipePostEntity> findByCategory(String category);
    List<RecipePostEntity> findByType(String type);

    List<RecipePostEntity> findByCategoryIgnoreCase(String category);

    List<RecipePostEntity> findByTypeIgnoreCase(String type);


    List<RecipePostEntity> findAllByUserId(Long id);
}
