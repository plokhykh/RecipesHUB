package com.example.recipehub.models.dto.categoryRecipe;

import com.example.recipehub.models.entity.CategoryRecipe;
import lombok.Data;

@Data
public class CategoryRecipeDTO {
    private int id;
    private String name;

    public CategoryRecipeDTO(CategoryRecipe categoryRecipe) {
        this.id = categoryRecipe.getId();
        this.name = categoryRecipe.getName();

    }
}
