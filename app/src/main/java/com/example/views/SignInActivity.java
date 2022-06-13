package com.example.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.R;
import com.example.databinding.ActivitySignInBinding;
import com.example.repositories.VolunteerRespository;
import com.example.viewmodels.VolunteerViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getCanonicalName();
    private ActivitySignInBinding binding;
    private SharedPreferences prefs;
    private FirebaseAuth mAuth;
    private VolunteerViewModel volunteerViewModel;
    private VolunteerRespository volunteerRespository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sign_in);

        this.binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        this.volunteerViewModel = VolunteerViewModel.getInstance(this.getApplication());
        this.volunteerRespository = this.volunteerViewModel.getVolunteerRepository();

        prefs = getApplicationContext().getSharedPreferences(getPackageName(), MODE_PRIVATE);

        if (prefs.contains("USER_EMAIL")) {

            String user = this.prefs.getString("USER_EMAIL", "");
            volunteerRespository.loggedInUserEmail = user;
            Log.e(TAG, "onCreate: user: " + this.prefs.getString("USER_EMAIL", "") );
            Toast.makeText(SignInActivity.this, "Already Logged In",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(SignInActivity.this, NewsfeedActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }

        if (prefs.contains("USER_EMAIL")){
            this.binding.etEmail.setText(this.prefs.getString("USER_EMAIL", ""));

        }
        if (prefs.contains("USER_PASSWORD")){
            this.binding.etPassword.setText(this.prefs.getString("USER_PASSWORD", ""));
        }

        this.binding.btnSignIn.setOnClickListener(this::onClick);
        this.binding.btnSignUp.setOnClickListener(this::onClick);
        this.mAuth = FirebaseAuth.getInstance();

    }

    public void onClick(View view) {
        if (view != null){
            switch (view.getId()){
                case R.id.btnSignIn:{
                    Log.d(TAG, "onClick: Sign In Button Clicked");
                    this.validateData();
                    break;
                }
                case R.id.btnSignUp:{
                    Log.d(TAG, "onClick: Sign Up Button Clicked");
                    Intent signUpIntent = new Intent(this, SignUpActivity.class);
                    startActivity(signUpIntent);
                    break;
                }
            }
        }
    }

    private void validateData(){
        Boolean validData = true;
        String email = "";
        String password = "";

        if (this.binding.etEmail.getText().toString().isEmpty()){
            this.binding.etEmail.setError("Email Cannot be Empty");
            validData = false;
        }else{
            email = this.binding.etEmail.getText().toString();
        }

        if (this.binding.etPassword.getText().toString().isEmpty()){
            this.binding.etPassword.setError("Password Cannot be Empty");
            validData = false;
        }else {
            password = this.binding.etPassword.getText().toString();
        }

        if (validData){
            this.signIn(email, password);
        }else{
            Toast.makeText(this, "Please provide correct inputs", Toast.LENGTH_SHORT).show();
        }
    }

    private void signIn(String email, String password){
        this.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.e(TAG, "onComplete: Sign In Successful");
                    saveToPrefs(email, password);
                    volunteerRespository.loggedInUserEmail = email;
                    goToMain();
                }else{
                    Log.e(TAG, "onComplete: Sign In Failed" + task.getException().getLocalizedMessage() );
                    Toast.makeText(SignInActivity.this, "Authentication Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void saveToPrefs(String email, String password){

        if (this.binding.switchRememberMe.isChecked()) {
            prefs.edit().putString("USER_EMAIL", email).apply();
            prefs.edit().putString("USER_PASSWORD", password).apply();
        }else{
            if (prefs.contains("USER_EMAIL")){
                prefs.edit().remove("USER_EMAIL").apply();
            }
            if (prefs.contains("USER_PASSWORD")){
                prefs.edit().remove("USER_PASSWORD").apply();
            }
        }
    }

    private void goToMain(){
        this.finishAffinity();
        Intent historyIntent = new Intent(this, NewsfeedActivity.class);
        startActivity(historyIntent);
    }
}