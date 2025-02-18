package com.example.foodplanningapp.ui.splash;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanningapp.R;
import com.example.foodplanningapp.models.Sharedprefrence;
import com.example.foodplanningapp.ui.MainActivity;


public class SplashFragment extends Fragment {
    LottieAnimationView lottie;


    public SplashFragment() {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lottie=view.findViewById(R.id.animationView);
        MainActivity mainActivity=(MainActivity) getActivity();
        mainActivity.hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Sharedprefrence.getInstance(requireContext()).isUserLoggedIn())
                {
                    Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_homeFragment);
                }
                else
                {
                    Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_startFragment);
                }

            }
        },3000);
    }
}