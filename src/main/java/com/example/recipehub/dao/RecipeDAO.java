package com.example.recipehub.dao;

import com.example.recipehub.models.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeDAO extends JpaRepository<Recipe, Integer>, PagingAndSortingRepository<Recipe, Integer> {

    @Query("SELECT r FROM Recipe r WHERE " +
            "r.title LIKE CONCAT('%',:query, '%')" +
            "Or r.description LIKE CONCAT('%', :query, '%')")
    Page<Recipe> search (Pageable pageable, String query);

}
