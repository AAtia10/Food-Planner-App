package com.example.foodplanningapp.ui.category.view;

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
import com.example.foodplanningapp.ui.category.presenter.CategoryPresenter;

import java.util.List;


public class CategoryFragment extends Fragment implements ICategoryView{

    String name;
    CategoryFragmentAdapter categoryFragmentAdapter;
    RecyclerView recyclerView;
    TextView title;
    CategoryPresenter categoryPresenter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        name=CategoryFragmentArgs.fromBundle(getArguments()).getNameSplit();
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.catgrecylepage);
        title=view.findViewById(R.id.txtmeal);
        categoryPresenter=new CategoryPresenter(this);
        categoryPresenter.showCategory(name);
        title.setText(name);
    }

    @Override
    public void showMealsCategory(List<MealDTO> list) {
        categoryFragmentAdapter=new CategoryFragmentAdapter(list);
        recyclerView.setAdapter(categoryFragmentAdapter);
        categoryFragmentAdapter.setOnItemClickListener(v ->{
            navigateToDetails(v);
        });

    }

    @Override
    public void navigateToDetails(int id) {
        Navigation.findNavController(getView()).navigate(CategoryFragmentDirections.actionCategoryFragmentToDetailsFragment(id));

    }
}