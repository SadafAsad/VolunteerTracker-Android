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
import com.example.databinding.ActivitySignUpBinding;
import com.example.models.User;
import com.example.viewmodels.UserViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getCanonicalName();
    private ActivitySignUpBinding binding;
    private FirebaseAuth mAuth;
    private UserViewModel userViewModel;
    private User newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sign_up);
        this.binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        this.binding.btnRegister.setOnClickListener(this::onClick);

        this.mAuth = FirebaseAuth.getInstance();
        this.newUser = new User();
        this.userViewModel = UserViewModel.getInstance(this.getApplication());
    }

    public void onClick(View view) {
        if (view != null){
            switch (view.getId()){
                case R.id.btnRegister:{
                    Log.d(TAG, "onClick: Create Account button Clicked");
                    this.validateData();
                    break;
                }
            }
        }
    }

    private void validateData(){
        Boolean validData = true;
        String name = "";
        String email = "";
        String password = "";

        if (this.binding.etName.getText().toString().isEmpty()){
            this.binding.etName.setError("Name Cannot be Empty");
            validData = false;
        }else{
            name = this.binding.etName.getText().toString();
        }

        if (this.binding.etEmailSignUp.getText().toString().isEmpty()){
            this.binding.etEmailSignUp.setError("Email Cannot be Empty");
            validData = false;
        }else{
            email = this.binding.etEmailSignUp.getText().toString();
        }

        if (this.binding.etPasswordSignUp.getText().toString().isEmpty()){
            this.binding.etPasswordSignUp.setError("Password Cannot be Empty");
            validData = false;
        }else {

            if (this.binding.etReconfirmPassword.getText().toString().isEmpty()) {
                this.binding.etReconfirmPassword.setError("Confirm Password Cannot be Empty");
                validData = false;
            } else {

                if (!this.binding.etPasswordSignUp.getText().toString().equals(this.binding.etReconfirmPassword.getText().toString())) {
                    this.binding.etReconfirmPassword.setError("Both passwords must be same");
                    validData = false;
                }else{
                    password = this.binding.etPasswordSignUp.getText().toString();
                }
            }
        }

        if (validData){
            this.createAccount(name, email, password);
        }else{
            Toast.makeText(this, "Please provide correct inputs", Toast.LENGTH_SHORT).show();
        }
    }

    private void createAccount(String name, String email, String password){
        this.mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if ( task.isSuccessful()){
                    //account created successfully
                    Log.e(TAG, "onComplete: Account created successfully");
                    saveToPrefs(email, password);
                    newUser.setName(name);
                    newUser.setEmail(email);
                    newUser.setPassword(password);
                    userViewModel.addUser(newUser);
                    goToSignIn();
                    Toast.makeText(SignUpActivity.this, "Account created successfully", Toast.LENGTH_LONG).show();

                }else{
                    Log.e(TAG, "onComplete: Failed to create user account " + task.getException() + task.getException().getLocalizedMessage());
                    Toast.makeText(SignUpActivity.this, "Cannot create account.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void saveToPrefs(String email, String password){
        SharedPreferences prefs = getApplicationContext().getSharedPreferences(getPackageName(), MODE_PRIVATE);
        prefs.edit().putString("USER_EMAIL", email).apply();
        prefs.edit().putString("USER_PASSWORD", password).apply();
    }

    private void goToSignIn(){
        Intent signInIntent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(signInIntent);
    }
}