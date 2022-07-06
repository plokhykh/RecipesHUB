package com.example.recipehub.dao;

import com.example.recipehub.models.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeDAO extends JpaRepository<Recipe, Integer>, PagingAndSortingRepository<Recipe, Integer> {

//     @Query("select r from Recipe r join fetch r.ingredientQuantities")
//    List<Recipe> customQueryRecipesWithFetchIngredients();
//    List<Recipe> customQueryRecipesWithFetchIngredients();
//
//     @Query("select r from Recipe r join fetch r.ingredientQuantities where r.id=:id")
//   Recipe customQueryRecipeByIdWithFetchIngredients (int id);

}
