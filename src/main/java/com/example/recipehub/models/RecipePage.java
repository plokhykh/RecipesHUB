package com.example.recipehub.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipePage {
    private int page = 1;
    private int size = 10;
}
