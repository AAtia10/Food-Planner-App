package com.example.foodplanningapp.ui.country.view.view;

import com.example.foodplanningapp.models.MealDTO;

import java.util.List;

public interface ICountryView {

    void showMealsCountry(List<MealDTO> list);
    void navigateToDetails(int id);
}
