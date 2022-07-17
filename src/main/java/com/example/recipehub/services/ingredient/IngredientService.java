package com.example.recipehub.services.ingredient;

import com.example.recipehub.dao.IngredientDAO;
import com.example.recipehub.models.DefaultPage;
import com.example.recipehub.models.dto.ingredient.IngredientDTO;
import com.example.recipehub.models.dto.recipe.RecipeWithIngredientsDTO;
import com.example.recipehub.models.entity.Ingredient;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IngredientService implements IIngredientService{

    private IngredientDAO ingredientDAO;

    @Override
    public PageImpl<IngredientDTO> getAll(DefaultPage ingredientPage) {
        Pageable pageable = PageRequest.of(ingredientPage.getPage() - 1, ingredientPage.getSize());
        Page<Ingredient> ingredients = ingredientDAO.findAll(pageable);
        List<IngredientDTO> ingredientsList = ingredients.getContent()
                .stream()
                .map(IngredientDTO::new)
                .collect(Collectors.toList());

        return new PageImpl<>(ingredientsList, pageable, ingredients.getTotalElements());
    }

    @Override
    public IngredientDTO findById(int id) {
        Ingredient ingredient = ingredientDAO.findById(id).orElse(new Ingredient());
        return new IngredientDTO(ingredient);
    }

    @Override
    public HashSet<RecipeWithIngredientsDTO> findByIdAllRecipes(int id) {
        Ingredient ingredient = ingredientDAO.findById(id).orElse(new Ingredient());

        if (ingredient.getIngredientQuantities().isEmpty()) new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return (HashSet<RecipeWithIngredientsDTO>) ingredient.getIngredientQuantities()
                .stream()
                .map(item -> new RecipeWithIngredientsDTO(item.getRecipe()))
                .collect(Collectors.toSet());
    }

    @Override
    public Ingredient findByIdFullInfo(int id) {
        return ingredientDAO.findIngredientById(id);
    }

    @Override
    public IngredientDTO create(Ingredient ingredient) {
        return new IngredientDTO(ingredientDAO.save(ingredient));
    }

    @Override
    public IngredientDTO update(int id, Ingredient ingredient) {
        ingredient.setId(id);
        return new IngredientDTO(ingredientDAO.save(ingredient));
    }

    @Override
    public void deleteById(int id) {
        ingredientDAO.deleteById(id);
    }
}

