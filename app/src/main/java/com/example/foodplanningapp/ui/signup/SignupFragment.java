package com.example.foodplanningapp.ui.signup;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanningapp.R;
import com.example.foodplanningapp.models.Sharedprefrence;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class SignupFragment extends Fragment {
    private TextInputEditText editTextUsername;
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextpass;
    private TextInputEditText editTextConfirmpass;
    private Button signupbtn;
    private TextView login;
    Sharedprefrence cashing;

    private FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextUsername=view.findViewById(R.id.usernametxtfield);
        editTextEmail=view.findViewById(R.id.emaitxtfield);
        editTextpass=view.findViewById(R.id.passtxtfield);
        editTextConfirmpass=view.findViewById(R.id.confirmpasstxtfield);
        signupbtn=view.findViewById(R.id.signupbtn);
        login=view.findViewById(R.id.logintxt);
        signupbtn.setOnClickListener(v -> registerUser(view));
        login.setOnClickListener(v ->
                Navigation.findNavController(view).navigate(R.id.action_signupFragment_to_loginFragment2)
        );
        cashing=Sharedprefrence.getInstance(requireContext());
    }


    private void registerUser(View view) {
        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextpass.getText().toString().trim();
        String confirmPassword = editTextConfirmpass.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Username is required");
            return;
        }
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Valid email is required");
            return;
        }
        if (TextUtils.isEmpty(password) || password.length() < 8) {
            editTextpass.setError("Password must be at least 8 characters");
            return;
        }
        if (!password.equals(confirmPassword)) {
            editTextConfirmpass.setError("Passwords do not match");
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        cashing.storeUser(firebaseAuth.getCurrentUser().getDisplayName(),firebaseAuth.getCurrentUser().getEmail(),firebaseAuth.getCurrentUser().getUid());
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(username)
                                    .build();
                            user.updateProfile(profileUpdates);
                        }
                        Snackbar.make(view, "Registration Successful", Snackbar.LENGTH_LONG).show();
                        Navigation.findNavController(requireView()).navigate(R.id.action_signupFragment_to_homeFragment);
                    } else {
                        Snackbar.make(view, "Registration Failed: " + task.getException().getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                });
    }
}