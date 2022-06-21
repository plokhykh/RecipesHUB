package com.example.recipehub.services;

import com.example.recipehub.dao.IngredientDAO;
import com.example.recipehub.models.dto.ingredient.IngredientDTO;
import com.example.recipehub.models.dto.recipe.RecipeWithIngredientsDTO;
import com.example.recipehub.models.entity.Ingredient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IngredientService {

    private IngredientDAO ingredientDAO;

    public IngredientDTO createIngredient(Ingredient ingredient) {
        return new IngredientDTO(ingredientDAO.save(ingredient));
    }


    public ResponseEntity<List<IngredientDTO>> getAllIngredients() {
        List<Ingredient> ingredients = ingredientDAO.findAll();
        if (ingredients == null) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
                ingredients.stream().map(IngredientDTO::new).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    public ResponseEntity<IngredientDTO> findByIdIngredient(int id) {
        Ingredient ingredient = ingredientDAO.findById(id).orElse(new Ingredient());
        if (ingredient == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(
                new IngredientDTO(ingredient),
                HttpStatus.OK
        );
    }

    public ResponseEntity<HashSet<RecipeWithIngredientsDTO>> findRecipesByIngredient(int id) {
        Ingredient ingredient = ingredientDAO.findById(id).orElse(new Ingredient());

        if (ingredient.getIngredientQuantities().isEmpty()) new ResponseEntity<>(HttpStatus.NOT_FOUND);

        HashSet<RecipeWithIngredientsDTO> recipes = (HashSet<RecipeWithIngredientsDTO>) ingredient.getIngredientQuantities()
                .stream()
                .map(item -> new RecipeWithIngredientsDTO(item.getRecipe()))
                .collect(Collectors.toSet());

        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }


    public ResponseEntity<IngredientDTO> updateIngredient(int id, Ingredient ingredient) {
        Ingredient checkIngredient = ingredientDAO.findIngredientById(id);
        if (checkIngredient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ingredient.setId(id);
        return new ResponseEntity<>(
                new IngredientDTO(ingredientDAO.save(ingredient)),
                HttpStatus.OK
        );
    }


    public void deleteIngredient(int id) {

        ingredientDAO.deleteById(id);
    }


    public Ingredient findFullIngredient(int id) {
        Ingredient ingredient = ingredientDAO.findIngredientById(id);
        return ingredient;
    }
}
