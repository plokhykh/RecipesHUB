package com.example.recipehub.models.dto.categoryRecipe;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CategoryRecipeWithSubcategoriesDTO {
    private int id;
    private String name;
    private List<SubcategoryRecipeDTO> subcategories;

    public CategoryRecipeWithSubcategoriesDTO(int id, String name, List<SubcategoryRecipeDTO> subCategoriesDTO) {
        this.id = id;
        this.name = name;
        this.subcategories = subCategoriesDTO;
    }


}
