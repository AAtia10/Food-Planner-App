package com.example.foodplanningapp.ui.ingredient.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodplanningapp.R;
import com.example.foodplanningapp.adapters.CategoryFragmentAdapter;
import com.example.foodplanningapp.models.MealDTO;
import com.example.foodplanningapp.ui.country.view.presenter.CountryPresenter;
import com.example.foodplanningapp.ui.country.view.view.CountryFragmentDirections;
import com.example.foodplanningapp.ui.ingredient.presenter.IngredientPresenter;

import java.util.List;


public class IngredientFragment extends Fragment implements IIngredientView{
    String name;
    CategoryFragmentAdapter categoryFragmentAdapter;
    RecyclerView recyclerView;
    TextView title;
    IngredientPresenter ingredientPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredient2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name=IngredientFragmentArgs.fromBundle(getArguments()).getNameIngredient();
        recyclerView=view.findViewById(R.id.catgrecylepage);
        title=view.findViewById(R.id.txtmeal);
        ingredientPresenter=new IngredientPresenter(this);
        ingredientPresenter.showIngredients(name);
        title.setText(name);
    }

    @Override
    public void showMealsIngredients(List<MealDTO> list) {
        categoryFragmentAdapter=new CategoryFragmentAdapter(list);
        recyclerView.setAdapter(categoryFragmentAdapter);
        categoryFragmentAdapter.setOnItemClickListener(v ->{
            navigateToDetails(v);
        });

    }

    @Override
    public void navigateToDetails(int id) {

        Navigation.findNavController(getView()).navigate(IngredientFragmentDirections.actionIngredientFragmentToDetailsFragment(id));

    }
}