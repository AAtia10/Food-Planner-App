package com.example.foodplanningapp.ui.ingredient.presenter;

import com.example.foodplanningapp.models.IngredientsDTO;
import com.example.foodplanningapp.models.MealDTO;
import com.example.foodplanningapp.repos.RemoteRepo;
import com.example.foodplanningapp.ui.ingredient.view.IIngredientView;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class IngredientPresenter {
RemoteRepo remoteRepo;
IIngredientView iIngredientView;

    public IngredientPresenter( IIngredientView iIngredientView) {
        this.remoteRepo = RemoteRepo.getInstance();
        this.iIngredientView = iIngredientView;
    }

    public void showIngredients(String name)
    {
        remoteRepo.getMealingr(name).subscribe(new SingleObserver<List<MealDTO>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull List<MealDTO> list) {
                iIngredientView.showMealsIngredients(list);

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }


    }

