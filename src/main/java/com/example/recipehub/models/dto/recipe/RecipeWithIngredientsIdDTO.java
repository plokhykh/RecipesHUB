package com.example.recipehub.models.dto.recipe;

import com.example.recipehub.models.dto.ingredient.IngredientIntoDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RecipeWithIngredientsIdDTO {
    private int id;
    private String title;
    private String description;
    private List<Integer> categories = new ArrayList<>();
    private String author;
    private List<IngredientIntoDTO> ingredients = new ArrayList<>();
}


