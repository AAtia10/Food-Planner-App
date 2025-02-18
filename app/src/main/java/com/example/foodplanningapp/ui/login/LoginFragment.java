package com.example.foodplanningapp.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodplanningapp.R;
import com.example.foodplanningapp.models.Sharedprefrence;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {

    private TextInputEditText editTextEmail;
    private TextInputEditText editTextPass;
    private Button loginBtn;
    private FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
    Sharedprefrence cashing;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextEmail = view.findViewById(R.id.emaitxtfield);
        editTextPass = view.findViewById(R.id.passtxtfield);
        loginBtn = view.findViewById(R.id.loginbtn);
        loginBtn.setOnClickListener(v -> loginUser(view));
        cashing=Sharedprefrence.getInstance(requireContext());

    }


    private void loginUser(View view) {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPass.getText().toString().trim();

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Valid email is required");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            editTextPass.setError("Password is required");
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        cashing.storeUser(firebaseAuth.getCurrentUser().getDisplayName(),firebaseAuth.getCurrentUser().getEmail(),firebaseAuth.getCurrentUser().getUid());
                        Snackbar.make(view, "Login Successful", Snackbar.LENGTH_LONG).show();

                        Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment2_to_homeFragment);
                    } else {
                        Snackbar.make(view, "Login Failed: " + task.getException().getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                });
    }





}