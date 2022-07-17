package com.example.recipehub.controllers;

import com.example.recipehub.models.dto.categoryRecipe.CategoryRecipeWithSubcategoriesDTO;
import com.example.recipehub.services.categoryRecipe.ICategoryRecipeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes/categories")
@AllArgsConstructor
public class CategoryRecipeController {

    ICategoryRecipeService categoryRecipeService;

    @GetMapping("")
    public List<CategoryRecipeWithSubcategoriesDTO> getAll() {
        return categoryRecipeService.getAll();
    }

    @GetMapping("/{id}")
    public CategoryRecipeWithSubcategoriesDTO getByIdWithSubcategories (@PathVariable int id) {
        return categoryRecipeService.getByIdWithSubcategories(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryRecipeWithSubcategoriesDTO create(@RequestBody CategoryRecipeWithSubcategoriesDTO categoryRecipe) {
        return categoryRecipeService.create(categoryRecipe);
    }

    @PatchMapping("/{id}")
    public CategoryRecipeWithSubcategoriesDTO updateWithSubcategory(@PathVariable int id, @RequestBody CategoryRecipeWithSubcategoriesDTO categoryRecipe) {
        return categoryRecipeService.updateWithSubcategory(id, categoryRecipe);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        categoryRecipeService.deleteById(id);
    }

}
