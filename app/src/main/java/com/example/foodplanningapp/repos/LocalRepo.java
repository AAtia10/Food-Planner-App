package com.example.foodplanningapp.repos;

import android.content.Context;

import com.example.foodplanningapp.db.MealLocalDatasource;
import com.example.foodplanningapp.models.StoreMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class LocalRepo {
    private static LocalRepo instance;
   private MealLocalDatasource mealLocalDatasource;

    private LocalRepo(Context context) {
        mealLocalDatasource=MealLocalDatasource.getInstance(context);
    }
    public static LocalRepo getInstance(Context context)
    {
        if (instance==null)
        {
            instance=new LocalRepo(context);
        }
        return instance;
    }



    public Completable insert(StoreMeal meal)
    {
        return mealLocalDatasource.insert(meal);
    }
    public Completable delete(StoreMeal meal)
    {
        return mealLocalDatasource.delete(meal);
    }

    public Single<List<StoreMeal>> getAllFavorites(String id, String flag)
    {
        return mealLocalDatasource.getAllFavorites(id, flag);
    }


    public Single<List<StoreMeal>> getPlan(String id,String flag, String date)
    {
        return mealLocalDatasource.getPlan(id, flag, date);
    }
}
