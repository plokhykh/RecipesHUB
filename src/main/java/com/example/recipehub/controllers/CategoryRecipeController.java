package com.example.recipehub.controllers;

import com.example.recipehub.models.dto.categoryRecipe.CategoryRecipeWithSubcategoriesDTO;
import com.example.recipehub.services.CategoryRecipeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes/categories")
@AllArgsConstructor
public class CategoryRecipeController {

    CategoryRecipeService categoryRecipeService;

    @PostMapping("")
    public ResponseEntity<CategoryRecipeWithSubcategoriesDTO> createCategory(@RequestBody CategoryRecipeWithSubcategoriesDTO categoryRecipe) {
        return new ResponseEntity<>(categoryRecipeService.createCategory(categoryRecipe), HttpStatus.OK);
    }

    @GetMapping("")
    public List<CategoryRecipeWithSubcategoriesDTO> getAllCategories() {
        return categoryRecipeService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryRecipeWithSubcategoriesDTO> getCategoryWithSubcategoriesById (@PathVariable int id) {
        return categoryRecipeService.getCategoryWithSubcategoriesById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryRecipeWithSubcategoriesDTO> updateCategoryWithSubcategory(@PathVariable int id, @RequestBody CategoryRecipeWithSubcategoriesDTO categoryRecipe) {
        return categoryRecipeService.updateCategoryWithSubcategory(id, categoryRecipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id) {
        categoryRecipeService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
