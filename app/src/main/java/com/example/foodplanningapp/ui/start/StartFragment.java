package com.example.foodplanningapp.ui.start;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanningapp.R;
import com.example.foodplanningapp.models.Sharedprefrence;
import com.example.foodplanningapp.ui.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class StartFragment extends Fragment {

    private AppCompatButton mailbtn, googlebtn, guestbtn;
    private FirebaseAuth auth;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private Sharedprefrence caching;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Hide action bar if needed
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            mainActivity.hide();
        }

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Initialize caching properly (moved inside onViewCreated)
        caching = Sharedprefrence.getInstance(requireContext());

        // Configure Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso);

        // Initialize buttons
        mailbtn = view.findViewById(R.id.mailbtn);
        googlebtn = view.findViewById(R.id.gbtn);
        guestbtn = view.findViewById(R.id.guestbtn);

        // Set button listeners
        mailbtn.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_startFragment_to_signupFragment));
        googlebtn.setOnClickListener(v -> signInWithGoogle());
        guestbtn.setOnClickListener(view1 -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_startFragment_to_homeFragment);

        });
    }

    private void signInWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            Log.w("Google Sign-In", "signInResult:failed code=" + e.getStatusCode());
            Snackbar.make(requireView(), "Google sign-in failed", Snackbar.LENGTH_SHORT).show();
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("Google Sign-In", "firebaseAuthWithGoogle:" + acct.getId());

        auth.signInWithCredential(GoogleAuthProvider.getCredential(acct.getIdToken(), null))
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null) {
                            caching.storeUser(user.getDisplayName(), user.getEmail(), user.getUid());
                            Log.d("Google Sign-In", "User: " + user.getDisplayName());
                            Navigation.findNavController(requireView()).navigate(R.id.action_startFragment_to_homeFragment);
                        }
                    } else {
                        Log.w("Google Sign-In", "signInWithCredential:failure", task.getException());
                        Snackbar.make(requireView(), "Authentication Failed", Snackbar.LENGTH_SHORT).show();
                    }
                });
    }
}
