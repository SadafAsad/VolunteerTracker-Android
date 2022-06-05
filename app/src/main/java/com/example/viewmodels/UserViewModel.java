package com.example.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.models.User;
import com.example.repositories.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private final UserRepository repository = new UserRepository();
    private static UserViewModel instance;

    public UserViewModel(@NonNull Application application) {
        super(application);
    }

    public static UserViewModel getInstance(Application application) {
        if (instance == null) {
            instance = new UserViewModel(application);
        }
        return instance;
    }

    public UserRepository getUserRepository() {
        return this.repository;
    }

    public void addUser(User user) {
        this.repository.addUser(user);
    }
}
