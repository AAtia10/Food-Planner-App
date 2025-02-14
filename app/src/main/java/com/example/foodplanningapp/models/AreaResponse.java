package com.example.foodplanningapp.models;

import java.util.List;

public class AreaResponse {

    private List<AreaDTO> meals;

    public List<AreaDTO> getMeals() {
        return meals;
    }

    public void setMeals(List<AreaDTO> meals) {
        this.meals = meals;
    }
}
