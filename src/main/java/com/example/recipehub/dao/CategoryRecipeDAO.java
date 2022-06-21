package com.example.recipehub.dao;

import com.example.recipehub.models.entity.CategoryRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRecipeDAO extends JpaRepository<CategoryRecipe, Integer> {

    List<CategoryRecipe> findAllByParentOrId (int parent, int id);
    void deleteAllByParent (int parent);

}
