package com.example.foodplanningapp.ui.home.view;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;

import com.bumptech.glide.Glide;
import com.example.foodplanningapp.R;
import com.example.foodplanningapp.adapters.MeasureAdapter;
import com.example.foodplanningapp.databinding.FragmentDetailsBinding;
import com.example.foodplanningapp.models.FlagHelper;
import com.example.foodplanningapp.models.MealDTO;
import com.example.foodplanningapp.ui.home.presenter.DetailPresenter;


public class DetailsFragment extends Fragment implements IDetailView {
    DetailPresenter detailPresenter;
    FragmentDetailsBinding binding;
    MeasureAdapter measureAdapter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding=FragmentDetailsBinding.bind(view);
        int id=DetailsFragmentArgs.fromBundle(getArguments()).getMealId();
        detailPresenter=new DetailPresenter(this);
        detailPresenter.getDetails(id);

    }

    @Override
    public void showData(MealDTO mealDTO) {
       binding.mealtittle.setText(mealDTO.getStrMeal());
       binding.cattittle.setText(mealDTO.getStrCategory());
       binding.countryname.setText(mealDTO.getStrArea());
       binding.steps.setText(mealDTO.getStrInstructions());
       measureAdapter=new MeasureAdapter(mealDTO.getMeasureList());
       binding.ingrrecycle.setAdapter(measureAdapter);

        Glide.with(getContext()).load(mealDTO.getStrMealThumb()).into(binding.mealimg);
        getVideo(mealDTO.getStrYoutube());

        String url= FlagHelper.getUrl(mealDTO.getStrArea());
        Glide.with(getContext()).load(url).into(binding.flagitem);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }

    private void getVideo(String youtubeUrl) {
        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.getSettings().setMediaPlaybackRequiresUserGesture(false);
        binding.webview.setWebChromeClient(new WebChromeClient());

        Uri uri = Uri.parse(youtubeUrl);
        String videoId = uri.getQueryParameter("v");

        if (videoId != null) {
            String iframe = "<iframe width=\"100%\" height=\"100%\" " +
                    "src=\"https://www.youtube.com/embed/" + videoId + "?autoplay=0&mute=0\" " +
            "frameborder=\"0\" allowfullscreen></iframe>";

            binding.webview.loadData(iframe, "text/html", "utf-8");
        }
    }
}