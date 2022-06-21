package com.example.recipehub.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private int calories;
    private double total_protein;
    private double total_fat;
    private double saturated_fatty_acids;
    private double monounsaturated_fatty_acids;
    private double polyunsaturated_fatty_acids;
    private double carbohydrate;
    private double fiber;
    private double sugars;
    private int vitamin_a;
    private int thiamin_vitamin_b1;
    private int riboflavin_vitamin_b2;
    private int niacin_vitamin_b3;
    private int choline_vitamin_b4;
    private int pantothenic_acid_vitamin_b5;
    private int vitamin_b6;
    private int folic_acid_vitamin_b5;
    private int vitamin_b12_cobalamin;
    private int vitamin_d;
    private int vitamin_e_alpha_tocopherol;
    private double vitamin_k;
    private int vitamin_c_ascorbic_acid;
    private int cholesterol;
    private int sodium;
    private int potassium;
    private int calcium;
    private double copper;
    private int phosphorus_p;
    private int magnesium;
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
