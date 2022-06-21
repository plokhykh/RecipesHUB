package com.example.recipehub.dao;

import com.example.recipehub.models.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientDAO extends JpaRepository<Ingredient, Integer> {
    Ingredient findIngredientById (int id);
}
