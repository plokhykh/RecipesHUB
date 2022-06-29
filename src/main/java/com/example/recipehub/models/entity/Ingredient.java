package com.example.recipehub.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type_ukr;
    private String name_ukr;
    private String name;
    private String type;
    private int weight;
    private double calories;
    private double total_protein;
    private double total_fat;
    private double saturated_fatty_acids;
    private double monounsaturated_fatty_acids;
    private double polyunsaturated_fatty_acids;
    private double carbohydrate;
    private double fiber;
    private double sugars;
    private double vitamin_a;
    private double thiamin_vitamin_b1;
    private double riboflavin_vitamin_b2;
    private double niacin_vitamin_b3;
    private double choline_vitamin_b4;
    private double pantothenic_acid_vitamin_b5;
    private double vitamin_b6;
    private double folic_acid_vitamin_b5;
    private double vitamin_b12_cobalamin;
    private double vitamin_d;
    private double vitamin_e_alpha_tocopherol;
    private double vitamin_k;
    private double vitamin_c_ascorbic_acid;
    private double cholesterol;
    private double sodium;
    private double potassium;
    private double calcium;
    private double copper;
    private double phosphorus_p;
    private double magnesium;
    private double iron;
    private double zinc;
    private double manganese;
    private double selenium;

    @OneToMany(
            mappedBy = "ingredient",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<IngredientQuantity> ingredientQuantities = new ArrayList<>();

}
