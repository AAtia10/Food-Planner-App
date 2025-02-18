package com.example.foodplanningapp.ui.favourite.presenter;

import android.content.Context;

import com.example.foodplanningapp.models.StoreMeal;
import com.example.foodplanningapp.network.FirebaseLocal;
import com.example.foodplanningapp.repos.LocalRepo;
import com.example.foodplanningapp.ui.favourite.view.IFavouriteView;

import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavouritePresenter {

    LocalRepo localRepo;
    IFavouriteView favouriteView;
    FirebaseLocal firebaseLocal;

    public FavouritePresenter(IFavouriteView favouriteView, Context context) {
        this.favouriteView = favouriteView;
        localRepo=LocalRepo.getInstance(context);
        firebaseLocal=FirebaseLocal.getInstance();
    }

    public void showData(String id,String flag)
    {
        localRepo.getAllFavorites(id, flag).map(localDTOList ->
                        localDTOList.stream()
                                .map(StoreMeal::getMeal)
                                .collect(Collectors.toList())
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> favouriteView.showData(meals),
                        throwable -> favouriteView.showData(null)
                );;

    }
    public void delete(StoreMeal meal)
    {
        localRepo.delete(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        firebaseLocal.deleteFirebase(meal.getId(), meal.getMeal(), meal.getDate(), meal.getFlag());

    }


}
