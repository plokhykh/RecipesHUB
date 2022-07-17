package com.example.recipehub.services.ingredient;

import com.example.recipehub.models.DefaultPage;
import com.example.recipehub.models.dto.ingredient.IngredientDTO;
import com.example.recipehub.models.dto.recipe.RecipeWithIngredientsDTO;
import com.example.recipehub.models.entity.Ingredient;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;

public interface IIngredientService {

    PageImpl<IngredientDTO> getAll(DefaultPage ingredientPage);

    IngredientDTO findById(int id);

    HashSet<RecipeWithIngredientsDTO> findByIdAllRecipes(int id);

    Ingredient findByIdFullInfo (int id);

    IngredientDTO create(Ingredient ingredient);

    IngredientDTO update(int id, Ingredient ingredient);

    void deleteById (int id);
}
