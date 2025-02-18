package com.example.foodplanningapp.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodplanningapp.R;
import com.example.foodplanningapp.models.Sharedprefrence;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileFragment extends Fragment {
    Button logout;
    FirebaseAuth auth;
    GoogleSignInClient googleSignInClient;
    TextView txtemail;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient= GoogleSignIn.getClient(requireContext(),gso);
        txtemail=view.findViewById(R.id.emailtxt);

        logout=view.findViewById(R.id.logoutbtn);
        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        String email= "guest";
        if(user!=null)
        {
            email=user.getEmail();
        }

        txtemail.setText(email);
        logout.setOnClickListener(v -> {
            logoutUser(view);
        });

    }
    private void logoutUser(View view) {
        auth.signOut();
        googleSignInClient.signOut();
        Sharedprefrence.getInstance(requireContext()).clearCache();
        Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_startFragment);

    }
}