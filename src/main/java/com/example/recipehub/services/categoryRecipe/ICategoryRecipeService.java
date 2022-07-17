package com.example.recipehub.services.categoryRecipe;

import com.example.recipehub.models.dto.categoryRecipe.CategoryRecipeWithSubcategoriesDTO;
import com.example.recipehub.models.entity.CategoryRecipe;

import java.util.List;

public interface ICategoryRecipeService {

    List<CategoryRecipeWithSubcategoriesDTO> getAll();

    CategoryRecipe getById(int id);

    CategoryRecipeWithSubcategoriesDTO getByIdWithSubcategories(int id);

    CategoryRecipeWithSubcategoriesDTO create(CategoryRecipeWithSubcategoriesDTO categoryRecipe);

    CategoryRecipeWithSubcategoriesDTO updateWithSubcategory(int id, CategoryRecipeWithSubcategoriesDTO categoryRecipeDTO);

    void deleteById(int id);


}
