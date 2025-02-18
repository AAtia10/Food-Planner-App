package com.example.foodplanningapp.ui.details.details.presenter;

import android.content.Context;

import com.example.foodplanningapp.models.MealDTO;
import com.example.foodplanningapp.models.StoreMeal;
import com.example.foodplanningapp.network.FirebaseLocal;
import com.example.foodplanningapp.repos.LocalRepo;
import com.example.foodplanningapp.repos.RemoteRepo;
import com.example.foodplanningapp.ui.details.details.view.IDetailView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailPresenter {
    IDetailView detailView;
    RemoteRepo remoteRepo;
    LocalRepo localRepo;
    FirebaseLocal firebaseLocal;

    public DetailPresenter(IDetailView detailView, Context context) {
        this.detailView = detailView;
        this.remoteRepo = RemoteRepo.getInstance();
        localRepo=LocalRepo.getInstance(context);
        firebaseLocal=FirebaseLocal.getInstance();
    }
    public void getDetails(int id)
    {
        remoteRepo.getDetails(id)
                .subscribe(new SingleObserver<MealDTO>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull MealDTO mealDTO) {
                        detailView.showData(mealDTO);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    public void insert(StoreMeal meal)
    {
        localRepo.insert(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        firebaseLocal.Insert(meal);
    }

}
