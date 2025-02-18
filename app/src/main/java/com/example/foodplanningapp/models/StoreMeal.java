package com.example.foodplanningapp.models;


import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "StoreMeal",primaryKeys = {"flag","id","date","meal"})
public class StoreMeal {

    @NonNull
    private String flag;
    @NonNull
    private String id;
    @NonNull
    private String date;
    @NonNull
    private MealDTO meal;

    public StoreMeal() {
    }

    public StoreMeal(String flag, String id, String date, MealDTO meal) {
        this.flag = flag;
        this.id = id;
        this.date = date;
        this.meal = meal;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public MealDTO getMeal() {
        return meal;
    }

    public void setMeal(MealDTO meal) {
        this.meal = meal;
    }
}
