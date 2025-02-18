package com.example.foodplanningapp.ui.country.view.view;

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
import com.example.foodplanningapp.adapters.CountryAdapter;
import com.example.foodplanningapp.models.MealDTO;
import com.example.foodplanningapp.ui.category.presenter.CategoryPresenter;
import com.example.foodplanningapp.ui.category.view.CategoryFragmentArgs;
import com.example.foodplanningapp.ui.category.view.CategoryFragmentDirections;
import com.example.foodplanningapp.ui.country.view.presenter.CountryPresenter;

import java.util.List;


public class CountryFragment extends Fragment implements ICountryView {
    String name;
    CategoryFragmentAdapter categoryFragmentAdapter;
    RecyclerView recyclerView;
    TextView title;
    CountryPresenter countryPresenter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name= CountryFragmentArgs.fromBundle(getArguments()).getNameCountry();
        recyclerView=view.findViewById(R.id.catgrecylepage);
        title=view.findViewById(R.id.txtmeal);
        countryPresenter=new CountryPresenter(this);
        countryPresenter.showCountry(name);
        title.setText(name);

    }

    @Override
    public void showMealsCountry(List<MealDTO> list) {
        categoryFragmentAdapter=new CategoryFragmentAdapter(list);
        recyclerView.setAdapter(categoryFragmentAdapter);
        categoryFragmentAdapter.setOnItemClickListener(v ->{
            navigateToDetails(v);
        });


    }

    @Override
    public void navigateToDetails(int id) {

        Navigation.findNavController(getView()).navigate(CountryFragmentDirections.actionCountryFragmentToDetailsFragment(id));

    }
}