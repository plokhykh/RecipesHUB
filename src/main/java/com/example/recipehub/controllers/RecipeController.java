package com.example.recipehub.controllers;

import com.example.recipehub.models.DefaultPage;
import com.example.recipehub.models.dto.recipe.RecipeWithIngredientsIdDTO;
import com.example.recipehub.models.dto.recipe.RecipeWithIngredientsDTO;
import com.example.recipehub.services.recipe.IRecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/recipes")
@AllArgsConstructor
public class RecipeController {
    IRecipeService recipeService;

    @GetMapping("")
    public PageImpl<RecipeWithIngredientsDTO> getAll(DefaultPage recipePage, @RequestParam(required = false) String q) {
        return recipeService.getAll(recipePage, q);
    }

    @GetMapping("/{id}")
    public RecipeWithIngredientsDTO getById(@PathVariable int id) {
        return recipeService.getById(id);
    }

    @GetMapping("/by_categories/{id}")
    public List<RecipeWithIngredientsDTO> getByCategories(@PathVariable int id) {
        return recipeService.getByCategories(id);
    }

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeWithIngredientsDTO create(@RequestParam String recipe, @RequestParam(required = false) MultipartFile image) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        RecipeWithIngredientsIdDTO recipeDTO = mapper.readValue(recipe, RecipeWithIngredientsIdDTO.class);
        return recipeService.create(recipeDTO, image);
    }

    @PatchMapping("/{id}")
    public RecipeWithIngredientsDTO update(@PathVariable int id, @RequestParam String recipe, @RequestParam(required = false) MultipartFile image) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        RecipeWithIngredientsIdDTO recipeDTO = mapper.readValue(recipe, RecipeWithIngredientsIdDTO.class);
        return recipeService.updateById(id, recipeDTO, image);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        recipeService.deleteById(id);
    }
}
