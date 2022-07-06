package com.example.recipehub.controllers;

import com.example.recipehub.models.DefaultPage;
import com.example.recipehub.models.dto.ingredient.IngredientDTO;
import com.example.recipehub.models.dto.recipe.RecipeWithIngredientsDTO;
import com.example.recipehub.models.entity.Ingredient;
import com.example.recipehub.services.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/ingredients")
@AllArgsConstructor
public class IngredientController {
    IngredientService ingredientService;

    @GetMapping("")
    public ResponseEntity<PageImpl<IngredientDTO>> getAllIngredients(DefaultPage ingredientPage) {
        return ingredientService.getAllIngredients(ingredientPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDTO> findByIdIngredient(@PathVariable int id) {
        return ingredientService.findByIdIngredient(id);
    }

    @GetMapping("/{id}/recipes")
    public ResponseEntity<HashSet<RecipeWithIngredientsDTO>> findRecipesByIngredient(@PathVariable int id) {
        return ingredientService.findRecipesByIngredient(id);
    }

    @PostMapping("")
    public ResponseEntity<IngredientDTO> createIngredient(@RequestBody Ingredient ingredient) {
        return new ResponseEntity<>(ingredientService.createIngredient(ingredient), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<IngredientDTO> updateIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {
        return ingredientService.updateIngredient(id, ingredient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIngredient(@PathVariable int id) {
        ingredientService.deleteIngredient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
