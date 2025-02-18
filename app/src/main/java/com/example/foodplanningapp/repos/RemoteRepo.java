package com.example.foodplanningapp.repos;

import com.example.foodplanningapp.models.AreaDTO;
import com.example.foodplanningapp.models.CategoryDTO;
import com.example.foodplanningapp.models.CategoryResponse;
import com.example.foodplanningapp.models.IngredientsDTO;
import com.example.foodplanningapp.models.MealDTO;
import com.example.foodplanningapp.models.RandomMealDTO;
import com.example.foodplanningapp.network.MealRemoteDatasource;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RemoteRepo {
    private MealRemoteDatasource mealRemoteDatasource;
    private static RemoteRepo instance=null;

    private RemoteRepo() {
        this.mealRemoteDatasource = MealRemoteDatasource.getInstance();
    }

    public static synchronized RemoteRepo getInstance() {
        if (instance==null)
        {
            instance=new RemoteRepo();
        }
        return instance;
    }


    public Single<List<MealDTO>> getDailyMeals(){
        return mealRemoteDatasource
                .getDailyMeals()
                .repeat(10)
                .flatMapIterable(x-> x.getMeals())
                .subscribeOn(Schedulers.io())
                .toList()
                .observeOn(AndroidSchedulers.mainThread());




    }
    public Single<List<CategoryDTO>> getAllCategories()
    {
       return mealRemoteDatasource.getAllCategories()
                .subscribeOn(Schedulers.io())
                .map(x->x.getCategories())
                .observeOn(AndroidSchedulers.mainThread());

    }
    public Single <MealDTO>getDetails(int id)
    {
        return mealRemoteDatasource.getDetails(id)
                .subscribeOn(Schedulers.io())
                .map(x->x.getMeals().get(0))
                .observeOn(AndroidSchedulers.mainThread());

    }
    public Single<List<AreaDTO>>getArea()
    {
        return mealRemoteDatasource.getArea()
                .subscribeOn(Schedulers.io())
                .map(x->x.getMeals())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Single<List<IngredientsDTO>>getIngredients()
    {
        return mealRemoteDatasource.getIngredients()
                .subscribeOn(Schedulers.io())
                .map(x->x.getIngredients())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<MealDTO>> getMealCategory(String s){
        return mealRemoteDatasource.getMealcategory(s)
                .subscribeOn(Schedulers.io())
                .map(x->x.getMeals())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public Single<List<MealDTO>> getMealCountry(String s){
        return mealRemoteDatasource.getMealCountry(s)
                .subscribeOn(Schedulers.io())
                .map(x->x.getMeals())
                .observeOn(AndroidSchedulers.mainThread());

    }
    public Single<List<MealDTO>> getMealingr(String s){
        return mealRemoteDatasource.getMealingr(s)
                .subscribeOn(Schedulers.io())
                .map(x->x.getMeals())
                .observeOn(AndroidSchedulers.mainThread());

    }

}
