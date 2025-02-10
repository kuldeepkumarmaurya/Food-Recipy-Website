package com.pets.petcare.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "recipe_posts") // Ensure this matches your actual table name
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipePostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String itemName;

    @ElementCollection
    private List<String> ingredients;

    @ElementCollection
    @Column(length = 2000)
    private List<String> steps;

    private String videoPath;

    @ElementCollection
    private List<String> imagePaths; // If you are storing multiple images

    private boolean isDeleted; // For soft delete

    private String calories;
    private String preparationTime;

    // New fields
    private String thumbnail; // Thumbnail image path

    @Column(length = 2000)
    private String description; // Description of the recipe
    private String category; // Veg or Non-Veg
    private String type; // Breakfast, Lunch, or Dinner
}
