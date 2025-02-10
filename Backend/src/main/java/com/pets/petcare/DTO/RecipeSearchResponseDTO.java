package com.pets.petcare.DTO;

import lombok.Data;

import java.util.List;

@Data
public class RecipeSearchResponseDTO {
    private Long id;
    private String name;
    private String thumbnel;
    private List<String> ingredients;
}
