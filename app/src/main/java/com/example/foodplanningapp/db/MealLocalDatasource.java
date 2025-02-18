package com.example.foodplanningapp.db;

import android.content.Context;

import com.example.foodplanningapp.models.StoreMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class MealLocalDatasource {
    private MealDao mealDao;
    private static MealLocalDatasource instance;


   private MealLocalDatasource(Context context) {
        mealDao=AppDatabase.getInstance(context).getDao();
    }
    public static MealLocalDatasource getInstance(Context context)
    {
        if(instance==null)
        {
            instance=new MealLocalDatasource(context);
        }
        return instance;
    }

    public Completable insert(StoreMeal meal)
    {
        return mealDao.insert(meal);
    }
     public Completable delete(StoreMeal meal)
     {
         return mealDao.delete(meal);
     }

    public Single<List<StoreMeal>> getAllFavorites(String id, String flag)
    {
        return mealDao.getAllFavorites(id, flag);
    }


    public Single<List<StoreMeal>> getPlan(String id,String flag, String date)
    {
        return mealDao.getPlan(id, flag, date);
    }



}
