package com.example.foodplanningapp.ui.plan.view;

import com.example.foodplanningapp.models.MealDTO;
import com.example.foodplanningapp.models.StoreMeal;

import java.util.List;

public interface IPlanView {

    public void showData(List<MealDTO> list);
    public void deleteMeal(StoreMeal meal);
}
