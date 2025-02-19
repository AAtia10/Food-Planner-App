package com.example.foodplanningapp.ui.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanningapp.adapters.CategoryAdapter;
import com.example.foodplanningapp.adapters.CountryAdapter;
import com.example.foodplanningapp.adapters.IngridentAdapter;
import com.example.foodplanningapp.models.AreaDTO;
import com.example.foodplanningapp.models.CategoryDTO;
import com.example.foodplanningapp.models.Connection;
import com.example.foodplanningapp.models.IngredientsDTO;
import com.example.foodplanningapp.models.MealDTO;
import com.example.foodplanningapp.models.Sharedprefrence;
import com.example.foodplanningapp.models.StoreMeal;
import com.example.foodplanningapp.network.MealRemoteDatasource;
import com.example.foodplanningapp.R;
import com.example.foodplanningapp.adapters.DailyMealAdapter;
import com.example.foodplanningapp.ui.MainActivity;
import com.example.foodplanningapp.ui.home.presenter.HomePresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements IHomeView {
    RecyclerView recyclerView;
    DailyMealAdapter adapter;
    HomePresenter presenter;
    RecyclerView recyclerViewcat;
    CategoryAdapter categoryAdapter;
    RecyclerView flagrecycle;
    CountryAdapter countryAdapter;
    SearchView searchView;
    Chip catchip;
    Chip countrychip;
    Chip ingredientschip;
    RecyclerView ingredientRecycle;
    IngridentAdapter ingridentAdapter;
    ChipGroup chipGroup;
    ScrollView scrollView;
    LottieAnimationView lottie;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.homerecyle);
        recyclerViewcat = view.findViewById(R.id.categoryrecyle);
        flagrecycle = view.findViewById(R.id.flagrecycle);
        ingredientRecycle = view.findViewById(R.id.ingrhomeecycle);
        scrollView=view.findViewById(R.id.scrollviewhome);
        lottie=view.findViewById(R.id.animationView);

        catchip = view.findViewById(R.id.catchip);
        countrychip = view.findViewById(R.id.countrychip);
        ingredientschip = view.findViewById(R.id.ingchip);
        chipGroup=view.findViewById(R.id.chipgroup);
        searchView = view.findViewById(R.id.searchview);


        presenter = new HomePresenter(this,requireContext());



        // Register network callback to monitor connectivity changes
        Connection.registerNetworkCallback(requireContext(), isConnected -> {
            requireActivity().runOnUiThread(()->{
                if (isConnected) {
                    lottie.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                    presenter.getDailyMeals();
                    presenter.getAllCategories();
                    presenter.getArea();
                    presenter.getIngredients();
                } else {
                    lottie.setVisibility(View.VISIBLE);
                    scrollView.setVisibility(View.GONE);
                }
            });

        });

        if (Connection.isConnected(requireContext())) {
            lottie.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
            presenter.getDailyMeals();
            presenter.getAllCategories();
            presenter.getArea();
            presenter.getIngredients();
        } else {
            lottie.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }




        categoryAdapter = new CategoryAdapter(new ArrayList<>());
        countryAdapter = new CountryAdapter(new ArrayList<>());
        ingridentAdapter = new IngridentAdapter(new ArrayList<>());




        if(Sharedprefrence.getInstance(requireContext()).getUserId()!=null)
        {
            presenter.getPlan(Sharedprefrence.getInstance(requireContext()).getUserId());
            presenter.getFav(Sharedprefrence.getInstance(requireContext()).getUserId());

        }




        MainActivity mainActivity=(MainActivity) getActivity();
        mainActivity.show();
        chipGroup.setVisibility(View.GONE);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                chipGroup.setVisibility(View.VISIBLE);



                if (catchip.isChecked()) {
                    presenter.searchCategory(s);
                } else {
                    presenter.searchCategory("");
                }
                if (countrychip.isChecked()) {

                    presenter.searchCountry(s);
                } else {
                    presenter.searchCountry("");
                }

                if (ingredientschip.isChecked()) {
                    presenter.searchIngredients(s);

                } else {
                    presenter.searchIngredients("");

                }




                return false;
            }
        });


    }

    @Override
    public void showDailyMeals(List<MealDTO> list) {
        adapter = new DailyMealAdapter(list);
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
        categoryAdapter.setList(list);
        recyclerViewcat.setAdapter(categoryAdapter);
        categoryAdapter.setOnItemClickListener(id -> {
            navigateToCategory(id);

        });

    }

    @Override
    public void showAreas(List<AreaDTO> list) {
        countryAdapter.setList(list);
        flagrecycle.setAdapter(countryAdapter);
        countryAdapter.setOnItemClickListener(id->{
            navigateToCountry(id);
        });
    }

    @Override
    public void showIngredients(List<IngredientsDTO> list) {
       ingridentAdapter.setList(list);
        ingredientRecycle.setAdapter(ingridentAdapter);
        ingridentAdapter.setOnItemClickListener(id->{
            navigateToIngredient(id);
        });

    }

    @Override
    public void categorySearch(List<CategoryDTO> list) {
        categoryAdapter.setList(list);
    }

    @Override
    public void countrySearch(List<AreaDTO> list) {
        list.forEach((x)-> Log.i("TAG", "countrySearch: "+x.getStrArea()));
        Log.i("TAG", "countrySearch:--------------------------------------- ");
        countryAdapter.setList(list);

    }

    @Override
    public void IngredientSearch(List<IngredientsDTO> list) {
        ingridentAdapter.setList(list);
    }

    private void navigateToDetails(int id) {
        Navigation.findNavController(getView()).navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(id));
    }

    private void navigateToCategory(String name) {
        Navigation.findNavController(getView()).navigate(HomeFragmentDirections.actionHomeFragmentToCategoryFragment(name));
    }

    private void navigateToCountry(String name) {
        Navigation.findNavController(getView()).navigate(HomeFragmentDirections.actionHomeFragmentToCountryFragment(name));
    }
    private void navigateToIngredient(String name) {
        Navigation.findNavController(getView()).navigate(HomeFragmentDirections.actionHomeFragmentToIngredientFragment(name));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Connection.unregisterNetworkCallback(requireContext());
    }


}