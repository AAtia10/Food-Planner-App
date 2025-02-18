package com.example.foodplanningapp.models;

import java.util.List;

public class IngredientResponse {

    private List<IngredientsDTO> meals;

    public List<IngredientsDTO> getIngredients() {
        return meals;
    }

    public void setMeals(List<IngredientsDTO> meals) {
        this.meals = meals;
    }


}
