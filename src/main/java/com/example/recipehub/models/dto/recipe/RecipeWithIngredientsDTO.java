package com.example.recipehub.models.dto.recipe;

import com.example.recipehub.models.dto.categoryRecipe.CategoryRecipeWithSubcategoriesDTO;
import com.example.recipehub.models.dto.ingredient.IngredientDTO;
import com.example.recipehub.models.entity.Recipe;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class RecipeWithIngredientsDTO {
    private int id;
    private String title;
    private String description;
    private String image;
    private String author;
    private List<CategoryRecipeWithSubcategoriesDTO> categories;
    private List<IngredientDTO> ingredients = new ArrayList<>();


    public RecipeWithIngredientsDTO(Recipe recipe) {
        this.id = recipe.getId();
        this.title = recipe.getTitle();
        this.description = recipe.getDescription();
        this.image = getImage();
        this.author = recipe.getAuthor();
        if (recipe.getIngredientQuantities() != null) {
            this.ingredients = recipe.getIngredientQuantities()
                    .stream()
                    .map(item -> new IngredientDTO(item.getIngredient(), item.getQuantity()))
                    .collect(Collectors.toList());
        }
    }

    public RecipeWithIngredientsDTO(Recipe recipe, List<CategoryRecipeWithSubcategoriesDTO> categoryRecipeWithSubcategoriesDTO) {
        this.id = recipe.getId();
        this.title = recipe.getTitle();
        this.description = recipe.getDescription();
        this.image = recipe.getImage();
        this.author = recipe.getAuthor();
        if (recipe.getIngredientQuantities() != null) {
            this.ingredients = recipe.getIngredientQuantities()
                    .stream()
                    .map(item -> new IngredientDTO(item.getIngredient(), item.getQuantity()))
                    .collect(Collectors.toList());
        }
        this.categories = categoryRecipeWithSubcategoriesDTO;
    }
}
