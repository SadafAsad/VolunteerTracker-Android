package com.example.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.models.Volunteer;
import com.example.repositories.VolunteerRespository;

public class VolunteerViewModel extends AndroidViewModel {
    private final VolunteerRespository respository = new VolunteerRespository();
    private static VolunteerViewModel instance;

    public VolunteerViewModel(@NonNull Application application) {
        super(application);
    }

    public static VolunteerViewModel getInstance(Application application) {
        if (instance == null) {
            instance = new VolunteerViewModel(application);
        }
        return instance;
    }

    public VolunteerRespository getVolunteerRepository() {
        return this.respository;
    }

    public void addVolunteer(Volunteer volunteer) {
        this.respository.addVolunteer(volunteer);
    }
}
