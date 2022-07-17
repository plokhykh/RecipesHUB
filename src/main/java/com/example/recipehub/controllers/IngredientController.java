package com.example.recipehub.controllers;

import com.example.recipehub.models.DefaultPage;
import com.example.recipehub.models.dto.ingredient.IngredientDTO;
import com.example.recipehub.models.dto.recipe.RecipeWithIngredientsDTO;
import com.example.recipehub.models.entity.Ingredient;
import com.example.recipehub.services.ingredient.IIngredientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/ingredients")
@AllArgsConstructor
public class IngredientController {
    IIngredientService ingredientService;

    @GetMapping("")
    public PageImpl<IngredientDTO> getAll(DefaultPage ingredientPage) {
        return ingredientService.getAll(ingredientPage);
    }

    @GetMapping("/{id}")
    public IngredientDTO findById(@PathVariable int id) {
        return ingredientService.findById(id);
    }

    @GetMapping("/{id}/recipes")
    public HashSet<RecipeWithIngredientsDTO> findByIdAllRecipes(@PathVariable int id) {
        return ingredientService.findByIdAllRecipes(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public IngredientDTO create(@RequestBody Ingredient ingredient) {
        return ingredientService.create(ingredient);
    }

    @PatchMapping("/{id}")
    public IngredientDTO update(@PathVariable int id, @RequestBody Ingredient ingredient) {
        return ingredientService.update(id, ingredient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById (@PathVariable int id) {
        ingredientService.deleteById(id);
    }

}
