package com.example.recipehub.models.dto.recipe;

import com.example.recipehub.models.dto.ingredient.IngredientIntoDTO;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class RecipeWithIngredientsIdDTO {
    private int id;
    private String title;
    private String description;
    private String image;
    private List<Integer> categories = new ArrayList<>();
    private String author;
    private List<IngredientIntoDTO> ingredients = new ArrayList<>();
}


