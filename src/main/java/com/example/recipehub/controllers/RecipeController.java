package com.example.recipehub.controllers;

import com.example.recipehub.models.RecipePage;
import com.example.recipehub.models.dto.recipe.RecipeWithIngredientsIdDTO;
import com.example.recipehub.models.dto.recipe.RecipeWithIngredientsDTO;
import com.example.recipehub.services.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/recipes")
@AllArgsConstructor
public class RecipeController {
    RecipeService recipeService;

    @PostMapping(value = "")
    public ResponseEntity<RecipeWithIngredientsDTO> createRecipe(@RequestParam String recipe, @RequestParam(required = false) MultipartFile image) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        RecipeWithIngredientsIdDTO recipeDTO = mapper.readValue(recipe, RecipeWithIngredientsIdDTO.class);
        return new ResponseEntity<>(recipeService.createRecipe(recipeDTO, image), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<PageImpl<RecipeWithIngredientsDTO>> getAllRecipes(RecipePage recipePage) {
        return recipeService.getAllRecipes(recipePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeWithIngredientsDTO> getRecipeById(@PathVariable int id) {
        return recipeService.getRecipeById(id);
    }

    @GetMapping("/by_categories/{id}")
    public ResponseEntity<List<RecipeWithIngredientsDTO>> getRecipesByCategories(@PathVariable int id) {
        return recipeService.getRecipesByCategories(id);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<RecipeWithIngredientsDTO> updateRecipeById(@PathVariable int id, @RequestParam String recipe, @RequestParam(required = false) MultipartFile image) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        RecipeWithIngredientsIdDTO recipeDTO = mapper.readValue(recipe, RecipeWithIngredientsIdDTO.class);
        return recipeService.updateRecipeById(id, recipeDTO, image);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable int id) {
        recipeService.deleteRecipe(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
