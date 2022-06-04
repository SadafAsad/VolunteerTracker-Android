package com.example.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.models.Volunteer;
import com.example.repositories.VolunteerRespository;

public class VolunteerViewModel extends AndroidViewModel {
    private final VolunteerRespository respository = new VolunteerRespository();

    public VolunteerViewModel(@NonNull Application application) {
        super(application);
    }

    public void addVolunteer(Volunteer volunteer) {
        this.respository.addVolunteer(volunteer);
    }
}
