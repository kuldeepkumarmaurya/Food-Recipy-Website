package com.pets.petcare.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "saved_recipes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SavedRecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long recipeId;

    // Optional: You can add a createdAt field to track when the recipe was saved
    private LocalDateTime savedAt;
}
