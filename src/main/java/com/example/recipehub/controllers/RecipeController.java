package com.example.recipehub.controllers;

import com.example.recipehub.models.RecipePage;
import com.example.recipehub.models.dto.recipe.RecipeWithIngredientsIdDTO;
import com.example.recipehub.models.dto.recipe.RecipeWithIngredientsDTO;
import com.example.recipehub.services.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/recipes")
@AllArgsConstructor
public class RecipeController {
    RecipeService recipeService;

    @PostMapping("")
    public ResponseEntity<RecipeWithIngredientsDTO> createRecipe(@RequestBody RecipeWithIngredientsIdDTO recipe) {
        return new ResponseEntity<>(recipeService.createRecipe(recipe), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<PageImpl<RecipeWithIngredientsDTO>> getAllRecipes (RecipePage recipePage){
        return recipeService.getAllRecipes(recipePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeWithIngredientsDTO> getRecipeById (@PathVariable int id){
        return recipeService.getRecipeById(id);
    }

    @GetMapping("/by_categories/{id}")
    public ResponseEntity<List<RecipeWithIngredientsDTO>> getRecipesByCategories (@PathVariable int id) {
        return recipeService.getRecipesByCategories(id);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<RecipeWithIngredientsDTO> updateRecipeById (@PathVariable int id, @RequestBody RecipeWithIngredientsIdDTO recipe){
        return recipeService.updateRecipeById(id, recipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe (@PathVariable int id){
        recipeService.deleteRecipe(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
