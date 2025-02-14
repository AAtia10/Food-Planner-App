package com.example.foodplanningapp.ui.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanningapp.adapters.CategoryAdapter;
import com.example.foodplanningapp.adapters.CountryAdapter;
import com.example.foodplanningapp.models.AreaDTO;
import com.example.foodplanningapp.models.CategoryDTO;
import com.example.foodplanningapp.models.MealDTO;
import com.example.foodplanningapp.network.MealRemoteDatasource;
import com.example.foodplanningapp.R;
import com.example.foodplanningapp.adapters.DailyMealAdapter;
import com.example.foodplanningapp.ui.home.presenter.HomePresenter;

import java.util.List;


public class HomeFragment extends Fragment implements IHomeView {
    RecyclerView recyclerView;
    DailyMealAdapter adapter;
    HomePresenter presenter;
    RecyclerView recyclerViewcat;
    CategoryAdapter categoryAdapter;
    RecyclerView flagrecycle;
    CountryAdapter countryAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.homerecyle);
        recyclerViewcat=view.findViewById(R.id.categoryrecyle);
        presenter=new HomePresenter(this);
        presenter.getDailyMeals();
        presenter.getAllCategories();
        presenter.getArea();
        flagrecycle=view.findViewById(R.id.flagrecycle);

    }

    @Override
    public void showDailyMeals(List<MealDTO> list) {
        adapter=new DailyMealAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(id -> {
            navigateToDetails(id);

        });
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAllCategories(List<CategoryDTO> list) {
        categoryAdapter=new CategoryAdapter(list);
        recyclerViewcat.setAdapter(categoryAdapter);

    }

    @Override
    public void showAreas(List<AreaDTO> list) {
       countryAdapter=new CountryAdapter(list);
       flagrecycle.setAdapter(countryAdapter);
    }

    private void navigateToDetails(int id)
    {
        Navigation.findNavController(getView()).navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(id));
    }
}