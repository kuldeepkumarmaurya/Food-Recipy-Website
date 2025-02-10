package com.pets.petcare.ServiceImpl;

import com.pets.petcare.DTO.RecipePostDTO;
import com.pets.petcare.DTO.RecipeSearchResponseDTO;
import com.pets.petcare.Entity.RecipePostEntity;
import com.pets.petcare.Entity.RegistrationEntity;
import com.pets.petcare.ServiceI.FileDataServiceI;
import com.pets.petcare.ServiceI.RecipePostServiceI;
import com.pets.petcare.repository.RecipePostRepository;
import com.pets.petcare.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipePostServiceImpl implements RecipePostServiceI {

    @Autowired
    private RecipePostRepository recipePostRepository;

    @Autowired
    private FileDataServiceI fileDataServiceI;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Override
    public String createRecipePost(RecipePostDTO recipePostDTO, Long userId) throws IOException {
        System.out.println("Size b" + recipePostDTO.getSteps().size());
        RecipePostEntity recipePost = new RecipePostEntity();
        Optional<RegistrationEntity> checkuser = registrationRepository.findById(userId);
        if(!checkuser.isPresent()){
            return  "User Not Found";
        }
        recipePost.setUserId(userId);

        // Handle thumbnail
        if (recipePostDTO.getThumbnail() != null){
            String thumbnailName = fileDataServiceI.uploadImage(recipePostDTO.getThumbnail());
            recipePost.setThumbnail(thumbnailName);
        }

        // Handle images

        // Handle video
        if (recipePostDTO.getVideo() != null) {
            String videoName = fileDataServiceI.uploadImage(recipePostDTO.getVideo());
            recipePost.setVideoPath(videoName);
        }

        // Set ingredients and steps
        recipePost.setIngredients(recipePostDTO.getIngredients());
        recipePost.setSteps(recipePostDTO.getSteps());
        System.out.println("Size"+ recipePostDTO.getSteps().size());
        recipePost.setItemName(recipePostDTO.getItemName());

        // Set new fields
        recipePost.setDescription(recipePostDTO.getDescription());
        recipePost.setCategory(recipePostDTO.getCategory());
        recipePost.setType(recipePostDTO.getType());
        recipePost.setCalories(recipePostDTO.getCalories());
        recipePost.setPreparationTime(recipePostDTO.getPreparationTime());

        // Save the recipe post to the database
        recipePostRepository.save(recipePost);
        return "Recipe post created successfully!";
    }


    @Override
    public Optional<RecipePostEntity> getRecipeById(Long id) {
        return recipePostRepository.findByIdAndIsDeletedFalse(id); // Check for non-deleted recipes
    }

    // Search by recipe name
    public List<RecipeSearchResponseDTO> searchRecipesByName(String name) {
        List<RecipePostEntity> recipes = recipePostRepository.findByItemNameContainingIgnoreCase(name);

        // Convert to DTO
        return recipes.stream().map(recipe -> {
            RecipeSearchResponseDTO dto = new RecipeSearchResponseDTO();
            dto.setId(recipe.getId());
            dto.setName(recipe.getItemName());
            return dto;
        }).collect(Collectors.toList());
    }

    // Search by ingredients
    public List<RecipeSearchResponseDTO> searchRecipesByIngredient(String ingredient) {
        List<RecipePostEntity> allRecipes = recipePostRepository.findAll(); // Fetch all recipes

        // Filter recipes by checking if the ingredient is contained in the list of ingredients
        List<RecipePostEntity> filteredRecipes = allRecipes.stream()
                .filter(recipe -> recipe.getIngredients() != null && recipe.getIngredients()
                        .stream().anyMatch(ing -> ing.toLowerCase().contains(ingredient.toLowerCase())))
                .collect(Collectors.toList());

        // Convert to DTO
        return filteredRecipes.stream().map(recipe -> {
            RecipeSearchResponseDTO dto = new RecipeSearchResponseDTO();
            dto.setId(recipe.getId());
            dto.setName(recipe.getItemName());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<RecipeSearchResponseDTO> findAllByCategory(String category) {
        List<RecipePostEntity> recipes = recipePostRepository.findByCategoryIgnoreCase(category);

        // Convert to DTO
        return recipes.stream().map(recipe -> {
            RecipeSearchResponseDTO dto = new RecipeSearchResponseDTO();
            dto.setId(recipe.getId());
            dto.setName(recipe.getItemName());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<RecipeSearchResponseDTO> findAllByType(String type) {
        List<RecipePostEntity> getAllPost = recipePostRepository.findByTypeIgnoreCase(type);

//        List<RecipePostEntity> getAllPost = recipePostRepository.findAll();
        System.out.println(getAllPost.size());
        List<RecipeSearchResponseDTO> responseDTOList = new ArrayList<>();
        for (int i =  0 ; i<getAllPost.size() ;i++){
            RecipeSearchResponseDTO responce = new RecipeSearchResponseDTO();
            responce.setId(getAllPost.get(i).getId());
            responce.setIngredients(getAllPost.get(i).getIngredients());
            responce.setName(getAllPost.get(i).getItemName());
            responce.setThumbnel(getAllPost.get(i).getThumbnail());
            responseDTOList.add(responce);
        }
        return responseDTOList;
    }

    @Override
    public List<RecipePostEntity> getFeed() {

        List<RecipePostEntity> getAllPost = recipePostRepository.findAll();



        return List.of();
    }

    @Override
    public List<RecipeSearchResponseDTO> getAll() {

        List<RecipePostEntity> getAllPost = recipePostRepository.findAll();
        System.out.println(getAllPost.size());
        List<RecipeSearchResponseDTO> responseDTOList = new ArrayList<>();
        for (int i =  0 ; i<getAllPost.size() ;i++){
            RecipeSearchResponseDTO responce = new RecipeSearchResponseDTO();
            responce.setId(getAllPost.get(i).getId());
            responce.setIngredients(getAllPost.get(i).getIngredients());
            responce.setName(getAllPost.get(i).getItemName());
            responce.setThumbnel(getAllPost.get(i).getThumbnail());
            responseDTOList.add(responce);
        }
        return responseDTOList;
    }

    @Override
    public List<RecipeSearchResponseDTO> getAllByUser(String userId) {
        Long userImd = Long.parseLong(userId);

        List<RecipePostEntity> getAllPost = recipePostRepository.findAllByUserId(userImd);
        System.out.println(getAllPost.size());
        List<RecipeSearchResponseDTO> responseDTOList = new ArrayList<>();
        for (int i =  0 ; i<getAllPost.size() ;i++){
            RecipeSearchResponseDTO responce = new RecipeSearchResponseDTO();
            responce.setId(getAllPost.get(i).getId());
            responce.setIngredients(getAllPost.get(i).getIngredients());
            responce.setName(getAllPost.get(i).getItemName());
            responce.setThumbnel(getAllPost.get(i).getThumbnail());
            responseDTOList.add(responce);
        }
        return responseDTOList;
    }


}
