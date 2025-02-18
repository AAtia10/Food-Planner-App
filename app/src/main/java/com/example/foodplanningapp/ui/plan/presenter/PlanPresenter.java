package com.example.foodplanningapp.ui.plan.presenter;

import android.content.Context;

import com.example.foodplanningapp.models.StoreMeal;
import com.example.foodplanningapp.network.FirebaseLocal;
import com.example.foodplanningapp.repos.LocalRepo;
import com.example.foodplanningapp.ui.plan.view.IPlanView;

import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanPresenter {
   private LocalRepo localRepo;
   private IPlanView planView;
   FirebaseLocal firebaseLocal;

    public PlanPresenter(IPlanView planView, Context context) {
        this.planView = planView;
        localRepo=LocalRepo.getInstance(context);
        firebaseLocal=FirebaseLocal.getInstance();
    }


    public void showData(String id,String flag,String date)
    {
        localRepo.getPlan(id, flag, date).map(localDTOList ->
                        localDTOList.stream()
                                .map(StoreMeal::getMeal)
                                .collect(Collectors.toList())
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> planView.showData(meals),
                        throwable -> planView.showData(null)
                );;

    }
    public void delete(StoreMeal meal)
    {
        localRepo.delete(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        ()->planView.deleteMeal(meal)
                        );
        firebaseLocal.deleteFirebase(meal.getId(), meal.getMeal(), meal.getDate(), meal.getFlag());

    }
}
